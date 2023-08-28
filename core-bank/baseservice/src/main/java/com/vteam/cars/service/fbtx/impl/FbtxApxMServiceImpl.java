package com.vteam.cars.service.fbtx.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.mapper.FbtxApxMMapper;
import com.vteam.cars.entity.model.FbtxApxM;
import com.vteam.cars.entity.vo.FbtxApxMVo;
import com.vteam.cars.plugin.file.upload.FileManager;
import com.vteam.cars.service.fbtx.FbtxApxMService;
import com.vteam.cars.util.BeanUtils;
import com.vteam.cars.util.SmeAssert;
import com.vteam.cars.util.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 附件存储表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
@Service
public class FbtxApxMServiceImpl extends ServiceImpl<FbtxApxMMapper, FbtxApxM> implements FbtxApxMService {



    @Resource(type = FbtxApxMMapper.class)
    private FbtxApxMMapper fbtxApxMMapper;

//    @Resource(type = FbpaOrgMService.class)
//    private FbpaOrgMService fbpaOrgMService;

    @Resource(type = FileManager.class)
    private FileManager fileManager;

    @Override
    public List<FbtxApxMVo> listContractByUUID(String uuid) {
        // 转换为VO对象列表
        List<FbtxApxMVo> fbtxApxMVoList = new ArrayList<>();
        String[] uuidArray = uuid.split(GlobalConstants.Symbol.COMMA);
        if (uuidArray != null && uuidArray.length > 0) {
            for (String oneUuid : uuidArray) {

                LambdaQueryWrapper<FbtxApxM> queryWrapper = new QueryWrapper<FbtxApxM>().lambda();
                queryWrapper.eq(FbtxApxM::getFileUuid, oneUuid);
                queryWrapper.eq(FbtxApxM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
                // 按UUID查询出文件列表
                List<FbtxApxM> fbtxApxMList = list(queryWrapper);
                if (CollectionUtils.isNotEmpty(fbtxApxMList)) {
                    fbtxApxMVoList.addAll(BeanUtils.copyList(fbtxApxMList, FbtxApxMVo.class));
                }
            }
        }

        return fbtxApxMVoList;
    }

    @Override
    public void removeFileByRefcode(Integer refcode) {
        FbtxApxM fbtxApxM = super.getById(refcode);
        SmeAssert.notNull(fbtxApxM, "附件不存在。");
        fbtxApxM.setDelFlag(FieldConstant.PublicFieldValue.DEL_FLAG_YES);
        super.updateById(fbtxApxM);
    }

    /**
     * 通过资源唯一UUID删除附件
     */
    @Override
    public void removeFileByResourceUuid(String resourceUuid) {
        FbtxApxM fbtxApxM = this.getByResourceUuid(resourceUuid);
        SmeAssert.notNull(fbtxApxM, "附件不存在。");
        fbtxApxM.setDelFlag(FieldConstant.PublicFieldValue.DEL_FLAG_YES);
        super.updateById(fbtxApxM);
    }

    /**
     * 通过附件流水号数组批量删除附件
     */
    @Override
    public void removeFileByRefcodes(Integer[] refcodes) {
        List<FbtxApxM> fbtxApxMList = new ArrayList<>();
        for (Integer refcode : refcodes) {
            if (null != refcode) {
                FbtxApxM fbtxApxM = new FbtxApxM();
                fbtxApxM.setRefcode(refcode);
                fbtxApxM.setDelFlag(FieldConstant.PublicFieldValue.DEL_FLAG_YES);
                fbtxApxMList.add(fbtxApxM);
            }
        }
        if (CollectionUtils.isNotEmpty(fbtxApxMList)) {
            super.updateBatchById(fbtxApxMList);
        }
    }

    /**
     * 通过资源唯一UUID数组删除附件
     */
    @Override
    public void removeFileByResourceUuids(String[] resourceUuids) {
        for (String resourceUuid : resourceUuids) {
            if (StringUtils.isNotBlank(resourceUuid)) {
                this.removeFileByResourceUuid(resourceUuid);
            }
        }
    }

    @Override
    public List<FbtxApxMVo> listFbpaApxMByCondition(FbtxApxMVo condition) {
        // 转换为VO对象列表
        List<FbtxApxMVo> fbtxApxMVoList = new ArrayList<>();
        LambdaQueryWrapper<FbtxApxM> queryWrapper = new QueryWrapper<FbtxApxM>().lambda();
        queryWrapper.eq(FbtxApxM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        if (condition.getOrgApxRefcodes() != null && condition.getOrgApxRefcodes().length > 0) {
            queryWrapper.in(FbtxApxM::getRefcode, Arrays.asList(condition.getOrgApxRefcodes()).toArray());
        }
        if (StringUtils.isNotEmpty(condition.getFileUuid())) {
            queryWrapper.eq(FbtxApxM::getFileUuid, condition.getFileUuid());
        }

        List<FbtxApxM> fbtxApxMList = super.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(fbtxApxMList)) {
            fbtxApxMVoList = BeanUtils.copyList(fbtxApxMList, FbtxApxMVo.class);
        }
        return fbtxApxMVoList;
    }

    /**
     * 根据资源唯一UUID获取文件信息
     */
    @Override
    public FbtxApxM getByResourceUuid(String resourceUuid) {
        SmeAssert.notBlank(resourceUuid, "文件资源唯一UUID为空");
        LambdaQueryWrapper<FbtxApxM> queryWrapper = new QueryWrapper<FbtxApxM>().lambda();
        queryWrapper.eq(FbtxApxM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        queryWrapper.eq(FbtxApxM::getResourceUuid, resourceUuid);
        List<FbtxApxM> fbtxApxMList = super.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(fbtxApxMList)) {
            return fbtxApxMList.get(0);
        }
        return null;
    }

    @Override
    public FbtxApxM getFileByFileUuid(String fileUuid) {
        SmeAssert.notBlank(fileUuid, "文件资源唯一UUID为空");
        LambdaQueryWrapper<FbtxApxM> queryWrapper = new QueryWrapper<FbtxApxM>().lambda();
        queryWrapper.eq(FbtxApxM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        queryWrapper.eq(FbtxApxM::getFileUuid, fileUuid);
        List<FbtxApxM> fbtxApxMList = super.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(fbtxApxMList)) {
            return fbtxApxMList.get(0);
        }
        return null;
    }

    @Override
    public FbtxApxMVo getFileByUuid(String uuid) {
        // 查询文件，优先用资源唯一编号查找
        FbtxApxM fbtxApxM = this.getByResourceUuid(uuid);
        // 判断文件是否存在
        if (fbtxApxM == null) {
            // 其次用文件上传对象惟一编号查找
            fbtxApxM = this.getFileByFileUuid(uuid);
        }
        FbtxApxMVo fbtxApxMVo = BeanUtils.copy(fbtxApxM, FbtxApxMVo.class);
        return fbtxApxMVo;
    }

    @Override
    public List<FbtxApxMVo> listFbtxApxMListByCondition(FbtxApxMVo condition) {
        LambdaQueryWrapper<FbtxApxM> wrapper = new QueryWrapper<FbtxApxM>().lambda();
        wrapper.eq(FbtxApxM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        if (CollectionUtils.isNotEmpty(condition.getFileUuidList())) {
            wrapper.in(FbtxApxM::getFileUuid, condition.getFileUuidList());
        }
        List<FbtxApxM> fbtxApxMList = list(wrapper);
        return BeanUtils.copyList(fbtxApxMList, FbtxApxMVo.class);
    }

}
