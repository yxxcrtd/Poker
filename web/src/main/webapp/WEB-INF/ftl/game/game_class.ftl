<div class="tit c pr">
	<em>棋牌规则</em>
	<div class="nav c pa">
		<a class="on" href="javascript:;">牌类</a> <a href="javascript:;">麻将</a>
		<a href="javascript:;">棋类</a> <a href="upload/game_rule.html">全部</a>
	</div>
</div>

<!-- 棋类 -->
<div class="box-rule c" style="display: block;">
<#if (list?? && 0 < list?size)>
	<#list list as l>
		<#if (l.gameCategoryId==1)>
		<div class="game">
			<img width="102" height="102" src="/upload/${l.gamePicture}" />
			<em>${l.gameTitle}</em>
			<p><#if (60 < l.gameRule?length) >${l.gameRule[0..60]}<#else>${l.gameRule}</#if></p>
			<a class="btn-detail mc" href="/upload/gamerule${l.gameId}.html">查看详情</a>
		</div>
		</#if>
	</#list>
</#if>
</div>
<!-- 牌类 -->
<div class="box-rule c">
<#if (list?? && 0 < list?size)>
	<#list list as l>
			<#if (l.gameCategoryId==2)>
		<div class="game">
			<img width="102" height="102" src="/upload/${l.gamePicture}" />
			<em>${l.gameTitle}</em>
			<p><#if (100 < l.gameRule?length)>${l.gameRule[0..100]}<#else>${l.gameRule}</#if></p>
			<a class="btn-detail mc" href="/upload/gamerule${l.gameId}.html">查看详情</a>
		</div>
		</#if>
	</#list>
</#if>
</div>
<!-- 麻将 -->
<div class="box-rule c">
<#if (list?? && 0 < list?size)>
	<#list list as l>
		<#if (l.gameCategoryId==3)>
		<div class="game">
			<img width="102" height="102" src="/upload/${l.gamePicture}" />
			<em>${l.gameTitle}</em>
			<p><#if (100 < l.gameRule?length)>${l.gameRule[0..100]}<#else>${l.gameRule}</#if></p>
			<a class="btn-detail mc" href="/upload/gamerule${l.gameId}.html">查看详情</a>
		</div>
		</#if>
	</#list>
</#if>
</div>
