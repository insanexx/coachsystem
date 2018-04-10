<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>用户注册</title>
 	<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap-3.3.7/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath }/jquery-3.1.0/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath }/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
    
</head>
<body>
	 <label id="msg" style="display:none">${requestScope.message }</label>
	 <form class="form-horizontal" style="padding: 30px 100px 10px;" action="${pageContext.request.contextPath }/user/UserServlet?method=register" method="POST">
      <img class="mb-4" src="${pageContext.request.contextPath }/img/2.png" alt="" width="72" height="72">
      &nbsp;&nbsp;&nbsp;
 	  <span style="font-size:30px;" class="jumbotron-heading">私教预定系统-用户注册</span>
		<hr>
		<div class="form-group">
			<label for="username" class="col-sm-2 control-label">账号：</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="username" name="username" value="${user.username }" maxlength=15 placeholder="用户名(15字以内)" required autofocus>
			</div>
		</div>
		
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">姓名：</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name" value="${user.name }" maxlength=15 placeholder="用户名(15字以内)" required autofocus>
			</div>
		</div>

		<div class="form-group">
			<label for="phone" class="col-sm-2 control-label">手机号码：</label>
			<div class="col-sm-4">
				<input type="number" class="form-control" id="phone" value="${user.phone }" name="phone" maxlength=20 placeholder="手机号" required autofocus>
			</div>
		</div>

		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">密码：</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" id="password" name="password" placeholder="密码" required autofocus>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
				<button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
			</div>
		</div>
	</form>
</body>
</html>