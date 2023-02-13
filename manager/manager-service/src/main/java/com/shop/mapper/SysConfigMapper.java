package com.shop.mapper;

import com.shop.domain.SysConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
* @author 27330
* @description 针对表【sys_config(系统配置信息表)】的数据库操作Mapper
* @createDate 2023-02-13 23:37:15
* @Entity com.shop.domain.SysConfig
*/
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {

}




