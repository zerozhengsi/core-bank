package com.vteam.cars.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailParamVo {
	// 产品流水号
	private Integer prorefcode;
	// 客户流水号
	private Integer custrefcode;
	// 邮件类型
	private String emailType;
	// 合约编号
	private String fcnNum;
}
