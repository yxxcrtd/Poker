$(function() {
	$("#red").on("change", function() {
		var file = this.files[0];
		$("#chessRedPic").attr("value", file.name);
	});

});
$(function() {
	$("#black").on("change", function() {
		var file = this.files[0];
		$("#chessBlackPic").attr("value", file.name);
	});

});
/* 返回选择的id数组 */
function getIdArray() {
	var i = 0;
	var idArray = new Array();
	$("input[name='checkName']").each(function() {
		if (this.checked) {
			idArray[i] = $(this).val();
			i++;
		}
	});
	return idArray;
}

/* 批量更新为显示 */
$("#showAll").click(function() {
	updateArr(0);
});

/* 批量更新为隐藏 */
$("#hideAll").click(function() {
	updateArr(1);
});

/* 修改单个状态(显示/隐藏) */
function updateStatus(id) {
	var status = 0;
	var nowStatus = $("#" + id + "").text();
	if (nowStatus == "显示") {
		status = 1;
	}
	var idArray = [ id ];
	updateStatusByIds(idArray, status, updateStatusUrl());
}

/* 批量修改状态 */
function updateArr(status) {
	var anArray = getIdArray();
	if (anArray == null || anArray.length == 0) {
		alert("请至少选择一条数据!");
		return false;
	}
	updateStatusByIds(anArray, status, updateStatusUrl());
}

/* ajax异步修改显示状态 */
function updateStatusByIds(idArray, status, url) {
	$.ajax({
		url : url,
		data : {
			"idList" : idArray,
			"status" : status
		},
		type : 'post',
		async : false,
		dataType : 'text',
		error : function() {
			alert("sorry,服务器异常");
		},
		success : function() {
			if (status == 1) {
				$.each(idArray, function(n, value) {
					$("#" + value + "").html("<span class='red'>隐藏</span>");
				});
			} else {
				$.each(idArray, function(n, value) {
					$("#" + value + "").text("显示");
				});
			}
		}
	});
}
// 以上可为公用部分
$("#s").on("change", function() {
	$("#sub").click();
});

$("#submit").on("click", function() {
	if (confirm("棋谱保存后不可更改，确认保存棋谱？")) {
		return $("#chessPk").contents().find("#saveBn").click();
	}
});

/* 游戏更新状态地址 */
function updateStatusUrl() {
	return $("#contextPath").val() + '/manage/chess/updateStatus';
}
