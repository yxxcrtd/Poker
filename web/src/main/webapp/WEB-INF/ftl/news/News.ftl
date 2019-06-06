<#include "../Obj.ftl" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<div id="seo"></div>
		<link rel="stylesheet" type="text/css" href="./css/style.css" />
	</head>
	
	<body class="page-news" banner="1">
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
					<a href="news.html" id="current">新闻动态</a>
					<a href="game.html">棋牌游戏库</a>
					<a href="match.html">赛事专区</a>
					<a href="action.html">最新活动</a>
					<a href="video.html">视频专区</a>
					<a href="class.html">棋牌学堂</a>
				</div>
			</div>
		</div>

		<div class="box-hot c">
			<div class="hot fl pr" id="ad_news"></div>
			<div class="news fr">
				<div class="tit pr">今日头条<a class="btn-more sp pa" href="/upload/news_list.html">查看更多</a></div>
				<ul class="ul-news mc">
					<#if (list?? && 0 < list?size)>
						<#list list as l>
							<#if (0 == l_index)>
								<li class="topline"><a href="/upload/news${l.newsId}.html" target="_blank">${l.newsTitle}</a></li>
							<#else>
								<li><a href="/upload/news${l.newsId}.html" target="_blank"><em class="tag tag1"><@compress single_line=true><@getNewsType l.newsType /></@compress></em>${l.newsTitle}</a></li>
							</#if>
							<#if (5 == l_index)><#break /></#if>
						</#list>
					</#if>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="rank">
	<div class="container c">
		<div class="new-comm fl">
			<p class="tit">腾讯棋牌综合新闻</p>
			<ul class="ul-news-comm">
				<#if (category_0_list?? && 0 < category_0_list?size)>
					<#list category_0_list as l>
						<li><a href="/upload/news${l.newsId}.html" target="_blank"><img class="pa" width="104" height="70" src="/upload/${l.newsPicture}" /><em>${l.newsTitle}</em>${l.newsSummary}</a></li>
						<#if (3 == l_index)><#break /></#if>
					</#list>
				</#if>
			</ul>
		</div>
		<div class="box-rank fr pr">
			<p class="line pa"></p>
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
</div>
<div class="column">
	<div class="container c">
		<div class="outer">
			<div class="box-column type1 fl">
				<div class="tit pr">
					<i class="sp pa"></i>德州扑克
					<a class="btn-more sp pa" href="/upload/news_list1.html">查看更多</a>
				</div>
				<ul class="ul-link">
					<#if (category_1_list?? && 0 < category_1_list?size)>
						<#list category_1_list as l>
							<#if (0 == l_index)>
								<li class="headline"><a href="/upload/news${l.newsId}.html" target="_blank"><img width="440" height="120" src="/upload/${l.newsPicture}" /><p class="blank pa">${l.newsTitle}</p></a></li>
							<#else>
								<li><i></i><a href="/upload/news${l.newsId}.html" target="_blank">${l.newsTitle}</a></li>
							</#if>
							<#if (4 == l_index)><#break /></#if>
						</#list>
					</#if>
				</ul>
			</div>
			<div class="box-column type2 fr">
				<div class="tit pr">
					<i class="sp pa"></i>斗地主
					<a class="btn-more sp pa" href="/upload/news_list2.html">查看更多</a>
				</div>
				<ul class="ul-link">
					<#if (category_2_list?? && 0 < category_2_list?size)>
						<#list category_2_list as l>
							<#if (0 == l_index)>
								<li class="headline"><a href="/upload/news${l.newsId}.html" target="_blank"><img width="440" height="120" src="/upload/${l.newsPicture}" /><p class="blank pa">${l.newsTitle}</p></a></li>
							<#else>
								<li><i></i><a href="/upload/news${l.newsId}.html" target="_blank">${l.newsTitle}</a></li>
							</#if>
							<#if (4 == l_index)><#break /></#if>
						</#list>
					</#if>
				</ul>
			</div>
		</div>
		<div class="outer">
			<div class="box-column type3 fl">
				<div class="tit pr">
					<i class="sp pa"></i>棋类
					<a class="btn-more sp pa" href="/upload/news_list4.html">查看更多</a>
				</div>
				<ul class="ul-link">
					<#if (category_4_list?? && 0 < category_4_list?size)>
						<#list category_4_list as l>
							<#if (0 == l_index)>
								<li class="headline"><a href="/upload/news${l.newsId}.html" target="_blank"><img width="440" height="120" src="/upload/${l.newsPicture}" /><p class="blank pa">${l.newsTitle}</p></a></li>
							<#else>
								<li><i></i><a href="/upload/news${l.newsId}.html" target="_blank">${l.newsTitle}</a></li>
							</#if>
							<#if (4 == l_index)><#break /></#if>
						</#list>
					</#if>
				</ul>
			</div>
			<div class="box-column type4 fr">
				<div class="tit pr">
					<i class="sp pa"></i>麻将
					<a class="btn-more sp pa" href="/upload/news_list3.html">查看更多</a>
				</div>
				<ul class="ul-link">
					<#if (category_3_list?? && 0 < category_3_list?size)>
						<#list category_3_list as l>
							<#if (0 == l_index)>
								<li class="headline"><a href="/upload/news${l.newsId}.html" target="_blank"><img width="440" height="120" src="/upload/${l.newsPicture}" /><p class="blank pa">${l.newsTitle}</p></a></li>
							<#else>
								<li><i></i><a href="/upload/news${l.newsId}.html" target="_blank">${l.newsTitle}</a></li>
							</#if>
							<#if (4 == l_index)><#break /></#if>
						</#list>
					</#if>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="news-other">
	<div class="container c">
		<div class="tit pr">
			其他新闻
			<a class="btn-more sp pa" href="/upload/news_list9.html">查看更多</a>
		</div>
		<div class="pic-link fl">
			<#if (category_9_list?? && 0 < category_9_list?size)>
				<#list category_9_list as l>
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
			<#if (category_9_list?? && 0 < category_9_list?size)>
				<#list category_9_list as l>
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

		<div class="footer">
			<div class="container pr">
				<div id="link"></div>
				<div id="footer"></div>
				<script language="javaScript" src="./js/jquery.js"></script>
				<script language="javaScript" src="./js/public.js"></script>
				<script language="javaScript" src="./js/jquery.slideBox.js"></script>
