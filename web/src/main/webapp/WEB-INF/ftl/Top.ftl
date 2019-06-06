<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<link rel="styleSheet" type="text/css" href="${request.contextPath}/css/manage.css" />
	</head>
	
	<body>
		<div id="top">
			<div id="logo"><img src="${request.contextPath}/images/manage_logo.png" width="170" height="50" alt="Logo" /></div>
			<div id="welcome">后台管理中心</div>
			<div id="profile">
				欢迎您，${user.managerName}！&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${request.contextPath}/logout" onClick="javascript:return confirm('确认要退出吗?');"><span class="red">注销</span></a>
			</div>
		</div>
	</body>
</html>