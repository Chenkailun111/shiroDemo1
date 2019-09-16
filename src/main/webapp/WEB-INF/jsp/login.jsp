<%--
  Created by IntelliJ IDEA.
  User: liliting
  Date: 2018/11/19
  Time: 上午10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="login">
        <input type="text" name="username"><br>
        <input type="password" name="password"><br>
        <input type="checkbox" name="rememberMe"  value="true"><br>
        ${error}
        <input type="submit" value="提交">
    </form>



</body>
</html>
