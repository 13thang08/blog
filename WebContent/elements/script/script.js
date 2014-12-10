$(document).ready(function () {
	$("#previousmonth").click(function() {
		var url = "${preMonth}";
		alert(url);
	});
});

function changeCalendar(obj) {
	var url = obj.getAttribute("href");
	$("#calendarID").load(url + " #calendarID");
	return false;
}

function changeContent(obj) {
	var url = obj.getAttribute("href");
	$("#content").load(url + " #content");
	return false;
}