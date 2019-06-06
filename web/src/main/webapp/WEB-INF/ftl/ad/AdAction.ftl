<div class="slideBox">
	<ul class="items">
	  	<#if (actionList?? && 0 < actionList?size)>
			<#list actionList as l>
		    	<li>
		    		<a href="<#if (!l.adUrl?starts_with('http'))>http://</#if>${l.adUrl}" title="${l.adTitle}" target="_blank">
		    			<img width="580" height="385" src="/upload/${l.adPicture}">
		    		</a>
		    	</li>
				<#if (3 == l_index)><#break /></#if>
			</#list>
		</#if>
	</ul>
</div>
<script type="text/javascript"><!--$(".slideBox").slideBox({});//--></script>
<#--
<div class="outer pa">
	<#if (actionList?? && 0 < actionList?size)>
		<#list actionList as l>
			<a class="hot-link" href="<#if (!l.adUrl?starts_with('http'))>http://</#if>${l.adUrl}" target="_blank"	title="${l.adTitle}">
				<img width="580" height="385" src="/upload/${l.adPicture}" />
			</a>
			<#if (3 == l_index)><#break /></#if>
		</#list>
	</#if>
</div>
<div class="tit blank pa">
	<a class="play-link" href="javascript:;"></a>
	<div class="dot c pa"></div>
</div>
<script type="text/javascript">
//热门列表效果
var hotI = 0;// 热门列表ID
var hotL = $(".box-hot .hot-link").length;// 获取热门列表数量
var hotH = $(".box-hot .hot-link").height();// 获取热门列表高度
$(".box-hot .play-link").html($(".box-hot .hot-link").eq(0).attr("title"));// 获取第一位标题并显示
for (var i = 0; i < hotL; i++) { // 智能添加小圆点
	$(".box-hot .dot").append("<a href='javascript:;'></a>");
}
$(".box-hot .dot a").eq(0).addClass("on");// 点亮第一位
$(".box-hot .outer").css("height", hotL * hotH + "px");// 智能改变容器宽度
$(".box-hot .dot a").each(function(i) {// 点击小圆点
	$(this).mouseover(function() {
		hotI = i;
		hotGo();
	});
});
function hotGo() {
	$(".box-hot .outer").stop(true).animate({
		top : '-' + hotI * hotH + 'px'
	});
	$(".box-hot .play-link").hide().html(
			$(".box-hot .hot-link").eq(hotI).attr("title")).stop(true)
			.slideDown("slow");
	$(".box-hot .dot a").removeClass("on");
	$(".box-hot .dot a").eq(hotI).addClass("on");
}
</script>
-->