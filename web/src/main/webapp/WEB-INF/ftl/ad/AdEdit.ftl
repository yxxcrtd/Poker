<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/ad/save" method="post" enctype="multipart/form-data">
		<div class="label">广告状态：<@s.formRadioButtons "ad.adVisible" visibleMap /></div>
				
		<div class="label">广告投放位置：</div>
		<div>
			<@s.formSingleSelect "ad.adLocation" locationMap "class='selectStyle'" />
		</div>
		
	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>标题：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Title" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>

		<div class="label">广告摘要：</div>
		<div><@s.formInput "ad.adSummary" "class='input500'" /></div>
		
		<div class="label">广告内容：</div>
		<div><@s.formInput "ad.adContent" "class='input872'" /></div>
		
		<div class="label">广告链接：<span class="star">*</span></div>
		<div><@s.formInput "ad.adUrl" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
				
		<div class="label">广告排序：<span class="star">*</span></div>
		<div><@s.formInput "ad.adOrderby" "class='input'" /><@s.showErrors classOrStyle="red" /></div>
				
		<div id="objPicture">
			<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>图片：<span class="star">*</span>（首页广告尺寸：宽1000像素 * 高410像素）</div>
			<div>
				<input type="file" name="file" id="file" class="input" style="width: 505px; padding: 1px;" />
				<@s.formHiddenInput "ad.adPicture" /><@s.showErrors classOrStyle="red" />
				<#if (0 lt ad.adPicture && ad.adPicture??)>
					<br />
					<img src="/upload/${ad.adPicture}" />
				</#if>
			</div>
		</div>
		
		<div id="operation">
			<input type="submit" value="确 定" class="button icon_save" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "ad.adId" />
	</form>
<#include "MainBottom.ftl" />