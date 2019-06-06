<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>标题：
				<input type="text" name="k" value="${k}" class="input160" onMouseOver="this.select();" placeholder="请输入<@compress single_line=true><@getNameByObj active /></@compress>标题" />
				<input type="submit" value="查 询" class="button"/>
			</span>
		</form>
	</div>
	<div>		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr id="thead">
				<td width="15%"><@compress single_line=true><@getNameByObj active /></@compress>ID</td>
				<td width="15%" class="left"><@compress single_line=true><@getNameByObj active /></@compress>操作者</td>
				<td width="35%" class="left"><@compress single_line=true><@getNameByObj active /></@compress>标题</td>
				<td width="15%" class="left"><@compress single_line=true><@getNameByObj active /></@compress>操作IP</td>
				<td width="20%" class="left"><@compress single_line=true><@getNameByObj active /></@compress>操作时间</td>
	  		</tr>
			<#if (list?? && 0 < list?size)>
				<#list list as l>
					<tr onMouseOver="changeBgColor(this, '#F9F9F9');" onMouseOut="changeBgColor(this, '#FFFFFF');">
						<td>${l.logId}</td>
						<td class="left">${l.logUser}</td>
						<td class="left">${l.logTitle}</td>
						<td class="left">${l.logIP}</td>
						<td class="left">${l.logCreateTime}</td>
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
		<div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
	</div>
	<input type=hidden id="contextPath" value=${request.contextPath}>
<#include "MainBottom.ftl" />