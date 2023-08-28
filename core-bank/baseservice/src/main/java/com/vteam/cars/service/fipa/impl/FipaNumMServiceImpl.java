package com.vteam.cars.service.fipa.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.mapper.FipaNumMMapper;
import com.vteam.cars.entity.model.FipaNumM;
import com.vteam.cars.entity.vo.FipaNumMVo;
import com.vteam.cars.service.fipa.FipaNumMService;
import com.vteam.cars.util.DateUtils;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.cache.SequenceContainer;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * <p>
 * 系统编号生成表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
@Service
public class FipaNumMServiceImpl extends ServiceImpl<FipaNumMMapper, FipaNumM> implements FipaNumMService {

    /**
     * 默认填充字符串=0
     */
    private static final String DEFAULT_FILL_STR = "0";

    @Resource
    private SequenceContainer sequenceContainer;

    @Override
    public String getSequenceKey(String key) {
        return FipaNumMServiceImpl.class.getSimpleName() + GlobalConstants.Symbol.SEMICOLON + "sequence" + GlobalConstants.Symbol.SEMICOLON + key;
    }

    @Override
    public String getSequenceGroupKey(String key) {
        return FipaNumMServiceImpl.class.getSimpleName() + GlobalConstants.Symbol.SEMICOLON + "sequence-group" + GlobalConstants.Symbol.SEMICOLON + key;
    }

    @Override
    public Integer getMaxNum(Integer numType, String numValue) {
        return this.getMaxNum(numType, numValue, null);
    }

    @Override
    public Integer getMaxNum(Integer numType, String numValue, String numDate) {
        return Long.valueOf(sequenceContainer.incr(getSequenceKey(numType.toString()))).intValue();
    }

    @Override
    public String getMaxNumFillStr(Integer numType, String numValue, int length) {
        return this.getMaxNumFillStr(numType, numValue, null, length);
    }

    @Override
    public String getMaxNumFillStr(Integer numType, String numValue, String numDate, int length) {
        return this.getMaxNumFillStr(numType, numValue, numDate, length, DEFAULT_FILL_STR, false);
    }

    @Override
    public String getMaxNumFillStr(Integer numType, String numValue, int length, String filler, boolean isAppend) {
        return this.getMaxNumFillStr(numType, numValue, null, length, DEFAULT_FILL_STR, false);
    }

    @Override
    public String getMaxNumFillStr(Integer numType, String numValue, String numDate, int length, String filler, boolean isAppend) {
        Integer maxSeqnum = this.getMaxNum(numType, numValue, numDate);
        return StringUtils.fillStr(maxSeqnum.toString(), length, filler, isAppend);
    }

    @Override
    public String getCurrentTxNo() {
        Integer numType = FieldConstant.FipaNum.NUM_TYPE_TX_NO;
        String numDate = LocalDateTime.now().format(DateUtils.FMT_DATE_N);
        String numValue = numDate;
        String maxSeqnum = this.getMaxNumFillStr(numType, numValue, numDate, FieldConstant.FipaNum.TX_NO_SEQNUM_LENGTH);
        return numValue + maxSeqnum;
    }

    @Override
    public void saveFipaNum(int numTypeFundNo, Set<String> numTypeFundNoMembers) {
        FipaNumMVo fipaNumMVo;
        LambdaQueryWrapper<FipaNumM> queryWrapper;
        String numValue;
        for (String member : numTypeFundNoMembers) {
            numValue = member.substring(member.indexOf(GlobalConstants.Symbol.MINUS) + 1);

            // 初始化序列信息
            fipaNumMVo = new FipaNumMVo();
            Long currentSeq = sequenceContainer.incr(getSequenceKey(member));
            fipaNumMVo.setCurrSeqnum(currentSeq.intValue());
            fipaNumMVo.setNumValue(numValue);
            fipaNumMVo.setNumType(numTypeFundNo);
            fipaNumMVo.setNumDate(DateTimeFormatter.ofPattern(GlobalConstants.DateFormat.TO_DATE_N).format(LocalDate.now().minusDays(1)));

            // 删除旧的序列信息
            queryWrapper = new QueryWrapper<FipaNumM>().lambda();
            queryWrapper.eq(FipaNumM::getNumType, numTypeFundNo);
            queryWrapper.eq(FipaNumM::getNumValue, numValue);
            remove(queryWrapper);

            // 新增新的序列信息
            save(fipaNumMVo);
        }
    }

    @Override
    public String getRedeemBatchno() {
        // 兑换码批次号规则：yyyymmdd+4位序号（每天0001开始）
        Integer numType = FieldConstant.FipaNum.NUM_TYPE_REDEEM_BATCHNO;
        String numDate = LocalDateTime.now().format(DateUtils.FMT_DATE_N);
        String key = String.format("%s-%s", String.valueOf(numType), numDate);
        long maxSeqnum = sequenceContainer.incr(getSequenceKey(key));
        return numDate + StringUtils.fillStr(String.valueOf(maxSeqnum), FieldConstant.FipaNum.REDEEM_BATCHNO_SEQNUM_LENGTH, DEFAULT_FILL_STR, false);
    }

    @Override
    public String getAplCode() {
        // 投资申请编号规则：yyyymmdd+4位序号（每天0001开始）
        Integer numType = FieldConstant.FipaNum.NUM_TYPE_INV_APLCODE;
        String numDate = LocalDateTime.now().format(DateUtils.FMT_DATE_N);
        String key = String.format("%s-%s", String.valueOf(numType), numDate);
        long maxSeqnum = sequenceContainer.incr(getSequenceKey(key));
        return numDate + StringUtils.fillStr(String.valueOf(maxSeqnum), FieldConstant.FipaNum.INV_APLCODE_SEQNUM_LENGTH, DEFAULT_FILL_STR, false);
    }

    @Override
    public String getFcnCode(String type) {
        String businesNo = StringUtils.EMPTY;

        if (GlobalConstants.ArabicNumeral.N1.equals(type)) {
            // 合约编号
            Integer numType = FieldConstant.FipaNum.NUM_TYPE_FCNCODE;
            String numDate = LocalDateTime.now().format(DateUtils.FMT_DATE_N);
            String key = String.format("%s-%s", String.valueOf(numType), numDate);
            long maxSeqnum = sequenceContainer.incr(getSequenceKey(key));
            businesNo = "HY" + numDate + StringUtils.fillStr(String.valueOf(maxSeqnum), FieldConstant.FipaNum.INV_APLCODE_SEQNUM_LENGTH, DEFAULT_FILL_STR, false);
        } else {
            // 公用编号规则：yyyymmdd+4位序号（每天0001开始）
            businesNo = this.getAplCode();
        }
        return  businesNo;
    }

}
