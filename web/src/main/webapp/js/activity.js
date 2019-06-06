$(function() {
	$("#file").on("change", function() {
		var file = this.files[0];
		$("#activityPicture").attr("value", file.name);
	});

//	$("#province").on("change", function() {
//		$("#activityProvince").attr("value", $("#province").find("option:selected").text());
//	});
	
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

/* 批量更新为停止 */
$("#stopAll").click(function() {
	updateArr(2);
});

/* 修改单个状态(发布/停止) */
function updateStatus(id, status) {
	var idArray = [ id ];
	updateStatusByIds(idArray, status, updateStatusUrl());
}

/* 批量停止 */
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
		data : { "idList" : idArray, "status" : status },
		type : 'post',
		async : false,
		dataType : 'text',
		success : function() {
			if (status == 1) {
				$.each(idArray, function(n, value) {
					$("#" + value + "").html("<span class='red'>停止</span>");
					$("#" + value + "").attr("onClick", "updateStatus(" + value + ", 2);");
					$("#p" + value + "").html("进行中");
				});
			} else {
				$.each(idArray, function(n, value) {
					$("#" + value + "").html("发布");
					$("#" + value + "").attr("onClick", "updateStatus(" + value + ", 1);");
					$("#p" + value + "").html("已结束");
				});
			}
		},
		error : function() { alert("sorry, 服务器异常！"); }
	});
}
// 以上可为公用部分
/* 活动 查询(排序、名称或id,查询字符) */
function search() {
	var orderBy = $("#orderBy option:selected").val();
	var status = $("#selectStatus option:selected").val();
	var begin = $("#activityBeginTime").val();
	var end = $("#activityEndTime").val();
	var str = $("#k").val();
	location.href = $("#contextPath").val() + "/manage/activity?o=" + orderBy
			+ "&s=" + status + "&b=" + begin + "&e=" + end + "&k=" + str
			+ "&p=" + 1;
}

/* 活动更新状态地址 */
function updateStatusUrl() {
	return $("#contextPath").val() + '/manage/activity/updateStatus';
}