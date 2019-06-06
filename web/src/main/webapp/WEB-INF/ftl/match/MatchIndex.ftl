<ul class="ul-event">
	<#if (list?? && 0 < list?size)>
		<#list list as l>
			<li>
				<a href="match.html"><img class="pa" width="70" height="70" src="/upload/${l.matchPicture}" />
					<em title="${l.matchName}"><#if (16 < l.matchName?length)>${l.matchName[0..15]}<#else>${l.matchName}</#if></em>
					时间：${l.matchStartDate}-${l.matchEndDate}<br />
					地点：${l.matchAddress}
				</a>
			</li>
			<#if (3 == l_index)><#break /></#if>
		</#list>
	</#if>
</ul>
<script type="text/javascript">
<!--
$(function() {
	$(".ul-event a").each(function(i) {
		var tempImg = "";
		$(this).mouseover(function() {
			tempImg = $(".ul-event img").eq(i).attr("src");
			$(".ul-event img").eq(i).attr("src", "images/event.jpg");
		});
		$(this).mouseout(function() {
			$(".ul-event img").eq(i).attr("src", tempImg);
		});
	});
});
//-->
</script>