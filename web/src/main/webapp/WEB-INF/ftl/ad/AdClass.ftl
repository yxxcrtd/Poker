<div class="slideBox">
	<ul class="items">
	  	<#if (classList?? && 0 < classList?size)>
			<#list classList as l>
		    	<li>
		    		<a href="<#if (!l.adUrl?starts_with('http'))>http://</#if>${l.adUrl}" title="${l.adTitle}" target="_blank">
		    			<img width="420" height="320" src="/upload/${l.adPicture}">
		    		</a>
		    	</li>
				<#if (3 == l_index)><#break /></#if>
			</#list>
		</#if>
	</ul>
</div>
<script type="text/javascript"><!--$(".slideBox").slideBox({});//--></script>