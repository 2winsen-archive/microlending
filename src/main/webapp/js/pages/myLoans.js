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
					return formatDate(new Date(parseInt(this.data.dueDateMillis)));
				},
				"formatLoanAmount" : function() {
					return CURRENCY + " " + parseInt(this.data.amount);
				},
				"formatLoanInterest" : function() {
					return (this.data.interest * 100).toFixed() + "%";
				},
				"formatReturnAmount" : function() {
					return CURRENCY + " " + this.data.returnAmount;
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
		var rowSelector = "#rowId" + data.results.id;
		$(rowSelector).css("color", "red"); 
	}
}

function extendLoanErrorHandler(data) {
	showErrorMessage();
}

function showErrorMessage() {
	$("#myLoansAlertMessage").text(data.error);
	$("#myLoansAlert").show();
}


// EVENTS
// =======================================

function redirectToApplyForLoan() {
	window.location.href = "applyForLoan.html";
}

function extendLoanEvent(id) {
	extendLoan(id, extendLoanSuccessHandler, extendLoanErrorHandler);
}
