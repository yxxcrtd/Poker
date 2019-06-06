<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/${active}/save" method="post" enctype="multipart/form-data">
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>状态：<@s.formRadioButtons "${active}.${active}Status" statusMap /></div>
				
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>分类：<@s.formRadioButtons "${active}.${active}Category" categoryMap /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>质量：<@s.formRadioButtons "${active}.${active}Quality" qualityMap /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>排序：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Orderby" "class='input'" /><@s.showErrors classOrStyle="red" /></div>
		
	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>标题：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Title" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
				
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>摘要：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Summary" "class='input872'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>地址：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Url" "class='input500' maxlength='128'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div id="objPicture">
			<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>图片：<span class="star">*</span></div>
			<div>
				<input type="file" name="file" id="file" class="input" style="width: 505px; padding: 1px;" />
				<@s.formHiddenInput "${active}.${active}Picture" /><@s.showErrors classOrStyle="red" />
				<#if (0 lt video.videoPicture && video.videoPicture??)>
					<br />
					<img src="/upload/${video.videoPicture}" />
				</#if>
			</div>
		</div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>时长：（单位：分钟）<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}During" "class='input'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>地区：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Area" "class='input'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div id="operation">
			<input type="submit" value="确 定" class="button icon_save" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "video.videoId" />
		<@s.formHiddenInput "video.videoHit" />
		<@s.formHiddenInput "video.videoCreateTime" />
	</form>
<#include "MainBottom.ftl" />