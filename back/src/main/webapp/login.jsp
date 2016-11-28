<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String basePath = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>登录</title>
<base href="<%=basePath%>">
<link rel="icon" href="resource/images/favicon.ico1">
<link rel="stylesheet" href="resource/css/style.css">

<body>
<div class="login-container">
  <h1>系统登录</h1>
  <div class="connect">
    <p>hushuang.me</p>
    <c:url value="/names" var="loginUrl"/>
    <c:if test="${param.error != null}">
      <p>用户名密码错误！</p>
    </c:if>
    <c:if test="${param.logout != null}">
      <p>您已注销！</p>
    </c:if>
  </div>
  <form action="${loginUrl}" method="post" id="loginForm">
    <div>
      <input type="text" name="username" class="username" placeholder="用户名" autocomplete="off"/>
    </div>
    <div>
      <input type="password" name="password" class="password" placeholder="密码" oncontextmenu="return false" onpaste="return false"/>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button id="submit" type="submit">登录</button>
  </form>
  <a href="register.html">
    <button type="button" class="register-tis">注册</button>
  </a>
</div>

<script src="resource/js/jquery.min.js"></script>
<script src="resource/js/common.js"></script>
<script src="resource/js/supersized.3.2.7.min.js"></script>
<script src="resource/js/supersized-init.js"></script>
<script src="resource/js/jquery.validate.min.js"></script>

<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
  <p><a href="http://chwshuang.github.io" target="_blank" style="text-decoration: none;"><img src="resource/images/chwshuang.png"></a></p>
  <p><a href="http://chwshuang.github.io" target="_blank" style="color: #ffffff;text-decoration: none;">Copyright © 2016 大大的微笑保留所有权利。</a></p>
</div>
</body>

</html>