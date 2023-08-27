package com.vteam.cars.entity.vo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailVo {
	//发件人
	private String from;
	//收件人
	private String to;
	//抄送
	private String cc;
	//密送
	private String bcc;
	//主题
	private String subject;
	//内容
	private String content;
	//是否立刻发送【1-是，2-否，预约发送】
	private String imSendTime;
	//发送时间
	private LocalDateTime sendTime;
}
