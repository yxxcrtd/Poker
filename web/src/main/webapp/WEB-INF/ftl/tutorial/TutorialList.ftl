<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>分类：
				<select name="s1">
					<option value="0" <#if (0 == s1)>selected="selected"</#if> >全部</option>
					<option value="1" <#if (1 == s1)>selected="selected"</#if> >德州扑克</option>
					<option value="2" <#if (2 == s1)>selected="selected"</#if> >斗地主</option>
					<option value="3" <#if (3 == s1)>selected="selected"</#if> >麻将</option>
					<option value="4" <#if (4 == s1)>selected="selected"</#if> >棋类</option>
					<option value="9" <#if (5 == s1)>selected="selected"</#if> >其他</option>
				</select>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>状态：
		        <select name="s2">
		          <option value="2" <#if (2 == s2)>selected="selected"</#if> >全部</option>
		          <option <#if (0 == s2)>selected="selected"</#if> value="0">显示</option>
		          <option <#if (1 == s2)>selected="selected"</#if> value="1">隐藏</option>
		        </select>
			</span>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>名称：
				<input type="text" name="k" value="${k}" class="input160" onMouseOver="this.select();" placeholder="请输入<@compress single_line=true><@getNameByObj active /></@compress>名称" />
				<input type="submit" id="sub" value="查 询" class="button"/>
			</span>
		</form>
	</div>
	<div>		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr id="thead">
				<td width="5%"><input type="checkbox" class="checkbox" id="chk_all" title="全选/全不选"/></td>
				<td width="10%"><@compress single_line=true><@getNameByObj active /></@compress>分类</td>
				<td width="30%"><@compress single_line=true><@getNameByObj active /></@compress>标题【访问量】</td>
				<td width="5%" class="center">排序</td>
				<td width="15%" class="center">显示状态</td>
				<td width="20%" class="center">发布时间</td>
				<td width="15%">操作</td>
	  		</tr>
			<#if (list?? && 0 < list?size)>
				<#list list as l>
					<tr bgColor="#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
						<td><input type="checkbox" name="chk_list" value="${l.tutorialId}"/></td>
						<td><@compress single_line=true><@getNewsCategory l.tutorialCategory /></@compress></td>
						<td class="left">${l.tutorialTitle}<span style="color: #FF0000;" title="访问量">【${l.tutorialHit}】</span></td>
						<td>${l.tutorialOrderby}</td>
						<td><a href="javascript:()" id="${l.tutorialId}" onclick="updateStatus(${l.tutorialId});"><#if (0 == l.tutorialStatus)>显示<#else><span class="red">隐藏</span></#if></a></td>
						<td>${l.tutorialCreateTime}</td>
						<td><a href="${request.contextPath}/manage/tutorial/edit/${l.tutorialId}">编辑</a></td>
					</tr>
				</#list>
			<#else>
				<tr bgColor="F9F9F9">
					<td colspan="7">没有数据！</td>
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