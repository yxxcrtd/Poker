<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<div id="seo"></div>
	<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>

<body class="page-game_other" banner="2">
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
					<a  href="/news.html">新闻动态</a>
					<a id="current" href="/game.html">棋牌游戏库</a>
					<a  href="/match.html">赛事专区</a>
					<a  href="/action.html">最新活动</a>
					<a  href="/video.html">视频专区</a>
					<a  href="/class.html">棋牌学堂</a>
				</div>
		</div>
	</div>
	
	<div class="game-other c">
		<div class="fl">
			<p class="tit">${game.gameTitle}</p>
			<div class="other-info">
				<img class="mc" width="100" height="100" src="/upload/${game.gamePicture}" />
				<p>${game.gameHelp}</p>
				<a class="btn1" href="http://${game.gameOfficialUrl}" target="_blank"><i class="sp"></i>进入官网</a> <a
					class="btn2" href="http://${game.gameLoadUrl}" target="_blank"><i class="sp"></i>下载游戏</a>
			</div>
		</div>
		<div class="fr">
			<p class="tit">精品推荐</p>
			<div class="other-show">
				<ul class="ul-other">
					<#if (list?? && 0 < list?size)>
						<#list list as l>
							<li><a href="/upload/game${l.gameId}.html" target="_blank"> <img width="65"
							height="65" src="/upload/${l.gamePicture}" /> <em>${l.gameTitle}</em>
							25889999人在玩
							</a></li>
							<#if (5 == l_index)><#break /></#if>
						</#list>
					</#if>
				</ul>
			</div>
		</div>
	</div>

	<div class="game-show">
		<div class="container">
			<div class="show c pr">
				<p class="blank2 pa"></p>
				<#if (pic_list?? && 0 < pic_list?size)>
					<#list pic_list as l>
						<a <#if (0 == l_index)>class="on"</#if> href="<#if (!l.pictureUrl?starts_with('http'))>http://</#if>${l.pictureUrl}" target="_blank" alt="${l.pictureAlt}"> <em>0${l_index+1}</em> ${l.pictureAlt}
						<i class="sp"></i> <img class="pa" width="405" height="295" src="/upload/${l.pictureUri}" />
						</a>
						<#if ( 5== l_index)>
							<#break />
						</#if>
					</#list>
				<#else>
					</a> <a href="javascript:;"> <em>01</em> 极致精美的<br />3D人设 <i
						class="sp"></i> <img class="pa" width="405" height="295"
						src="/images/test29.jpg" />
					</a> <a class="on" href="javascript:;"> <em>02</em> 极致精美的<br />3D人设 <i
						class="sp"></i> <img class="pa" width="405" height="295"
						src="/images/test29.jpg" />
					</a> <a href="javascript:;"> <em>03</em> 极致精美的<br />3D人设 <i
						class="sp"></i> <img class="pa" width="405" height="295"
						src="/images/test29.jpg" />
					</a> <a href="javascript:;"> <em>04</em> 极致精美的<br />3D人设 <i
						class="sp"></i> <img class="pa" width="405" height="295"
						src="/images/test29.jpg" />
					</a> <a href="javascript:;"> <em>05</em> 极致精美的<br />3D人设 <i
						class="sp"></i> <img class="pa" width="405" height="295"
						src="/images/test29.jpg" />
					</a> <a href="javascript:;"> <em>06</em> 极致精美的<br />3D人设 <i
						class="sp"></i> <img class="pa" width="405" height="295"
						src="/images/test29.jpg" />
					</a>
				</#if>
			</div>
		</div>
	</div>

	<div class="footer">
		<div class="container pr">
			<div id="link"></div>
			<div id="footer"></div>
			<script language="javaScript" src="/js/jquery.js"></script>
			<script language="javaScript" src="/js/public.js"></script>
