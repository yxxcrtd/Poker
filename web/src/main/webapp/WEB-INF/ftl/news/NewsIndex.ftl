<#include "../Obj.ftl" />
<div class="container c">
	<div class="news fl">
		<div class="tit pr">
			<em>新闻资讯</em>
			<p class="nav pa">
				<a class="on" href="javascript:;">德州扑克</a>
				<a href="javascript:;">斗地主</a>
				<a href="javascript:;">麻将</a>
				<a href="javascript:;">其他</a>
			</p>
		</div>
		<ul class="ul-news c mc" style="display: block;">
			<#if (category_1_list?? && 0 < category_1_list?size)>
				<#list category_1_list as l>
					<#if (0 == l_index)>
						<li class="headline pr"><a href="/upload/news${l.newsId}.html" target="_blank"><img class="pa" width="104" height="88" src="/upload/${l.newsPicture}" /><em title="${l.newsTitle}"><#if (18 < l.newsTitle?length)>${l.newsTitle[0..17]}<#else>${l.newsTitle}</#if></em>${l.newsSummary}</a></li>
					<#else>
						<li><a class="fl" href="/upload/news${l.newsId}.html" target="_blank"><em class="tag tag1"><@compress single_line=true><@getNewsType l.newsType /></@compress></em>${l.newsTitle}</a><span class="fr">${l.newsCreateTime?string("MM/dd")}</span></li>
					</#if>
					<#if (5 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
		<ul class="ul-news c mc">
			<#if (category_2_list?? && 0 < category_2_list?size)>
				<#list category_2_list as l>
					<#if (0 == l_index)>
						<li class="headline pr"><a href="/upload/news${l.newsId}.html" target="_blank"><img class="pa" width="104" height="88" src="/upload/${l.newsPicture}" /><em title="${l.newsTitle}"><#if (18 < l.newsTitle?length)>${l.newsTitle[0..17]}<#else>${l.newsTitle}</#if></em>${l.newsSummary}</a></li>
					<#else>
						<li><a class="fl" href="/upload/news${l.newsId}.html" target="_blank"><em class="tag tag1"><@compress single_line=true><@getNewsType l.newsType /></@compress></em>${l.newsTitle}</a><span class="fr">${l.newsCreateTime?string("MM/dd")}</span></li>
					</#if>
					<#if (5 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
		<ul class="ul-news c mc">
			<#if (category_3_list?? && 0 < category_3_list?size)>
				<#list category_3_list as l>
					<#if (0 == l_index)>
						<li class="headline pr"><a href="/upload/news${l.newsId}.html" target="_blank"><img class="pa" width="104" height="88" src="/upload/${l.newsPicture}" /><em title="${l.newsTitle}"><#if (18 < l.newsTitle?length)>${l.newsTitle[0..17]}<#else>${l.newsTitle}</#if></em>${l.newsSummary}</a></li>
					<#else>
						<li><a class="fl" href="/upload/news${l.newsId}.html" target="_blank"><em class="tag tag1"><@compress single_line=true><@getNewsType l.newsType /></@compress></em>${l.newsTitle}</a><span class="fr">${l.newsCreateTime?string("MM/dd")}</span></li>
					</#if>
					<#if (5 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
		<ul class="ul-news c mc">
			<#if (category_9_list?? && 0 < category_9_list?size)>
				<#list category_9_list as l>
					<#if (0 == l_index)>
						<li class="headline pr"><a href="/upload/news${l.newsId}.html" target="_blank"><img class="pa" width="104" height="88" src="/upload/${l.newsPicture}" /><em title="${l.newsTitle}"><#if (18 < l.newsTitle?length)>${l.newsTitle[0..17]}<#else>${l.newsTitle}</#if></em>${l.newsSummary}</a></li>
					<#else>
						<li><a class="fl" href="/upload/news${l.newsId}.html" target="_blank"><em class="tag tag1"><@compress single_line=true><@getNewsType l.newsType /></@compress></em>${l.newsTitle}</a><span class="fr">${l.newsCreateTime?string("MM/dd")}</span></li>
					</#if>
					<#if (5 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
	</div>
	<div class="event fr">
		<div class="tit pr">
			<em>赛事日程</em>
			<a class="btn-more sp pa" href="match.html">查看更多</a>
		</div>
		<div id="index_match"></div>
	</div>
</div>
<script type="text/javascript">
<!--
$(function() {
	$("#index_match").load("/upload/index_match.html");
});
$(".box-news .nav a").each(function(i) {
	$(this).click(function() {
		$(".box-news .nav a").removeClass("on");
		$(".box-news .nav a").eq(i).addClass("on");
		$(".ul-news").hide();
		$(".ul-news").eq(i).show();
	});
});
//-->
</script>