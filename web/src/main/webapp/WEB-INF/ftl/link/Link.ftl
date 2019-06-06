<p class="box-link pa">
	<em class="sp">友情链接</em>
	<#if (list?? && 0 < list?size)>
		<#list list as l>
			<a href="<#if (!l.linkAddress?starts_with('http'))>http://</#if>${l.linkAddress}" target="_blank" title="${l.linkName}">${l.linkName}</a><#if l_has_next>|</#if>
		</#list>
	</#if>
</p>