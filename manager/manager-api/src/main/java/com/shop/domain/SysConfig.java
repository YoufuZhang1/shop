package com.shop.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName sys_config
 */
@TableName(value ="sys_config")
@Data
public class SysConfig implements Serializable {
    private Long id;

    private String param_key;

    private String param_value;

    private String remark;

    private static final long serialVersionUID = 1L;
}