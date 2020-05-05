$(function() {
	$("#challenge").on('click','#hostBehaviorV6',function() {
		$("#challenge").html('<div class="heading">Host Behavior Challenge</div>'
					+ 'Touches : <input type="text" name="touches" id="touchesV6" class="input"/> Ex.- (1,9),(8,8),(7,3) <br/>'
					+ '<input id="hostBehaviorV6Chall" type="button" value="Submit" class="input"/>');
	});
});

$("#challenge").on('click','#hostBehaviorV6Chall',function() {
	$("#challenge").hide();
	$("#show").html("");
	var sendInfo = {
		sessionToken : session,
		agentId : agent,
		touches : $("#touchesV6").val().trim(),
		required : "true"
	};
	$.ajax({
		url : base_url + "/hostBehaviorV6Challenge",
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