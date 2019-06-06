<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><#--if 0 == obj?uncap_first.obj?uncap_first-Id>发布<#else>修改</#if>？</title-->
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag">
					<a href="${request.contextPath}" title="首页">首页</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					<a href="${request.contextPath}/manage/service" title="？列表">？列表</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					<#--if (0 == obj?uncap_first.obj?uncap_first-Id)>发布<#else>修改</#if-->？：
				</span>
			</div>
			
			<form action="${request.contextPath}/manage/${obj?uncap_first}/save" method="post">
				
			    <div class="label">？标题：<span class="star">*</span></div>
				<div>
					<#--@s.formInput "objChineseTitle" "class='input'" /><@s.showErrors classOrStyle="red" /-->
				</div>
				
				<div id="objPicture">
					<div class="label">？图片：（图片的尺寸：宽320像素 * 高240像素）</div>
					<div>
						<input type="file" class="input" style="width: 300px;" name="file" />
						<#--@s.formHiddenInput "obj?uncap_first.obj?uncap_first-Picture" /><@s.showErrors classOrStyle="red" /-->
						<#--if (0 lt obj?uncap_first.obj?uncap_first-Id && obj?uncap_first.obj?uncap_first-Picture??)>
							<br />
							<img src="/upload/ad/${obj?uncap_first.obj?uncap_first-Picture}" />
						</#if-->
					</div>
				</div>
				
				<div id="operation">
					<input type="submit" value="<#--if (0 == obj?uncap_first.obj?uncap_first-Id)>保 存<#else>修 改</#if-->" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
				<#--@s.formHiddenInput "obj?uncap_first.obj?uncap_first-Id" /-->
			</form>
		</div>
	</body>
</html>
