package com.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.domain.SysUser;
import com.shop.service.SysUserService;
import com.shop.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 27330
* @description 针对表【sys_user(系统用户)】的数据库操作Service实现
* @createDate 2023-02-13 23:37:16
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




