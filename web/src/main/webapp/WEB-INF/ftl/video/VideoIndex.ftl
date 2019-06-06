<div class="box-video">
	<div class="container">
		<div class="tit pr">
			精彩视频
			<p class="nav pa">
				<a class="on" href="javascript:;">赛事直播</a>
				<a href="javascript:;">赛事回放</a>
				<a href="javascript:;">专题栏目</a>
			</p>
		</div>
		<ul class="ul-video c" style="display: block;">
			<#if (category_0_list?? && 0 < category_0_list?size)>
				<#list category_0_list as l>
					<li>
						<a href="/upload/video${l.videoId}.html" target="_blank"><img width="235" height="170" src="/upload/${l.videoPicture}" /><i class="pa" style="width: 235px; background-image: url('../images/video2.png');"></i></a>
						<p>
							<em>${l.videoTitle}</em>
							${l.videoSummary}
						</p>
					</li>
					<#if (3 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
		<ul class="ul-video c">
			<#if (category_1_list?? && 0 < category_1_list?size)>
				<#list category_1_list as l>
					<li>
						<a href="/upload/video${l.videoId}.html" target="_blank"><img width="235" height="170" src="/upload/${l.videoPicture}" /><i class="pa" style="width: 235px; background-image: url('../images/video2.png');"></i></a>
						<p>
							<em>${l.videoTitle}</em>
							${l.videoSummary}
						</p>
					</li>
					<#if (3 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
		<ul class="ul-video c">
			<#if (category_2_list?? && 0 < category_2_list?size)>
				<#list category_2_list as l>
					<li>
						<a href="/upload/video${l.videoId}.html" target="_blank"><img width="235" height="170" src="/upload/${l.videoPicture}" /><i class="pa" style="width: 235px; background-image: url('../images/video2.png');"></i></a>
						<p>
							<em>${l.videoTitle}</em>
							${l.videoSummary}
						</p>
					</li>
					<#if (3 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
	</div>
</div>
<script type="text/javascript">
<!--
// 精彩视频
$(".ul-video li").each(function(i) {
	if (i % 3 == 0) {
		$(".ul-video li").eq(i - 1).removeClass("fix");
	}
	if (i % 4 == 0) {
		$(".ul-video li").eq(i - 1).addClass("fix");
	}
});
// 精彩视频切换
$(".box-video .nav a,.box-video2 .nav a").each(function(i) {
	$(this).click(function() {
		$(".box-video .nav a, .box-video2 .nav a").removeClass("on");
		$(".box-video .nav a, .box-video2 .nav a").eq(i).addClass("on");
		$(".ul-video, .box-video2 .ul-video").hide();
		$(".ul-video, .box-video2 .ul-video").eq(i).show();
	});
});
//-->
</script>