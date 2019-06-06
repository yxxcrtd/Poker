<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				显示状态：
		        <select name="s1">
		          <option value="">全部</option>
		          <option <#if (0 == status)>selected="selected"</#if> value="0">显示</option>
		          <option <#if (1 == status)>selected="selected"</#if> value="1">隐藏</option>
		        </select>
			</span>
			<span class="condition">
				网站名称：
				<input type="text" name="k" value="${k}" class="input160" onMouseOver="this.select();" placeholder="请输入网站名称" />
				<input type="submit" value="查询" class="button" />
			</span>
		</form>
	</div>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr id="thead">
			<td width="5%"><input type="checkbox" class="checkbox" id="checkAll" name="checkAllName" title="全选/全不选" /></td>
			<td width="20%" class="left">网站名称</td>
			<td width="25%" class="left">网站地址</td>
			<td width="20%" class="left">网站LOGO</td>
			<td width="10%">排序</td>
			<td width="10%">显示状态</td>
			<td width="10%">操作</td>
		</tr>
		<#if (linkList?? && 0 < linkList?size)>
			<#list linkList as l>
				<tr onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#FFFFFF');">
					<td><input type="checkbox" class="checkbox" name="checkName" value="${l.linkId}" /></td>
					<td class="left">${l.linkName}</td>
					<td class="left"><a href="<#if (!l.linkAddress?starts_with('http'))>http://</#if>${l.linkAddress}" target="_blank">${l.linkAddress}</a></td>
					<td class="left"><img src="/upload/${l.linkLogo}" width="60" alt="${l.linkName}" /></td>
					<td>${l.linkOrderby}</td>
					<td><a href="javascript:;" id="${l.linkId}" onClick="updateStatus(${l.linkId});"><#if (0 == l.linkStatus)>显示<#else>隐藏</#if></a></td>
					<td><a href="${request.contextPath}/manage/link/edit/${l.linkId}">修改</a></td>
				</tr>
			</#list>
		<#else>
			<tr>
				<td colspan="7">没有数据！</td>
			</tr>
		</#if>
	</table>

	<div id="operation">
		<input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName" />反选&nbsp;&nbsp;
		<input type="button" id="hideAll" value="设为隐藏" class="button" />&nbsp;&nbsp;
		<input type="button" id="showAll" value="设为显示" class="button" />
		<div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
	</div>
<#include "MainBottom.ftl" />