$(function() {
	getAllLoans(getAllLoansSuccessHandler, getAllLoansErrorHandler);
});

function getAllLoansSuccessHandler(data) {
	if (data.error != null) {
		showErrorMessage();
		return;
	}
	var newResults = [];
	if (data.results != null) {
		if (!(data.results instanceof Array)) {
			data.results = [data.results];
		}
		for (index in data.results) {
			newResults.push({
				"index" : parseInt(index) + 1,
				"data" : data.results[index],
				"formatCreationDate" : function() {
					return formatDate(new Date(parseInt(this.data.creationDateMillis)));
				},
				"formatDueDate" : function() {
					return formatDate(new Date(parseInt(this.data.dueDateMillis)));
				},
				"formatLoanAmount" : function() {
					return CURRENCY + " " + parseInt(this.data.amount);
				},
				"formatLoanInterest" : function() {
					return this.data.interest*100 + "%";
				},
				"formatReturnAmount" : function() {
					return CURRENCY + " " + this.data.returnAmount;
				}
			});
		}
		var template = $('#loansTemplate').html();
		var html = Mustache.to_html(template, {items: newResults});
		$('#templateContainer').html(html);
	} else {
		$("#templateContainer").html("<h3>There are no active loans...</h3>").css("text-align", "center");
	}
}

function getAllLoansErrorHandler(data) {
	showErrorMessage();
}

function showErrorMessage() {
	$("#myLoansAlertMessage").text(data.error);
	$("#myLoansAlert").show();
}
