var play = play || {};

play.init = function() {

	play.my = 1; // 玩家红方
	play.map = com.arr2Clone(com.initMap); // 初始化棋盘
	play.nowManKey = false; // 现在要操作的棋子
	play.nowPlayer = 1; // 现在要操作的玩家
	play.pace = []; // 记录每一步
	play.isPlay = true; // 是否能走棋
	play.mans = com.mans;
	play.bylaw = com.bylaw;
	play.show = com.show;
	play.showPane = com.showPane;
	// play.isOffensive = true; // 是否先手
	// play.depth = play.depth || 3; // 搜索深度

	play.isFoul = false; // 是否犯规长将

	com.pane.isShow = false; // 隐藏方块

	// 初始化棋子
	for (var i = 0; i < play.map.length; i++) {
		for (var n = 0; n < play.map[i].length; n++) {
			var key = play.map[i][n];
			if (key) {
				com.mans[key].x = n;
				com.mans[key].y = i;
				com.mans[key].isShow = true;
			}
		}
	}
	play.show();
	refresh();
	// clearInterval(play.timer);
	// com.get("autoPlay").addEventListener("click", function(e) {
	// clearInterval(play.timer);
	// play.timer = setInterval("play.AIPlay()",1000);
	// play.AIPlay()
	// })
	/*
	 * com.get("offensivePlay").addEventListener("click", function(e) {
	 * play.isOffensive=true; play.isPlay=true ;
	 * com.get("chessRight").style.display = "none"; play.init(); })
	 * 
	 * com.get("defensivePlay").addEventListener("click", function(e) {
	 * play.isOffensive=false; play.isPlay=true ;
	 * com.get("chessRight").style.display = "none"; play.init(); })
	 */

	/*
	 * var initTime = new Date().getTime(); for (var i=0; i<=100000; i++){
	 * 
	 * var h="" var h=play.map.join(); //for (var n in play.mans){ // if
	 * (play.mans[n].show) h+=play.mans[n].key+play.mans[n].x+play.mans[n].y //} }
	 * var nowTime= new Date().getTime(); z([h,nowTime-initTime])
	 */

}

// 悔棋
play.regret = function() {
	/* 如果 */
	if (play.pace == null || play.pace == "" || play.pace.length == 0) {
		var elem = parent.document.getElementById("checkPk");
		elem.innerHTML = "尚未开始对局，不能悔棋";
		return false;
	}
	var map = com.arr2Clone(com.initMap);
	// 初始化所有棋子
	for (var i = 0; i < map.length; i++) {
		for (var n = 0; n < map[i].length; n++) {
			var key = map[i][n];
			if (key) {
				com.mans[key].x = n;
				com.mans[key].y = i;
				com.mans[key].isShow = true;
			}
		}
	}
	var pace = play.pace;
	pace.pop();
	// pace.pop();

	for (var i = 0; i < pace.length; i++) {
		var p = pace[i].split("");
		var x = parseInt(p[0], 10);
		var y = parseInt(p[1], 10);
		var newX = parseInt(p[2], 10);
		var newY = parseInt(p[3], 10);
		var key = map[y][x];
		// try{

		var cMan = map[newY][newX];
		if (cMan)
			com.mans[map[newY][newX]].isShow = false;
		com.mans[key].x = newX;
		com.mans[key].y = newY;
		map[newY][newX] = key;
		delete map[y][x];
		if (i == pace.length - 1) {
			com.showPane(newX, newY, x, y);
		}
		// } catch (e){
		// com.show()
		// z([key,p,pace,map])

		// }
	}
	play.map = map;
	play.my = 0 - play.my;
	play.isPlay = true;
	com.show();
	refresh();
}
// 点击棋盘事件
play.clickCanvas = function(e) {
	console.log("nowManKey");
	console.log(play.nowManKey);
	if (!play.isPlay) {
		console.log("对战已结束或不可对战");
		return false;
	}
	var key = play.getClickMan(e);
	var point = play.getClickPoint(e);

	var x = point.x;
	var y = point.y;
	if (key) {
		// 点击棋子（选择或吃子） && com.mans[key].my == play.my
		var man = com.mans[key];
		console.log("clickMan");
		console.log(man);
		// 吃子(有选中棋子，且该棋子！=点击的棋子，且两颗棋子属于不同方,且棋子可移动至目标位置)
		if (play.nowManKey && play.nowManKey != key
				&& man.my != com.mans[play.nowManKey].my
				&& play.indexOfPs(com.mans[play.nowManKey].ps, [ x, y ])) {
			play.clickEatMan(man, x, y);
		} else if (man.my == play.my) {
			// 选中棋子(点击的棋子属于当前点击者的棋子)
			play.clickSelectMan(man, x, y);
		} else {
			console.log("选择棋子有误");
		}
		// play.clickMan(key, x, y);
	} else if (play.nowManKey) {
		// 点击着点（移动棋子）
		play.clickPoint(x, y);
	} else {
		console.log("点击有误");
	}
	play.isFoul = play.checkFoul();// 检测是不是长将
	refresh();
	/* 检测是否死棋 TODO */
}

// 选中棋子，准备移动
play.clickSelectMan = function(man, x, y) {
	console.log("选择棋子，移动");
	// 如果已经选择过棋子，取消选择
	if (com.mans[play.nowManKey])
		com.mans[play.nowManKey].alpha = 1;
	man.alpha = 0.6;
	com.pane.isShow = false;
	play.nowManKey = man.key;
	man.ps = man.bl(); // 获得所有能着点
	com.dot.dots = man.ps;
	com.show();
	com.get("selectAudio").play();

}
// 点击棋子,并吃子
play.clickEatMan = function(man, x, y) {
	console.log("吃子");
	// 隐藏被吃棋子
	man.isShow = false;
	var pace = com.mans[play.nowManKey].x + "" + com.mans[play.nowManKey].y;
	// z(bill.createMove(play.map,man.x,man.y,x,y))
	// 删除移动的棋子，并把把目标位置放置为该棋子
	delete play.map[com.mans[play.nowManKey].y][com.mans[play.nowManKey].x];
	play.map[y][x] = play.nowManKey;
	com.showPane(com.mans[play.nowManKey].x, com.mans[play.nowManKey].y, x, y);
	com.mans[play.nowManKey].x = x;
	com.mans[play.nowManKey].y = y;
	com.mans[play.nowManKey].alpha = 1;

	play.pace.push(pace + x + y);
	play.nowManKey = false;
	com.pane.isShow = false;
	com.dot.dots = [];
	com.show();
	com.get("clickAudio").play();
	console.log(man.key);
	if (man.key == "j0")
		play.showWin(-1);
	if (man.key == "J0")
		play.showWin(1);
	play.my = 0 - play.my;
}
// 点击着点
play.clickPoint = function(x, y) {
	var key = play.nowManKey;
	var man = com.mans[key];
	if (play.indexOfPs(com.mans[key].ps, [ x, y ])) {
		var pace = man.x + "" + man.y
		// z(bill.createMove(play.map,man.x,man.y,x,y))
		delete play.map[man.y][man.x];
		play.map[y][x] = key;
		com.showPane(man.x, man.y, x, y)
		man.x = x;
		man.y = y;
		man.alpha = 1;
		play.pace.push(pace + x + y);
		play.nowManKey = false;
		com.dot.dots = [];
		com.show();
		com.get("clickAudio").play();
		play.my = 0 - play.my;
	} else {
		console.log("不能这么走哦！");
	}

}

// 检查是否长将
play.checkFoul = function() {
	var p = play.pace;
	var len = parseInt(p.length, 10);
	if (len > 11 && p[len - 1] == p[len - 5] && p[len - 5] == p[len - 9]) {
		return p[len - 4].split("");
	}
	return false;
}

play.indexOfPs = function(ps, xy) {
	for (var i = 0; i < ps.length; i++) {
		if (ps[i][0] == xy[0] && ps[i][1] == xy[1])
			return true;
	}
	return false;

}

// 获得点击的着点
play.getClickPoint = function(e) {
	var domXY = com.getDomXY(com.canvas);
	var x = Math.round((e.pageX - domXY.x - com.pointStartX - 20) / com.spaceX)
	var y = Math.round((e.pageY - domXY.y - com.pointStartY - 20) / com.spaceY)
	return {
		"x" : x,
		"y" : y
	}
}

// 获得棋子
play.getClickMan = function(e) {
	var clickXY = play.getClickPoint(e);
	var x = clickXY.x;
	var y = clickXY.y;
	if (x < 0 || x > 8 || y < 0 || y > 9)
		return false;
	return (play.map[y][x] && play.map[y][x] != "0") ? play.map[y][x] : false;
}

play.showWin = function(my) {
	play.isPlay = false;
	if (my == 1) {
		alert("红方赢了！");
	} else {
		alert("黑方赢了！");
	}
}
// 淡出效果(含淡出到指定透明度)
function fadeout(ele, speed, opacity) {
	if (ele) {
		var v = ele.style.filter.replace("alpha(opacity=", "").replace(")", "")
				|| ele.style.opacity || 100;
		v < 1 && (v = v * 100);
		var count = speed / 1000;
		var avg = (100 - opacity) / count;
		var timer = null;
		timer = setInterval(function() {
			if (v - avg > opacity) {
				v -= avg;
				setOpacity(ele, v);
			} else {
				clearInterval(timer);
			}
		}, 500);
	}
}
function setOpacity(ele, opacity) {
	if (ele.style.opacity != undefined) {
		// /兼容FF和GG和新版本IE
		ele.style.opacity = opacity / 100;

	} else {
		// /兼容老版本ie
		ele.style.filter = "alpha(opacity=" + opacity + ")";
	}
}
// function fadeOut(elem, speed, opacity) {
// /*
// * 29 * 参数说明 30 * elem==>需要淡入的元素 31 * speed==>淡入速度,正整数(可选) 32 *
// * opacity==>淡入到指定的透明度,0~100(可选) 33
// */
// speed = speed || 20;
// opacity = opacity || 0;
// // 初始化透明度变化值为0
// var val = 100;
// // 循环将透明值以5递减,即淡出效果
// (function() {
// iBase.SetOpacity(elem, val);
// val -= 5;
// if (val >= opacity) {
// setTimeout(arguments.callee, speed);
// } else if (val < 0) {
// // 元素透明度为0后隐藏元素
// elem.style.display = 'none';
// }
// })();
// }
