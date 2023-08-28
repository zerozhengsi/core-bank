package com.vteam.cars.entity.mapper;

import com.vteam.cars.entity.model.EmailConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vteam.cars.entity.vo.EmailConfigVo;

import java.util.List;

/**
 * <p>
 * 邮件配置表 Mapper 接口
 * </p>
 *
 * @author vteam-generator
 * @since 2023-06-15
 */
public interface EmailConfigMapper extends BaseMapper<EmailConfig> {

    public List<EmailConfigVo> listEmailConfigVoByCondition(EmailConfigVo emailConfigVo);

}
