$(function() {
	$("#challenge").on('click','#prompt',function() {
		$("#challenge").html('<div class="heading">Prompt Challenge</div>'
				+ 'Question : <input type="text" name="ques" id="ques" class="input"/><br/>'
				+ 'Answer : <input type="text" name="ans" id="ans" class="input"/><br/>'
				+ '<input id="promptChall" type="button" value="Submit" class="input"/>');
	});
});

$("#challenge").on('click','#promptChall',function() {
	$("#challenge").hide();
	var sendInfo = {
		sessionToken : session,
		agentId : agent,
		ques : $("#ques").val().trim(),
		ans : $("#ans").val().trim(),
		required : "true"
	};
	$.ajax({
		url : base_url + "/promptChallenge",
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