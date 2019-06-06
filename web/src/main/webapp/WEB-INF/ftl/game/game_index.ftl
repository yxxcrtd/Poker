<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<div id="seo"></div>
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
</head>

<body class="page-game" banner="2">
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
					<a href="/news.html">新闻动态</a>
					<a href="/game.html" id="current">棋牌游戏库</a>
					<a href="/match.html">赛事专区</a>
					<a href="/action.html">最新活动</a>
					<a href="/video.html">视频专区</a>
					<a href="/class.html">棋牌学堂</a>
				</div>
		</div>
	</div>
	
	<div class="game-comm c">
		<p class="tit">棋牌游戏库</p>
		<div class="game-intro fl pr">
			<p>腾讯棋牌旗下有超过130款棋牌游戏，覆盖电脑、手机、pad和电视多个终端，包括欢乐斗地主、欢乐麻将、天天德州等精品棋牌游戏单品，以及QQ游戏大厅下的棋牌游戏。</p>
				<#if (game_comm_list?? && 4 < game_comm_list?size)>
					<a class="arrow left sp pa" href="javascript:;"></a>
					<a class="arrow right sp pa" href="javascript:;"></a>
				</#if>
				<ul class="ul-ico c mc">
					<#if (game_comm_list?? && 0 < game_comm_list?size)>
						<#list game_comm_list as l>
							<li tabindex="${((l_index+1)/4)?ceiling}" <#if (3 < l_index)>style="display:none;"</#if>><a class="mc" href="/upload/game${l.gameId}.html" target="_blank"><img width="90" height="90" src="/upload/${l.gamePicture}" /><em>${l.gameTitle}</em></a></li>
						</#list>
					</#if>
				</ul>
				
		</div>
		<div class="game-pic fr pr" id="ad_game">
		</div>
	</div>
	<div class="game-list">
		<div class="container">
			<div class="tit pr">
				<p class="nav">
					<a class="on" href="javascript:;">棋类</a> <a href="javascript:;">牌类</a>
					<a href="javascript:;">麻将</a> <a href="javascript:;">其他</a>
				</p>
			</div>
			<!-- 棋类 -->
			<div class="library c" style="display: block;">
				<div class="outer" style="display: block;">
					<#if (game_1_list?? && 0 < game_1_list?size)>
						<#list game_1_list as l>
							<a href="/upload/game${l.gameId}.html" target="_blank"> <img width="100" height="100"
								src="/upload/${l.gamePicture}" title="${l.gameTitle}" />
							</a>
						<#if (3 == l_index)><#break /></#if>
						</#list>
					</#if>
				</div>
			</div>
			<!-- 牌类 -->
			<div class="library c" >
				<div class="outer" style="display: block;">
					<#if (game_2_list?? && 0 < game_2_list?size)>
						<#list game_2_list as l>
							<a href="/upload/game${l.gameId}.html" target="_blank"> <img width="100" height="100"
								src="/upload/${l.gamePicture}" title="${l.gameTitle}" />
							</a>
						<#if (3 == l_index)><#break /></#if>
						</#list>
					</#if>
				</div>
			</div>
			<!-- 麻将 -->
			<div class="library c">
				<div class="outer" style="display: block;">
					<#if (game_3_list?? && 0 < game_3_list?size)>
						<#list game_3_list as l>
							<a href="/upload/game${l.gameId}.html" target="_blank"> <img width="100" height="100"
								src="/upload/${l.gamePicture}" title="${l.gameTitle}" />
							</a>
						<#if (3 == l_index)><#break /></#if>
						</#list>
					</#if>
				</div>
			</div>
			<!-- 其他 -->
			<div class="library c">
				<div class="outer" style="display: block;">
					<#if (game_9_list?? && 0 < game_9_list?size)>
						<#list game_9_list as l>
							<a href="/upload/game${l.gameId}.html" target="_blank"> <img width="100" height="100"
								src="/upload/${l.gamePicture}" title="${l.gameTitle}" />
							</a>
						<#if (3 == l_index)><#break /></#if>
						</#list>
					</#if>
				</div>
			</div>
		</div>
	</div>

	<div class="footer">
		<div class="container pr">
			<div id="link"></div>
			<div id="footer"></div>
			<script language="javaScript" src="./js/jquery.js"></script>
			<script language="javaScript" src="./js/public.js"></script>
			<script language="javaScript" src="./js/jquery.slideBox.js"></script>