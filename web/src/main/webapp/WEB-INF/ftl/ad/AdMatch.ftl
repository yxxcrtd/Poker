<#if (matchList?? && 0 < matchList?size)>
	<#list matchList as l>
		<a href="<#if (!l.adUrl?starts_with('http'))>http://</#if>${l.adUrl}" target="_blank" title="${l.adTitle}">
			<img width="310" height="210" src="/upload/${l.adPicture}" />
		</a>
		<#if (2 == l_index)><#break /></#if>
	</#list>
</#if>
<script type="text/javascript">
<!--
$(".match-link a").each(function(i) {
	if (i % 3 == 0) {
		$(".match-link a").eq(i - 1).addClass("fix");
	}
});
//-->
</script>