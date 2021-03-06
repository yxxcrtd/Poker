<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>分类：
		        <select name="s1">
					<option value="">全部</option>
					<option value="0" <#if (0 == status1)>selected="selected"</#if>>赛事直播</option>
					<option value="1" <#if (1 == status1)>selected="selected"</#if>>赛事回放</option>
					<option value="2" <#if (2 == status1)>selected="selected"</#if>>专题栏目</option>
					<option value="3" <#if (3 == status1)>selected="selected"</#if>>德州扑克</option>
					<option value="4" <#if (4 == status1)>selected="selected"</#if>>中国象棋</option>
					<option value="5" <#if (5 == status1)>selected="selected"</#if>>斗地主</option>
					<option value="6" <#if (6 == status1)>selected="selected"</#if>>其他</option>
		        </select>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>质量：
		        <select name="s2">
		          <option value="">全部</option>
		          <option value="0" <#if (0 == status2)>selected="selected"</#if>>蓝光</option>
		          <option value="1" <#if (1 == status2)>selected="selected"</#if>>超清</option>
		          <option value="2" <#if (2 == status2)>selected="selected"</#if>>高清</option>
		          <option value="3" <#if (3 == status2)>selected="selected"</#if>>标清</option>
		        </select>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>状态：
		        <select name="s3">
		          <option value="">全部</option>
		          <option value="0" <#if (0 == status3)>selected="selected"</#if>>显示</option>
		          <option value="1" <#if (1 == status3)>selected="selected"</#if>>隐藏</option>
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
			<td width="10%"><@compress single_line=true><@getNameByObj active /></@compress>质量</td>
			<td width="5%" class="center">排序</td>
			<td width="10%"><@compress single_line=true><@getNameByObj active /></@compress>图片</td>
			<td width="26%" class="left"><@compress single_line=true><@getNameByObj active /></@compress>名称【访问量】</td>
			<td width="6%" class="center">时长</td>
			<td width="8%"><@compress single_line=true><@getNameByObj active /></@compress>状态</td>
			<td width="7%">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr bgColor="#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
					<td><input type="checkbox" name="checkName" value="${l.videoId}" /></td>
					<td><@compress single_line=true><@getVideoCategory l.videoCategory /></@compress></td>
					<td><@compress single_line=true><@getVideoQuality l.videoQuality /></@compress></td>
					<td>${l.videoOrderby}</td>
					<td><img src="/upload/${l.videoPicture}" width="80" /></td>
					<td class="left">${l.videoTitle} <span style="color: #FF0000;" title="访问量">【${l.videoHit}】</span></td>
					<td>${l.videoDuring}</td>
					<td><a href="javascript:;" id="${l.videoId}" onclick="updateStatus(${l.videoId});"><#if (0 == l.videoStatus)>显示<#else><span class="red">隐藏</span></#if></a></td>
					<td><a href="${request.contextPath}/manage/video/edit/${l.videoId}">修改</a></td>
				</tr>
			</#list>
		<#else>
			<tr bgColor="F9F9F9">
				<td colspan="10">没有数据！</td>
			</tr>
		</#if>
	</table>

	<div id="operation">
		<input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName" />反选&nbsp;&nbsp;
		<input type="button" id="showAll" value="设为显示" class="button" />&nbsp;&nbsp;
		<input type="button" id="hideAll" value="设为隐藏" class="button" />
		<div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
	</div>
<#include "MainBottom.ftl" />