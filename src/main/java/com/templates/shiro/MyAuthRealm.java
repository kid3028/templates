package com.templates.shiro;

import com.templates.entity.Permission;
import com.templates.entity.Role;
import com.templates.entity.User;
import com.templates.service.PermissionService;
import com.templates.service.RoleService;
import com.templates.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;


public class MyAuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User userInfo  = (User)principals.getPrimaryPrincipal();
        System.out.println(userInfo);
        for(Role role: roleService.getRolesByUserId(userInfo.getUserId())){
            System.out.println(role);
            authorizationInfo.addRole(role.getRoleName());
            System.out.println(role.getRoleName());
            for(Permission p:permissionService.getPermsByRoleId(role.getRoleId())){
                System.out.println(p.getPermUrl());
                if(!Objects.equals(p.getPermUrl(), null) && !(p.getPermUrl().length() == 0)) {
                    authorizationInfo.addStringPermission(p.getPermUrl());
                }
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("--------->MyAuthRealm.doGetAuthenticationInfo()");
        String userName = (String) authenticationToken.getPrincipal();
        System.out.println(userName);
        // 3、根据userName从数据库中获取用户信息
        System.out.println("从数据库中获取" + userName + "对应信息");
        User user = userService.getUserByUserName(userName);
        if(user == null) {
            return null;
        }

        if("0".equals(user.getUserStatus())) {
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(),ByteSource.Util.bytes(user.getSalt()), getName());
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "md5";
        int hashIterations = 2;
        String credentials = "123456";
        String salt = "/4QFCGXeQN+EpQ1FfkXiJA==";
        Object result = new SimpleHash(hashAlgorithmName, credentials,salt, hashIterations);
        System.out.println(result);
    }



}