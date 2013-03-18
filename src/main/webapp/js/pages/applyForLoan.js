// Cashed
var cachedDueDate;

$(function() {
	getServerConstants(prepareApplyForLoan);
});

function prepareApplyForLoan() {
	var amountSlider = $("#amountSlider");
	var amount = $("#amount");
	var detailsAmount = $("#detailsAmount");

	var detailsReturnAmount = $("#detailsReturnAmount");

	amountSlider.slider({
		range : "min",
		value : MIN_LOAN_TERM,
		step : 5,
		min : MIN_LOAN_AMOUNT,
		max : MAX_LOAN_AMOUNT,
		slide : function(event, ui) {
			amount.val(CURRENCY + " " + ui.value);
			detailsAmount.text(amount.val());

			// Return amount
			detailsReturnAmount.text(getReturnAmount(ui.value, termSlider
					.slider("value")));
		},
		stop: function(event, ui) {
			console.log("gu");
		}
	});
	amount.val(CURRENCY + " " + amountSlider.slider("value"));
	detailsAmount.text(amount.val());

	var termSlider = $("#termSlider");
	var term = $("#term");
	var detailsTerm = $("#detailsTerm");
	var detailsDueDate = $("#detailsDueDate");
	var termStep = 1;
	var termMax = MAX_LOAN_TERM;

	var tomorrow = getTommorowDate();

	var lastSliderValue = 0;
	termSlider.slider({
		range : "min",
		value : MIN_LOAN_TERM,
		step : termStep,
		min : MIN_LOAN_TERM,
		max : termMax,
		start : function(event, ui) {
			lastSliderValue = ui.value;
		},
		slide : function(event, ui) {
			var tempDate;
			term.val("Days " + ui.value);
			detailsTerm.text(term.val());

			// Due Date
			if (ui.value > lastSliderValue) {
				// next
				tempDate = new Date();
				tempDate.setDate(tempDate.getDate() + ui.value);
			} else {
				// prev
				tempDate = new Date();
				tempDate.setDate(tempDate.getDate() + termMax);
				tempDate.setDate(tempDate.getDate() - (termMax - ui.value));
			}
			detailsDueDate.text(formatDate(tempDate));
			cachedDueDate = tempDate;

			// Return amount
			detailsReturnAmount.text(getReturnAmount(amountSlider
					.slider("value"), ui.value));

			// Setting end values
			lastSliderValue = ui.value;
		}
	});
	term.val("Days " + termSlider.slider("value"));
	detailsTerm.text(term.val());
	detailsDueDate.text(formatDate(tomorrow));
	cachedDueDate = getTommorowDate();
	detailsReturnAmount.text(getReturnAmount(amountSlider.slider("value"),
			termSlider.slider("value")));
}

function takeLoanSuccessHandler(data) {
	if (data.error != null) {
		showErrorMessage(data);
		return;
	}
	if (data.page != null) {
		window.location.href = data.page;
	}
}

function takeLoanErrorHandler(data) {
	showErrorMessage(data);
}

function showErrorMessage(data) {
	$("#applyForLoanAlertMessage").text(data.error);
	$("#applyForLoanAlert").show();
}

function getReturnAmount(amount, term) {
	var amountDecimal = new BigDecimal(amount.toString());
	var termDecimal = new BigDecimal(term.toString());
	var interest = INTEREST.multiply(termDecimal);
	var result = amountDecimal.multiply(interest).add(amountDecimal);
	return CURRENCY + " " + result.setScale(NUM_DECIMALS, BigDecimal.prototype.ROUND_HALF_UP);
}


// EVENTS
//=======================================

$('#applyForLoanButton').click(function() {
	var loan = JSON.stringify({
		"amount" : $("#amountSlider").slider("value"),
		"term" : $("#termSlider").slider("value"),
		"ipAddress" : (IP_ADDRESS != undefined) ? IP_ADDRESS : UNKNOWN_IP_ADDRESS
	});
	takeLoan(loan, takeLoanSuccessHandler, takeLoanErrorHandler);
});
