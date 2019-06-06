$(function() {
	$("#file").on("change", function() {
		var file = this.files[0];
		$("#adPicture").attr("value", file.name);
	});
	
});
