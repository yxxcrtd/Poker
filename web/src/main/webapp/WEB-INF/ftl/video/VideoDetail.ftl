<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<div id="seo"></div>
		<link rel="stylesheet" type="text/css" href="../css/style.css" />
	</head>
	
	<body class="page-video_article" banner="1">
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

		<div class="box-play">
			<div class="container c">
				<p class="location">
					首页<em>></em>
					视频专区<em>></em>
					${video.videoTitle}
				</p>
				<div class="play-video fl" id="play-video"></div>
				<div class="play-list fr">
					<p class="tit">播放列表</p>
					<ul class="ul-list mc">
						<li>
							<a href="/upload/video${video.videoId}.html" vid="q0011iyvdam">
								<img width="115" height="65" src="/upload/${video.videoPicture}" />
								<span class="blank pa">${video.videoDuring}</span>
								<em class="pa">正在播放</em>
								<p>${video.videoTitle}</p>
								<i class="sp"></i>${video.videoHit}
							</a>
						</li>
						<#if (list?? && 0 < list?size)>
							<#list list as l>
								<#if (l.videoId != video.videoId)>
									<li>
										<a href="/upload/video${l.videoId}.html" vid="q0011iyvdam">
											<img width="115" height="65" src="/upload/${l.videoPicture}" />
											<span class="blank pa">${l.videoDuring}</span>
											<em class="pa">正在播放</em>
											<p>${l.videoTitle}</p>
											<i class="sp"></i>${l.videoHit}
										</a>
									</li>
									<#if (9 == l_index)><#break /></#if>
								</#if>
							</#list>
						</#if>
					</ul>
				</div>
			</div>
		</div>

		<div class="footer">
			<div class="container pr">
				<div id="link"></div>
				<div id="footer"></div>
<script language="javaScript" src="../js/milo.js"></script>
<script language="javaScript" src="../js/jquery.js"></script>
<script language="javaScript" src="../js/public.js"></script>
<script language="javaScript" src="../js/tvp.player.js"></script>
<script type="text/javascript">
<!--
// 视频播放列表点击
$(".ul-list a").each(function(i) {
	$(this).click(function() {
		$(".ul-list a").removeClass("on");
		$(".ul-list a").eq(i).addClass("on");
		videoPlay($(".ul-list a").eq(i).attr("vid"));
	});
});
$(".ul-list a").eq(0).click();

// 判断IE
function IE() {
	return $.browser.msie ? true : false;
}
// 判断IE6
function IE6() {
	// return $.browser.msie && $.browser.version=="6.0" ?true:false;
	return false;
}

// 滚动
function scrollTo(n, s) {
	$("html,body").stop(true).animate({
		scrollTop : (n ? (isNaN(n) ? $(n).offset().top : n) : 0)
	}, s)
	// killyesterday
}

videoPlay("${video.videoUrl}");
// 视频
function videoPlay(vid) {
	var video = new tvp.VideoInfo();
	video.setVid(vid);
	var player = new tvp.Player("100%", "100%");  // 初始化播放器对象并设置宽、高
	player.setCurVideo(video);                   // 设置播放器初始化时加载的视频
// 	player.addParam('type', '1');                 // 设置播放器为直播状态，1表示直播，2表示点播，默认为2
	player.addParam("wmode", "opaque");     	 // 设置透明化，不设置时，视频为最高级，总是处于页面的最上面，此时设置z-index无效
	player.addParam('autoplay', "1");           // 是否自动播放，1为自动播放，0为不自动播放
// 	player.addParam('pic', '');                   // 播放器默认图，当autoplay=0时有效；不传入则使用视频截图                                  
	player.addParam('showend', 0)                 // 结束时是否有广告
// 	player.addParam("flashskin", "http://imgcache.qq.com/minivideo_v1/vd/res/skins/TencentPlayerMiniSkin.swf"); // flash播放器皮肤；不传入则使用默认皮肤；点播状态下可选值（注意仅限点播，直播无效）,这里是使视频窗口为小窗口
// 	player.addParam("loadingswf", "http://imgcache.qq.com/minivideo_v1/vd/res/skins/web_small_loading.swf");   // 加载视频时的swf动画; 不传入则使用默认样式
	player.write("play-video");                   // 输出播放器
}

milo.ready(function() {
	need("biz.login-min", function(LoginManager) {
		LoginManager.checkLogin(function() {
			g("login_qq_span").innerHTML = LoginManager.getUserUin();// 获取QQ号
		});
	});
});
$.get("/view", { 'obj' : 'video', 'id' : ${video.videoId} });
//-->
</script>	
				