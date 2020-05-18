$(function() {
	$("#challenge").on('click','#hostBehavior',function() {
		$("#challenge").removeClass('challengeType-contact100-form-btn');
		$("#hostchallenge").hide();
		$("#challenge").html('<span class="contact100-form-title">Host Behavior Challenge</span>'
				+ '<div class="wrap-input100 rs1-wrap-input100 validate-input"><span class="label-input100">Orientation *</span><input type="text" name="orientation" id="orientation" class="input100" placeholder="Enter your orientation" /> 0 or 1</div>'
				+ '<div class="wrap-input100 rs1-wrap-input100 validate-input"><span class="label-input100">Touches *</span><input type="text" name="touches" id="touches" class="input100" placeholder="Enter your touches" /> 0 - 6</div>'
				+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="hostBehaviorChall">Submit</button></div>');
	});
});

$("#challenge").on('click','#hostBehaviorChall',function() {
	var sendInfo = {
		sessionToken : session,
		/*agentId : agent,*/
		orientation : $("#orientation").val().trim(),
		touches : $("#touches").val().trim(),
		required : "true"
	};
	$("#challenge").html('<span class="contact100-form-title">Host Behavior Challenge</span>');
	$.ajax({
		url : base_url + "/hostBehaviorChallenge",
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