package com.vteam.vtarm.codec;

import com.vteam.vtarm.CommonConstants;
import com.vteam.vtarm.utils.AesUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 数据处理策略基类 .<br>
 *
 * @author andy.sher
 * @date 2018/10/9 13:18
 */
public abstract class BaseDataCodecStrategy implements DataCodecStrategy {

    @Override
    public void doDecrypt(Object target) {
        if (ArrayUtils.isNotEmpty(getFields())) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(target);
            Object decryptValue;
            for (String field : getFields()) {
                if (StringUtils.isNotBlank(field)) {
                    if (null != beanWrapper.getPropertyValue(field)) {
                        decryptValue = beanWrapper.getPropertyValue(field);
                        if (null != decryptValue && StringUtils.isNotBlank(decryptValue.toString())) {
                            if (!decryptValue.toString().contains(CommonConstants.Symbol.COMMA)) {
                                beanWrapper.setPropertyValue(field, AesUtils.decrypt(decryptValue.toString(), AesUtils.Mode.DB));
                            } else {
                                String[] valueArray = decryptValue.toString().split(CommonConstants.Symbol.COMMA);
                                String[] value = new String[valueArray.length];
                                for (int i = 0; i < valueArray.length; i++) {
                                    value[i] = AesUtils.decrypt(valueArray[i], AesUtils.Mode.DB);
                                }
                                beanWrapper.setPropertyValue(field, String.join(CommonConstants.Symbol.COMMA, value));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void doEncrypt(Object target) {
        if (ArrayUtils.isNotEmpty(getFields())) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(target);

            Object encryptValue;
            for (String field : getFields()) {
                if (StringUtils.isNotBlank(field)) {
                    if (null != beanWrapper.getPropertyValue(field)) {
                        encryptValue = beanWrapper.getPropertyValue(field);
                        if (null != encryptValue && StringUtils.isNotBlank(encryptValue.toString())) {
                            beanWrapper.setPropertyValue(field, AesUtils.encrypt(encryptValue.toString(), AesUtils.Mode.DB));
                        }
                    }
                }
            }
        }
    }
}
