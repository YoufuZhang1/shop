package com.shop.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName sys_log
 */
@TableName(value ="sys_log")
@Data
public class SysLog implements Serializable {
    private Long id;

    private String username;

    private String operation;

    private String method;

    private String params;

    private Long time;

    private String ip;

    private Date create_date;

    private static final long serialVersionUID = 1L;
}