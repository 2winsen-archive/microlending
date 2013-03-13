function formatDate(date) {
	return date.getDate() + "." + date.getMonth() + "." + date.getFullYear();
}

function getTommorowDate() {
	var tommorrow = new Date();
	tommorrow.setDate(tommorrow.getDate() + 1);
	return tommorrow;
}
