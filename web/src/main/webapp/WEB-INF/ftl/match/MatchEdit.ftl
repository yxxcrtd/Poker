<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/${active}/save" method="post" enctype="multipart/form-data">
		
	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>名称：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Name" "class='input500' maxLength=32" /><@s.showErrors classOrStyle="red" /></div>

		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>介绍：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Summary" "class='input872' maxLength=128" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>地点：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Address" "class='input500' maxLength=32" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>开始时间（格式：2015-12-12）：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}StartDate" "class='input' maxLength=10" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>结束时间（格式：2015-12-12）：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}EndDate" "class='input' maxLength=10" /><@s.showErrors classOrStyle="red" /><span id="tips"><#if tips??>${tips}</#if></span></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>排序：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Orderby" "class='input'" /><@s.showErrors classOrStyle="red" /></div>
				
		<div id="objPicture">
			<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>图片：<span class="star">*</span>（首页广告尺寸：宽340像素 * 高220像素）</div>
			<div>
				<input type="file" name="file" id="file" class="input" style="width: 505px; padding: 1px;" />
				<@s.formHiddenInput "${active}.${active}Picture" /><@s.showErrors classOrStyle="red" />
				<#if (0 lt match.matchPicture && match.matchPicture??)>
					<br />
					<img src="/upload/${match.matchPicture}" style="width: 300px; height:200px" />
				</#if>
			</div>
		</div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>地址：<span class="star"></span></div>
		<div><@s.formInput "${active}.${active}Url" "class='input500'" /></div>
		
		<div id="operation">
			<input type="submit" value="确 定" class="button icon_save" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "${active}.${active}Id" />
	</form>
<#include "MainBottom.ftl" />