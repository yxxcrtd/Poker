<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/seo/save" method="post" enctype="multipart/form-data">
	    <div class="label">标题：<span class="star">*</span></div>
		<div><@s.formInput "seo.seoTitle" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>

	    <div class="label">关键词：<span class="star">*</span></div>
		<div><@s.formInput "seo.seoKeywords" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>

	    <div class="label">描述：</div>
		<div><@s.formInput "seo.seoDescribe" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div id="objPicture">
			<div class="label">ICON 图标：（图片的尺寸：宽20像素 * 高20像素）<span class="star">*</span></div>
			<div>
				<input type="file" class="input" style="width: 505px; padding: 3px!important;" name="file" />
				<span class="star"><#if (0 lt seo.seoId && seo.seoIcon??)><img width="30" src="/upload/${seo.seoIcon}" /></#if></span>
				<@s.formHiddenInput "seo.seoIcon" /><@s.showErrors classOrStyle="red" />
			</div>
		</div>
		
		<div id="operation">
			<@s.formHiddenInput "seo.seoId" />
			<input type="submit" value="保 存" class="button icon_save" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
		</div>
		
	</form>
<#include "MainBottom.ftl" />