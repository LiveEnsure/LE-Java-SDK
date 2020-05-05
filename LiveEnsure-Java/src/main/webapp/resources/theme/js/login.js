var agent = "";
var api = "";
var emailId = "";
var session = "";
var base = "https://staging.liveensure.com";//"https://liveensure.damcogroup.com:8443";
var base_url = "/liveensure";
$("#submit").click(function() {
	clearInterval(myInterval);
	$("#challenge").html("").hide();
	$("#show").html("").hide();
	agent = $("#agentId").val().trim();
	api = $("#apiKey").val().trim();
	emailId = $("#email").val().trim();
	var sendInfo = {
		agentId: $("#agentId").val().trim(),
		apiKey: $("#apiKey").val().trim(),
		email: $("#email").val().trim()
	};
	$.ajax({
		type: "POST",
		url: base_url+"/login",
		dataType: "json",
		data: JSON.stringify(sendInfo),
		contentType: 'application/json',
		success: function(msg) {
			console.log(msg);
			if (msg) {
				session=msg.sessionToken;
				$('#login').before('<div class="deleteButton"><button id="delete" name="delete">Delete</button></div>');
				$("#challenge").show().html('<div class="button"><button id="device" name="Challenge">Device Challenge</button></div>'
								+ '<div class="button"><button id="prompt" name="Challenge">Prompt Challenge</button></div>'
								+ '<div class="button"><button id="hostBehavior" name="Challenge">Host Behavior Challenge</button></div>'
								+ '<div class="button"><button id="location" name="Challenge">Location Challenge</button></div>'
								+ '<div class="button"><button id="hostBehaviorV6" name="Challenge">Host Behavior V6 Challenge</button></div>'
								+ '<div class="button"><button id="locationV6" name="Challenge">Location V6 Challenge</button></div>'
								+ '<div class="button"><button id="biometric" name="Challenge">Biometric Challenge</button></div>'
								+ '<div class="button"><button id="time" name="Challenge">Time Challenge</button></div>');
		
			} else {
				console.log("Cannot add to list !");
			}
		}
	});
});

$("#challenge").on('click', '#device', function() {
	$("#challenge").hide();
	pollStatus();
	$('#show').show().prepend('<img id="theImg" src="' + base + '/live-identity/QR?w=240&sessionToken='
			+ session 
			+ '" />');
});
	/* $("#submit").click(function() {
		$("#show").html("");
		var userId = $("#userId").val();
		$.ajax({
			url : "/login?email=" + userId,
			success : function(result) {
				$("#challenge").show();
			}
		});
	}); */



var myInterval;
function pollStatus() {
	myInterval = setInterval(function() {
		var sendInfo = {
			sessionToken : session,
			agentId : agent
		};
		$.ajax({
			type : "POST",
			url : base_url + "/challengeStatus",
			dataType : "json",
			data : JSON.stringify(sendInfo),
			contentType : 'application/json',
			success : function(result) {
				if (result.challengeStatuses.length > 0 && result.challengeStatuses[0].answerState != 'NA') {
					$("#show").html("Poll Status : " + result.challengeStatuses[0].answerState);
					clearInterval(myInterval);
				}
			},
		 	error : function() {
		 		clearInterval(myInterval);
	        }
		});
	}, 6000);
}

$("#content").on('click',"#delete",function() {
$("#challenge").html("").hide();
$("#show").html("").hide();
	var sendInfo = {
		agentId :agent,
		apiKey : api,
		email : emailId
	};
	$.ajax({
		type : "POST",
		url : base_url + "/delete",
		dataType : "json",
		data : JSON.stringify(sendInfo),
		contentType : 'application/json',
		success : function(msg) {
			if (msg) {
				$("#agentId").val('');
				$("#apiKey").val('');
				$("#email").val('');
				agent='';api='';emailId='';
				session='';
				$("#show").show().html(msg.userID + " user successfully deleted.");
			} else {
				$("#show").show().html("User not delete!");
				}
			}
	});
});

$.getScript(base_url + "/resources/theme/js/api.js/promptApi.js");
$.getScript(base_url + "/resources/theme/js/api.js/hostBehaviorApi.js");
$.getScript(base_url + "/resources/theme/js/api.js/locationApi.js");
$.getScript(base_url + "/resources/theme/js/api.js/hostBehaviorApiV6.js");
$.getScript(base_url + "/resources/theme/js/api.js/locationApiV6.js");
$.getScript(base_url + "/resources/theme/js/api.js/biometricApi.js");
$.getScript(base_url + "/resources/theme/js/api.js/timeApi.js");