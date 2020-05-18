$("#challenge").on('click', "#location",function() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition, showError);
		$("#challenge").removeClass('challengeType-contact100-form-btn');
		$("#hostchallenge").hide();
		$("#challenge").html('<span class="contact100-form-title">Location Challenge</span>'
				+ '<div class="wrap-input100 rs1-wrap-input100 validate-input"><span class="label-input100">Latitude *</span><input type="text" name="latitude" id="latitude" class="input100" placeholder="Enter your latitude" /></div>'
				+ '<div class="wrap-input100 rs1-wrap-input100 validate-input"><span class="label-input100">Longitude *</span><input type="text" name="longitude" id="longitude" class="input100" placeholder="Enter your longitude" /></div>'
				+ '<div class="wrap-input100"><span class="label-input100">Radius </span><input type="text" name="radius" id="radius" class="input100" placeholder="Enter radius" /></div>'
				+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="locationChall">Submit</button></div>');
	} else {
		console.log("Geolocation is not supported by this browser.");
	}
});


$("#challenge").on('click', "#locationChall",function() {
	var sendInfo = {
		sessionToken : session,
		/*agentId : agent,*/
		latitude : $("#latitude").val().trim(),
		longitude : $("#longitude").val().trim(),
		radius : $("#radius").val().trim() != "" ? $("#radius").val().trim() : "100",
		inout : "true",
		required : "true"
	};
	$("#challenge").html('<span class="contact100-form-title">Location Challenge</span>');
	$.ajax({
		url : base_url + "/locationChallenge",
		type : "POST",
		dataType : "json",
		data : JSON.stringify(sendInfo),
		contentType : 'application/json',
		success : function(result) {
			console.log(result);
			pollStatus();
			$('#show').html("").show().prepend('<img id="theImg" src="' + result.FileUrl + '" />');
		},
		error: function (textStatus, errorThrown) {
			$('#show').show().html("Something went worng!!!");
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