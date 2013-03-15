$(function() {
	getAllLoans(getAllLoansSuccessHandler, getAllLoansErrorHandler);
});

function getAllLoansSuccessHandler(data) {
	if (data.error != null) {
		showErrorMessage();
		return;
	}
}

function getAllLoansErrorHandler(data) {
	showErrorMessage();
}

function showErrorMessage() {
	$("#applyForLoanAlert").show();
}

