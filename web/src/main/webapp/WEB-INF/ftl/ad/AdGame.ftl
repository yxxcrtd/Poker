<div class="slideBox" id="slideBox">
	<ul class="items">
	<#if (gameList?? && 0 < gameList?size)>
		<#list gameList as l>
			<li>
		    	<a href="<#if (!l.adUrl?starts_with('http'))>http://</#if>${l.adUrl}" title="${l.adTitle}" target="_blank">
		    			<img width="440" height="320" src="/upload/${l.adPicture}">
		    		</a>
		    </li>
		<#if (3 == l_index)><#break /></#if>
		</#list>
	</#if>
	</ul>
</div>
<script type="text/javascript">$("#slideBox").slideBox({});</script>
<!--
<div class="outer pa">
	<#if (gameList?? && 0 < gameList?size)>
		<#list gameList as l>
			<a href="<#if (!l.adUrl?starts_with('http'))>http://</#if>${l.adUrl}" target="_blank">
				<img width="440" height="320" src="/upload/${l.adPicture}" />
			</a>
		</#list>
	</#if>
</div>
/*
<a class="arrow left sp pa" href="javascript:;"></a> 
<a class="arrow right sp pa" href="javascript:;"></a>
<script type="text/javascript">
	var gamePicI = 0;
	var gamePicW = $(".game-pic .outer a").width();
	var gamePicL = $(".game-pic .outer a").length;
	$(".game-pic .outer").css("width", gamePicW * gamePicW + "px");
	$(".game-pic .left").click(function() {
		gamePicI--;
		if (gamePicI < 0) {
			gamePicI = 0;
		}
		gamePicGo();
	});
	$(".game-pic .right").click(function() {
		gamePicI++;
		if (gamePicI > gamePicL - 1) {
			gamePicI = gamePicL - 1;
		}
		gamePicGo();
	});
	function gamePicGo() {
	$(".game-pic .outer").stop(true).animate({
		left : '-' + gamePicI * gamePicW + 'px'
	});
}
</script>
-->


