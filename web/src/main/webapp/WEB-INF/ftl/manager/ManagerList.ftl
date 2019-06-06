<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>名称：
				<input type="text" name="k" value="${k}" class="input160" onMouseOver="this.select();" placeholder="请输入管理员名称" />
				<input type="submit" value="查 询" class="button" />
			</span>
		</form>
	</div>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr id="thead">
			<td width="5%"><input type="checkbox" class="checkbox" id="checkAll" name="checkAllName" title="全选/全不选" /></td>
			<td width="10%"><@compress single_line=true><@getNameByObj active /></@compress>名称</td>
			<td width="68%" class="left">权限</td>
			<td width="17%">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr onMouseOver="changeBgColor(this, '#F9F9F9');" onMouseOut="changeBgColor(this, '#FFFFFF');">
					<td><input type="checkbox" <#if (0 == l.managerRole)>name="checkName"</#if> class="checkbox" value="${l.managerId}" <#if (1 == l.managerRole)>disabled="disabled"</#if> /></td>
					<td>${l.managerName}</td>
					<td class="left">
						<input type="checkbox" class="checkbox" name="managerNews" <#if l.managerNews>checked="checked"</#if> <#if (1 == l.managerRole)>disabled="disabled"</#if> onClick="checkThis('ManagerNews', ${l.managerId});" />新闻权限&nbsp;&nbsp;
						<input type="checkbox" class="checkbox" name="managerVideo" <#if l.managerVideo>checked="checked"</#if> <#if (1 == l.managerRole)>disabled="disabled"</#if> onClick="checkThis('ManagerVideo', ${l.managerId});" />视频权限&nbsp;&nbsp;
						<input type="checkbox" class="checkbox" name="managerAd" <#if l.managerAd>checked="checked"</#if> <#if (1 == l.managerRole)>disabled="disabled"</#if> onClick="checkThis('ManagerAd', ${l.managerId});" />广告权限&nbsp;&nbsp;
						<input type="checkbox" class="checkbox" name="managerGame" <#if l.managerGame>checked="checked"</#if> <#if (1 == l.managerRole)>disabled="disabled"</#if> onClick="checkThis('ManagerGame', ${l.managerId});" />游戏库权限&nbsp;&nbsp;
						<input type="checkbox" class="checkbox" name="managerActive" <#if l.managerActive>checked="checked"</#if> <#if (1 == l.managerRole)>disabled="disabled"</#if> onClick="checkThis('ManagerActive', ${l.managerId});" />活动权限&nbsp;&nbsp;
						<input type="checkbox" class="checkbox" name="managerMatch" <#if l.managerMatch>checked="checked"</#if> <#if (1 == l.managerRole)>disabled="disabled"</#if> onClick="checkThis('ManagerMatch', ${l.managerId});" />赛事权限&nbsp;&nbsp;
						<input type="checkbox" class="checkbox" name="managerPoker" <#if l.managerMatch>checked="checked"</#if> <#if (1 == l.managerRole)>disabled="disabled"</#if> onClick="checkThis('ManagerPoker', ${l.managerId});" />棋牌权限&nbsp;&nbsp;
						<input type="checkbox" class="checkbox" name="managerWebsite" <#if l.managerWebsite>checked="checked"</#if> <#if (1 == l.managerRole)>disabled="disabled"</#if> onClick="checkThis('ManagerWebsite', ${l.managerId});" />网站设置权限
					</td>
					<td>
						<a href="${request.contextPath}/manage/manager/reset/${l.managerId}" onClick="javascript:return confirm('确认要重置密码吗?');">重置密码</a>
						<a href="${request.contextPath}/manage/manager/updatePassword/${l.managerId}">修改密码</a>
						<#if (1 > l.managerRole)><a class="red" href="${request.contextPath}/manage/manager/del/${l.managerId}" onClick="return confirm('确定删除吗？');">删除</a></#if>
					</td>
				</tr>
			</#list>
		<#else>
			<tr bgColor="F9F9F9">
				<td colspan="7">没有数据！</td>
			</tr>
		</#if>
	</table>

	<div id="operation">
		<input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName" />反选&nbsp;&nbsp;
		<input type="button" id="batchAll" value="批量删除" class="button" />
		<div id="pageNav">共${count}条数据</div>
	</div>
<#include "MainBottom.ftl" />