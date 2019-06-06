<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				<@compress single_line=true><@getNameByObj active /></@compress>类别：
		        <select id="searchCategory">
		          <option selected  value="0">全部</option>
		          <option value="1">象棋</option>
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
			<td width="30%">棋谱标题【访问量】</td>
			<td width="15%" class="center">红方VS黑方</td>
			<td width="15%">时间</td>
			<td width="10%">排序</td>
			<td width="5%">显示状态</td>
			<td width="10%">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr bgColor="#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
					<td><input type="checkbox" name="checkName" value="${l.chessId}" /></td>
					<td class="left">${l.chessTitle}<span style="color: #FF0000;" title="访问量">【${l.chessHit}】</span></td>
					<td>${l.chessRed} VS ${l.chessBlack}</td>
					<td>${(l.chessCreateTime?string("yyyy-MM-dd"))!}</td>
					<td>${l.chessOrderby}</td>
					<td><a href="javascript:()" id="${l.chessId}" onclick="updateStatus(${l.chessId});"><#if (0 == l.chessStatus)>显示<#else><span class="red">隐藏</span></#if></a></td>
					<td><a href="${request.contextPath}/manage/chess/edit/${l.chessId}">编辑</a>&nbsp;&nbsp;</td>
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