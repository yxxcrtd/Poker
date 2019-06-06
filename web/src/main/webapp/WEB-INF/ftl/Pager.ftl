<#if pager??>
	<#assign pageCount = pager.totalPageCount>
	<#if (1 < pageCount)>
		<#assign offset = 5>
		<#assign current = pager.pageNo>
		<#assign maxStart = (pageCount - offset * 2)>
		<#if (1 > maxStart)>
			<#assign maxStart = 1>
		</#if>
		<#assign minEnd = (offset * 2 + 1)>
		<#assign start = (current - offset)>
		<#assign end = (current + offset)>
		<#if (1 > start)>
			<#assign start = 1>
			<#assign end = minEnd>
		</#if>
		<#if (end > pageCount)>
			<#assign end = pageCount>
			<#assign start = maxStart>
		</#if>
		<#if (1 < current)>
			<a href="javascript:go(${current - 1});" class="arrow">上一页</a>
		</#if>
		<#if (1 < start)>
			<a href="javascript:go(1);">1</a>
		</#if>
		<#if (2 < start)>
			<span>...</span>
		</#if>
		<#list start..end - 1 as i>
			<#if (i == current)>
				<a href="javascript:go(${i});" class="active">${i}</a>
			<#else>
				<a href="javascript:go(${i});">${i}</a>
			</#if>
		</#list>
		<#if (start < maxStart)>
			<span>...</span>
		</#if>
		<#if current == pageCount>
			<a href="javascript:go(${current});" class="active">${current}</a>
		<#else>
			<a href="javascript:go(${pageCount});">${pageCount}</a>
			<a href="javascript:go(${current + 1});" class="arrow">下一页</a>
		</#if>

		<script type="text/javascript">
		<!--
		function go(i) {
			window.location.href = "${pager.pageUrl}".replace("{p}", i);
		}
		//-->
		</script>
	</#if>
</#if>
