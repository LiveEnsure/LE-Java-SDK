$("#challenge").on('click','#biometric',
function() {
	$("#challenge").hide();
	var sendInfo = {
		sessionToken : session,
		agentId : agent,
		touches : "true",
		required : "true"
	};
	$.ajax({
		url : base_url + "/biometricChallenge",
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