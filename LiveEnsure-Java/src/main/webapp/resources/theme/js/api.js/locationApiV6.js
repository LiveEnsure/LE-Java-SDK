$("#challenge").on('click', "#locationV6",function() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPositionV6, showErrorV6);
		$("#challenge").html('<div class="heading">Location Challenge</div>'
			+ 'Latitude : <input type="text" name="latitude" id="latitudeV6" class="input"/><br/>'
			+ 'Longitude : <input type="text" name="longitude" id="longitudeV6" class="input"/><br/>'
			+ 'Radius : <input type="text" name="radius" id="radiusV6" class="input"/> miles<br/>'
			+ '<input type="radio" name="inoutV6" value="true" class="input"/> In<br/>'
			+ '<input type="radio" name="inoutV6" value="false" class="input"/> Out<br/>'
			+ '<input id="locationV6Chall" type="button" value="Submit" class="input"/>');
	} else {
		console.log("Geolocation is not supported by this browser.");
	}
});


$("#challenge").on('click', "#locationV6Chall",function() {
	$("#challenge").hide();
	$("#show").html("");
	var sendInfo = {
		sessionToken : session,
		agentId : agent,
		latitude : $("#latitudeV6").val().trim(),
		longitude : $("#longitudeV6").val().trim(),
		radius : $("#radiusV6").val().trim() != "" ? $("#radiusV6").val().trim() : "100",
		inout : $('input[name="inoutV6"]:checked').val(),
		required : "true"
	};
	$.ajax({
		url : base_url + "/locationV6Challenge",
		type : "POST",
		dataType : "json",
		data : JSON.stringify(sendInfo),
		contentType : 'application/json',
		success : function(result) {
			console.log(result);
			pollStatus();
			$('#show').show().prepend('<img id="theImg" src="' + result.FileUrl + '" />');
		}
	});
});

function showPositionV6(position) {
	console.log("Latitude: " + position.coords.latitude + "<br>Longitude: " + position.coords.longitude);
	$("#latitudeV6").val(position.coords.latitude);
	$("#longitudeV6").val(position.coords.longitude);
}

function showErrorV6(error) {
	switch (error.code) {
	case error.PERMISSION_DENIED:
		$('#show').show().prepend("User denied the request for Geolocation.")
		break;
	case error.POSITION_UNAVAILABLE:
		$('#show').show().prepend("Location information is unavailable.")
		break;
	case error.TIMEOUT:
		$('#show').show().prepend("The request to get user location timed out.")
		break;
	case error.UNKNOWN_ERROR:
		$('#show').show().prepend("An unknown error occurred.")
		break;
	}
}