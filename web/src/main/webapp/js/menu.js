// By yxxcrtd@gmail.com@2015
$(function() {
	$("#menu li ul").hide();
	$("#firstMenu").css("background-image", "url('./images/menu_minus.gif')");
	$.each($("#menu"), function() {
		$("#" + this.id + ".expandfirst ul:first").show();
	});
	$("#menu .subMenu").click(function() {
		$(this).next().slideToggle("normal");
		changeIcon($(this));
	});
});

function changeIcon(node) {
	if (node) {
		if (node.css("background-image").indexOf("./images/menu_plus.gif") >= 0) {
			node.css("background-image", "url('./images/menu_minus.gif')");
		} else {
			node.css("background-image", "url('./images/menu_plus.gif')");
		}
	}
}