package com.shop.authserver.mapper;

import com.shop.authserver.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 27330
* @description 针对表【sys_user(系统用户)】的数据库操作Mapper
* @createDate 2023-02-01 20:21:56
* @Entity com.shop.authserver.domain.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<String> selectAuthListByUserId(@Param("userId") Long userId);
}




