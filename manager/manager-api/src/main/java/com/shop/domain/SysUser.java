package com.shop.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser implements Serializable {
    private Long user_id;

    private String username;

    private String password;

    private String email;

    private String mobile;

    private Integer status;

    private Long create_user_id;

    private Date create_time;

    private Long shop_id;

    private static final long serialVersionUID = 1L;
}