package com.vteam.vtarm.sql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SQL语句模型 .<br>
 *
 * @author andy.sher
 * @date 2018/7/12 9:10
 */
@Getter
@Setter
@ToString
public class SQLModel {

    /**
     * SQL语句
     */
    private String sql;

    public SQLModel() {
    }

    public SQLModel(String sql) {
        this.sql = sql;
    }

}
