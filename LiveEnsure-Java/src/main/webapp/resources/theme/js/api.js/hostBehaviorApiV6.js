$(function() {
	$("#challenge").on('click','#hostBehaviorV6',function() {
		$("#challenge").removeClass('challengeType-contact100-form-btn');
		$("#hostchallenge").hide();
		$("#challenge").html('<span class="contact100-form-title">Host Behavior V6 Challenge</span>'
				+ '<div class="wrap-input100"><span class="label-input100">Touches *</span><input type="text" name="touches" id="touchesV6" class="input100" placeholder="Enter your touches" /> Ex.- (1,9),(8,8),(7,3)</div>'
				+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="hostBehaviorV6Chall">Submit</button></div>');
	});
});

$("#challenge").on('click','#hostBehaviorV6Chall',function() {
	var sendInfo = {
		sessionToken : session,
		/*agentId : agent,*/
		touches : $("#touchesV6").val().trim(),
		required : "true"
	};
	$("#challenge").html('<span class="contact100-form-title">Host Behavior V6 Challenge</span>');
	$.ajax({
		url : base_url + "/hostBehaviorV6Challenge",
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