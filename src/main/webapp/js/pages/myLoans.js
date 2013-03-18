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
			data.results = [ data.results ];
		}
		for (index in data.results) {
			newResults.push({
				"index" : parseInt(index) + 1,
				"data" : data.results[index],
				"formatCreationDate" : function() {
					return formatDate(new Date(parseInt(this.data.creationDateMillis)));
				},
				"formatDueDate" : function() {
					return formatDueDate(this.data.dueDateMillis);
				},
				"formatLoanAmount" : function() {
					return formatLoanAmount(this.data.amount);
				},
				"formatLoanInterest" : function() {
					return formatLoanInterest(this.data.interest);
				},
				"formatReturnAmount" : function() {
					return formatReturnAmount(this.data.returnAmount);
				}
			});
		}
		var template = $('#loansTemplate').html();
		var html = Mustache.to_html(template, {
			items : newResults
		});
		$('#templateContainer').html(html);
	} else {
		$("#templateContainer").html("<h3>There are no active loans...</h3>").css("text-align", "center");
	}
	$("#applyForLoanButtonContainer").html("<button id='applyForLoanButton' class='btn btn-large btn-primary' type='button' onClick='redirectToApplyForLoan()'>Apply for Loan?</button>");
}

function getAllLoansErrorHandler(data) {
	showErrorMessage();
}

function extendLoanSuccessHandler(data) {
	console.log("extendLoanSuccessHandler");
	if (data.results != null) {
		var rowSelector = "#row" + data.results.id;
		var termSelector = "#term" + data.results.id;
		var interestSelector = "#interest" + data.results.id;
		var dueDateSelector = "#dueDate" + data.results.id;
		var returnAmountSelector = "#returnAmount" + data.results.id;
		var extendedSelector = "#extended" + data.results.id;
		$(termSelector).text(data.results.term);
		$(interestSelector).text(formatLoanInterest(data.results.interest));
		$(dueDateSelector).text(formatDueDate(data.results.dueDateMillis));
		$(returnAmountSelector).text(formatReturnAmount(data.results.returnAmount));
		$(extendedSelector).text("EXTENDED");
		$(rowSelector).addClass("info");
	}
}

function extendLoanErrorHandler(data) {
	showErrorMessage();
}

function showErrorMessage() {
	$("#myLoansAlertMessage").text(data.error);
	$("#myLoansAlert").show();
}

function formatDueDate(dueDateMillis) {
	return formatDate(new Date(parseInt(dueDateMillis)));
}

function formatLoanAmount(amount) {
	return CURRENCY + " " + parseInt(amount);
}

function formatLoanInterest(interest) {
	return (interest * 100) + "%";
}

function formatReturnAmount(returnAmount) {
	return CURRENCY + " " + returnAmount;
}

// EVENTS
// =======================================

function redirectToApplyForLoan() {
	window.location.href = "applyForLoan.html";
}

function extendLoanEvent(id) {
	extendLoan(id, extendLoanSuccessHandler, extendLoanErrorHandler);
}
