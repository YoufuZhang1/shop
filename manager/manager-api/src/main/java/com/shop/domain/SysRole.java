package com.shop.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
public class SysRole implements Serializable {
    private Long role_id;

    private String role_name;

    private String remark;

    private Long create_user_id;

    private Date create_time;

    private static final long serialVersionUID = 1L;
}