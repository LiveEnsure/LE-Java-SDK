var agent = "";
var api = "";
var emailId = "";
var session = "";
var base = "https://staging.liveensure.com";//"https://liveensure.damcogroup.com:8443";
var base_url = "/liveensure";
$("#submit").click(function() {
	$("#submit").attr("disabled", true).addClass("buttonFade");
	clearInterval(myInterval);
	$("#challenge").html("").hide();
	$("#hostchallenge").hide();
	$("#show").html("").hide();
	/*agent = $("#agentId").val().trim();
	api = $("#apiKey").val().trim();*/
	emailId = $("#email").val().trim();
	var sendInfo = {
		/*agentId: $("#agentId").val().trim(),
		apiKey: $("#apiKey").val().trim(),*/
		email: $("#email").val().trim()
	};
	$.ajax({
		type: "POST",
		url: base_url+"/login",
		dataType: "json",
		data: JSON.stringify(sendInfo),
		contentType: 'application/json',
		success: function(msg) {
			$("#submit").removeAttr("disabled").removeClass("buttonFade");
			console.log(msg);
			if (msg) {
				session=msg.sessionToken;
				$('#login').before('<div class="wrap-contact100-form-btn deleteButton"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="delete" name="delete">Delete</button></div>');
				$("#hostchallenge").show();
				$("#challenge").addClass('challengeType-contact100-form-btn').show().html('<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="device" name="Challenge">Device Challenge</button></div>'
								+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="prompt" name="Challenge">Prompt Challenge</button></div>'
								+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="hostBehavior" name="Challenge">Host Behavior Challenge</button></div>'
								+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="location" name="Challenge">Location Challenge</button></div>'
								+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="hostBehaviorV6" name="Challenge">Host Behavior V6 Challenge</button></div>'
								+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="locationV6" name="Challenge">Location V6 Challenge</button></div>'
								+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="biometric" name="Challenge">Biometric Challenge</button></div>'
								+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="time" name="Challenge">Time Challenge</button></div></div>');
		
			} else {
				console.log("Cannot add to list !");
			}
		},
		error: function (textStatus, errorThrown) {
			$("#submit").removeAttr("disabled").removeClass("buttonFade");
			$('#show').show().html("Something went worng!!!");
	    }
	});
});

$("#challenge").on('click', '#device', function() {
	$("#hostchallenge").hide();
	pollStatus();
	$("#challenge").removeClass('challengeType-contact100-form-btn').show().html('<span class="contact100-form-title">Device Challenge</span>');
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
				$("#hostchallenge").show();
			}
		});
	}); */



var myInterval;
function pollStatus() {
	myInterval = setInterval(function() {
		var sendInfo = {
			sessionToken : session
			/*agentId : agent*/
		};
		$.ajax({
			type : "POST",
			url : base_url + "/challengeStatus",
			dataType : "json",
			data : JSON.stringify(sendInfo),
			contentType : 'application/json',
			success : function(result) {
				if (result.challengeStatuses.length > 0 && result.challengeStatuses[0].answerState != 'NA') {
					$("#challenge").html('<span class="contact100-form-title">Poll Status</span>');
					$("#show").html(result.challengeStatuses[0].answerState);
					clearInterval(myInterval);
				}
			},
			error: function (textStatus, errorThrown) {
				$('#show').show().html("Something went worng!!!");
				clearInterval(myInterval);
		    }
		});
	}, 6000);
}

$("#content").on('click',"#delete",function() {
$("#challenge").html("").hide();
$("#hostchallenge").hide();
$("#show").html("").hide();
	var sendInfo = {
		/*agentId :agent,
		apiKey : api,*/
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
					/*$("#agentId").val('');
					$("#apiKey").val('');*/
					$("#email").val('');
					agent='';api='';emailId='';
					session='';
					$("#show").show().html(msg.userID + " user successfully deleted.");
				} else {
					$("#show").show().html("User not delete!");
				}
			},
			error: function (textStatus, errorThrown) {
				$('#show').show().html("Something went worng!!!");
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