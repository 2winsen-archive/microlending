var HOST = "http://localhost";
var PORT = "8080";
var BASE_PATH = "microlending/rest";

var rootURL = HOST + ":" + PORT + "/" + BASE_PATH;

function getServerConstants() {
	console.log('getServerConstants');
	$.ajax({
		type: 'GET',
		url: rootURL + "/getServerConstants",
		dataType: "json",
		success: function(data) {
			var a = 10;
			var b = 10;
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#wineList li').remove();
	$.each(list, function(index, wine) {
		$('#wineList').append('<li><a href="#" data-identity="' + wine.id + '">'+wine.name+'</a></li>');
	});
}
