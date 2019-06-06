<#include "../Obj.ftl" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<div id="seo"></div>
		<link rel="stylesheet" type="text/css" href="./css/style.css" />
	</head>
	
	<body class="page-video" banner="5">
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
					<a href="/news.html">新闻动态</a>
					<a href="/game.html">棋牌游戏库</a>
					<a href="/match.html">赛事专区</a>
					<a href="/action.html">最新活动</a>
					<a href="/video.html" id="current">视频专区</a>
					<a href="/class.html">棋牌学堂</a>
				</div>
			</div>
		</div>

		<div class="box-hot c">
			<div class="hot fl pr" id="ad_video"></div>
			<ul class="ul-video-sml fr">
				<#if (list?? && 0 < list?size)>
					<#list list as l>
						<#if (3 < l_index)>
							<li>
								<a href="/upload/video${l.videoId}.html" target="_blank"><img width="190" height="110" src="/upload/${l.videoPicture}" /><i class="pa"></i></a>
								<p><em>${l.videoTitle}</em>腾讯棋牌品牌刷新广告</p>
							</li>
						</#if>
						<#if (7 == l_index)><#break /></#if>
					</#list>
				</#if>
			</ul>
		</div>
	</div>
</div>
<div class="box-video">
	<div class="container">
		<div class="tit pr">
			赛事回放
			<a class="btn-more sp pa" href="/upload/video_list1.html" target="_blank">查看更多</a>
		</div>
		<ul class="ul-video c">
			<#if (category_1_list?? && 0 < category_1_list?size)>
				<#list category_1_list as l>
					<li>
						<a href="/upload/video${l.videoId}.html" target="_blank"><img width="320" height="170" src="/upload/${l.videoPicture}" /><i class="pa"></i></a>
						<p>
							<em>${l.videoTitle}</em>
							${l.videoSummary}
						</p>
					</li>
					<#if (5 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
	</div>
</div>
<div class="box-video box-video2">
	<div class="container">
		<div class="tit pr">
			视频教学
			<p class="nav pa">
				<a class="on" href="javascript:;">德州扑克</a>
				<a href="javascript:;">中国象棋</a>
				<a href="javascript:;">斗地主</a>
				<a href="javascript:;">其他</a>
			</p>
		</div>
		<ul class="ul-video c" style="display: block;">
			<#if (category_3_list?? && 0 < category_3_list?size)>
				<#list category_3_list as l>
					<li>
						<a href="/upload/video${l.videoId}.html" target="_blank"><img width="320" height="170" src="/upload/${l.videoPicture}" /><i class="pa"></i></a>
						<p>
							<em>${l.videoTitle}</em>
							${l.videoSummary}
						</p>
					</li>
					<#if (5 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
		<ul class="ul-video c">
			<#if (category_4_list?? && 0 < category_4_list?size)>
				<#list category_4_list as l>
					<li>
						<a href="/upload/video${l.videoId}.html" target="_blank"><img width="320" height="170" src="/upload/${l.videoPicture}" /><i class="pa"></i></a>
						<p>
							<em>${l.videoTitle}</em>
							${l.videoSummary}
						</p>
					</li>
					<#if (5 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
		<ul class="ul-video c">
			<#if (category_5_list?? && 0 < category_5_list?size)>
				<#list category_5_list as l>
					<li>
						<a href="/upload/video${l.videoId}.html" target="_blank"><img width="320" height="170" src="/upload/${l.videoPicture}" /><i class="pa"></i></a>
						<p>
							<em>${l.videoTitle}</em>
							${l.videoSummary}
						</p>
					</li>
					<#if (5 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
		<ul class="ul-video c">
			<#if (category_6_list?? && 0 < category_6_list?size)>
				<#list category_6_list as l>
					<li>
						<a href="/upload/video${l.videoId}.html" target="_blank"><img width="320" height="170" src="/upload/${l.videoPicture}" /><i class="pa"></i></a>
						<p>
							<em>${l.videoTitle}</em>
							${l.videoSummary}
						</p>
					</li>
					<#if (5 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
	</div>
</div>

		<div class="footer">
			<div class="container pr">
				<div id="link"></div>
				<div id="footer"></div>
				<script language="javaScript" src="./js/milo.js"></script>
				<script language="javaScript" src="./js/jquery.js"></script>
				<script language="javaScript" src="./js/public.js"></script>
				<script language="javaScript" src="./js/jquery.slideBox.js"></script>

<script type="text/javascript">
<!--
// 精彩视频
$(".ul-video li").each(function(i) {
	if (i % 3 == 0) {
		$(".ul-video li").eq(i - 1).addClass("fix");
	}
});
// 视频切换
$(".box-video2 .nav a").each(function(i) {
	$(this).click(function() {
		$(".box-video2 .nav a").removeClass("on");
		$(".box-video2 .nav a").eq(i).addClass("on");
		$(".box-video2 .ul-video").hide();
		$(".box-video2 .ul-video").eq(i).show();
	});
});
//-->
</script>