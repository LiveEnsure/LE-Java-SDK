$(function() {
	$("#challenge").on('click','#prompt',function() {
		$("#challenge").removeClass('challengeType-contact100-form-btn');
		$("#hostchallenge").hide();
		$("#challenge").html('<span class="contact100-form-title">Prompt Challenge</span>'
				+ '<div class="wrap-input100 rs1-wrap-input100 validate-input"><span class="label-input100">Question *</span><input type="text" name="ques" id="ques" class="input100" placeholder="Enter your prompt question" /></div>'
				+ '<div class="wrap-input100 rs1-wrap-input100 validate-input"><span class="label-input100">Answer *</span><input type="text" name="ans" id="ans" class="input100" placeholder="Enter your prompt answer" /></div>'
				+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="promptChall">Submit</button></div>');
	});
});

$("#challenge").on('click','#promptChall',function() {
	var sendInfo = {
		sessionToken : session,
		/*agentId : agent,*/
		ques : $("#ques").val().trim(),
		ans : $("#ans").val().trim(),
		required : "true"
	};
	$("#challenge").html('<span class="contact100-form-title">Prompt Challenge</span>');
	$.ajax({
		url : base_url + "/promptChallenge",
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