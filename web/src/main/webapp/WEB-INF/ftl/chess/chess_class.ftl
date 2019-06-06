<div class="tit pr">
	棋谱
	<p class="nav c pa">
		<a class="on" href="javascript:;">最新棋谱</a> <a href="javascript:;">本周热门</a>
		<a href="javascript:;">本月热门</a>
	</p>
	<!-- 
	<div class="search sp pa">
		<input class="ipt pa" type="text" value="请输入关键词搜索" onfocus="if(value=='请输入关键词搜索'){value=''}" onblur="if(value==''){value='请输入关键词搜索'}" /> <a class="btn-search pa" href="javascript:;" title="搜索"></a>
	</div>
	-->
	<a class="btn-more sp pa" href="/upload/manual_list.html" target="_blank">查看更多</a>
</div>

<div class="class-news c" style="display: block;">
	<ul class="ul-news-other fr" style="float: left;">
		<#if (list?? && 0 < list?size)>
			<#list list as l>
			<li>
				<a href="/upload/manual${l.chessId}.html" target="_blank">
					<em title="${l.chessTitle}"><#if (16 < l.chessTitle?length)>${l.chessTitle[0..15]}<#else>${l.chessTitle}</#if></em><#if (16 < l.chessSummary?length)>${l.chessSummary[0..15]}<#else>${l.chessSummary}</#if>
					<p class="pa">${(l.chessCreateTime?string("yyyy-MM-dd"))!}</p>
				</a>
			</li>
			<#if (5 == l_index)><#break /></#if>
			</#list>
		</#if>
	</ul>
	<div class="box-rank fr pr" style="width: 340px; float: right;">
		<p class="tit">棋谱关注排行</p>
		<ul class="ul-rank">
			<#if (hotChess?? && 0 < hotChess?size)>
				<#list hotChess as l>
					<#if (3 > l_index)>
						<li class="rank${l_index + 1}"><em>${l_index + 1}</em><a href="/upload/manual${l.chessId}.html" target="_blank">${l.chessTitle}</a></li>
					<#else>
						<li><em>${l_index + 1}</em><a href="/upload/manual${l.chessId}.html" target="_blank">${l.chessTitle}</a></li>
					</#if>
					<#if (9 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
	</div>
</div>
<div class="class-news c">
	<ul class="ul-news-other fr" style="float: left;">
		<#if (z_hit?? && 0 < z_hit?size)>
			<#list z_hit as l>
			<li>
				<a href="/upload/manual${l.chessId}.html" target="_blank">
					<em title="${l.chessTitle}"><#if (16 < l.chessTitle?length)>${l.chessTitle[0..15]}<#else>${l.chessTitle}</#if></em><#if (16 < l.chessSummary?length)>${l.chessSummary[0..15]}<#else>${l.chessSummary}</#if>
					<p class="pa">${(l.chessCreateTime?string("yyyy-MM-dd"))!}</p>
				</a>
			</li>
			<#if (5 == l_index)><#break /></#if>
			</#list>
		</#if>
	</ul>
	<div class="box-rank fr pr" style="width: 340px; float: right;">
		<p class="tit">棋谱关注排行</p>
		<ul class="ul-rank">
			<#if (hotChess?? && 0 < hotChess?size)>
				<#list hotChess as l>
					<#if (3 > l_index)>
						<li class="rank${l_index + 1}"><em>${l_index + 1}</em><a href="/manual/manual${l.chessId}.html" target="_blank">${l.chessTitle}</a></li>
					<#else>
						<li><em>${l_index + 1}</em><a href="/upload/manual${l.chessId}.html" target="_blank">${l.chessTitle}</a></li>
					</#if>
					<#if (9 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
	</div>
</div>
<div class="class-news c">
	<ul class="ul-news-other fr" style="float: left;">
		<#if (y_hit?? && 0 < y_hit?size)>
			<#list y_hit as l>
				<li>
					<a href="/upload/manual${l.chessId}.html" target="_blank">
						<em title="${l.chessTitle}"><#if (16 < l.chessTitle?length)>${l.chessTitle[0..15]}<#else>${l.chessTitle}</#if></em><#if (16 < l.chessSummary?length)>${l.chessSummary[0..15]}<#else>${l.chessSummary}</#if>
						<p class="pa">${(l.chessCreateTime?string("yyyy-MM-dd"))!}</p>
					</a>
				</li>
				<#if (5 == l_index)><#break /></#if>
			</#list>
		</#if>
	</ul>
	<div class="box-rank fr pr" style="width: 340px; float: right;">
		<p class="tit">棋谱关注排行</p>
		<ul class="ul-rank">
			<#if (hotChess?? && 0 < hotChess?size)>
				<#list hotChess as l>
					<#if (3 > l_index)>
						<li class="rank${l_index + 1}"><em>${l_index + 1}</em><a href="/upload/manual${l.chessId}.html" target="_blank">${l.chessTitle}</a></li>
					<#else>
						<li><em>${l_index + 1}</em><a href="/upload/manual${l.chessId}.html" target="_blank">${l.chessTitle}</a></li>
					</#if>
					<#if (9 == l_index)><#break /></#if>
				</#list>
			</#if>
		</ul>
	</div>
</div>

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