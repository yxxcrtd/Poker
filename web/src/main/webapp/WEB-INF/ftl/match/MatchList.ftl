<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>状态：
		        <select name="s1">
		          <option value="">全部</option>
		          <option <#if (0 == status1)>selected="selected"</#if> value="0">即将开始</option>
		          <option <#if (1 == status1)>selected="selected"</#if> value="1">进行中</option>
		          <option <#if (2 == status1)>selected="selected"</#if> value="2">已结束</option>
		        </select>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>名称：
				<input type="text" name="k" value="${k}" class="input160" onMouseOver="this.select();" placeholder="请输入<@compress single_line=true><@getNameByObj active /></@compress>名称" />
				<input type="submit" value="查 询" class="button" />
			</span>
		</form>
	</div>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr id="thead">
			<td width="5%"><input type="checkbox" class="checkbox" id="checkAll" name="checkAllName" title="全选/全不选" /></td>
			<td width="11%" class="left"><@compress single_line=true><@getNameByObj active /></@compress>图片</td>
			<td width="15%" class="left"><@compress single_line=true><@getNameByObj active /></@compress>名称</td>
			<td width="15%"><@compress single_line=true><@getNameByObj active /></@compress>开始时间</td>
			<td width="15%"><@compress single_line=true><@getNameByObj active /></@compress>结束时间</td>
			<td width="15%" class="left"><@compress single_line=true><@getNameByObj active /></@compress>地点</td>
			<td width="8%">排序</td>
			<td width="8%">状态</td>
			<td width="8%">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr onMouseOver="changeBgColor(this, '#F9F9F9');" onMouseOut="changeBgColor(this, '#FFFFFF');">
					<td><input type="checkbox" name="checkName" value="${l.matchId}" /></td>
					<td><img src="/upload/${l.matchPicture}" width="120" alt="${l.matchName}" /></td>
					<td class="left" title="${l.matchSummary}"><#if (l.matchUrl??)><a href="<#if (!l.matchUrl?starts_with('http'))>http://</#if>${l.matchUrl}" target="_blank">${l.matchName}</a><#else>${l.matchName}</#if></td>
					<td>${l.matchStartDate}</td>
					<td>${l.matchEndDate}</td>
					<td class="left">${l.matchAddress}</td>
					<td>${l.matchOrderby}</td>
					<td><@compress single_line=true><@getMatchStatus l.matchStatus /></@compress></td>
					<td><a href="${request.contextPath}/manage/match/edit/${l.matchId}">编辑</a>&nbsp;&nbsp;</td>
				</tr>
			</#list>
		<#else>
			<tr>
				<td colspan="10">没有数据！</td>
			</tr>
		</#if>
	</table>

	<div id="operation">
		<#--<input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName" />反选-->
		<div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
	</div>
<#include "MainBottom.ftl" />