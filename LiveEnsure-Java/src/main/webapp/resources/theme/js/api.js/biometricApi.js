$("#challenge").on('click','#biometric', function() {
	$("#challenge").removeClass('challengeType-contact100-form-btn');
	$("#hostchallenge").hide();
	var sendInfo = {
		sessionToken : session,
		/*agentId : agent,*/
		touches : "true",
		required : "true"
	};
	$("#challenge").html('<span class="contact100-form-title">Biometric Challenge</span>');
	$.ajax({
		url : base_url + "/biometricChallenge",
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
			$('#show').html("").show().html("Something went worng!!!");
	    }
	});
});