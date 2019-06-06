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
					<a <#if ("class" == cur)></#if> id="current" href="/class.html">棋牌学堂</a>
				</div>
			</div>
		</div>

<div class="box-article">
	<div class="container c pr">
		<p class="location"><a href="/">首页</a><em>></em><a href="/class.html">棋牌学堂</a><em>></em>棋牌教程<em>></em>
			<#if (18 < tutorial.tutorialTitle?length)>${tutorial.tutorialTitle[0..18]}<#else>${tutorial.tutorialTitle}</#if></p>
		<div class="article fl">
			<p class="tit">
				<em>${tutorial.tutorialTitle!}</em>
				<span>${tutorial.tutorialCreateTime?string("yyyy-MM-dd HH:mm:ss")}</span>
				<span>管理员：${tutorial.tutorialCreateUser!}</span>
			</p>
			<#if (tutorial.tutorialPicture?? && 3 < tutorial.tutorialPicture?length)>
			<!-- 图片宽度0-670，高度不限  -->
			<img width="670" height="400" src="/upload/${tutorial.tutorialPicture}" />
			</#if>
			<br />
			<div style="min-height: 280px;">
			${tutorial.tutorialContent}
			</div>
			<div class="article-link">
				<p>相关阅读</p>
				<ul>
					<#if (keywords?? && 0 < keywords?size)>
						<#list keywords as l>
							<li><a href="/upload/tutorial${l.tutorialId}.html" target="_blank">${l.tutorialTitle!}<em>${l.tutorialCreateTime?string("yyyy.MM.dd")}</em></a></li>
							<#if (3 == l_index)><#break /></#if>
						</#list>
					</#if>
				</ul>
			</div>
		</div>
		<div class="box-rank fr pr" offset="85">
			<p class="tit">棋牌关注排行</p>
			<ul class="ul-rank">
				<#if (hotTutorial?? && 0 < hotTutorial?size)>
					<#list hotTutorial as l>
						<#if (3 > l_index)>
							<li class="rank${l_index + 1}"><em>${l_index + 1}</em><a href="/upload/tutorial${l.tutorialId}.html" target="_blank">${l.tutorialTitle}</a></li>
						<#else>
							<li><em>${l_index + 1}</em><a href="/upload/tutorial${l.tutorialId}.html" target="_blank">${l.tutorialTitle}</a></li>
						</#if>
						<#if (9 == l_index)><#break /></#if>
					</#list>
				</#if>
			</ul>
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
$.get("/view", { 'obj' : 'tutorial', 'id' : ${tutorial.tutorialId} });
//-->
</script>	
