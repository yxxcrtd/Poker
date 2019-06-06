<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>游戏：
				<select id="g" name="g">
					<option value="">全部</option>
					<#if gameMap?exists>
		                <#list gameMap?keys as key> 
		                <option <#if (game == key)> selected </#if> value="${key}">${gameMap[key]}</option>
		                </#list>
	           		</#if>
				</select>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>状态：
			    <span id="status">
			        <select id="s" name="s">
			          <option value="">全部</option>
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
			<td width="10%" class="center">游戏</td>
			<td width="20%">图片标题</td>
			<td width="30%">图片内容</td>
			<td width="10%">链接地址</td>
			<td width="10%">排序</td>
			<td width="5%">显示状态</td>
			<td width="10%">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr bgColor="#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
					<td><input type="checkbox" name="checkName" value="${l.pictureId}" /></td>
					<td>${gameMap['${l.pictureGame}']}</td>
					<td>${l.pictureAlt}</td>
					<td><img src="/upload/${l.pictureUri}" alt="${l.pictureAlt}" width="200px" /></td>
					<td>${l.pictureUrl}</td>
					<td>${l.pictureOrderby}</td>
					<td><a href="javascript:()" id="${l.pictureId}" onclick="updateStatus(${l.pictureId});"><#if (0 == l.pictureStatus)>显示<#else><span class="red">隐藏</span></#if></a></td>
					<td><a href="${request.contextPath}/manage/picture/edit/${l.pictureId}">编辑</a>&nbsp;&nbsp;</td>
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