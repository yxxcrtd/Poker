<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>
		<link type="text/css" rel="styleSheet" href="/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag">
					<a href="" title="首页">首页</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					<a href="/manage/service" title="？列表">？列表</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					？：
				</span>
			</div>
			
			<form action="/manage/user/save" method="post">
				
			    <div class="label">？标题：<span class="star">*</span></div>
				<div>
				</div>
				
				<div id="objPicture">
					<div class="label">？图片：（图片的尺寸：宽320像素 * 高240像素）</div>
					<div>
						<input type="file" class="input" style="width: 300px;" name="file" />
					</div>
				</div>
				
				<div id="operation">
					<input type="submit" value="" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
			</form>
		</div>
	</body>
</html>
