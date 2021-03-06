<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<div id="seo"></div>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>

<body class="page-class-manual" banner="6">
	<div class="topper"></div>
	<div class="head">
	<div class="container">
				<div class="box-search pr">
					<a class="logo sp pa" href="/" title="腾讯棋牌"></a>
					<div class="qrcode-s pa">
						<a class="btn-close sp pa" href="javascript:;" title="关闭"></a>
						<img width="91" height="91" src="/images/qrcode-s.jpg" />
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
					<a <#if ("news" == cur)></#if> href="/news.html">新闻动态</a>
					<a <#if ("game" == cur)></#if> href="/game.html">棋牌游戏库</a>
					<a <#if ("match" == cur)></#if> href="/match.html">赛事专区</a>
					<a <#if ("activity" == cur)></#if> href="/action.html">最新活动</a>
					<a <#if ("video" == cur)></#if> href="/video.html">视频专区</a>
					<a <#if ("class" == cur)></#if>id="current" href="/class.html">棋牌学堂</a>
				</div>
			</div>
	</div>
	
	<div class="box-manual">
		<div class="container">
			<p class="location"><a href="index.html">首页</a><em>></em><a href="class.html">棋牌学堂</a><em>></em>棋牌<em>></em>${game.gameTitle!}</p>
			<p class="tit">
				<em>${game.gameTitle}</em>
				<span>添加：admin</span>
			</p>
			<div class="flash">
			${game.gameRule}
			</div>
		</div>
	</div>

	<div class="footer">
		<div class="container pr">
			<div id="link"></div>
			<div id="footer"></div>
			<script language="javaScript" src="/js/jquery.js"></script>
			<script language="javaScript" src="/js/public.js"></script>
