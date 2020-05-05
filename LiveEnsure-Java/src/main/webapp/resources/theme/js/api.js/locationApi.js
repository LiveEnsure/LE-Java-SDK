$("#challenge").on('click', "#location",function() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition, showError);
		$("#challenge").html('<div class="heading">Location Challenge</div>'
			+ 'Latitude : <input type="text" name="latitude" id="latitude" class="input"/><br/>'
			+ 'Longitude : <input type="text" name="longitude" id="longitude" class="input"/><br/>'
			+ 'Radius : <input type="text" name="radius" id="radius" class="input"/> miles<br/>'
			+ '<input id="locationChall" type="button" value="Submit" class="input"/>');
	} else {
		console.log("Geolocation is not supported by this browser.");
	}
});


$("#challenge").on('click', "#locationChall",function() {
	$("#challenge").hide();
	$("#show").html("");
	var sendInfo = {
		sessionToken : session,
		agentId : agent,
		latitude : $("#latitude").val().trim(),
		longitude : $("#longitude").val().trim(),
		radius : $("#radius").val().trim() != "" ? $("#radius").val().trim() : "100",
		inout : "true",
		required : "true"
	};
	$.ajax({
		url : base_url + "/locationChallenge",
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

function showPosition(position) {
	console.log("Latitude: " + position.coords.latitude + "<br>Longitude: " + position.coords.longitude);
	$("#latitude").val(position.coords.latitude);
	$("#longitude").val(position.coords.longitude);
}

function showError(error) {
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