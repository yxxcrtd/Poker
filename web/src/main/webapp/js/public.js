$(function() {
	$("#seo").load("/upload/seo.html");
	$(".topper").load("/upload/topper.html");
	// $(".head").load("/upload/head.html"); // 当行菜单有问题
	$("#ad_index").load("/upload/ad_index.html");
	$("#ad_news").load("/upload/ad_news.html");
	$("#ad_game").load("/upload/ad_game.html");
	$("#ad_match").load("/upload/ad_match.html");
	$("#ad_action").load("/upload/ad_action.html");
	$("#ad_video").load("/upload/ad_video.html");
	$("#ad_class").load("/upload/ad_class.html");
	$(".box-news").load("/upload/index_news.html");
	$("#index_video").load("/upload/index_video.html");
	$("#index_game").load("/upload/index_game.html", function() {
		initGame();
	});
	$(".ul-event").load("/upload/index_match.html");
	$("#class_tutorial").load("/upload/class_tutorial.html", function() {
		initTutorial();
	});
	$("#class_manual").load("/upload/class_manual.html", function() {
		initManual();
	});
	$("#class_rule").load("/upload/class_game.html", function() {
		initClass();
	});
	// $(".box-game").load("/upload/index_game.html");
	$("#footer").load("/upload/footer.html");
	$("#link").load("/upload/link.html");
	$("#tips").fadeOut(10000);

	$(".btn-search").on("click", function() {
		var searchValue = $.trim($(".ipt").val());
		if ("请输入关键词搜索" == searchValue || "" == searchValue) {
			// alert("请输入搜索关键词！");
		} else {
			$("#searchForm").submit();
		}
	});
});

// 顶部二维码的关闭
$(".qrcode-s .btn-close").click(function() {
	$('.qrcode-s').slideUp('fast')
});

// 新闻排行浮动
var rankE = $(".page-news_list .box-rank,.page-news_article .box-rank");
var rankL;
var rankT;
var sTop;
var sLeft;
$(window).bind('load scroll', function() {
	sLeft = $(document).scrollLeft();
	rankFix();
})

// 新闻排行修正
function rankFix() {
	// if (!IE6()) {// 非IE6时
	sTop = $(document).scrollTop();
	// rankE.attr("offset") 底边距偏移量，产生原因未知，为固定值，因此用此种方法写在具体页面里
	if (sTop <= rankE.parent().height() - rankE.height() + rankT
			- rankE.attr("offset")) {// 判断是否超过底边距
		if (sTop >= rankT) {// 判断是否到达滚动高度
			rankE.css({
				"position" : "fixed",
				"left" : rankL - sLeft + "px",
				"top" : "0"
			})
		} else {
			rankE.css({
				"position" : "relative",
				"left" : "0",
				"top" : "0"
			})
		}
	} else {
		rankE.css({
			"position" : "absolute",
			"left" : "auto",
			"right" : "0",
			"top" : "auto"// ,
		// "bottom" : "0"
		})
		// }
	}
}

// 自适应
window.onload = function() {
	winSize();
}
window.onresize = function() {
	winSize();
}
function winSize() {
	// if (!IE6()) {
	rankE.css({
		"position" : "relative",
		"left" : "0",
		"top" : "0"
	})
	// rankL = rankE.offset().left;
	// rankT = rankE.offset().top;
	rankFix();
	// }
}

// 导航控制
// $(".banner a").removeClass("on");
// $(".banner a").eq($("body").attr("banner")).addClass("on");

// TOP条下拉框
$(".topper .msg, .topper .btn-close").click(function() {
	$(".topper .message").stop(true).slideToggle("fast");
});

function sectionGo(n) {
	if (n == 0) {
		setTimeout("javascript:sectionGo(0);", 6000);
		sectionI++;
		if (sectionI >= sectionL) {
			sectionI = 0;
		}
	}
	var e = $(".box-section .section").eq(sectionI).find(".tit");
	e.hide();
	$(".box-section .outer").stop(true).animate({
		left : '-' + sectionI * sectionW + 'px'
	}, function() {
		e.slideDown("slow")
	});
	$(".box-section .dot a").removeClass("on");
	$(".box-section .dot a").eq(sectionI).addClass("on");
}

// 快速入口效果initGame
var gameI = 0;// 游戏ID
var gameL = 6;
function initGame() {
	gameL = Math.ceil($(".ul-game li").length / 6);// 获取游戏数量
	for (var i = 0; i < gameL; i++) {// 智能添加小圆点
		$(".box-game .dot p")
				.append("<a class='disc' href='javascript:;'></a>");
	}
	$(".box-game .dot p a").eq(0).addClass("on");// 点亮第一位
	$(".ul-game").css("width", gameL * 1000 + "px");// 智能改变容器宽度
	$(".ul-game li").each(function(i) {
		if (i % 6 == 0) {// 游戏排版修正
			$(".ul-game li").eq(i - 1).addClass("fix");
		}
	});
	$(".box-game .left").click(function() {// 点击小圆点箭头
		gameI--;
		if (gameI < 0) {
			gameI = 0;
		}
		gameGo();
	});
	$(".box-game .right").click(function() {// 点击小圆点箭头
		gameI++;
		if (gameI > gameL - 1) {
			gameI = gameL - 1;
		}
		gameGo();
	});
	$(".box-game .dot .disc").each(function(i) {// 点击小圆点
		$(this).click(function() {
			gameI = i;
			gameGo();
		});
	});
}
function gameGo() {
	$(".ul-game").stop(true).animate({
		left : '-' + gameI * 1000 + 'px'
	});
	$(".box-game .dot a").removeClass("on");
	$(".box-game .dot a").eq(gameI).addClass("on");
}
// 赛事竞猜修正
$(".box-quiz .box-guess").eq(0).addClass("fix");
// 赛事竞猜效果
for (var i = 0; i <= $(".guess-list").length; i++) {
	$(".guess-list").eq(i).find("li").each(function(i) {
		if (i != 0) {
			$(this).mouseover(function() {
				$(this).addClass("on");
			});
			$(this).mouseout(function() {
				$(this).removeClass("on");
			});
		}
	});
}

// 游戏轮播图
var gameI = 1;//当前页
var gameL = 2;//页数
gameL = Math.ceil($(".ul-ico li").length / 4);// 获取游戏数量[向上取整]
console.log("gameL:"+gameL);
$(".game-intro .left").click(function() {// 点击小圆点箭头
	gameI--;
	if (gameI < 1) {
		gameI = 1;
	}
	gameGo(gameI);
});
$(".game-intro .right").click(function() {// 点击小圆点箭头
	gameI++;
	if (gameI > gameL) {
		gameI = gameL;
	}
	gameGo(gameI);
});
function gameGo(idx) {
	$(".ul-ico li").hide();
	$(".ul-ico li[tabindex='"+idx+"']").show();
}
// 游戏列表修正
for (var i = 0; i <= $(".library .outer").length; i++) {
	$(".library .outer").eq(i).find("a").each(function(i2) {
		if (i2 % 8 == 0) {
			$(".library .outer a").eq(i2 - 1).addClass("fix");
		}
	});
}
// 游戏列表切换
$(".game-list .nav a").each(function(i) {
	$(this).click(function() {
		$(".game-list .nav a").removeClass("on");
		$(".game-list .nav a").eq(i).addClass("on");
		$(".library").hide();
		$(".library").eq(i).show();
		$(".library:eq(" + i + ") .nav-s a").removeClass("on");
		$(".library:eq(" + i + ") .nav-s a").eq(0).addClass("on");
		$(".library .outer").hide();
		$(".library:eq(" + i + ") .outer:eq(0)").fadeIn();
	});
});
$(".game-list .nav-s a").each(function(i) {
	$(this).click(function() {
		$(".game-list .nav-s a").removeClass("on");
		$(".game-list .nav-s a").eq(i).addClass("on");
		$(".library .outer").hide();
		$(".library .outer").eq(i).fadeIn();
	});
});

// 赛事专区切换
$(".box-match .tit a").each(function(i) {
	$(this).click(function() {
		$(".box-match .tit a").removeClass("on");
		$(".box-match .tit a").eq(i).addClass("on");
		$(".box-match .outer").hide();
		$(".box-match .outer").eq(i).show();
	});
});

// 棋牌规则修正
var ruleL = $(".box-rule .game").length;
var ruleI = ruleL % 3;
if (ruleI == 0) {
	ruleI = 3;
}
for (var i = ruleL - ruleI; i < ruleL; i++) {
	$(".box-rule .game").eq(i).addClass("fixb");
}
$(".box-rule .game").each(function(i) {
	if (i % 3 == 0) {
		$(".box-rule .game").eq(i - 1).addClass("fixr");
	}
});

/* 棋牌学堂棋谱切换 */
function initManual() {
	$(".manual .container .tit .nav a").each(function(i) {
		$(this).click(function() {
			$(".manual .container .tit .nav a").removeClass("on");
			$(".manual .container .tit .nav a").eq(i).addClass("on");
			$(".class-news").hide();
			$(".class-news").eq(i).show();
		});
	});
}
function initTutorial() {
	/* 棋牌学堂教程切换 */
	$(".tutorial .nav a").each(function(i) {
		$(this).click(function() {
			$(".tutorial .tit .nav a").removeClass("on");
			$(".tutorial .tit .nav a").eq(i).addClass("on");
			$(".ul-tutorial").hide();
			$(".ul-tutorial").eq(i).show();
		});
	});
}

function initClass() {
	// 棋牌学堂规则切换
	$(".class-rule .tit .nav a").each(function(i) {
		$(this).click(function() {
			$(".class-rule .tit .nav a").removeClass("on");
			$(".class-rule .tit .nav a").eq(i).addClass("on");
			$(".class-rule .box-rule").hide();
			$(".class-rule .box-rule").eq(i).show();
		});
	});
}

// 最新活动修正
$(".action-event .act").each(function(i) {
	if (i % 3 == 0) {
		$(".action-event .act").eq(i - 1).addClass("fix");
	}
});

// 最新活动效果
$(".page-action .act").each(function(i) {
	$(this).mouseover(function() {
		$(this).addClass("on");
	});
	$(this).mouseout(function() {
		$(this).removeClass("on");
	});
});

// 二级活动页面的切换
$(".action-event .tit a").each(function(i) {
	$(this).click(function() {
		$(".action-event .tit a").removeClass("on");
		$(".action-event .tit a").eq(i).addClass("on");
		$(".action-event .outer").hide();
		$(".action-event .outer").eq(i).show();
	});
});

// 赛事大图修正
$(".ul-video-sort li").each(function(i) {
	if (i % 4 == 0) {
		$(".ul-video-sort li").eq(i - 1).addClass("fix");
	}
});

// 产品单页活动切换
$(".game-action .nav a").each(function(i) {
	$(this).click(function() {
		$(".game-action .nav a").removeClass("on");
		$(".game-action .nav a").eq(i).addClass("on");
		$(".game-action .tit .btn-more").hide();
		$(".game-action .tit .btn-more").eq(i).show();
		$(".game-action .box-action").hide();
		$(".game-action .box-action").eq(i).show();
	});
});

// 其它产品精品推荐修正
var otherL = $(".ul-other li").length;
$(".ul-other li").eq(otherL - 1).addClass("fix");
$(".ul-other li").eq(otherL - 2).addClass("fix");

// 其它产品精品推荐修正
$(".game-show a").eq($(".game-show a").length - 1).addClass("fix");

// 其它产品展示动画
$(".game-show a").each(function(i) {
	$(this).mouseover(function() {
		// if (IE()) {
		// $(".game-show img").css("width", "405px");
		// $(".game-show a").stop(true).animate({
		// width : "78px"
		// });
		// $(".game-show a").eq(i).stop(true).animate({
		// width : "485px"
		// });
		// } else {
		$(".game-show a").removeClass("on");
		$(".game-show a").eq(i).addClass("on");
		$(".game-show a").css("transition", "0.5s");
		// }
	});
});

// milo.ready(function() {
// need("biz.login-min", function(LoginManager) {
// LoginManager.checkLogin(function() {
// g("login_qq_span").innerHTML = LoginManager.getUserUin();// 获取QQ号
// });
// });
// });
