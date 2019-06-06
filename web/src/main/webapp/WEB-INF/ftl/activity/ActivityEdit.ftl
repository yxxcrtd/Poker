<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/${active}/save" method="post" enctype="multipart/form-data">
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>状态：<@s.formRadioButtons "${active}.${active}Status" statusMap /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>分类：<@s.formRadioButtons "${active}.${active}Category" categoryMap /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>排序：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Orderby" "class='input'" /><@s.showErrors classOrStyle="red" /></div>
				
	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>名称：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Title" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>城市：<span class="star">*</span></div>
		<div>
		<#--
	        <select id="province" onChange='getRegion()' class="selectStyle">
		        <option value="全国">全国</option>
	        </select>
	        <@s.formHiddenInput "${active}.${active}Province" />
	        <select id="city" name="activityCity" class="selectStyle">
		        <option value="全国">全国</option>
	        </select>
		-->
			<@s.formInput "${active}.${active}Province" "class='input135'" /><@s.showErrors classOrStyle="red" />-
			<@s.formInput "${active}.${active}City" "class='input135'" /><@s.showErrors classOrStyle="red" />
	    </div>
    		
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>时间：<span class="star">*</span></div>
		<div>
			<@s.formInput "activity.activityBeginDate" "class='input135'" /><@s.showErrors classOrStyle="red" />-
			<@s.formInput "activity.activityEndDate" "class='input135'" /><@s.showErrors classOrStyle="red" />
		</div>
				
		<div class="label">基本规则：<span class="star">*</span></div>
		<div><@s.formTextarea "${active}.activityBasicRules" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label">时间规则：<span class="star">*</span></div>
		<div><@s.formTextarea "${active}.activityTimeRules" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label">对弈规则：<span class="star">*</span></div>
		<div><@s.formTextarea "${active}.activityGameRules" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
				
		<div id="objPicture">
			<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>图片：<span class="star">*</span>（图片的尺寸：宽103像素 * 高103像素）</div>
			<div>
				<input type="file" name="file" id="file" class="input" style="width: 505px; padding: 1px;" />
				<@s.formHiddenInput "${active}.${active}Picture" /><@s.showErrors classOrStyle="red" />
				<#if (0 lt activity.activityPicture && activity.activityPicture??)>
					<br />
					<img src="/upload/${activity.activityPicture}" />
				</#if>
			</div>
		</div>
		
		<div id="operation">
			<input type="submit" value="确 定" class="button icon_save" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "${active}.activityId" />
		<@s.formHiddenInput "${active}.activityCreateTime" />
		<@s.formHiddenInput "${active}.activityHit" />
	</form>
<#include "MainBottom.ftl" />
<#--
	<script language="javascript" src="${request.contextPath}/js/province.js"></script>
	<script type="text/javascript">
		getProvince();
		<#if (0 lt activity.activityProvinceId && activity.activityCityId??)>
			setCity(${activity.activityProvinceId},${activity.activityCityId});
		</#if>
	</script>
-->