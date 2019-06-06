$(function() {

	$("#tips").fadeOut(10000);
	
	
	sameHeight("#mainLeft", "#mainCenter");
	sameHeight("#mainRight", "#mainCenter");
});
function sameHeight(obj1, obj2) { 
     var h1 = $(obj1).height(); //获取对象1的高度
//     console.info("mainLeft的高度是：" + h1);
      var h2 = $(obj2).height();  //获取对象2高度
//      console.info("mainCenter的高度是：" + h2);
      
//     $(obj1).height(document.documentElement.clientHeight); //重设高度，让两边div 高度保持一至

      $(obj1).height(h1);
//     console.info("#mainLeft：" + $("#mainLeft").outerHeight());
} 



// 列表页面的背景颜色改变
function changeBgColor(obj, color) {
	obj.style.backgroundColor = color;
}

// 选择或取消选择所有（1，设置多选框的class为checkAll的点击事件；2，设置checkbox的name为checkName）
$("#checkAll").on("click", function() {
//	if (this.checked) {
//		$("input[name='checkName']").each(function() {
//			$(this).prop("checked", true); // 全选
//		});
//	} else {
//		$("input[name='checkName']").each(function() {
//			$(this).prop("checked", false); // 全不选
//		});
//	}
	$("input[name='checkName']").prop("checked", this.checked);
	$("input[name='unCheckName']").prop("checked", false); // 让"反选"不状态
});

// 反选
$("#unCheckAll").on("click", function() {
	$("input[name='checkName']").each(function() {
		// $(this).prop("checked", !$(this).prop("checked")); // 反选（jQuery）
		this.checked = !this.checked; // 反选（原生JS）
	});
	$("input[name='checkAllName']").prop("checked", false); // 让"全选/全不选"不状态
	$(this).prop("checked", true); // 让自己永远是选择状态
});

// 获取多选框里（数组）的数据
function getArray() {
	var idArray = new Array();
	$("input[name='checkName']:checked").each(function(i) {
		if ($(this).prop("checked")) {
			idArray[i] = $(this).val();
		}
	});
	return idArray;
}

// 批量显示
$("#showAll").on("click", function() {
	updateArrary(0, $("#active").val());
});

// 批量隐藏
$("#hideAll").on("click", function() {
	updateArrary(1, $("#active").val());
});

// 批量操作显示或隐藏
function updateArrary(status, obj) {
	var ids = getArray();
	if (null != ids && 0 < ids.length) {
		updateStatusByIds(ids, status, getTargetUrl(obj));
	} else {
		$("#tips").html("没有选择！");
		return false;
	}
}

// 获取目标URL地址
function getTargetUrl(obj) {
	return $("#contextPath").val() + "/manage/" + obj + "/updateStatus";
}
// 单个修改
function updateStatus(id) {
	var status = 0;
	var idArray = [ id ];
	if ("显示" == $("#" + id + "").text()) {
		status = 1;
	}
	updateStatusByIds(idArray, status, getTargetUrl($("#active").val()));
}
// 批量修改
function updateStatusByIds(ids, status, url) {
	$.post(url, { 'ids' : ids, 'status' : status }, function(data) {
		if (1 == status) {
			$.each(ids, function(n, value) {
				$("#" + value + "").html("<span class='red'>隐藏</span>");
			});
		} else {
			$.each(ids, function(n, value) {
				$("#" + value + "").text("显示");
			});
		}
	}, "text");
}
