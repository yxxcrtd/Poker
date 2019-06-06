<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>位置：
		        <select name="s1">
		          <option value="">全部</option>
		          <option <#if (1 == status1)>selected="selected"</#if> value="1">首页</option>
		          <option <#if (2 == status1)>selected="selected"</#if> value="2">新闻</option>
		          <option <#if (3 == status1)>selected="selected"</#if> value="3">棋牌游戏</option>
		          <option <#if (4 == status1)>selected="selected"</#if> value="4">赛事专区</option>
		          <option <#if (5 == status1)>selected="selected"</#if> value="5">活动</option>
		          <option <#if (6 == status1)>selected="selected"</#if> value="6">视频</option>
		          <option <#if (7 == status1)>selected="selected"</#if> value="7">棋牌学堂</option>
		        </select>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>状态：
		        <select name="s2">
		          <option value="">全部</option>
		          <option <#if (0 == status2)>selected="selected"</#if> value="0">显示</option>
		          <option <#if (1 == status2)>selected="selected"</#if> value="1">隐藏</option>
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
			<td width="8%">广告位置</td>
			<td width="10%" class="left">广告图片</td>
			<td width="17%" class="left">广告标题</td>
			<td width="20%" class="left">广告摘要</td>
			<td width="17%" class="left">广告链接</td>
			<td width="8%">排序</td>
			<td width="8%">状态</td>
			<td width="8%">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr onMouseOver="changeBgColor(this, '#F9F9F9');" onMouseOut="changeBgColor(this, '#FFFFFF');">
					<td><input type="checkbox" name="checkName" value="${l.adId}" /></td>
					<td><@compress single_line=true><@getAdLocation l.adLocation /></@compress></td>
					<td><img src="/upload/${l.adPicture}" width="120" alt="${l.adTitle}" /></td>
					<td class="left">${l.adTitle}</td>
					<td class="left">${l.adSummary}</td>
					<td class="left"><a href="<#if (!l.adUrl?starts_with('http'))>http://</#if>${l.adUrl}" target="_blank">${l.adUrl}</a></td>
					<td>${l.adOrderby}</td>
					<td><a href="javascript:;" id="${l.adId}" onClick="updateStatus(${l.adId});"><@compress single_line=true><@getAdVisible l.adVisible /></@compress></a></td>
					<td><a href="${request.contextPath}/manage/ad/edit/${l.adId}">编辑</a>&nbsp;&nbsp;</td>
				</tr>
			</#list>
		<#else>
			<tr>
				<td colspan="10">没有数据！</td>
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