<div class="box-section pr">
	<div class="outer pa">
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<div class="section pr">
					<img src="/upload/${l.adPicture}" width="1000" height="410" alt="${l.adTitle}" />
					<p class="tit pa">
						<em>${l.adTitle}</em>
						${l.adSummary}
						<span>${l.adContent}</span>
						<a class="btn-detail" href="<#if (!l.adUrl?starts_with('http'))>http://</#if>${l.adUrl}" target="_blank">了解详情</a>
					</p>
				</div>
			</#list>
		</#if>
	</div>
	<div class="dot c pa"></div>
</div>
<script type="text/javascript">
<!--
// 轮播图滚动
var sectionI = 0; // 轮播图ID
var sectionL = $(".box-section .section").length; // 获取轮播图数量
var sectionW = $(".box-section .section").width(); // 获取轮播图宽度
for(var i = 0; i < sectionL; i++) { // 智能添加小圆点
	$(".box-section .dot").append("<a class='disc' href='javascript:;'></a>");
}
$(".box-section .dot a").eq(0).addClass("on"); // 点亮第一位
$(".box-section .dot").css("margin-left", "-" + $(".box-section .dot").width() / 2 + "px"); // 小圆点修正
$(".box-section .outer").css("width",sectionL*sectionW+"px"); // 智能改变容器宽度
setTimeout("javascript:sectionGo(0);", 10000); // 开始滚动轮播图
$(".box-section .dot a").each(function(i) { // 点击小圆点
	$(this).click(function() {
		sectionI = i;
		sectionGo();
	});
});
//-->
</script>