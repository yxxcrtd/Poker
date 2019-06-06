<!doctype html>
	<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<title>后台管理</title>
	</head>
	
	<frameset rows="50, *" id="framesetId" frameBorder="0" frameSpacing="0">
        <frame name="top" src="${request.contextPath}/manage/top" scrolling="no">
        <frameset cols="170, *">
			<frame id="menuFrame" name="menuFrame" src="${request.contextPath}/manage/menu" scrolling="auto" />
			<frame id="mainFrame" name="mainFrame" src="${request.contextPath}/manage/main" scrolling="auto" />
        </frameset>
	</frameset>
</html>