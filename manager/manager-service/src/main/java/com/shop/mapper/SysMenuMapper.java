package com.shop.mapper;

import com.shop.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 27330
* @description 针对表【sys_menu(菜单管理)】的数据库操作Mapper
* @createDate 2023-02-13 23:37:15
* @Entity com.shop.domain.SysMenu
*/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

}




