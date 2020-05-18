var base = "https://staging.liveensure.com";//"https://liveensure.damcogroup.com:8443";
var base_url = "/liveensure";
$("#register").click(function() {
	$("#register").attr("disabled", true).addClass("buttonFade");
	var sendInfo = {
			firstName: $("#firstName").val().trim(),
			lastName: $("#lastName").val().trim(),
			email: $("#email").val().trim()
	};
	$.ajax({
           type: "POST",
           url: base_url + "/registerConsumer",
           dataType: "json",
           data: JSON.stringify(sendInfo),
           contentType: 'application/json',
           success: function (msg) {
        	   $("#register").removeAttr("disabled").removeClass("buttonFade");
        	   console.log(msg);
               if (msg) {
            	   if(msg.hasOwnProperty('userId')){
	            	   $("#show").html("You have successfully registered.<br/><br/>Consumer Details:<br/>User Id : "
	            			   + msg.userId
	            			   + "<br/>Agent Id : " 
	            			   + msg.agentId 
	            			   + "<br/> API Key : " 
	            			   + msg.apiKey
	            			   + "<br/> Agent Password : " 
	            			   + msg.agentPass
	    	            	   + "<br/><br/><br/>and Click here for ");
	            	   $("#show").append("<a href='" + base + base_url + "' target='_blank' >Login</a>");
            	   } else {
            		   $("#show").html(msg.response + "<br/><br/>and Click here for ");
            		   $("#show").append("<a href='" + base + base_url + "' target='_blank' >Login</a>");
            	   }
               } else {
            	   $("#show").html("unfortunately User not registered!!!<br/><br/>and Click here for ");
            	   $("#show").append("<a href='" + base + base_url + "' target='_blank' >Login</a>");
               }
           },
		error: function (textStatus, errorThrown) {
			$("#register").removeAttr("disabled").removeClass("buttonFade");
			$('#show').html("Something went worng!!!<br/><br/>and Click here for ");
			$("#show").append("<a href='" + base + base_url + "' target='_blank' >Login</a>");
	    }
    });
});