package com.itqf.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖镇楼                  BUG辟易
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 *
 * @Description:
 * @Company: 千锋互联
 * @Author: 李丽婷
 * @Date: 2018/11/19
 * @Time: 上午10:43
 */
public class UsersRealm extends AuthorizingRealm {

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-------->授权"+principalCollection.getPrimaryPrincipal());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //根据登录用户的信息（id），查询用户的角色
        //根据角色id查询用户的权限
        List<String> roles = new ArrayList<String>();
        roles.add("admin");
        roles.add("PM");
        roles.add("programmer");


        //权限
        List<String> perms = new ArrayList<String>();
        perms.add("sys:user:*");
        perms.add("sys:project:*");

        authorizationInfo.addRoles(roles);//添加角色  Collection<String>
        authorizationInfo.addStringPermissions(perms);//添加权限Collection<String>

        return authorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("------->认证");
        //得到用户输入的用户名和密码
        String username = (String) authenticationToken.getPrincipal();//用户名
        //public char[] getPassword() {
        //        return this.password;
        //    }
        String password = new String ((char[])authenticationToken.getCredentials());//密码

        //数据库的用户信息
        String dbUsername = "admin";
        String dbPassword = "df655ad8d3229f3269fad2a8bab59b6c";//数据库存储的密码
        int state = 1;//状态  1，正常   0， 锁定

        //比较
        if (username.equals(dbUsername)){
            if (password.equals(dbPassword)){
                if (state==1){

                    //通过带三个参数的构造方法实例化对象  把用户信息存储到shiro中
                    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,this.getName());

                    return  authenticationInfo;

                }else{
                    throw  new LockedAccountException("账号锁定异常！");
                }
            }else{
                throw  new IncorrectCredentialsException("密码错误！");
            }
        }else{
            throw new UnknownAccountException("账号不存在异常!!");
        }



    }
}
