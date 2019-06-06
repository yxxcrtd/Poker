<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/${active}/save" method="post" enctype="multipart/form-data">
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>状态：<@s.formRadioButtons "${active}.${active}Status" statusMap /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>分类：<@s.formRadioButtons "${active}.${active}Category" categoryMap /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>排序：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Orderby" "class='input'" /><@s.showErrors classOrStyle="red" /></div>
		
	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>标题：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Title" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
		
	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>摘要：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Summary" "class='input500' maxlength='128'" /><@s.showErrors classOrStyle="red" /></div>
		
	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>添加人：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}CreateUser" "class='input500' maxlength='12'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>内容：<span class="star">*</span></div>
		<div><@s.formTextarea "${active}.${active}Content" "class='input', style='width: 800px; height: 380px;'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div id="objPicture">
			<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>图片：</div>
			<div>
				<input type="file" name="file" id="file" class="input" style="width: 505px; padding: 1px;" />
				<@s.formHiddenInput "${active}.${active}Picture" /><@s.showErrors classOrStyle="red" />
				<#if (0 lt tutorial.tutorialPicture && tutorial.tutorialPicture??)>
					<br />
					<img src="/upload/${tutorial.tutorialPicture}" />
				</#if>
			</div>
		</div>
		
	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>关键字：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Keyword" "class='input'" /><@s.showErrors classOrStyle="red" /></div>
		
		
		<div id="operation">
			<input type="submit" value="<#if (0 == tutorial.tutorialId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "${active}.${active}Id" />
		<@s.formHiddenInput "${active}.${active}Hit" />
		<@s.formHiddenInput "${active}.${active}CreateTime" />
	</form>
	<script language="javascript" src="${request.contextPath}/js/kindeditor.js"></script>
<#include "MainBottom.ftl" />
