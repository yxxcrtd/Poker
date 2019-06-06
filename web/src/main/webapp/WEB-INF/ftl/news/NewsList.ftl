<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>分类：
				<select name="s1">
					<option value="">全部</option>
					<option value="0" <#if (0 == status1)>selected="selected"</#if>>综合新闻</option>
					<option value="1" <#if (1 == status1)>selected="selected"</#if>>德州扑克</option>
					<option value="2" <#if (2 == status1)>selected="selected"</#if>>斗地主</option>
					<option value="3" <#if (3 == status1)>selected="selected"</#if>>麻将</option>
					<option value="4" <#if (4 == status1)>selected="selected"</#if>>棋类</option>
					<option value="9" <#if (9 == status1)>selected="selected"</#if>>其他</option>
				</select>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>类型：
				<select name="s2">
					<option value="">全部</option>
					<option value="0" <#if (0 == status2)>selected="selected"</#if>>新闻</option>
					<option value="1" <#if (1 == status2)>selected="selected"</#if>>赛事</option>
					<option value="2" <#if (2 == status2)>selected="selected"</#if>>公告</option>
				</select>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>状态：
		        <select name="s3">
		          <option value="">全部</option>
		          <option <#if (0 == status3)>selected="selected"</#if> value="0">显示</option>
		          <option <#if (1 == status3)>selected="selected"</#if> value="1">隐藏</option>
		        </select>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>名称：
				<input type="text" name="k" value="${k}" class="input160" onMouseOver="this.select();" placeholder="请输入<@compress single_line=true><@getNameByObj active /></@compress>名称" />
				<input type="submit" value="查 询" class="button"/>
			</span>
		</form>
	</div>
	<div>		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr id="thead">
				<td width="5%"><input type="checkbox" class="checkbox" id="chk_all" title="全选/全不选"/></td>
				<td width="10%"><@compress single_line=true><@getNameByObj active /></@compress>分类</td>
				<td width="10%"><@compress single_line=true><@getNameByObj active /></@compress>类型</td>
				<td width="10%"><@compress single_line=true><@getNameByObj active /></@compress>图片</td>
				<td width="25%" class="left"><@compress single_line=true><@getNameByObj active /></@compress>标题【访问量】</td>
				<td width="7%" class="center">排序</td>
				<td width="8%" class="center">显示状态</td>
				<td width="17%" class="center">发布时间</td>
				<td width="8%">操作</td>
	  		</tr>
			<#if (newsList?? && 0 < newsList?size)>
				<#list newsList as l>
					<tr onMouseOver="changeBgColor(this, '#F9F9F9');" onMouseOut="changeBgColor(this, '#FFFFFF');">
						<td><input type="checkbox" name="chk_list" value="${l.newsId}" /></td>
						<td><@compress single_line=true><@getNewsCategory l.newsCategory /></@compress></td>
						<td><@compress single_line=true><@getNewsType l.newsType /></@compress></td>
						<td><img src="/upload/${l.newsPicture}" width="120" alt="${l.newsTitle}" /></td>
						<td class="left">${l.newsTitle} <span style="color: #FF0000;" title="访问量">【${l.newsHit}】</span></td>
						<td>${l.newsOrderby}</td>
						<td><a href="javascript:()" id="${l.newsId}" onclick="updateStatus(${l.newsId});"><#if (0 == l.newsStatus)>显示<#else><span class="red">隐藏</span></#if></a></td>
						<td>${l.newsCreateTime}</td>
						<td><a href="${request.contextPath}/manage/news/edit/${l.newsId}">编辑</a></td>
					</tr>
				</#list>
			<#else>
				<tr bgColor="F9F9F9">
					<td colspan="10">没有数据！</td>
				</tr>
			</#if>
		</table>
	</div>	
	<div id="operation">
		<input type="checkbox" class="checkbox" id="un_CheckAll" />反选&nbsp;&nbsp;
	  	<input type="button" id="showAll" value="设为显示" class="button" />&nbsp;&nbsp;
		<input type="button" id="hideAll" value="设为隐藏" class="button" />
		<div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
	</div>
	<input type=hidden id="contextPath" value=${request.contextPath}>
<#include "MainBottom.ftl" />