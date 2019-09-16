<%--
  Created by IntelliJ IDEA.
  User: liliting
  Date: 2018/11/19
  Time: 上午11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>欢迎页面</title>
</head>
<body>
<shiro:guest>
    游客登录
</shiro:guest>


<!-- 用户信息 -->
   <shiro:principal></shiro:principal> 欢迎您！！

    <shiro:hasRole name="admin">
        管理员
    </shiro:hasRole>

    <<br>
    <shiro:hasPermission name="sys:user:add">
        新增用户
    </shiro:hasPermission>
    <shiro:hasPermission name="sys:user:update">
        修改用户
    </shiro:hasPermission>
    <!--具有-->
    <shiro:hasPermission name="sys:order:update">
        修改订单
    </shiro:hasPermission>

    <!--不具有-->
    <shiro:lacksPermission name="sys:order">
         <shiro:principal/>不具有对订单操作的权限
    </shiro:lacksPermission>

</body>
</html>
