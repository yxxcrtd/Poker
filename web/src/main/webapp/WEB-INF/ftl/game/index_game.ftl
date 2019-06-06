<div class="container">
	<div class="tit pr">
		快速入口
		<div class="dot c pa">
			<p></p>
			<a class="arrow left sp pa" href="javascript:;"></a>
			<a class="arrow right sp pa" href="javascript:;"></a>
		</div>
	</div>
	<div class="outer pr">
		<ul class="ul-game c pa">
			<#if (list?? && 0 < list?size)>
				<#assign typeli=1/>
				<#list list as l>
					<li class="type${typeli}">
						<p class="game">
							<img width="90" height="90" src="/upload/${l.gamePicture}" />
							<em>${l.gameTitle}</em>
							<span class="sp">154874</span>
						</p>
						<p class="download">
							<img width="112" height="112" src="images/test4.jpg" />
							<span>扫一扫 即可下载</span>
							<a class="btn-detail mc" href="upload/game${l.gameId}.html" target="_blank">了解详情</a>
						</p>
					</li>
					<#assign typeli=typeli+1/>
					<#if (5 == l_index)><#assign typeli=1/></#if>
				</#list>
			</#if>
		</ul>
	</div>
</div>