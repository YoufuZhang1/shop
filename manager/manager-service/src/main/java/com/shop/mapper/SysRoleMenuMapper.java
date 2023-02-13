package com.shop.mapper;

import com.shop.domain.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 27330
* @description 针对表【sys_role_menu(角色与菜单对应关系)】的数据库操作Mapper
* @createDate 2023-02-13 23:37:16
* @Entity com.shop.domain.SysRoleMenu
*/
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

}




