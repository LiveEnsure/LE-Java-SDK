$("#challenge").on('click', "#locationV6",function() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPositionV6, showErrorV6);
		$("#challenge").removeClass('challengeType-contact100-form-btn');
		$("#hostchallenge").hide();
		$("#challenge").html('<span class="contact100-form-title">Location V6 Challenge</span>'
				+ '<div class="wrap-input100 rs1-wrap-input100 validate-input"><span class="label-input100">Latitude *</span><input type="text" name="latitude" id="latitudeV6" class="input100" placeholder="Enter your latitude" /></div>'
				+ '<div class="wrap-input100 rs1-wrap-input100 validate-input"><span class="label-input100">Longitude *</span><input type="text" name="longitude" id="longitudeV6" class="input100" placeholder="Enter your longitude" /></div>'
				+ '<div class="wrap-input100"><span class="label-input100">Radius </span><input type="text" name="radius" id="radiusV6" class="input100" placeholder="Enter radius" /></div>'
				+ '<div class="wrap-input100 rs1-wrap-input100 validate-input"><span class="label-input100">In </span><input type="radio" name="inoutV6" value="true" class="input100 radioInput100" /></div>'
				+ '<div class="wrap-input100 rs1-wrap-input100 validate-input"><span class="label-input100">Out </span><input type="radio" name="inoutV6" value="false" class="input100 radioInput100" /></div>'
				+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="locationV6Chall">Submit</button></div>');
	} else {
		console.log("Geolocation is not supported by this browser.");
	}
});


$("#challenge").on('click', "#locationV6Chall",function() {
	var sendInfo = {
		sessionToken : session,
		/*agentId : agent,*/
		latitude : $("#latitudeV6").val().trim(),
		longitude : $("#longitudeV6").val().trim(),
		radius : $("#radiusV6").val().trim() != "" ? $("#radiusV6").val().trim() : "100",
		inout : $('input[name="inoutV6"]:checked').val(),
		required : "true"
	};
	$("#challenge").html('<span class="contact100-form-title">Location V6 Challenge</span>');
	$.ajax({
		url : base_url + "/locationV6Challenge",
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