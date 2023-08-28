package com.vteam.cars.service.cspa;

import com.vteam.cars.entity.model.CspaFunM;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.cars.entity.vo.CspaFunMVo;

import java.util.List;

/**
 * <p>
 * 系统资源表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface CspaFunMService extends IService<CspaFunM> {

    /**
     * 查询功能权限列表树状结构
     *
     * @return com.vteam.sme.entity.vo.CspaFunMVo
     * @author fu.tong
     * @date 2018/7/31 0031 15:18
     */
    List<CspaFunMVo> listCspaFunMvo(List<Integer> funRefcodeList, String systemType, String subUserType);

    /**
     * 根据多个流水号查找出所有权限
     *
     * @param funRefcodeList 权限集合
     * @param funSystemType 资源类型
     * @data 2022/10/25 15:57
     * @author li.jin
     * @return List<CspaFunM>
     */
    List<CspaFunM> listFunByFunRefcodeList(List<Integer> funRefcodeList, String funSystemType);

    /**
     * 根据所属系统类型查询关联权限信息 .
     *
     * @param systemType
     * @param funCodeList
     * @param brandStyle
     * @return java.util.List<java.lang.Integer>
     * @author zack.yin
     * @date 2019/3/28 13:51
     */
    List<Integer> listFunCodesBySystemType(String systemType, List<Integer> funCodeList, String brandStyle);

    /**
     * 查找菜单 树状结构 .
     *
     * @param funRefcodeList
     * @param systemType
     * @param fbpaOrgM 用户端企业信息
     * @param brandStyle
     * @param funModule
     * @return java.util.List<com.vteam.sme.entity.vo.CspaFunMVo>
     * @author fu.tong
     * @date 2019/3/7 11:14
     */
    List<CspaFunMVo> listFunMenuByFunRefcodeList(List<Integer> funRefcodeList, String systemType, String brandStyle, String funModule);
}
