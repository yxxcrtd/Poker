/* 棋子可动，没有棋谱*/
window.onload = function() {
	com.bg = new com.class.Bg();
	com.dot = new com.class.Dot();
	com.pane = new com.class.Pane();
	com.pane.isShow = false;

	com.childList = [ com.bg, com.dot, com.pane ];
	com.mans = {}; // 棋子集合
	com.createMans(com.initMap); // 生成棋子
	com.bg.show();
	com.get("chessRight").style.display = "none";
	// play.init();
	com.get("saveBn").onclick = function(e) {
		if (play.pace == null || play.pace == "") {
			parent.document.getElementById("checkPk").innerHTML = "请先进行棋局";
			// alert("请先进行棋局！");
			return false;
		}
		// this.disabled = "true";
		// com.get("saveBn").addEventListener("click", function(e) {
		// alert("已生成棋谱,请保存棋局！");
		// });
	};
	com.get("fight").onclick = function(e) {
		play.isPlay = true;
		play.init();
		this.value = "重新开始";
		// com.get("saveBn").disabled = "false";
		// com.get("regretBn").disabled = "false";
		com.get("fight").onclick = function() {
			if (confirm("已经开始对弈,确认重新开始？")) {
				play.isPlay = true;
				play.init();
			}
		}
		// if (confirm("确认开始对弈？")) {
		// }
	}
	// 绑定点击事件
	com.canvas.addEventListener("click", play.clickCanvas);
	com.get("regretBn").addEventListener("click", function(e) {
		play.regret();
	});

}
// 写入棋谱
createBox = function(bl, initMap) {
	var h = '';
	if (play.pace == null || play.pace == "" || play.pace.length == 0) {
	} else {
		var map = com.arr2Clone(initMap);
		var bl = bl.split("");
		console.log(bl);
		for (var i = 0; i < bl.length; i += 4) {
			h += '<li id="move_' + (i / 4) + '">';
			var x = bl[i + 0];
			var y = bl[i + 1];
			var newX = bl[i + 2];
			var newY = bl[i + 3];
			h += com.createMove(map, x, y, newX, newY);
			h += '</li>\n\r';
		}
		parent.document.getElementById("checkPk").innerHTML = "";
	}
	com.get("billBox").innerHTML = h;
}

function submit() {
	com.get("saveBn").click();
}

function refresh() {
	var process = play.pace.join("");
	com.get("chessRight").style.display = "block";
	createBox(process, com.arr2Clone(com.initMap));
	/* 设置到棋谱对象 */
	parent.document.getElementById("chessProcess").value = process;
	console.log(process);
}
