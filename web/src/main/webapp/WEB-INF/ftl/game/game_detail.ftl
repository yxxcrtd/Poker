<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<div id="seo"></div>
	<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>

<body class="page-game_detail" banner="2">
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
	<div class="game-comm c">
		<p class="tit">${game.gameTitle}</p>
		<div class="game-pic fl pr">
			<div class="outer pa">
			<!--广告 -->
				<#if (pic_list?? && 0 < pic_list?size)>
					<#list pic_list as l>
						<a href="<#if (!l.pictureUrl?starts_with('http'))>http://</#if>${l.pictureUrl}" target="_blank">
							<img width="545" height="320" src="/upload/${l.pictureUri}" />
						</a>
					</#list>
				</#if>
			</div>
			<a class="arrow left sp pa" href="javascript:;"></a> <a
				class="arrow right sp pa" href="javascript:;"></a>
		</div>
		<div class="game-intro fr">
			<div class="g-info c">
				<img class="fl" width="100" height="100" src="/upload/${game.gamePicture}" />
				<div class="fl">
					<em>${game.gameTitle}</em> <i class="sp"></i><i class="sp"></i><i class="sp"></i><i
						class="sp"></i><i class="sp"></i> <span>大于171万次下载 | 93.45 MB</span>
					<a class="btn-dl" href="http://${game.gameLoadUrl}"  target="_blank">下载</a>
				</div>
			</div>
			<p>${game.gameHelp}</p>
		</div>
	</div>
	
	<div class="news-other">
		<div class="container c">
			<div class="tit pr">
				新闻动态
				<a class="btn-more sp pa" href="/news.html" target="_blank">查看更多</a>
			</div>
			<div class="pic-link fl">
				<#if (news_list?? && 0 < news_list?size)>
					<#list news_list as l>
						<#if (0 == l_index)>
							<a class="big" href="/upload/news${l.newsId}.html"><img width="360" height="220" src="/upload/${l.newsPicture}" />${l.newsTitle}</a>
						<#elseif (1 == l_index)>
							<a class="fl" href="/upload/news${l.newsId}.html"><img width="170" height="120" src="/upload/${l.newsPicture}" />${l.newsTitle}</a>
						<#elseif (2 == l_index)>
							<a class="fr" href="/upload/news${l.newsId}.html"><img width="170" height="120" src="/upload/${l.newsPicture}" />${l.newsTitle}</a>
						</#if>
						<#if (2 == l_index)><#break /></#if>
					</#list>
				</#if>
			</div>
			<ul class="ul-news-other fr">
				<#if (news_list?? && 0 < news_list?size)>
					<#list news_list as l>
						<#if (2 < l_index)>
							<li>
								<a href="/upload/news${l.newsId}.html" target="_blank"><em>${l.newsTitle}</em>${l.newsSummary}</a>
								<p class="pa">${l.newsCreateTime?string("yyyy-MM-dd")}</p>
							</li>
						</#if>
						<#if (7 == l_index)><#break /></#if>
					</#list>
				</#if>
			</ul>
		</div>
	</div>
<div class="game-event">
		<div class="container c">
			<div class="game-action fl">
				<div class="tit pr">
					<div class="nav c pa">
						<a class="on" href="javascript:;">赛事信息</a> <a href="javascript:;">最新活动</a>
					</div>
					<a class="btn-more sp pa" href="/match.html" target="_blank">查看更多</a>
					<a class="btn-more sp pa" href="/action.html" target="_blank">查看更多</a>
				</div>
				<div class="box-action c" style="display: block;">
					<#if (match_list?? && 0 < match_list?size)>
						<#list match_list as l>
							<div class="fl">
								<img width="160" height="160" src="/upload/${l.matchPicture}" /> <a
									class="btn-go" href="/upload/match${l.matchId}.html" target="_blank">进入赛事页面</a>
							</div>
							<div class="fr">
								<p>
									<em><#if (12 < l.matchName?length)>${l.matchName[0..10]}<#else>${l.matchName}</#if></em> <span>活动时间： ${l.matchStartDate} —— ${l.matchEndDate}</span><!--?string("yyyy-MM-dd")!-->
									活动地点：+线上、线下Day2及直播+
								</p>
							<#break />
						</#list>
						<ul class="ul-news-act">
							<#if (match_list?? && 0 < match_list?size)>
								<#list match_list as l>
									<#if (0 != l_index)>
										<li><a href="/upload/match${l.matchId}.html" target="_blank"><i></i><#if (16 < l.matchName?length)>${l.matchName[0..15]}<#else>${l.matchName}</#if></a></li>
									</#if>
									<#if (4 == l_index)><#break /></#if>
								</#list>
							</#if>
						</ul>
					</div>
					</#if>
				</div>
				<div class="box-action c">
						<#if (action_list?? && 0 < action_list?size)>
							<div class="fl">
								<#list action_list as l>
									<li><a href="/upload/action${l.activityId}.html" target="_blank"><i></i><#if (16 < l.activityTitle?length)>${l.activityTitle[0..15]}<#else>${l.activityTitle}</#if></a></li>
								<img width="160" height="160" src="/upload/${l.activityPicture}" /> <a
									class="btn-go" href="/upload/action${l.activityId}.html" target="_blank">进入活动页面</a>
							</div>
							<div class="fr">
									<p>
										<em>${l.activityTitle}</em> <span>活动时间：${l.activityBeginDate?string("yyyy-MM-dd")!} —— ${l.activityEndDate?string("yyyy-MM-dd")!}</span>
										活动地点：+线上、线下Day2及直播+
									</p>
									<#break />
								</#list>
								<ul class="ul-news-act">
									<#if (action_list?? && 0 < action_list?size)>
										<#list action_list as l>
											<#if (0 != l_index)>
												<li><a href="/upload/action${l.activityId}.html" target="_blank"><i></i><#if (16 < l.activityTitle?length)>${l.activityTitle[0..15]}<#else>${l.activityTitle}</#if></a></li>
											</#if>
											<#if (4 == l_index)><#break /></#if>
										</#list>
									</#if>
								</ul>
							</div>
						</#if>
				</div>
			</div>
			<div class="game-news fr pr">
				<a class="btn-more sp pa" href="/class.html" target="_blank">查看更多</a>
				<p class="tit">棋牌学堂</p>
				<ul class="ul-news-act">
					<#if (tutorial_list?? && 0 < tutorial_list?size)>
						<#list tutorial_list as l>
							<li>
								<a href="/upload/tutorial${l.tutorialId}.html" target="_blank">
									<#if (16 < l.tutorialTitle?length)>${l.tutorialTitle[0..15]}<#else>${l.tutorialTitle}</#if>
								</a>
							</li>
						</#list>
					</#if>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="box-video">
		<div class="container">
			<div class="tit pr">
				赛事回放 <a class="btn-more sp pa" href="/video.html" target="_blank">查看更多</a>
			</div>
			<ul class="ul-video c">
				<#if (video_list?? && 0 < video_list?size)>
					<#list video_list as l>
						<li>
							<a href="/upload/video${l.videoId}.html" target="_blank"><img width="320"
								height="170" src="/upload/${l.videoPicture}" /><i class="pa"></i></a>
							<p>
								<em><#if (16 < l.videoTitle?length)>${l.videoTitle[0..15]}<#else>${l.videoTitle}</#if></em> 腾讯棋牌品牌刷新广告<br /> 腾讯棋牌品牌刷新广告
							</p>
						</li>
					</#list>
				</#if>
			</ul>
		</div>
	</div>
	
	<div class="footer">
		<div class="container pr">
			<div id="link"></div>
			<div id="footer"></div>
			<script language="javaScript" src="../js/jquery.js"></script>
			<script language="javaScript" src="../js/public.js"></script>
