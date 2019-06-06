<#include "../Obj.ftl" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<div id="seo"></div>
		<link rel="stylesheet" type="text/css" href="./css/style.css" />
	</head>

	<body class="page-match" banner="3">
		<div class="topper"></div>
		<div class="head">
			<div class="container">
				<div class="box-search pr">
					<a class="logo sp pa" href="/" title="腾讯棋牌"></a>
					<div class="qrcode-s pa">
						<a class="btn-close sp pa" href="javascript:;" title="关闭"></a>
						<img width="91" height="91" src="images/qrcode-s.jpg" />
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
					<a <#if ("news" == cur)></#if> href="news.html">新闻动态</a>
					<a <#if ("game" == cur)></#if> href="game.html">棋牌游戏库</a>
					<a <#if ("match" == cur)>id="current"</#if> href="match.html">赛事专区</a>
					<a <#if ("activity" == cur)></#if> href="action.html">最新活动</a>
					<a <#if ("video" == cur)></#if> href="video.html">视频专区</a>
					<a <#if ("class" == cur)></#if> href="class.html">棋牌学堂</a>
				</div>
			</div>
		</div>

		<div class="match">
			<div class="container">
				<p class="match-link c" id="ad_match"></p>
				<div class="box-match" style="display: block;">
					<div class="tit pr">
						<p class="inner pa">
							<a class="on" href="javascript:;">最新赛事</a>
							<a href="javascript:;">赛事回顾</a>
						</p>
					</div>
					
					<div class="outer" style="display: block;">
						<ul class="ul-match c mc">
							<#if (list?? && 0 < list?size)>
								<#list list as l>
									<#if (2 > l.matchStatus)>
										<li <@compress single_line=true><@getStyleByStatus l.matchStatus /></@compress>>
											<img class="fl" width="340" height="220" src="/upload/${l.matchPicture}" />
											<div class="info fr pr">
												<span class="sp pa"><@compress single_line=true><@getMatchStatus l.matchStatus /></@compress></span>
												<em>${l.matchName}</em>
												<table>
													<tr class="tr1">
														<td><strong class="sp">活动时间</strong></td>
														<td>${l.matchStartDate}-${l.matchEndDate}</td>
													</tr>
													<tr>
														<td><strong class="sp">活动标题</strong></td>
														<td>${l.matchName}</td>
													</tr>
													<tr>
														<td><strong class="sp">活动介绍</strong></td>
														<td>${l.matchSummary}</td>
													</tr>
												</table>
												<a class="btn-join pa" href="<#if (l.matchUrl?? && '' != l.matchUrl)><#if (!l.matchUrl?starts_with('http'))>http://</#if>${l.matchUrl}<#else>javascript:;</#if>" target="_blank">立即参与<i class="sp"></i></a>
											</div>
										</li>
									</#if>
								</#list>
							</#if>
						</ul>
						<#--
						<p class="match-bot c">
							<a class="btn-page" href="javascript:;">上一页</a>
							<a href="javascript:;">1</a>
							<a class="on" href="javascript:;">2</a>
							<a href="javascript:;">3</a>
							<a href="javascript:;">4</a>
							...
							<a href="javascript:;">25</a>
							<a class="btn-page" href="javascript:;">下一页</a>
						</p>
						-->
					</div>
					
					<div class="outer">
						<ul class="ul-match c mc">
							<#if (list?? && 0 < list?size)>
								<#list list as l>
									<#if (2 == l.matchStatus)>
										<li <@compress single_line=true><@getStyleByStatus l.matchStatus /></@compress>>
											<img class="fl" width="340" height="220" src="/upload/${l.matchPicture}" />
											<div class="info fr pr">
												<span class="sp pa"><@compress single_line=true><@getMatchStatus l.matchStatus /></@compress></span>
												<em>${l.matchName}</em>
												<table>
													<tr class="tr1">
														<td><strong class="sp">活动时间</strong></td>
														<td>${l.matchStartDate}-${l.matchEndDate}</td>
													</tr>
													<tr>
														<td><strong class="sp">活动标题</strong></td>
														<td>${l.matchName}</td>
													</tr>
													<tr>
														<td><strong class="sp">活动介绍</strong></td>
														<td>${l.matchSummary}</td>
													</tr>
												</table>
												<a class="btn-join pa" href="javascript:;">立即参与<i class="sp"></i></a>
											</div>
										</li>
									</#if>
								</#list>
							</#if>
						</ul>
						<#--
						<p class="match-bot c">
							<a class="btn-page" href="javascript:;">上一页</a>
							<a href="javascript:;">1</a>
							<a class="on" href="javascript:;">2</a>
							<a href="javascript:;">3</a>
							<a href="javascript:;">4</a>
							...
							<a href="javascript:;">25</a>
							<a class="btn-page" href="javascript:;">下一页</a>
						</p>
						-->
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
