<#include "Obj.ftl" />

<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<title><@compress single_line=true><@getNameByObj active /></@compress>管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/manage.css" />
	</head>
	
	<body>
		<div id="mainTop">
			<span id="mainTitle">
				<@compress single_line=true><@getNameByObj active /></@compress>管理
			</span>
			<span id="tips"><#if tips??>${tips}</#if></span>
			<#if ("manage" != active && "seo" != active && "log" != active)>
				<span id="new"><a class="button1" href="${request.contextPath}/manage/${active}/edit/0">添加<@compress single_line=true><@getNameByObj active /></@compress></a></span>
			</#if>
		</div>
		
		<div id="mainMain">
			<#--<div id="mainLeft"></div>-->
			<div id="mainCenter">
