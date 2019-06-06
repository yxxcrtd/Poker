$(function() {
	var nameNode = $("#managerName");
	var nameTipNode = $("#nameTip");
	var contextPath = $("#contextPath").val();
	nameNode.bind("input propertychange", function() {
		var nameValue = $.trim(nameNode.val());
		if (0 < nameValue.length) {
			$.get(contextPath + "/manage/manager/find", { 'name' : nameValue }, function(data) {
				if ("success" == data) {
					nameTipNode.html("");
					$("#ok").attr("class", "tips_ok");
				} else if ("admin" == data || "error" == data) {
					$("#ok").attr("class", "tips_del");
					nameTipNode.html("该名称已被占用！").addClass("red");
				} else {
					return;
				}
			}, "text");
		} else {
			$("#ok").removeClass("tips_ok");
		}
	});
	
	var managerAllNode = $("#managerAll");
	managerAllNode.on("click", function() {
		if ($(this).prop("checked")) {
			$(this).prop("checked", "checked");
			$(".checkbox").each(function() {
				$(this).prop("checked", "checked");
			});
		} else {
			$(".checkbox").each(function() {
				$(this).prop("checked", "");
			});
		}
	});
	
	// 批量删除
	$("#batchAll").on("click", function() {
		var ids = getArray();
		if (null != ids && 0 < ids.length) {
			if (confirm('确定要删除选择的用户吗？')) {
				location.href = contextPath + "/manage/manager/del/" + ids;
			}
		} else {
			$("#tips").html("没有选择！");
			return false;
		}
	});
});

// 单个选择
function checkThis(obj, id) {
	$.get($("#contextPath").val() + "/manage/manager/check", { 'obj' : obj, 'id' : id }, function(data) {}, "text");
}
