$(function() {
	$("#challenge").on('click','#hostBehavior',function() {
		$("#challenge").html('<div class="heading">Host Behavior Challenge</div>'
					+ 'Orientation : <input type="text" name="orientation" id="orientation" class="input"/> 0 or 1<br/>'
					+ 'Touches : <input type="text" name="touches" id="touches" class="input"/> 0 - 6 <br/>'
					+ '<input id="hostBehaviorChall" type="button" value="Submit" class="input"/>');
	});
});

$("#challenge").on('click','#hostBehaviorChall',function() {
	$("#challenge").hide();
	$("#show").html("");
	var sendInfo = {
		sessionToken : session,
		agentId : agent,
		orientation : $("#orientation").val().trim(),
		touches : $("#touches").val().trim(),
		required : "true"
	};
	$.ajax({
		url : base_url + "/hostBehaviorChallenge",
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