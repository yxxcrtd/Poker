<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/manager/updatePasswordSave" method="post">

		<div class="label">用户名称：<span class="star"></span></div>
		<div>
			<@s.formInput "manager.managerName" "class='input' disabled='disabled'" />
		</div>

		<div class="label">新密码：<span class="star">*</span></div>
		<div>
			<@s.formInput "manager.managerPassword" "class='input'" />
		</div>

		<div id="operation">
			<input type="submit" value="<#if (0 == manager.managerId)>保 存<#else>修 改</#if>" class="button" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "manager.managerId" />
	</form>
<#include "MainBottom.ftl" />