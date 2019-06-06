<#include "Obj.ftl" />
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
							<input class="ipt pa" type="text" name="k" value="${k}"
								onfocus="if(value=='请输入关键词搜索'){value=''}"
								onblur="if(value==''){value='请输入关键词搜索'}" />
								<a class="btn-search pa" href="javascript:;" title="搜索"></a>
						</form>
					</div>
				</div>
				<div class="banner pr">
					<i class="sp left pa"></i>
					<i class="sp right pa"></i>
					<a href="/" id="current">首页</a>
					<a href="/news.html">新闻动态</a>
					<a href="/game.html">棋牌游戏库</a>
					<a href="/match.html">赛事专区</a>
					<a href="/action.html">最新活动</a>
					<a href="/video.html">视频专区</a>
					<a href="/class.html">棋牌学堂</a>
				</div>
			</div>
		</div>

<div class="search-result">
	<div class="container">
		<div class="box-hot c">
			<p class="location" style="margin-bottom: 0;">首页<em>></em>搜索结果：</p>
			<ul class="ul-result newsdot">
				<#if (list?? && 0 < list?size)>
					<#list list as l>
						<li>
							<#if ("match" != l.obj)><a href="/upload/${l.obj}${l.id}.html" target="_blank">
							<#else><a href="javascript:;">
							</#if>
								<img width="220" height="130" src="/upload/${l.picture}" />
								<em>【<@compress single_line=true><@getSearchObj l.obj /></@compress>】${l.title}</em>
								${l.summary}<br />
								<#if l.time1??>${l.time1}<#else>${l.time2?string("yyyy-MM-dd HH:mm:ss")}</#if>
							</a>
						</li>
					</#list>
				<#else>
					<br /><center><em>没有搜索到与 ${k} 匹配的数据！</em></center>
				</#if>
			</ul>
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
