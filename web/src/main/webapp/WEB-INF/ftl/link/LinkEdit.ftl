<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/link/save" method="post" enctype="multipart/form-data">
				
		<div class="label">显示排序：<span class="star">*</span></div>
		<div>
			<@s.formInput "link.linkOrderby" "class='input'" /><@s.showErrors classOrStyle="red" />
		</div>
				
		<div class="label">网站名称：<span class="star">*</span></div>
		<div>
			<@s.formInput "link.linkName" "class='input500'" /><@s.showErrors classOrStyle="red" />
		</div>
				
		<div class="label">网站地址：<span class="star">*</span></div>
		<div>
			<@s.formInput "link.linkAddress" "class='input500'" /><@s.showErrors classOrStyle="red" />
		</div>
				
		<div id="objPicture">
			<div class="label">网站LOGO：<span class="star">*</span></div>
			<div>
				<input type="file" name="file" id="file" class="input" style="width: 505px; padding: 1px;" />
				<@s.formHiddenInput "link.linkLogo" /><@s.showErrors classOrStyle="red" />
				<#if (0 lt link.linkLogo && link.linkLogo??)>
					<br />
					<img src="/upload/${link.linkLogo}" />
				</#if>
			</div>
		</div>
				
		<div id="operation">
			<input type="submit" value="<#if (0 == link.linkId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "link.linkId" />
		<@s.formHiddenInput "link.linkStatus" />
	</form>
<#include "MainBottom.ftl" />
