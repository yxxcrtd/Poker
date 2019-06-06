<#include "../Obj.ftl" />
			<div class="tit c pr">
				<div class="nav c pa">
					<a class="on" href="javascript:;">德州扑克</a> <a href="javascript:;">斗地主</a>
					<a href="javascript:;">麻将</a> <a href="javascript:;">棋类</a> <a
						href="javascript:changeTutorial();">其他</a>
				</div>
				<a class="btn-more sp pa" href="/upload/tutorial_list.html" target="_blank">查看更多</a>
			</div>
			<ul class="ul-tutorial c mc" style="display: block;">
				<#if (category_1_list?? && 0 < category_1_list?size)>
					<#list category_1_list as l>
						<#if (0 == l_index)>
							<li class="headline pr"><a href="/upload/tutorial${l.tutorialId}.html" target="_blank"><em>${l.tutorialTitle}</em></a></li>
						<#else>
							<li><a class="fl" href="/upload/tutorial${l.tutorialId}.html" target="_blank">${l.tutorialTitle}</a><span class="fr">${l.tutorialCreateTime?string("MM/dd")}</span></li>
						</#if>
						<#if (4 == l_index)><#break /></#if>
					</#list>
				</#if>
			</ul>
			<ul class="ul-tutorial c mc">
				<#if (category_2_list?? && 0 < category_2_list?size)>
					<#list category_2_list as l>
						<#if (0 == l_index)>
							<li class="headline pr"><a href="/upload/tutorial${l.tutorialId}.html" target="_blank"><em>${l.tutorialTitle}</em></a></li>
						<#else>
							<li><a class="fl" href="/upload/tutorial${l.tutorialId}.html" target="_blank">${l.tutorialTitle}</a><span class="fr">${l.tutorialCreateTime?string("MM/dd")}</span></li>
						</#if>
						<#if (4 == l_index)><#break /></#if>
					</#list>
				</#if>
			</ul>
			<ul class="ul-tutorial c mc">
				<#if (category_3_list?? && 0 < category_3_list?size)>
					<#list category_3_list as l>
						<#if (0 == l_index)>
							<li class="headline pr"><a href="/upload/tutorial${l.tutorialId}.html" target="_blank"><em>${l.tutorialTitle}</em></a></li>
						<#else>
							<li><a class="fl" href="/upload/tutorial${l.tutorialId}.html" target="_blank">${l.tutorialTitle}</a><span class="fr">${l.tutorialCreateTime?string("MM/dd")}</span></li>
						</#if>
						<#if (4 == l_index)><#break /></#if>
					</#list>
				</#if>
			</ul>
			<ul class="ul-tutorial c mc">
				<#if (category_4_list?? && 0 < category_4_list?size)>
					<#list category_4_list as l>
						<#if (0 == l_index)>
							<li class="headline pr"><a href="/upload/tutorial${l.tutorialId}.html" target="_blank"><em>${l.tutorialTitle}</em></a></li>
						<#else>
							<li><a class="fl" href="/upload/tutorial${l.tutorialId}.html" target="_blank">${l.tutorialTitle}</a><span class="fr">${l.tutorialCreateTime?string("MM/dd")}</span></li>
						</#if>
						<#if (4 == l_index)><#break /></#if>
					</#list>
				</#if>
			</ul>
			<ul class="ul-tutorial c mc">
				<#if (category_9_list?? && 0 < category_9_list?size)>
					<#list category_9_list as l>
						<#if (0 == l_index)>
							<li class="headline pr"><a href="/upload/tutorial${l.tutorialId}.html" target="_blank"><em>${l.tutorialTitle}</em></a></li>
						<#else>
							<li><a class="fl" href="/upload/tutorial${l.tutorialId}.html" target="_blank">${l.tutorialTitle}</a><span class="fr">${l.tutorialCreateTime?string("MM/dd")}</span></li>
						</#if>
						<#if (4 == l_index)><#break /></#if>
					</#list>
				</#if>
			</ul>
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