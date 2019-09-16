package com.itqf.controller;

import com.itqf.servive.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
 * @Time: 上午11:10
 */
@Controller
public class UsersController {



    @Autowired
    private UserService userService;

    @RequestMapping("/del")
    public String  delete(){
        userService.delete(1);

        return "show";
    }

    @RequestMapping("/delOrder")
    public String  deleteOrder(){
        userService.deleteOrder(1);

        return "show";
    }


    @RequestMapping("/toLogin")
    public String toLogin(){

        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password,String rememberMe, Model model){
        //shiro+spring+springmvc后
        //1，用户名和密码封装成UsernamePasswordToken 对象,用户名为加盐，加盐次数
        Md5Hash md5Hash = new Md5Hash(password,username,1024);
        //加密后的密码
        password = md5Hash.toString();

        //1，用户名和密码封装成UsernamePasswordToken 对象
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        //记住我
        if (rememberMe!=null&&!"".equals(rememberMe)){
            usernamePasswordToken.setRememberMe(true);
        }

        //2，得到subject对象
        Subject subject = SecurityUtils.getSubject();

        System.out.println(subject.isRemembered()+"--记住我");

        //3,登录

        try {
            //javase 环境下，跟ini文件中的账户信息比较
            //javaee 整合后，调用自定义realm中的方法进行认证和授权
            subject.login(usernamePasswordToken);

            System.out.println(subject.hasRole("admin"));//编程式 判断用户是否有该角色

            System.out.println(subject.isPermitted("sys:user:add"));//编程式 判断用户的权限

            return "index";

        } catch (Exception e) {
            // throw  new RuntimeException("账号不存在异常");

            model.addAttribute("error", e.getMessage());

        }

        return "login";


    }


    @RequestMapping("/show")
    public String show(){

        return "show";
    }


}
