<#include "../Obj.ftl" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<div id="seo"></div>
		<link rel="stylesheet" type="text/css" href="../css/style.css" />
	</head>
	
	<body class="page-news" banner="1">
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
					<a href="/news.html" id="current">新闻动态</a>
					<a href="/game.html">棋牌游戏库</a>
					<a href="/match.html">赛事专区</a>
					<a href="/action.html">最新活动</a>
					<a href="/video.html">视频专区</a>
					<a href="/class.html">棋牌学堂</a>
				</div>
			</div>
		</div>

<div class="box-article">
	<div class="container">
		<p class="location">首页<em>></em>新闻动态<em>></em>新闻列表<em>></em>棋类</p>
		<div class="outer c pr">
			<div class="article fl">
				<ul class="ul-article newsdot">
					<#if (category_4_list?? && 0 < category_4_list?size)>
						<#list category_4_list as l>
							<li>
								<a href="/upload/news${l.newsId}.html" target="_blank">
									<img width="120" height="88" src="/upload/${l.newsPicture}" />
									<em title="${l.newsTitle}"><#if (22 < l.newsTitle?length)>${l.newsTitle[0..21]}<#else>${l.newsTitle}</#if></em>
									<#if (111 < l.newsSummary?length)>${l.newsSummary[0..108]}...<#else>${l.newsSummary}</#if>
									<p>${l.newsCreateTime?string("yyyy-MM-dd")}</p>
								</a>
							</li>
						</#list>
					</#if>
				</ul>
			</div>
			
			<div class="box-rank fr pr" offset="30" style="padding: 15px; width: 270px; border: medium none; background: #f8f8f8 none repeat scroll 0 0;">
				<p class="tit">新闻关注排行</p>
				<ul class="ul-rank">
					<#if (hotNews?? && 0 < hotNews?size)>
						<#list hotNews as l>
							<#if (3 > l_index)>
								<li class="rank${l_index + 1}"><em>${l_index + 1}</em><a href="/upload/news${l.newsId}.html" target="_blank">${l.newsTitle}</a></li>
							<#else>
								<li><em>${l_index + 1}</em><a href="/upload/news${l.newsId}.html" target="_blank">${l.newsTitle}</a></li>
							</#if>
							<#if (9 == l_index)><#break /></#if>
						</#list>
					</#if>
				</ul>
			</div>
		</div>
		<div class="holder"></div>
	</div>
</div>

		<div class="footer">
			<div class="container pr">
				<div id="link"></div>
				<div id="footer"></div>
				<script language="javaScript" src="../js/jquery.js"></script>
				<script language="javaScript" src="../js/public.js"></script>
				<script language="javaScript" src="../js/jquery.jPages.js"></script>
				<script type="text/javascript">$(function() { $("div.holder").jPages({}); });</script>
