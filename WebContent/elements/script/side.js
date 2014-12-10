$(document).ready(function () {
	$("#previousmonth").click(function() {
		var url = "${preMonth}";
		alert(url);
	});
});

function goPreMonth(obj) {
	var url = obj.getAttribute("href");
	$("#side").load(url + " #side");
	return false;
}