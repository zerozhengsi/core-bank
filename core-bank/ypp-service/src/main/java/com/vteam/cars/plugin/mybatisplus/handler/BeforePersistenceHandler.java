package com.vteam.cars.plugin.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.service.common.CommonService;
import com.vteam.vtarm.codec.CodecProperties;
import com.vteam.vtarm.codec.DataCodecExecutor;
import com.vteam.vtarm.lucene.LuceneDispatcher;
import com.vteam.vtarm.status.GlobalStatus;
import com.vteam.vtarm.utils.SpringContextUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 数据持久化前置处理器 .<br>
 *
 * @author andy.sher
 * @date 2018/7/25 15:50
 */
@Service
public class BeforePersistenceHandler implements MetaObjectHandler {

    @Resource
    private DataCodecExecutor dataCodecExecutor;

    @Resource
    private SmeConfiguration smeConfiguration;

    @Resource
    private CodecProperties codecProperties;

    @Resource(type = LuceneDispatcher.class)
    private LuceneDispatcher luceneDispatcher;

    /**
     * 是否已同步模式建立lucene索引
     */
    private boolean synchGenerateIndex = true;

    /**
     * 是否跳过填充逻辑
     */
    private static ThreadLocal<Boolean> skipFillLocal = new ThreadLocal<>();

    /**
     * 是否跳过修改日期逻辑
     */
    private static ThreadLocal<Boolean> skipLastModDateLocal = new ThreadLocal<>();

    /**
     * 跳过填充逻辑
     *
     * @author oscar.yu
     * @date 2019/8/8 15:18
     */
    public static void skipFill() {
        skipFillLocal.set(true);
    }

    /**
     * 跳过修改日期逻辑
     *
     * @author oscar.yu
     * @date 2019/8/8 15:18
     */
    public static void skipLastModDate() {
        skipLastModDateLocal.set(true);
    }

    /**
     * 重置填充逻辑
     *
     * @author oscar.yu
     * @date 2019/8/8 15:18
     */
    public static void resetFill() {
        skipFillLocal.set(null);
    }

    /**
     * 重置修改日期逻辑
     *
     * @author oscar.yu
     * @date 2019/8/8 15:18
     */
    public static void resetLastModDate() {
        skipLastModDateLocal.set(null);
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        if (null != skipFillLocal.get() && skipFillLocal.get()) {
            return;
        }

        setRefcode(metaObject);
        setResourceUuid(metaObject);
        setCreateUser(metaObject);
        setCreateDate(metaObject);
        setLastModUser(metaObject);
        setLastModDate(metaObject);
        setCreateEdtid(metaObject);
        setDataStatus(metaObject);

        // 记录索引
        this.updateLucene(metaObject);

        // 如果开启了数据安全策略，则根据策略加密字段值
        if (codecProperties.isDbEnable()) {
            dataCodecExecutor.encrypt(metaObject.getOriginalObject());
        }
    }

    /**
     * 更新Lucene
     * 
     * @param metaObject
     * @author oscar.yu
     * @date 2020/9/4 15:14
     */
    public void updateLucene(MetaObject metaObject) {
        if (synchGenerateIndex) {
            luceneDispatcher.add(new Object[]{metaObject.getOriginalObject()}, metaObject.getOriginalObject().getClass());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (null != skipFillLocal.get() && skipFillLocal.get()) {
            return;
        }
        setLastModUser(metaObject);
        setLastModDate(metaObject);
        setModEdtid(metaObject);

        if (metaObject.hasGetter(Constants.ENTITY)) {
            Object et = metaObject.getValue(Constants.ENTITY);
            MetaObject etMeta = null;
            if (et != null) {
                etMeta = SystemMetaObject.forObject(et);
            }
            if (null != etMeta) {
                if (etMeta.getOriginalObject() instanceof Serializable) {
                    this.updateLucene(etMeta);
                }
            }
        } else {
            if (metaObject.getOriginalObject() instanceof Serializable) {
                this.updateLucene(metaObject);
            }
        }

        // 如果开启了数据安全策略，则根据策略加密字段值
        if (codecProperties.isDbEnable()) {
            if (metaObject.hasGetter(Constants.ENTITY)) {
                Object et = metaObject.getValue(Constants.ENTITY);
                MetaObject etMeta = null;
                if (et != null) {
                    etMeta = SystemMetaObject.forObject(et);
                }
                if (null != etMeta) {
                    dataCodecExecutor.encrypt(etMeta.getOriginalObject());
                }
            } else {
                dataCodecExecutor.encrypt(metaObject.getOriginalObject());
            }

        }
    }

    /**
     * 设置流水号
     *
     * @param metaObject 数据对象
     * @return void
     * @author andy.sher
     * @date 2018/8/13 14:49
     */
    private void setRefcode(MetaObject metaObject) {
        if (metaObject.hasGetter(FieldConstant.PublicField.REFCODE) && null == metaObject.getValue(FieldConstant.PublicField.REFCODE)) {
            CommonService commonService = SpringContextUtils.getBean(CommonService.class);
            Class<?> objClass = metaObject.getOriginalObject().getClass().getSuperclass();
            // 修复传入对象无父级对象或父级对象为Object的问题
            if (objClass == Object.class) {
                objClass = metaObject.getOriginalObject().getClass();
            }
            setFieldValByName(FieldConstant.PublicField.REFCODE, commonService.getMaxRefcodeByBeanClass(objClass), metaObject);
        }
    }

    /**
     * 设置资源唯一UUID
     *
     * @param metaObject
     * @return void
     * @author jiangming.huang
     * @date 2019/5/14 15:20
     */
    private void setResourceUuid(MetaObject metaObject) {
        if (metaObject.hasGetter(FieldConstant.PublicField.RESOURCE_UUID) && null == metaObject.getValue(FieldConstant.PublicField.RESOURCE_UUID)) {
            setFieldValByName(FieldConstant.PublicField.RESOURCE_UUID, UUID.randomUUID().toString(), metaObject);
        }
    }

    /**
     * 设置创建人
     *
     * @param metaObject 数据对象
     * @return void
     * @author andy.sher
     * @date 2018/8/13 14:49
     */
    private void setCreateUser(MetaObject metaObject) {
        if (metaObject.hasGetter(FieldConstant.PublicField.CREATEUSER) && null == metaObject.getValue(FieldConstant.PublicField.CREATEUSER)) {
            String createUser;
            if (null == GlobalStatus.getAuthenticationInfo() || GlobalStatus.isAnonymous()) {
                createUser = smeConfiguration.getApplicationName();
            } else {
                createUser = RequestStore.getLoginUser().getUserid();
            }
            setFieldValByName(FieldConstant.PublicField.CREATEUSER, createUser, metaObject);
        }
    }

    /**
     * 设置修改人
     *
     * @param metaObject 数据对象
     * @return void
     * @author andy.sher
     * @date 2018/8/13 14:49
     */
    private void setLastModUser(MetaObject metaObject) {
        if (metaObject.hasGetter(FieldConstant.PublicField.LASTMODUSER) && null == metaObject.getValue(FieldConstant.PublicField.LASTMODUSER)) {

            String lastModUser;
            if (null == GlobalStatus.getAuthenticationInfo() || GlobalStatus.isAnonymous()) {
                lastModUser = smeConfiguration.getApplicationName();
            } else {
                lastModUser = RequestStore.getLoginUser().getUserid();
            }
            setFieldValByName(FieldConstant.PublicField.LASTMODUSER, lastModUser, metaObject);
        }
    }

    /**
     * 设置创建日期
     *
     * @param metaObject 数据对象
     * @return void
     * @author andy.sher
     * @date 2018/8/13 14:49
     */
    private void setCreateDate(MetaObject metaObject) {
        if (metaObject.hasGetter(FieldConstant.PublicField.CREATEDATE) && null == metaObject.getValue(FieldConstant.PublicField.CREATEDATE)) {
            setFieldValByName(FieldConstant.PublicField.CREATEDATE, LocalDateTime.now(), metaObject);
        }
    }

    /**
     * 设置修改日期
     *
     * @param metaObject 数据对象
     * @return void
     * @author andy.sher
     * @date 2018/8/13 14:49
     */
    private void setLastModDate(MetaObject metaObject) {
        if (metaObject.hasGetter(FieldConstant.PublicField.LASTMODDATE) && null == metaObject.getValue(FieldConstant.PublicField.LASTMODDATE)) {

            // 增加跳过修改日期的支持
            if (null != skipLastModDateLocal.get() && skipLastModDateLocal.get()) {
                return;
            }
            setFieldValByName(FieldConstant.PublicField.LASTMODDATE, LocalDateTime.now(), metaObject);
        }
    }

    /**
     * 设置数据状态
     *
     * @param metaObject 数据对象
     * @return void
     * @author andy.sher
     * @date 2018/8/13 14:49
     */
    private void setDataStatus(MetaObject metaObject) {
        if (metaObject.hasGetter(FieldConstant.PublicField.DATA_STATUS) && null == metaObject.getValue(FieldConstant.PublicField.DATA_STATUS)) {
            setFieldValByName(FieldConstant.PublicField.DATA_STATUS, FieldConstant.PublicFieldValue.DATA_STATUS_EFFECTIVE, metaObject);
        }
    }

    /**
     * 设置创建时的操作类型
     *
     * @param metaObject 数据对象
     * @return void
     * @author andy.sher
     * @date 2018/8/13 14:49
     */
    private void setCreateEdtid(MetaObject metaObject) {
        if (metaObject.hasGetter(FieldConstant.PublicField.EDTID) && null == metaObject.getValue(FieldConstant.PublicField.EDTID)) {
            setFieldValByName(FieldConstant.PublicField.EDTID, FieldConstant.PublicFieldValue.EDTID_NEW, metaObject);
        }
    }

    /**
     * 设置修改时的操作类型
     *
     * @param metaObject 数据对象
     * @return void
     * @author andy.sher
     * @date 2018/8/13 14:49
     */
    private void setModEdtid(MetaObject metaObject) {
        setFieldValByName(FieldConstant.PublicField.EDTID, FieldConstant.PublicFieldValue.EDTID_MOD, metaObject);
    }

}
