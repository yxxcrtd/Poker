<#include "../Obj.ftl" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<div id="seo"></div>
		<link rel="stylesheet" type="text/css" href="./css/style.css" />
	</head>
	
	<body class="page-action" banner="4">
		<div class="topper"></div>
		<div class="head">
			<div class="container">
				<div class="box-search pr">
					<a class="logo sp pa" href="/" title="腾讯棋牌"></a>
					<div class="qrcode-s pa">
						<a class="btn-close sp pa" href="javascript:;" title="关闭"></a>
						<img width="91" height="91" src="images/qrcode-s.jpg" />
					</div>
					<div class="search sp mc pr">
				<form action="/search" method="post" id="searchForm">
					<input class="ipt pa" type="text" name="k" value="请输入关键词搜索"
						onfocus="if(value=='请输入关键词搜索'){value=''}"
						onblur="if(value==''){value='请输入关键词搜索'}" />
						<a class="btn-search pa" href="javascript:;" title="搜索"></a>
				</form>
					</div>
				</div>
				<div class="banner pr">
					<i class="sp left pa"></i>
					<i class="sp right pa"></i>
					<a href="/">首页</a>
					<a href="news.html">新闻动态</a>
					<a href="game.html">棋牌游戏库</a>
					<a href="match.html">赛事专区</a>
					<a href="action.html" id="current">最新活动</a>
					<a href="video.html">视频专区</a>
					<a href="class.html">棋牌学堂</a>
				</div>
			</div>
		</div>

		<div class="box-hot c">
			<div class="hot fl pr" id="ad_action"></div>

			<div class="action-news fr">
				<ul class="ul-news-act">
					<#if (list?? && 0 < list?size)>
						<#list list as l>
							<li<#if (0 == l_index)> class="headline"</#if>><a href="/upload/action${l.activityId}.html" target="_blank"><#if (0 < l_index)><i></i></#if><#if (0 == l_index)><em></#if>${l.activityTitle}<#if (0 == l_index)></em>${l.activityBasicRules}</#if></a></li>
							<#if (6 == l_index)><#break /></#if>
						</#list>
					</#if>
				</ul>
			</div>
		</div>
	</div>
</div>


<div class="action-event">
	<div class="container c">
		<div class="tit pr">
			<p class="nav pa">
				<a class="on" href="javascript:;">全部</a>
				<a href="javascript:;">牌类</a>
				<a href="javascript:;">棋类</a>
				<a href="javascript:;">麻将</a>
			</p>
		</div>
		
		<div class="outer" style="display: block;"><#--step2多少天后结束；step3已结束-->
			<#if (list?? && 0 < list?size)>
				<#list list as l>
					<a class="act <#if (2 == l.activityStatus)>step3</#if> pr" href="/upload/action${l.activityId}.html" target="_blank">
						<img width="304" height="134" src="/upload/${l.activityPicture}" />
						<p class="follow-num blank pa">已有<em>${l.activityHit}</em>人关注</p>
						<p class="tag sp pa"><#if (1 == l.activityStatus)>正在<br />进行中<#elseif (2 == l.activityStatus)>已结束</#if></p>
						<p class="act-time mc">
							<em>${l.activityTitle}</em>
							<span>活动时间：${l.activityBeginDate?string("yyyy-MM-dd")}~${l.activityEndDate?string("yyyy-MM-dd")}</span>
						</p>
						<p class="act-txt pa">
							<em>活动简介</em>
							${l.activityBasicRules}
							<span class="btn-detail pa"><i class="sp"></i>查看活动详情</span>
						</p>
					</a>
				</#list>
			</#if>
		</div>
		<div class="outer">
			<#if (category_0_list?? && 0 < category_0_list?size)>
				<#list category_0_list as l>
					<a class="act <#if (2 == l.activityStatus)>step3</#if> pr" href="/upload/action${l.activityId}.html" target="_blank">
						<img width="304" height="134" src="/upload/${l.activityPicture}" />
						<p class="follow-num blank pa">已有<em>${l.activityHit}</em>人关注</p>
						<p class="tag sp pa"><#if (1 == l.activityStatus)>正在<br />进行中<#elseif (2 == l.activityStatus)>已结束</#if></p>
						<p class="act-time mc">
							<em>${l.activityTitle}</em>
							<span>活动时间：${l.activityBeginDate?string("yyyy-MM-dd")}~${l.activityEndDate?string("yyyy-MM-dd")}</span>
						</p>
						<p class="act-txt pa">
							<em>活动简介</em>
							${l.activityBasicRules}
							<span class="btn-detail pa"><i class="sp"></i>查看活动详情</span>
						</p>
					</a>
				</#list>
			</#if>
		</div>
		<div class="outer">
			<#if (category_1_list?? && 0 < category_1_list?size)>
				<#list category_1_list as l>
					<a class="act <#if (2 == l.activityStatus)>step3</#if> pr" href="/upload/action${l.activityId}.html" target="_blank">
						<img width="304" height="134" src="/upload/${l.activityPicture}" />
						<p class="follow-num blank pa">已有<em>${l.activityHit}</em>人关注</p>
						<p class="tag sp pa"><#if (1 == l.activityStatus)>正在<br />进行中<#elseif (2 == l.activityStatus)>已结束</#if></p>
						<p class="act-time mc">
							<em>${l.activityTitle}</em>
							<span>活动时间：${l.activityBeginDate?string("yyyy-MM-dd")}~${l.activityEndDate?string("yyyy-MM-dd")}</span>
						</p>
						<p class="act-txt pa">
							<em>活动简介</em>
							${l.activityBasicRules}
							<span class="btn-detail pa"><i class="sp"></i>查看活动详情</span>
						</p>
					</a>
				</#list>
			</#if>
		</div>
		<div class="outer">
			<#if (category_2_list?? && 0 < category_2_list?size)>
				<#list category_2_list as l>
					<a class="act <#if (2 == l.activityStatus)>step3</#if> pr" href="/upload/action${l.activityId}.html" target="_blank">
						<img width="304" height="134" src="/upload/${l.activityPicture}" />
						<p class="follow-num blank pa">已有<em>${l.activityHit}</em>人关注</p>
						<p class="tag sp pa"><#if (1 == l.activityStatus)>正在<br />进行中<#elseif (2 == l.activityStatus)>已结束</#if></p>
						<p class="act-time mc">
							<em>${l.activityTitle}</em>
							<span>活动时间：${l.activityBeginDate?string("yyyy-MM-dd")}~${l.activityEndDate?string("yyyy-MM-dd")}</span>
						</p>
						<p class="act-txt pa">
							<em>活动简介</em>
							${l.activityBasicRules}
							<span class="btn-detail pa"><i class="sp"></i>查看活动详情</span>
						</p>
					</a>
				</#list>
			</#if>
		</div>

	</div>
</div>




		<div class="footer">
			<div class="container pr">
				<div id="link"></div>
				<div id="footer"></div>		
				<script language="javaScript" src="./js/jquery.js"></script>
				<script language="javaScript" src="./js/public.js"></script>
				<script language="javaScript" src="./js/jquery.slideBox.js"></script>
