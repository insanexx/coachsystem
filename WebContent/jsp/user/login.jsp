<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>user login</title>
    <link href="${pageContext.request.contextPath }/bootstrap-4.0.0-dist/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/signin.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/jquery-3.1.0/jquery-3.1.0.min.js"></script>
  </head>
  <body class="text-center">
  	<label id="msg" style="display:none">${requestScope.message }</label>
    <form class="form-signin" action="${pageContext.request.contextPath }/user/UserServlet?method=login" method="POST">
      <img class="mb-4" src="${pageContext.request.contextPath }/img/2.png" alt="" width="72" height="72">
 	  <h1 class="jumbotron-heading">私人健身教练预定管理系统</h1>
 	  <h3>用户登录</h3>
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      <label for="name" class="sr-only">用户名</label>
      <input type="text" id="name" class="form-control" placeholder="用户名" value="${user.name }" required autofocus name="name">
      <label for="password" class="sr-only">密码</label>
      <input type="password" id="password" class="form-control" placeholder="密码" required name="password">
      <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      <br/>
      <a href="${pageContext.request.contextPath }/jsp/user/register.jsp">立即注册</a>
    </form>
  </body>
</html>