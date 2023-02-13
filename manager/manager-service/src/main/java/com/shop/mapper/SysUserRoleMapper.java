package com.shop.mapper;

import com.shop.domain.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 27330
* @description 针对表【sys_user_role(用户与角色对应关系)】的数据库操作Mapper
* @createDate 2023-02-13 23:37:16
* @Entity com.shop.domain.SysUserRole
*/
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}




