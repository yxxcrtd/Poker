<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/chess/save" method="post" enctype="multipart/form-data">
		<div class="label">显示状态：<@s.formRadioButtons "chess.chessStatus" visibleMap /></div>
		<div class="label">棋局结果：<@s.formRadioButtons "chess.chessResult" resultMap /></div>

	    <div class="label"><@compress single_line=true><@getNameByObj active /></@compress>标题：<span class="star">*</span></div>
		<div><@s.formInput "${active}.chessTitle" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
			
		<div class="label"><@compress single_line=true><@getNameByObj active /></@compress>摘要：<span class="star">*</span></div>
		<div><@s.formInput "${active}.${active}Summary" "class='input500' maxlength='128'" /><@s.showErrors classOrStyle="red" /></div>
			
		<div class="label">添加人：<span class="star">*</span></div>
		<div><@s.formInput "${active}.chessCreateUser" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
		<!--
		<div class="label">时间：<span class="star">*</span></div>
		<div><@s.formInput "${active}.chessCreateTime" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
		-->
		<div class="label">棋谱排序：<span class="star">*</span></div>
		<div><@s.formInput "${active}.chessOrderby" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
				
		
		<div class="label">红方名称：<span class="star">*</span></div>
		<div><@s.formInput "${active}.chessRed" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
		<!--
		<div class="label">红方国际：<span class="star">*</span></div>
		<div><@s.formInput "${active}.chessRedCountry" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
		-->		
		<div class="label">红方称号：<span class="star">*</span></div>
		<div><@s.formInput "${active}.chessRedLevel" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label">红方图片：</div>
		<div>
			<input type="file" name="red" id="red" class="input" style="padding: 1px;" />
				<@s.formHiddenInput "${active}.chessRedPic" /><@s.showErrors classOrStyle="red" />
				<#if (0 lt chess.chessRedPic && chess.chessRedPic??)>
					<br />
					<img src="/upload/${chess.chessRedPic}" />
				</#if>
		</div>
		
		<div class="label">黑方名称：<span class="star">*</span></div>
		<div><@s.formInput "${active}.chessBlack" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
		<!--
		<div class="label">黑方国际：<span class="star">*</span></div>
		<div><@s.formInput "${active}.chessBlackCountry" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
		-->		
		<div class="label">黑方称号：<span class="star">*</span></div>
		<div><@s.formInput "${active}.chessBlackLevel" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>
		
		<div class="label">黑方图片：</div>
		<div>
			<input type="file" name="black" id="black" class="input" style="padding: 1px;" />
				<@s.formHiddenInput "${active}.chessBlackPic" /><@s.showErrors classOrStyle="red" />
				<#if (0 lt chess.chessBlackPic && chess.chessBlackPic??)>
					<br />
					<img src="/upload/${chess.chessBlackPic}" />
				</#if>
		</div>
		
		<input type="hidden" id="chessProcess" name="chessProcess" value="${(chess.chessProcess)!}"/>
		<#if (0 == chess.chessId && 20> chess.chessProcess?length)>
		<div>
			<iframe id="chessPk" src="${request.contextPath}/upload/chessFight.html" style="width: 480px; height: 480px;" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no"></iframe> 
			<span class="red" id="checkPk"><#if (operation == "save" && 20> chess.chessProcess?length) > 请先完成棋局对战(至少对战5步) </#if></span></div>
		<div id="operation">
			<input type="submit" id="submit" value="确 认" class="button icon_save" />&nbsp;&nbsp;
		<#else>
		<div id="operation">
			<input type="submit" value="确 认" class="button icon_save" />&nbsp;&nbsp;
		</#if>
			<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "${active}.chessId" />
		<@s.formHiddenInput "${active}.chessCreateTime" />
	</form>
<#include "MainBottom.ftl" />