var HOST = "http://localhost";
var PORT = "8080";
var BASE_PATH = "microlending/rest";

var rootURL = HOST + ":" + PORT + "/" + BASE_PATH;

function getServerConstants(completeHandler) {
	console.log("getServerConstants");
	$.ajax({
		type: 'GET',
		url: rootURL + "/getServerConstants",
		dataType: "json",
		success: function(data) {
			INTEREST = data.interest;
		},
		complete: completeHandler
	});
}

function takeLoan(loan) {
	console.log("takeLoan");
	$.ajax({
		type: 'POST',
		url: rootURL + "/takeLoan",
		contentType: "application/json",
		data: loan,
		success: function(data) {
			console.log("Oh hai");			
		},
	});
}

