<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/game/save" method="post" enctype="multipart/form-data">
		<div class="label">游戏类别：</div>
		<div>
			<@s.formSingleSelect "${active}.gameCategoryId" categoryMap "class='selectStyle'" />
		</div>
		
		<div class="label">游戏分区：<@s.formRadioButtons "game.gameType" typeMap /></div>
		
	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>名称：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Title" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
				
		<div class="label">游戏描述：</div>
		<div><textarea id="gameHelp" name="gameHelp" style='width: 60%; height: 80px;'>${game.gameHelp}</textarea><@s.showErrors classOrStyle="red" /></div>
				
		<div class="label">官网地址：<span class="star">*</span></div>
		<div><@s.formInput "${active}.gameOfficialUrl" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label">下载地址：<span class="star">*</span></div>
		<div><@s.formInput "${active}.gameLoadUrl" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label">游戏排序：<span class="star">*</span></div>
		<div><@s.formInput "${active}.gameOrderby" "class='input100'" /><@s.showErrors classOrStyle="red" /></div>
				
		<div id="objPicture">
			<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>图片：<span class="star">*</span></div>
			<div>
				<input type="file" name="file" id="file" class="input" style="width: 400px; padding: 1px;" name="file" />
				<@s.formHiddenInput "${active}.gamePicture" /><@s.showErrors classOrStyle="red" />
				<#if (0 lt game.gamePicture && game.gamePicture??)>
					<br />
					<img src="/upload/${game.gamePicture}" style="width: 300px; height:200px"/>
				</#if>
			</div>
		</div>
		
		<div class="label">棋牌规则：<span class="star">*</span></div>
		<div><@s.formTextarea "${active}.gameRule" "class='input', style='width: 800px; height: 380px;'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label">显示状态：<@s.formRadioButtons "game.gameStatus" visibleMap /></div>
		
		<div class="label">棋牌规则显示状态：<@s.formRadioButtons "game.gameRuleStatus" visibleMap /></div>
		
		<div id="operation">
			<input type="submit" value="确 定" class="button icon_save" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "${active}.gameId" />
	</form>
		<script language="javascript" src="${request.contextPath}/js/kindeditor.js"></script>
<#include "MainBottom.ftl" />