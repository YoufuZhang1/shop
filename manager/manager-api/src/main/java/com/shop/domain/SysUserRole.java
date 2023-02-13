package com.shop.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName sys_user_role
 */
@TableName(value ="sys_user_role")
@Data
public class SysUserRole implements Serializable {
    private Long id;

    private Long user_id;

    private Long role_id;

    private static final long serialVersionUID = 1L;
}