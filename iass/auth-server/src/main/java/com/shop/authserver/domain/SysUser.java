package com.shop.authserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

/**
 * 系统用户
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser implements Serializable, UserDetails {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建者ID
     */
    @TableField(value = "create_user_id")
    private Long createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 用户所在的商城Id
     */
    @TableField(value = "shop_id")
    private Long shopId;

    /**
     * 权限
     */
    @TableField(exist = false)
    List<String> auths;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     *  username返回组建ID
     * @return
     */
    public String getUsername() {
        return String.valueOf(this.userId);
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 1;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        //权限列表不为空，将其分割开来
        if (!CollectionUtils.isEmpty(auths)){
       //遍历权限列表中的每个权限数据(权限列表)
            auths.forEach(a->{
                if (a.contains(",")){
                //字符串切割,得到集合数组
                    Arrays.asList( a.split(",")).forEach
                            (b-> authorities.add(new SimpleGrantedAuthority(b)));
                }else {
                    //单个
                    authorities.add(new SimpleGrantedAuthority(a));
                }

            });
        }
        return authorities;
    }

    public String getPassword() {
        return this.password;
    }
}