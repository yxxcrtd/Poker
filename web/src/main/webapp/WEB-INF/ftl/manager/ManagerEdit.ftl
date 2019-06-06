<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/manager/save" method="post">

		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>名称：<span class="star">*</span></div>
		<div>
			<@s.formInput "manager.managerName" "class='input'" /><@s.showErrors classOrStyle="red" />
			<img id="ok" src="${request.contextPath}/images/bg_blank.gif" />
			<span id="nameTip"></span>
		</div>

		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>密码：<span class="star">*</span></div>
		<div>
			<@s.formInput "manager.managerPassword" "class='input'" /><@s.showErrors classOrStyle="red" />
		</div>

		<div class="label">所有权限：<input type="checkbox" id="managerAll" class="checkbox" /></div>
		<div class="label">新闻权限：<@s.formCheckbox "manager.managerNews" "class='checkbox'" /></div>
		<div class="label">视频权限：<@s.formCheckbox "manager.managerVideo" "class='checkbox'" /></div>
		<div class="label">广告权限：<@s.formCheckbox "manager.managerAd" "class='checkbox'" /></div>
		<div class="label">游戏库权限：<@s.formCheckbox "manager.managerGame" "class='checkbox'" /></div>
		<div class="label">活动权限：<@s.formCheckbox "manager.managerActive" "class='checkbox'" /></div>
		<div class="label">赛事权限：<@s.formCheckbox "manager.managerMatch" "class='checkbox'" /></div>
		<div class="label">棋牌权限：<@s.formCheckbox "manager.managerPoker" "class='checkbox'" /></div>
		<div class="label">网站设置权限：<@s.formCheckbox "manager.managerWebsite" "class='checkbox'" /></div>

		<div id="operation">
			<input type="submit" value="<#if (0 == manager.managerId)>保 存<#else>修 改</#if>" class="button" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "manager.managerId" />
	</form>
<#include "MainBottom.ftl" />