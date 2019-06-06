<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<div id="seo"></div>
		<link rel="stylesheet" type="text/css" href="../css/style.css" />
	</head>
	
	<body class="page-news_article" banner="1">
		<div class="topper"></div>
		<div class="head">
			<div class="container">
				<div class="box-search pr">
					<a class="logo sp pa" href="/" title="腾讯棋牌"></a>
					<div class="qrcode-s pa">
						<a class="btn-close sp pa" href="javascript:;" title="关闭"></a>
						<img width="91" height="91" src="../images/qrcode-s.jpg" />
					</div>
					<div class="search sp mc pr">
				<form action="/search" method="post" id="searchForm">
					<input class="ipt pa" type="text" name="k" value="请输入关键词搜索"
						onfocus="if(value=='请输入关键词搜索'){value=''}"
						onblur="if(value==''){value='请输入关键词搜索'}" />
						<a class="btn-search pa" href="javascript:;" title="搜索"></a>
				</form>
					</div>
				</div>
				<div class="banner pr">
					<i class="sp left pa"></i>
					<i class="sp right pa"></i>
					<a href="/">首页</a>
					<a href="/news.html">新闻动态</a>
					<a href="/game.html">棋牌游戏库</a>
					<a href="/match.html">赛事专区</a>
					<a href="/action.html" id="current">最新活动</a>
					<a href="/video.html">视频专区</a>
					<a href="/class.html">棋牌学堂</a>
				</div>
			</div>
		</div>

<div class="box-detail">
	<div class="container">
		<p class="location">首页<em>></em>最新活动<em>></em>活动详情</p>
		<div class="detail">
			<p class="tit">${action.activityTitle}</p>
			<p><em>活动时间：</em>${action.activityBeginDate?string("yyyy-MM-dd")}~${action.activityEndDate?string("yyyy-MM-dd")}</p>
			<span class="tag">基本规则</span>${action.activityBasicRules}<br />
			<span class="tag">时间规则</span>${action.activityTimeRules}<br />
			<span class="tag">对弈规则</span>${action.activityGameRules}<br />
			<p><em>奖金设置：</em></p>
			<img src="/upload/${action.activityPicture}" />
		</div>
	</div>
</div>

		<div class="footer">
			<div class="container pr">
				<div id="link"></div>
				<div id="footer"></div>		
				<script language="javaScript" src="../js/jquery.js"></script>
				<script language="javaScript" src="../js/public.js"></script>

<script type="text/javascript">
<!--
$.get("/view", { 'obj' : 'activity', 'id' : ${action.activityId} });
//-->
</script>