$(function() {
	$("#file").on("change", function() {
		var file = this.files[0];
		$("#tutorialPicture").attr("value", file.name);
	});

});

/* 全选/全不选 */
$("#chk_all").click(function() {
	if ($(this).prop("checked")) {
		$("input[name='chk_list']").each(function() {
			$(this).prop("checked", true);
		});
	} else {
		$("input[name='chk_list']").each(function() {
			$(this).prop("checked", false);
		});
	}
});
/* 反选 */
$("#un_CheckAll").on("click", function() {
	$("input[name='chk_list']").each(function() {
		this.checked = !this.checked; // 反选（原生JS）
	});
	$("#chk_all").prop("checked", false); // 取消全选
	$("#un_CheckAll").prop("checked", true); // 取消全选
});
/* 返回选择的id数组 */
function getIdArray() {
	var i = 0;
	var idArray = new Array();
	$("input[name='chk_list']").each(function() {
		if ($(this).prop("checked")) {
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

/* 批量修改友情链接的状态 */
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
var editor;
KindEditor.ready(function(K) {
	editor = K.create("#tutorialContent");
});

/* 批量删除 */
$("#del").click(function() {
	var anArray = getIdArray();
	if (anArray == null || anArray.length == 0) {
		alert("请至少选择一条数据!");
		return false;
	}
	del(anArray);
});
/* 单个删除 */
function delOne(id) {
	if (confirm("确认删除？")) {
		var idArray = [ id ];
		del(idArray);
	}
}
/* 删除(ids,排序、名称或id,查询字符,页码) */
function del(ids) {
	if (confirm("确认删除？")) {
		// 选择确认，删除重定向到列表
		var order_by = $("#order_by option:selected").val();
		var where_by = $("#search_by option:selected").val();
		var str = $("input[name='k']").val();
		location.href = $("#contextPath").val() + "/manage/tutorial/del?o="
				+ order_by + "&w=" + where_by + "&s=" + str + "&p=" + 1
				+ "&ids=" + ids;
	}
}

/* 更新状态地址 */
function updateStatusUrl() {
	return $("#contextPath").val() + '/manage/tutorial/updateStatus';
}
