<r#include "ManageRightTop.ftl" />
	<div id="search">
		<span class="condition">
			显示状态：
		    <span id="status">
		        <select onchange="search();" id="searchSelect">
		          <option <r#if (2 == status)> selected <r/#if> value="2">全部</option>
		          <option <r#if (0 == status)> selected <r/#if> value="0">显示</option>
		          <option <r#if (1 == status)> selected <r/#if> value="1">隐藏</option>
		        </select>
		    </span>
		</span>
		<span class="condition">
			网站名称：
			<input id="search_name" type="text" value="${r"search_name"}" class="input200" />
			<input id="search_bt" type="button" onClick="search();" value="查询" class="button" />
		</span>
	</div>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr id="thead">
			<td width="5%"><input type="checkbox" style="margin-top: 5px;" /></td>
			<td width="20%" class="left">网站名称</td>
			<td width="25%" class="left">网站地址</td>
			<td width="20%" class="left">网站LOGO</td>
			<td width="10%">排序</td>
			<td width="10%">显示状态</td>
			<td width="10%">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr bgColor="#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
					<td><input type="checkbox" name="chk_list" value="${l.objId}"/></td>
					<td class="left">${l.objName}</td>
					<td class="left">${l.objAddress}</td>
					<td><img src="/upload/${l.objLogo}" width="120" alt="${l.objName}" /></td>
					<td>${l.objOrderby}</td>
					<td><a href="javascript:;" id="${l.objId}" onClick="updateStatus(${l.objId});"><#if (0 == l.objStatus)>显示<#else>隐藏</#if></a></td>
					<td><a href="${request.contextPath}/manage/${obj}/edit/${l.objId}">修改</a></td>
				</tr>
			</#list>
		<#else>
			<tr bgColor="F9F9F9">
				<td colspan="7">没有数据！</td>
			</tr>
		</#if>
	</table>

	<div id="operation">
		<input type="checkbox" id="chk_all" class="checkbox" />全选/反选&nbsp;&nbsp;
		<input type="button" id="hideAll" value="设为隐藏" class="button" />&nbsp;&nbsp;
		<input type="button" id="showAll" value="设为显示" class="button" />
		<div id="pageNav"><span class="condition">共${r"count"}条数据</span><r#include "Pager.ftl" /></div>
	</div>
<r#include "ManageRightBottom.ftl" />