<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/picture/save" method="post" enctype="multipart/form-data">
		<div class="label">显示状态：<@s.formRadioButtons "picture.pictureStatus" visibleMap /></div>
		<div class="label">游戏：<@s.formSingleSelect "picture.pictureGame" gameMap /></div>
		
		<div class="label">图片排序：<span class="star">*</span></div>
		<div><@s.formInput "${active}.pictureOrderby" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>

	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>标题：<span class="star">*</span></div>
		<div><@s.formInput "${active}.pictureAlt" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
			
		<div class="label">链接地址：<span class="star">*</span></div>
		<div><@s.formInput "${active}.pictureUrl" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
		
		
		<div class="label">图片：<span class="star">*</span></div>
		<div>
			<input type="file" name="file" id="file" class="input" style="padding: 1px;" />
				<@s.formHiddenInput "${active}.pictureUri" /><@s.showErrors classOrStyle="red" />
				<#if (0 lt picture.pictureUri && picture.pictureUri??)>
					<br />
					<img src="/upload/${picture.pictureUri}" />
				</#if>
		</div>
		<div id="operation">
			<input type="submit" value="确 认" class="button icon_save" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "${active}.pictureId" />
	</form>
<#include "MainBottom.ftl" />