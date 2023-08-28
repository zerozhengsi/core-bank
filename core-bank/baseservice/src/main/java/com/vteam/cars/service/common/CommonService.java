package com.vteam.cars.service.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.service.fspa.FspaSeqMService;
import com.vteam.cars.sql.QueryExecutor;
import com.vteam.cars.util.SmeAssert;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.cache.StringValueContainer;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 类功能描述:公共service
 *
 * @author oscar.yu
 * @version 1.0.0
 * @since  2018年7月20日 oscar.yu 创建 CommonService.java
 * [1.0] 2019-6-28 17:01 oscar.yu mod 调整Mysql数据库的获取索引方式为从Redis获取
 */
@Service
public class CommonService {

    @Resource
    private QueryExecutor queryExecutor;

    @Resource(type = FspaSeqMService.class)
    private FspaSeqMService fspaSeqMService;

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;
    
    @Resource(type = StringValueContainer.class)
    private StringValueContainer stringValueContainer;

    /**
     * 序列的前缀
     */
    public static final String SEQUENCE_PREFIX = "SEQUENCE_";

    /**
     * 验证码最大校验次数
     */
    public static final int MAX_CODE_CHECK_COUNT = 5;

    /**
     * 验证码验证次数信息
     */
    public static final String CHECK_CODE_TRY_COUNT = "check_code-try-count-%s";

    /**
     * 验证码业务信息标记，用于生成临时授权码
     */
    public static final String CHECK_CODE_SCOPE = "check_code-scope";

    /**
     * 获取最大批次号(交易) 根据Sequence
     * @return 当前最大批次号 + 1
     */
    public int getMaxBatchNo() {
        return getMaxRefcodeBySequenceName("SEQUENCE_BATCHNO");
    }


    /**
     * 校验手机/邮箱/图片验证码
     * 
     * @param reqEntity 请求对象
     * @param codeType 验证码类型[mobilephone=手机/image=图片/email=邮箱]
     * @param onlyProd 标记是否仅生产环境校验验证码错误
     * @return data 验证通过的业务信息
     * @author oscar.yu
     * @date 2019/12/10 17:37
     */
    public JSONObject checkVerificationCodeCommon(ReqEntity reqEntity, String codeType, boolean onlyProd) {

        JSONObject requestData = JSON.parseObject(reqEntity.getData());

        // 获取输入的验证码和标识符
        final String inCode = requestData.getString(GlobalConstants.Flag.VERIFICATION_CODE);
        final String key = requestData.getString(GlobalConstants.Flag.UUID);
        // 获取输入的业务信息(手机号/邮箱)，计算出实际保存的验证码信息，格式为：验证码_手机号/邮箱
        String codeScope = null;
        // 默认为验证码
        String saveRealCode = inCode;
        if (GlobalConstants.Flag.MOBILEPHONE.equals(codeType)) {
            codeScope = requestData.getString(GlobalConstants.Flag.MOBILEPHONE);
            SmeAssert.notBlank(codeScope, "手机号码为空。");
        } else if (GlobalConstants.Flag.EMAIL.equals(codeType)) {
            codeScope = requestData.getString(GlobalConstants.Flag.EMAIL);
            SmeAssert.notBlank(codeScope, "电子邮箱为空。");
        }

        // 图片验证码不提示获取
        if (!GlobalConstants.Flag.IMAGE.equals(codeType)) {
            saveRealCode += GlobalConstants.Symbol.UNDERLINE + codeScope; 
            SmeAssert.notBlank(key, "请点击获取验证码。");
        }

        // 从缓存中取出验证码，非图片验证码时，格式为：验证码_手机号/邮箱
        final String realCode = stringValueContainer.get(key);

        SmeAssert.notBlank(inCode, "验证码为空。");
        SmeAssert.notBlank(realCode, "验证码已超时。");

        // 尝试次数
        String tryCountKey = String.format(CHECK_CODE_TRY_COUNT, key);
        Integer tryCount = this.validCodeTryCount(saveRealCode, realCode, tryCountKey);

        SmeAssert.lt(tryCount, MAX_CODE_CHECK_COUNT, "已达到最大校验次数，该验证码已失效，请重新获取。");

        // 清空尝试次数
        stringValueContainer.delete(tryCountKey);
        // 删除验证码
        stringValueContainer.delete(key);
        
        // 设置业务信息
        JSONObject data = new JSONObject();
        data.put(CHECK_CODE_SCOPE, codeScope);
        return data;
    }
    
    /**
     * 校验手机/邮箱/图片验证码
     * 
     * @param reqEntity 请求对象
     * @param codeType 验证码类型[mobilephone=手机/image=图片/email=邮箱]
     * @return data 验证通过的业务信息
     * @author oscar.yu
     * @date 2019/12/10 17:37
     */
    public JSONObject checkVerificationCodeCommon(ReqEntity reqEntity, String codeType) {
        return this.checkVerificationCodeCommon(reqEntity, codeType, false);
    }
    
    /**
     * 校验验证码尝试次数 .
     *
     * @param inCode      输入的验证码
     * @param realCode    真实验证码
     * @param tryCountKey
     * @return java.lang.Integer 已尝试次数
     * @author andy.sher
     * @date 2019/4/26 11:14
     */
    private Integer validCodeTryCount(String inCode, String realCode, String tryCountKey) {
        int tryCount = 0;
        String count = stringValueContainer.get(tryCountKey);
        if (StringUtils.isNotBlank(count)) {
            tryCount = Integer.parseInt(count);
        }
        if (!realCode.equals(inCode)) {
            tryCount++;
            stringValueContainer.set(tryCountKey, String.valueOf(tryCount));
        }
        return tryCount;
    }

    /**
     * 根据表名获取最大的流水号
     *
     * @param tableName 表名
     * @return 当前最大流水号+1
     */
    public int getMaxRefcodeByTableName(String tableName) {
        return getMaxRefcodeBySequenceName(SEQUENCE_PREFIX + tableName.toUpperCase());
    }

    /**
     * 根据SEQUENCE NAME 获取最大的流水号
     *
     * @param sequenceName SEQUENCE NAME
     * @return 当前最大流水号+1
     */
    public int getMaxRefcodeBySequenceName(String sequenceName) {
        // 数据库主键从Redis获取
        return fspaSeqMService.nextval(sequenceName);

        /*StringBuffer sqlBuffer = new StringBuffer();
        // 获取系统数据库类型
        final String dbType = smeConfiguration.getDbType();
        // 连接的Mysql数据库
        if (GlobalConstants.DbType.SYSTEM_DB_MYSQL.equals(dbType)) {
            // [1.0] 调整获取索引方式为从Redis获取
            return fspaSeqMService.nextval(sequenceName);
        }
        // 连接的浪潮KDB数据库
        else if (GlobalConstants.DbType.SYSTEM_DB_INSPUR_KDB.equals(dbType)) {
            // [1.0] 调整获取索引方式为从Redis获取
            return fspaSeqMService.nextval(sequenceName);
        }
        // 连接的DB2数据库
        else if (GlobalConstants.DbType.SYSTEM_DB_DB2.equals(dbType)) {
            // [1.0] 调整获取索引方式为从Redis获取
            return fspaSeqMService.nextval(sequenceName);
        } else {
            // 连接的Oracle数据库
            sqlBuffer.append(" SELECT ").append(sequenceName).append(".NEXTVAL NEXT_VAL ");
            sqlBuffer.append(" FROM DUAL");
        }
        int refcode = 0;
        List<Map<String, Object>> list = queryExecutor.genericQuery(sqlBuffer.toString());
        if (CollectionUtils.isNotEmpty(list)) {
            // 修复根据key可能出现取值错误的问题,直接获取第一个值
            Object refcodeObject = list.get(0).values().iterator().next();
            if (refcodeObject != null) {
                refcode = Integer.parseInt(String.valueOf(refcodeObject));
            }
        }
        return refcode;*/
    }

    /**
     * 根据BO CLASS 获取最大的流水号
     *
     * @param boClass Table对应的BO Class
     * @return 当前最大流水号+1
     */
    public int getMaxRefcodeByBeanClass(Class<?> boClass) {
        return getMaxRefcodeByTableName(StringUtils.camelToUnderLine(boClass.getSimpleName()));
    }
    /**
     * @param idNum:
     * @return: boolean
     * @author: deng.dun
     * @date: 2023/3/17 15:02
     * @description:身份证号码的严格校验
     */
    public boolean checkIdCardNum(String idNum) throws ParseException {
        //将末尾可能存在的x转成X
        idNum=idNum.toUpperCase();
        //前6位地址码。后面仍需打表校验
        String regex="";
        regex+="^[1-6]\\d{5}";
        //年份。后面仍需校验
        regex+="(18|19|20)\\d{2}";
        //月份。后面仍需校验
        regex+="((0[1-9])|(1[0-2]))";
        //日期。后面仍需校验
        regex+="(([0-2][1-9])|10|20|30|31)";
        //3位顺序码
        regex+="\\d{3}";
        //检验码。后面仍需验证
        regex+="[0-9X]";

        if(!idNum.matches(regex)) {
            return false;
        }

        //第1，2位(省)打表进一步校验
        int[] d={11,12,13,14,15,
                21,22,23,31,32,33,34,35,36,37,
                41,42,43,
                44,45,46,
                50,51,52,53,53,
                61,62,63,64,65,
                83,81,82};
        boolean flag=false;
        int prov=Integer.parseInt(idNum.substring(0, 2));
        for(int i=0;i<d.length;i++) {
            if(d[i]==prov)
            {
                flag=true;
                break;
            }
        }
        if(!flag) {
            return false;
        }

        //生日校验：生日的时间不能比当前时间（指程序检测用户输入的身份证号码的时候）晚
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Date birthDate=sdf.parse(idNum.substring(6, 14));
        Date curDate=new Date();
        if(birthDate.getTime()>curDate.getTime()) {
            return false;
        }

        //生日校验：每个月的天数不一样（有的月份没有31），还要注意闰年的二月
        int year=Integer.parseInt(idNum.substring(6, 10));
        int leap=((year%4==0 && year%100!=0) || year%400==0)?1:0;
        final int[] month={0,31,28+leap,31,30,31,30,31,31,30,31,30,31};
        int mon=Integer.parseInt(idNum.substring(10, 12));
        int day=Integer.parseInt(idNum.substring(12, 14));
        if(day>month[mon])
        {
            //System.out.println(day+" "+month[mon]+"\n");
            //System.out.println("---");
            return false;
        }


        //检验码
        if(idNum.charAt(17)!=getLastChar(idNum)) {
            return false;
        }

        return true;
    }
    //根据身份证号码的前17位计算校验码
    public char getLastChar(String idNum) {
        final int[] w={0,7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
        //这就是为什么一开始将末尾可能存在的x转成X的原因
        final char[] ch={'1','0','X','9','8','7','6','5','4','3','2'};
        int res=0;
        for(int i=0;i<17;i++)
        {
            int t=idNum.charAt(i)-'0';
            res+=(t*w[i+1]);
        }
        return ch[res%11];
    }
}
