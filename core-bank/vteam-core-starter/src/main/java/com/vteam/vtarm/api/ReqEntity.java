package com.vteam.vtarm.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 标准请求实体类 .<br>
 *
 * @author andy.sher
 * @date 2018/7/16 15:21
 */
@Getter
@Setter
@ToString
public class ReqEntity implements Serializable {

    private static final long serialVersionUID = -3439039643191796038L;

    /**
     * 客户端标识
     */
    private String client;

    /**
     * 请求数据
     */
    private String data;

    /**
     * 分页下标
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 排序方式（{field: ASC|DESC},...）
     */
    private String sort;

}
