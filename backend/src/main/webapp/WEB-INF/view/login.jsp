<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>3D Model Management System - Login</title>
<meta name="description" content="">
<meta name="author" content="templatemo">


<link href="<%=request.getContextPath()%>/static/css/font-awesome.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/static/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/static/css/templatemo-style.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
</head>
<body class="light-gray-bg">
	<div class="templatemo-content-widget templatemo-login-widget white-bg" style="margin-top: 15%;">
		<header class="text-center">
			<h1>OT Invoice MS</h1>
		</header>
		<form action="login.do" class="templatemo-login-form" method="post">

			<div class="form-group">
				<div class="input-group">
					<div class="input-group-addon">
						<i class="fa fa-user fa-fw"></i>
					</div>
					<input type="text" name="userName" class="form-control"
						placeholder="admin@vr-kb.cn">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-addon">
						<i class="fa fa-key fa-fw"></i>
					</div>
					<input type="password" name="userPassword" class="form-control"
						placeholder="******">
				</div>
			</div>
	
			<div class="form-group">
				<div><span style="color: red;">${message}</span></div>
			</div>
			<div class="form-group">
				<button type="submit" class="templatemo-blue-button width-100">登录</button>
			</div>
		</form>
	</div>
	<div
		class="templatemo-content-widget templatemo-login-widget templatemo-register-widget white-bg">
		<p>
			若是没有账号，请 <strong><a href="#"
				class="blue-text">联系管理员</a></strong>
		</p>
	</div>

</body>
</html>