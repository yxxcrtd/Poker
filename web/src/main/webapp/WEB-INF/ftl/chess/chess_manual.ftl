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
			<p class="location"><a href="/index.html">首页</a><em>></em><a href="/class.html">棋牌学堂</a><em>></em>棋谱<em>></em>${chess.newsTitle!}</p>
			<p class="tit">
				<em>${chess.chessTitle}</em>
				<span>${(chess.chessCreateTime?string("yyyy-MM-dd"))!}</span>
				<span>添加：${chess.chessCreateUser}</span>
			</p>
			<input id="chessProcess" type="hidden" value="${chess.chessProcess}">
			<link href="/css/flash.css" type="text/css" rel="stylesheet" />
			<div class="flash">
			<!--<iframe src="${request.contextPath}/upload/chessFlash.html" style="width: 1000px; height: 480px;" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
			 棋手信息（左上）-->
				<div class="chessleft">
					<div class="player">
						<div class="info-r">
							<div>
								<img src=<#if (chess.chessRedPic?? && 5 < chess.chessRedPic?length)>"/upload/${chess.chessRedPic}"<#else>"/images/img_flash/red.png"</#if> class="headicon"/>							
								<#if (chess.chessResult==0)>
								<img src="/images/img_flash/win.png" />
								</#if>
							</div>
							<span class="name">红方：${chess.chessRed!}</span> 
							<span class="name">称号：${chess.chessRedLevel!}</span>
						</div>
						<div class="info-b">
							<div>
								<img src=<#if (chess.chessBlackPic??) && 5 < chess.chessBlackPic?length>"/upload/${chess.chessBlackPic}"<#else>"/images/img_flash/black.png"</#if> class="headicon"/>							
								<#if (chess.chessResult==1)><img src="/images/img_flash/win.png" /></#if>
							</div>
							<span class="name">黑方：${chess.chessBlack!}</span> 
							<span class="name">称号：${chess.chessBlackLevel!}</span>
						</div>
					</div>
					<!-- 棋谱详情 （左下）-->
					<div class="manuals">
						<ol id="billBox" class="bill_box">
						</ol>
					</div>
				</div>
		
				<!-- 棋盘内容 （中）-->
				<div class="canvas">
					<canvas id="chess">对不起，您的浏览器不支持HTML5，请切换为极速模式或升级浏览器至IE9、firefox或者谷歌浏览器！</canvas>
					<audio src="/images/audio/click.wav" id="clickAudio" preload="auto"></audio>
					<div class="bn_box" id="bnBox">
						<input type="button" id="pre" class="btn" value="上一步" /> <input
							type="button" id="start" class="btn" value="重新开局" /> <input
							type="button" id="next" class="btn" value="下一步" /> <input
							type="button" id="autoPlay" class="btn" value="自动播放" /> <input
							type="button" id="stopPlay" class="btn" value="停止播放" /> 
							<input type="button" id="speed" class="btn" value="播放速度" />
						</select>
					</div>
					<div class="time">
						<ul id="timeul">
							<li><a href="javaScript:" rel="1">1秒</a></li>
							<li><a href="javaScript:" rel="5">5秒</a></li>
							<li><a href="javaScript:" rel="15">15秒</a></li>
							<li><a href="javaScript:" rel="30">30秒</a></li>
							<li><a href="javaScript:" rel="60">60秒</a></li>
						</ul>
					</div>
				</div>
			
			
			</div>
		</div>
	</div>

	<div class="footer">
		<div class="container pr">
			<div id="link"></div>
			<div id="footer"></div>
			<script language="javaScript" src="/js/jquery.js"></script>
			<script language="javaScript" src="/js/public.js"></script>
			<script type="text/javascript" src="/js/js_chess/flash.js"></script>
<script type="text/javascript">
<!--
$.get("/view", { 'obj' : 'manual', 'id' : ${chess.chessId} });
//-->
</script>		
