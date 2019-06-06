<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>类别：
		        <select id="c"  name="c"> 
		          <option <#if (0 == category)> selected </#if> value="0">全部</option>
		          <option <#if (1 == category)> selected </#if> value="1">棋类</option>
		          <option <#if (2 == category)> selected </#if> value="2">牌类</option>
		          <option <#if (3 == category)> selected </#if> value="3">麻将</option>
		          <option <#if (4 == category)> selected </#if> value="9">其他</option>
		        </select>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>状态：
			    <span id="status" >
			        <select id="s" name="s">
			          <option <#if (2 == status)> selected </#if> value="2">全部</option>
			          <option <#if (0 == status)> selected </#if> value="0">显示</option>
			          <option <#if (1 == status)> selected </#if> value="1">隐藏</option>
			        </select>
			    </span>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>名称：
				<input type="text"  id="k"  name="k" value="${k}" class="input160" onMouseOver="this.select();" placeholder="请输入<@compress single_line=true><@getNameByObj active /></@compress>名称" />
				<input type="submit" id="sub" value="查 询" class="button" />
			</span>
		</form>
	</div>	

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr id="thead">
			<td width="5%"><input type="checkbox" class="checkbox" id="checkAll" name="checkAllName" title="全选/全不选" /></td>
			<td width="5%">游戏ID</td>
			<td width="15%">游戏类别</td>
			<td width="15%">游戏名称</td>
			<td width="30%">游戏地址</td>
			<td width="10%">排序</td>
			<td width="10%">显示状态</td>
			<td width="10%">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr bgColor="#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
					<td><input type="checkbox" name="checkName" value="${l.gameId}" /></td>
					<td>${l.gameId}</td>
					<td>${categoryMap['${l.gameCategoryId}']}--<#if (0 == l.gameType)>8大单品<#else>其他产品</#if></td>
					<td>${l.gameTitle}</td>
					<td><a href="${request.contextPath}/upload/game${l.gameId}.html" target="_blank">单品地址</a> &nbsp; <a href="http://${l.gameOfficialUrl}" target="_blank">官网地址</a>&nbsp;<a href="http://${l.gameLoadUrl}" target="_blank">下载地址</a></td>
					<td>${l.gameOrderby}</td>
					<td><a href="javascript:()" id="${l.gameId}" onclick="updateStatus(${l.gameId});"><#if (0 == l.gameStatus)>显示<#else><span class="red">隐藏</span></#if></a></td>
					<td><a href="${request.contextPath}/manage/game/edit/${l.gameId}">编辑</a>&nbsp;&nbsp;</td>
				</tr>
			</#list>
		<#else>
			<tr bgColor="F9F9F9">
				<td colspan="8">没有数据！</td>
			</tr>
		</#if>
	</table>

	<div id="operation">
		<input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName" />反选&nbsp;&nbsp;
		<input type="button" id="hideAll" value="隐 藏" class="button" />&nbsp;&nbsp;
		<input type="button" id="showAll" value="显 示" class="button" />
		<div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
	</div>
	<input type=hidden id="contextPath" value=${request.contextPath}>
<#include "MainBottom.ftl" />