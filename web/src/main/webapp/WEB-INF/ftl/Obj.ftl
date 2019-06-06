<#macro getNameByObj obj>
	<#if obj??>
		<#if ("manage" == obj)>后台
		<#elseif ("ad" == obj)>广告
		<#elseif ("link" == obj)>友情链接
		<#elseif ("seo" == obj)>SEO设置
		<#elseif ("news" == obj)>新闻
		<#elseif ("game" == obj)>游戏库
		<#elseif ("picture" == obj)>游戏图片
		<#elseif ("news" == obj)>新闻
		<#elseif ("activity" == obj)>活动
		<#elseif ("video" == obj)>视频
		<#elseif ("chess" == obj)>棋谱
		<#elseif ("tutorial" == obj)>棋牌教程
		<#elseif ("manager" == obj)>管理员
		<#elseif ("match" == obj)>赛事
		<#elseif ("log" == obj)>日志
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<#macro getAdLocation obj>
	<#if obj??>
		<#if (1 == obj)>首页
		<#elseif (2 == obj)>新闻
		<#elseif (3 == obj)>棋牌游戏
		<#elseif (4 == obj)>赛事专区
		<#elseif (5 == obj)>活动
		<#elseif (6 == obj)>视频
		<#elseif (7 == obj)>棋牌学堂
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<#macro getAdVisible obj>
	<#if obj??>
		<#if (0 == obj)>显示
		<#elseif (1 == obj)><span class="red">隐藏</span>
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<#macro getMatchStatus obj>
	<#if obj??>
		<#if (0 == obj)>即将开始
		<#elseif (1 == obj)>进行中
		<#elseif (2 == obj)>已结束
		<#else>Oops！
		</#if>
	</#if>
</#macro>
<#macro getStyleByStatus obj>
	<#if obj??>
		<#if (1 == obj)>class="step2"
		<#elseif (2 == obj)>class="step3"
		<#else>
		</#if>
	</#if>
</#macro>

<#macro getVideoCategory obj>
	<#if obj??>
		<#if (0 == obj)>赛事直播
		<#elseif (1 == obj)>赛事回放
		<#elseif (2 == obj)>专题栏目
		<#elseif (3 == obj)>德州扑克
		<#elseif (4 == obj)>中国象棋
		<#elseif (5 == obj)>斗地主
		<#elseif (6 == obj)>其他
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<#macro getVideoQuality obj>
	<#if obj??>
		<#if (0 == obj)>蓝光
		<#elseif (1 == obj)>超清
		<#elseif (2 == obj)>高清
		<#elseif (3 == obj)>标清
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<#macro getVideoStatus obj>
	<#if obj??>
		<#if (0 == obj)>显示
		<#elseif (1 == obj)><span class="red">隐藏</span>
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<#macro getActionStatus obj>
	<#if obj??>
		<#if (0 == obj)>准备
		<#elseif (1 == obj)>进行中
		<#elseif (2 == obj)>已结束
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<#macro getActionCategory obj>
	<#if obj??>
		<#if (0 == obj)>牌类
		<#elseif (1 == obj)>棋类
		<#elseif (2 == obj)>麻将
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<#macro getNewsCategory obj>
	<#if obj??>
		<#if (0 == obj)>综合新闻
		<#elseif (1 == obj)>德州扑克
		<#elseif (2 == obj)>斗地主
		<#elseif (3 == obj)>麻将
		<#elseif (4 == obj)>棋类
		<#elseif (9 == obj)>其他
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<#macro getNewsType obj>
	<#if obj??>
		<#if (0 == obj)><em class="tag tag1">新闻</em>
		<#elseif (1 == obj)><em class="tag tag2">赛事</em>
		<#elseif (2 == obj)><em class="tag tag3">公告</em>
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<#macro getNewsStatus obj>
	<#if obj??>
		<#if (0 == obj)>显示
		<#elseif (1 == obj)>不显示
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<#macro getSearchObj obj>
	<#if obj??>
		<#if ("match" == obj)>赛事
		<#elseif ("video" == obj)>视频
		<#elseif ("game" == obj)>游戏
		<#elseif ("action" == obj)>活动
		<#elseif ("news" == obj)>新闻
		<#else>Oops！
		</#if>
	</#if>
</#macro>
