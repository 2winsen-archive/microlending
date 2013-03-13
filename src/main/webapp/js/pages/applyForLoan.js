$(function() {
	getServerConstants(getServerConstantsCompleteHandler);
});

function getServerConstantsCompleteHandler() {
	var amountSlider = $("#amountSlider");
	var amount = $("#amount");
	var detailsAmount = $("#detailsAmount");

	var detailsReturnAmount = $("#detailsReturnAmount");

	amountSlider.slider({
		range : "min",
		value : 5,
		step : 5,
		min : 5,
		max : 200,
		slide : function(event, ui) {
			amount.val(CURRENCY + " " + ui.value);
			detailsAmount.text(amount.val());

			// Return amount
			detailsReturnAmount.text(getReturnAmount(ui.value, termSlider
					.slider("value")));
		}
	});
	amount.val(CURRENCY + " " + amountSlider.slider("value"));
	detailsAmount.text(amount.val());

	var termSlider = $("#termSlider");
	var term = $("#term");
	var detailsTerm = $("#detailsTerm");
	var detailsDueDate = $("#detailsDueDate");
	var termStep = 1;
	var termMax = 30;

	var tomorrow = getTommorowDate();

	var lastSliderValue = 0;
	termSlider.slider({
		range : "min",
		value : 1,
		step : termStep,
		min : 1,
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
	detailsReturnAmount.text(getReturnAmount(amountSlider.slider("value"),
			termSlider.slider("value")));
}

function getReturnAmount(amount, term) {
	// interest
	var interest = INTEREST * term;
	var result = amount + (amount * interest);
	return CURRENCY + " "
			+ (Math.round(result * 100) / 100).toFixed(NUM_DECIMALS);

}
