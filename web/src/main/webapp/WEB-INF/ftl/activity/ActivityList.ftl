<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>状态：
		        <select name="s1">
		          <option value="">全部</option>
		          <option <#if (0 == status1)>selected="selected"</#if> value="0">准备</option>
		          <option <#if (1 == status1)>selected="selected"</#if> value="1">进行中</option>
		          <option <#if (2 == status1)>selected="selected"</#if> value="2">已结束</option>
		        </select>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>分类：
				<select name="s2">
					<option value="">全部</option>
					<option <#if (0 == status2)>selected="selected"</#if> value="0">牌类</option>
					<option <#if (1 == status2)>selected="selected"</#if> value="1">棋类</option>
					<option <#if (2 == status2)>selected="selected"</#if> value="2">麻将</option>
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
			<td width="10%"><@compress single_line=true><@getNameByObj active /></@compress>分类</td>
			<td width="7%"><@compress single_line=true><@getNameByObj active /></@compress>排序</td>
			<td width="10%"><@compress single_line=true><@getNameByObj active /></@compress>图片</td>
			<td width="15%" class="left"><@compress single_line=true><@getNameByObj active /></@compress>名称【关注量】</td>
			<td width="10%"><@compress single_line=true><@getNameByObj active /></@compress>地点</td>
			<td width="10%"><@compress single_line=true><@getNameByObj active /></@compress>时间</td>
			<td width="10%"><@compress single_line=true><@getNameByObj active /></@compress>状态</td>
			<td width="10%">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr bgColor="#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
					<td><input type="checkbox" name="checkName" value="${l.activityId}" /></td>
					<td><@compress single_line=true><@getActionCategory l.activityCategory /></@compress></td>
					<td>${l.activityOrderby}</td>
					<td><img src="/upload/${l.activityPicture}" width="120" alt="${l.activityTitle}" /></td>
					<td class="left">${l.activityTitle}<span style="color: #FF0000;" title="关注量">【${l.activityHit}】</span></td>
					<td>${l.activityProvince}·${l.activityCity}</td>
					<td>${l.activityBeginDate?string("yyyy-MM-dd")}<br />${l.activityEndDate?string("yyyy-MM-dd")}</td>
					<td id="p${l.activityId}"><@compress single_line=true><@getActionStatus l.activityStatus /></@compress></td>
					<td>
						<#if (1 == l.activityStatus)>
							<a href="javascript:;" id="${l.activityId}" onclick="updateStatus(${l.activityId}, 2);"><span class="red">停止</span></a>
						<#else>
							<a href="javascript:;" id="${l.activityId}" onclick="updateStatus(${l.activityId}, 1);">发布</a>
						</#if>
						<a href="${request.contextPath}/manage/activity/edit/${l.activityId}">修改</a>
					</td>
				</tr>
			</#list>
		<#else>
			<tr bgColor="F9F9F9">
				<td colspan="12">没有数据！</td>
			</tr>
		</#if>
	</table>

	<div id="operation">
		<input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName" />反选&nbsp;&nbsp;
		<input type="button" id="stopAll" value="设为停止" class="button" />&nbsp;&nbsp;
		<div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
	</div>
	<input type=hidden id="contextPath" value=${request.contextPath}>
<#include "MainBottom.ftl" />
<#--
<script language="javascript" src="${request.contextPath}/js/province.js"></script>
<script type="text/javascript">
	$("td[name='city']").each(function(){
		var i= $(this).attr("title");
		var j= $(this).attr("value");
		$(this).text(city[i][0] + city[i][j]);
	});
</script>
-->