package com.shop.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName sys_role_menu
 */
@TableName(value ="sys_role_menu")
@Data
public class SysRoleMenu implements Serializable {
    private Long id;

    private Long role_id;

    private Long menu_id;

    private static final long serialVersionUID = 1L;
}