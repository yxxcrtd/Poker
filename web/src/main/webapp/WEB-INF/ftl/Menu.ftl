<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<link rel="styleSheet" type="text/css" href="${request.contextPath}/css/manage.css" />
		<style type="text/css">
			li a { display: block; }
		</style>
	</head>
	
	<body style="background-color: #242424;">
		<ul id="menu" class="menu noaccordion expandfirst">
			<li>
				<a id="firstMenu" class="subMenu" href="javascript:;">首页</a>
				<ul>
					<#if (user.managerNews)><li style="list-style-type:disc;"><a target="mainFrame" href="${request.contextPath}/manage/news">新闻管理</a></li></#if>
					<#if (user.managerVideo)><li style="list-style-type:disc;"><a target="mainFrame" href="${request.contextPath}/manage/video">视频管理</a></li></#if>
					<#if (user.managerAd)><li style="list-style-type:disc;"><a target="mainFrame" href="${request.contextPath}/manage/ad">广告管理</a></li></#if>
					<#if (user.managerActive)><li style="list-style-type:disc;"><a target="mainFrame" href="${request.contextPath}/manage/activity">活动管理</a></li></#if>
					<#if (user.managerMatch)><li style="list-style-type:disc;"><a target="mainFrame" href="${request.contextPath}/manage/match">赛事管理</a></li></#if>
				</ul>
			</li>
			<#if (user.managerGame)>
				<li>
					<a class="subMenu" href="javascript:;">游戏库管理</a>
					<ul>
						<li><a target="mainFrame" href="${request.contextPath}/manage/game">游戏管理</a></li>
						<li><a target="mainFrame" href="${request.contextPath}/manage/picture">游戏图片</a></li>
					</ul>
				</li>
			</#if>
			<#if (user.managerPoker)>
				<li>
					<a class="subMenu" href="javascript:;">棋牌管理</a>
					<ul>
						<li><a target="mainFrame" href="${request.contextPath}/manage/chess">棋谱管理</a></li>
						<li><a target="mainFrame" href="${request.contextPath}/manage/tutorial">棋牌教程</a></li>
					</ul>
				</li>
			</#if>
			<#if (1 == user.managerRole)>
				<li>
					<a class="subMenu" href="javascript:;">用户管理</a>
					<ul>
						<#--<li><a target="mainFrame" href="javascript:;">普通用户</a></li>-->
						<li><a target="mainFrame" href="${request.contextPath}/manage/manager">管理员列表</a></li>
					</ul>
				</li>
				<li>
					<a class="subMenu" href="javascript:;">日志管理</a>
					<ul>
						<#--<li><a target="mainFrame" href="javascript:;">用户日志</a></li>-->
						<li><a target="mainFrame" href="${request.contextPath}/manage/log/manager">操作日志</a></li>
					</ul>
				</li>
			</#if>
			<#if (user.managerWebsite)>
				<li>
					<a class="subMenu" href="javascript:;">网站设置</a>
					<ul>
						<li><a target="mainFrame" href="${request.contextPath}/manage/seo">SEO设置</a></li>
						<li><a target="mainFrame" href="${request.contextPath}/manage/link">友情链接</a></li>
					</ul>
				</li>
			</#if>
		</ul>
		<script type="text/javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/menu.js"></script>
	</body>
</html>