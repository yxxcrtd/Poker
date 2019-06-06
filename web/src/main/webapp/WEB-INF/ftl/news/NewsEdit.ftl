<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/${active}/save" method="post" enctype="multipart/form-data">
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>状态：<@s.formRadioButtons "${active}.${active}Status" statusMap /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>分类：<@s.formRadioButtons "${active}.${active}Category" categoryMap /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>类型：<@s.formRadioButtons "${active}.${active}Type" typeMap /></div>

		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>排序：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Orderby" "class='input'" /><@s.showErrors classOrStyle="red" /></div>
		
	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>标题：<span class="star">*</span></div>
		<div><@s.formInput "news.newsTitle" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
		
	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>摘要：<span class="star">*</span></div>
		<div><@s.formInput "news.newsSummary" "class='input500' maxlength='128'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>内容：<span class="star">*</span></div>
		<div><@s.formTextarea "news.newsContent" "class='input', style='width: 800px; height: 380px;'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div id="objPicture">
			<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>图片：<span class="star">*</span></div>
			<div>
				<input type="file" name="file" id="file" class="input" style="width: 505px; padding: 1px;" />
				<@s.formHiddenInput "${active}.${active}Picture" /><@s.showErrors classOrStyle="red" />
				<#if (0 lt news.newsPicture && news.newsPicture??)>
					<br />
					<img src="/upload/${news.newsPicture}" />
				</#if>
			</div>
		</div>
		
	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>关键字：</div>
		<div><@s.formInput "${active}.${active}Keyword" "class='input'" /></div>
		
		
		<div id="operation">
			<input type="submit" value="<#if (0 == news.newsId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "news.newsId" />
		<@s.formHiddenInput "news.newsHit" />
		<@s.formHiddenInput "news.newsCreateTime" />
	</form>
	<script language="javascript" src="${request.contextPath}/js/kindeditor.js"></script>
<#include "MainBottom.ftl" />
