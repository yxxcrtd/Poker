<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<title>后台登录</title>
		<link rel="stylesheet" href="./css/style.css" media="all" />
		<style type="text/css">
		#loginContainer {
			behavior: url(./css/ie-css3.htc);
    		background: #50a3a2;
		}
		</style>
	</head>
	
	<body>
		<div id="loginContainer">
			<div id="loginSubContainer">
				<h1>棋牌官网管理后台</h1>
				<form id="loginForm" action="${request.contextPath}/login/check" method="post">
					<div id="loginBody">
						<div id="loginTips"><#if tips??>${tips}</#if></div>
						<div class="loginItem">
							<@s.formInput "manager.managerName" "class='loginInput' placeholder='请输入您的用户名'" />
						</div>
						<div class="loginItem">
							<@s.formInput "manager.managerPassword" "class='loginInput' placeholder='请输入您的密码'" "password" />
						</div>
						<div class="loginItem">
							<button id="login-button" type="submit">提 交</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
