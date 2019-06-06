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
					<a href="/news.html">新闻动态</a>
					<a href="/game.html">棋牌游戏库</a>
					<a href="/match.html">赛事专区</a>
					<a href="/action.html">最新活动</a>
					<a href="/video.html" id="current">视频专区</a>
					<a href="/class.html">棋牌学堂</a>
				</div>
			</div>
		</div>

<div class="search-result">
	<div class="container">
		<div class="box-hot c">
			<p class="location" style="margin-bottom: 0;">首页<em>></em>视频专区<em>></em>赛事回放</p>
			<ul class="ul-result newsdot">
				<#if (category_1_list?? && 0 < category_1_list?size)>
					<#list category_1_list as l>
						<li>
							<a href="/upload/video${l.videoId}.html" target="_blank">
								<img width="220" height="130" src="/upload/${l.videoPicture}" />
								<em>${l.videoTitle}</em>
								<span>
									类型：<i>赛事回放</i>
									地区：<i>${l.videoArea}</i>
									时长：<i>${l.videoDuring}分钟</i>
									总播放次数：<i>${l.videoHit}</i>
								</span>
								${l.videoSummary}
							</a>
						</li>
					</#list>
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
