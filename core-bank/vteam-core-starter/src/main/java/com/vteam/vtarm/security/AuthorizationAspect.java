package com.vteam.vtarm.security;

import com.vteam.vtarm.status.GlobalStatus;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.util.Set;

/**
 * 授权过滤切面 .<br>
 * 判断请求是否需要授权
 *
 * @author andy.sher
 * @date 2019/4/18 13:26
 */
@Aspect
@Order(4)
public class AuthorizationAspect {

    /**
     * 拦截请求，需要授权后才能访问 .
     *
     * @param joinPoint 切入点
     * @return void
     * @author andy.sher
     * @date 2019/4/22 13:31
     */
    @Before("@annotation(com.vteam.vtarm.security.RequiredAuthorize)")
    public void requiredAuthorized(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        RequiredAuthorize requiredAuthorize = methodSignature.getMethod().getDeclaredAnnotation(RequiredAuthorize.class);

        // 获取api的授权策略
        String[] permissions = requiredAuthorize.permissions();
        String[] roles = requiredAuthorize.roles();

        // 是否校验通过
        boolean passed = false;

        if (Boolean.FALSE == GlobalStatus.getAuthSuccess()) {
            throw new UnauthenticatedException();
        }

        if (null == GlobalStatus.getAuthenticationInfo() || CollectionUtils.isEmpty(GlobalStatus.getAuthenticationInfo().getPermissions())) {
            throw new UnauthorizedException();
        }

        // 当权限不为空的时候进行权限校验
        if (ArrayUtils.isNotEmpty(permissions)) {
            boolean isPermissionAnd = requiredAuthorize.andPermission();
            // 获取权限校验是否通过
            passed = validation(GlobalStatus.getAuthenticationInfo().getPermissions(), permissions, isPermissionAnd);
        }

        // 当权限校验通过的时候才会进行角色校验
        if (passed) {
            // 当角色不为空的时候进行角色校验
            if (ArrayUtils.isNotEmpty(roles)) {
                boolean isRoleAnd = requiredAuthorize.andRole();
                // 获取角色校验是否通过
                passed = validation(GlobalStatus.getAuthenticationInfo().getRoles(), roles, isRoleAnd);
            }
        }

        if (!passed) {
            // 授权校验不通过，抛出异常
            throw new UnauthorizedException();
        }
    }

    /**
     * 权限和角色校验 .
     *
     * @param resources  用户持有的资源[用户已有的权限或角色]
     * @param conditions 用户访问的资源所需要的条件[权限或角色]
     * @param and        是否为and
     * @return boolean
     * @author andy.sher
     * @date 2019/4/22 14:12
     */
    private boolean validation(Set<String> resources, String[] conditions, boolean and) {
        boolean passed = false;
        // 使用and逻辑处理权限校验
        if (and) {
            // 默认为通过校验
            passed = true;
            for (int i = 0; i < conditions.length; i++) {
                if (resources.contains(conditions[i])) {
                    continue;
                } else {
                    // 如果有一项不通过则表示不通过，并终止校验
                    passed = false;
                    break;
                }
            }
        } else {
            for (int i = 0, len = conditions.length; i < len; i++) {
                // 如果有一项通过则表示通过
                if (resources.contains(conditions[i])) {
                    passed = true;
                    break;
                }
            }
        }
        return passed;
    }

}
