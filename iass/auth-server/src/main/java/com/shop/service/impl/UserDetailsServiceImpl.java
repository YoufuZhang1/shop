package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.constants.AuthConstants;
import com.shop.domain.SysUser;
import com.shop.mapper.SysUserMapper;
import com.shop.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zyf
 * @date 2022年12月27日22:19时
 */
@Service
public class UserDetailsServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserDetailsService, SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 权限认证授权的操作
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据前端传递的标记,来决定是哪个用户的登录授权操作
        /*
            后台管理系统: loginType = sysUser
            前端(小程序) loginType = user
         */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        String loginTypeValue = request.getHeader(AuthConstants.LOGIN_TYPE);

        switch (loginTypeValue) {
            //后台用户
            case AuthConstants.SYS_USER:
                //根据用户名来查询用户信息(sys_user)
                SysUser sysUser = sysUserMapper.selectOne(
                        new QueryWrapper<SysUser>()
                                .eq(StringUtils.isNotBlank(username),"username",username )
                );

                //根据用户的id,查询用户所需的权限列表
                if (!ObjectUtils.isEmpty(sysUser)) {
                     /*
                        已有的是userId,根据userId来查询我们的权限信息
                        select distinct m.perms from sys_menu m
                                        left join sys_role_menu rm on m.menu_id = rm.menu_id
                                        left join sys_user_role ur on ur.role_id = rm.role_id
                                        where ur.user_id = #{userId} and m.type=2
                     */
                    List<String> auths = sysUserMapper.selectAuthListByUserId(sysUser.getUserId());

                    if (!CollectionUtils.isEmpty(auths)){
                        sysUser.setAuths(auths);
                    }

                    return sysUser;
                }
            case AuthConstants.USER:
                //前端小程序登录
        }
        //如果用户为空,返回null,代表校验未通过,没有权限
        return null;
    }
}
