package com.shop.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
public class SysMenu implements Serializable {
    private Long menu_id;

    private Long parent_id;

    private String name;

    private String url;

    private String perms;

    private Integer type;

    private String icon;

    private Integer order_num;

    private static final long serialVersionUID = 1L;
}