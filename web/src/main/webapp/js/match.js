$(function() {
	$("#file").on("change", function() {
		var file = this.files[0];
		$("#matchPicture").attr("value", file.name);
	});
	
});
