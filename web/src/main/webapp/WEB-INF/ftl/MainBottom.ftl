			</div>
			<#--<div id="mainRight"></div>-->
		</div>
		<input type="hidden" id="contextPath" value="${request.contextPath}" />
		<input type="hidden" id="active" value="${active}" />
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script language="javascript" src="${request.contextPath}/js/manage.js"></script>
		<#if ("ad" == active || "link" == active || "manager" == active || "video" == active || "news" == active || "game" == active || "activity" == active || "match" == active|| "chess" == active|| "tutorial" == active|| "picture" == active)>
			<script language="javascript" src="${request.contextPath}/js/${active}.js"></script>
		</#if>
	</body>
</html>