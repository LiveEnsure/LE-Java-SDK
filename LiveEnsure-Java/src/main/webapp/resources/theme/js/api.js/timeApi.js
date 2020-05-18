var sd=null;
$(function() {
	$("#challenge").on('click','#time',function() {
		$("#challenge").removeClass('challengeType-contact100-form-btn');
		$("#hostchallenge").hide();
		$("#challenge").html('<span class="contact100-form-title">Time Challenge</span>'
				+ '<div class="wrap-input100"><span class="label-input100">Start Time *</span><input type="text" name="startDate" class="input100" id="startDate" placeholder="Enter your start date" /></div>'
				+ '<div class="wrap-input100"><span class="label-input100">Add Validation Period </span></div>'
				+ '<div class="row"><div class="col-md-3"><span class="label-input100">Day *</span></div><div class="col-md-1"><span class="input-group-btn"><button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[1]"><span class="glyphicon glyphicon-minus"></span></button></span></div>'
		        + '<div class="col-md-2"><input type="text" name="quant[1]" id="days" class="form-control input-number" value="0" min="0" max="10"></div>'
		        + '<div class="col-md-1"><span class="input-group-btn"><button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[1]"><span class="glyphicon glyphicon-plus"></span></button></span></div></div>'
		        + '<div class="row"><div class="col-md-3"><span class="label-input100">Hour(s) *</span></div><div class="col-md-1"><span class="input-group-btn"><button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[2]"><span class="glyphicon glyphicon-minus"></span></button></span></div>'
		        + '<div class="col-md-2"><input type="text" name="quant[2]" id="hours" class="form-control input-number" value="0" min="0" max="23"></div>'
		        + '<div class="col-md-1"><span class="input-group-btn"><button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[2]"><span class="glyphicon glyphicon-plus"></span></button></span></div></div>'
		        + '<div class="row"><div class="col-md-3"><span class="label-input100">Minute(s) *</span></div><div class="col-md-1"><span class="input-group-btn"><button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[3]"><span class="glyphicon glyphicon-minus"></span></button></span></div>'
		        + '<div class="col-md-2"><input type="text" name="quant[3]" id="minutes" class="form-control input-number" value="1" min="1" max="59"></div>'
		        + '<div class="col-md-1"><span class="input-group-btn"><button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[3]"><span class="glyphicon glyphicon-plus"></span></button></span></div></div>'
		        + '<div class="wrap-input100 rs1-wrap-input100 validate-input"><span class="label-input100">In </span><input type="radio" name="inoutV6" value="true" class="input100 radioInput100" /></div>'
				+ '<div class="wrap-input100 rs1-wrap-input100 validate-input"><span class="label-input100">Out </span><input type="radio" name="inoutV6" value="false" class="input100 radioInput100" /></div>'
				+ '<div class="wrap-contact100-form-btn"><div class="contact100-form-bgbtn"></div><button class="contact100-form-btn" id="timeChall">Submit</button></div>');
				/*+ '<div class="row"><input id="timeChall" type="button" value="Submit" class="form-control btn success"/></div>');*/
		
        $('#startDate').datetimepicker({
//        	Default: moment.locale(),
        	format: "YYYY-MM-DD hh:mm A",
        	minDate: Date()
        }).on('dp.change', function(e){ 
        	sd = e.date.format(e.date._f);
        });

    	$('.btn-number').click(function(e){
            e.preventDefault();
            fieldName = $(this).attr('data-field');
            type      = $(this).attr('data-type');
            var input = $("input[name='"+fieldName+"']");
            var currentVal = parseInt(input.val());
            if (!isNaN(currentVal)) {
                if(type == 'minus') {
                    
                    if(currentVal > input.attr('min')) {
                        input.val(currentVal - 1).change();
                    } 
                    if(parseInt(input.val()) == input.attr('min')) {
                        $(this).attr('disabled', true);
                    }
                } else if(type == 'plus') {

                    if(currentVal < input.attr('max')) {
                        input.val(currentVal + 1).change();
                    }
                    if(parseInt(input.val()) == input.attr('max')) {
                        $(this).attr('disabled', true);
                    }
                }
            } else {
                input.val(0);
            }
        });
        $('.input-number').focusin(function(){
           $(this).data('oldValue', $(this).val());
        });
        $('.input-number').change(function() {
            minValue =  parseInt($(this).attr('min'));
            maxValue =  parseInt($(this).attr('max'));
            valueCurrent = parseInt($(this).val());
            
            name = $(this).attr('name');
            if(valueCurrent >= minValue) {
                $(".btn-number[data-type='minus'][data-field='"+name+"']").removeAttr('disabled')
            } else {
                alert('Sorry, the minimum value was reached');
                $(this).val($(this).data('oldValue'));
            }
            if(valueCurrent <= maxValue) {
                $(".btn-number[data-type='plus'][data-field='"+name+"']").removeAttr('disabled')
            } else {
                alert('Sorry, the maximum value was reached');
                $(this).val($(this).data('oldValue'));
            }
        });
        $(".input-number").keydown(function (e) {
            // Allow: backspace, delete, tab, escape, enter and .
            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
                 // Allow: Ctrl+A
                (e.keyCode == 65 && e.ctrlKey === true) || 
                 // Allow: home, end, left, right
                (e.keyCode >= 35 && e.keyCode <= 39)) {
                     // let it happen, don't do anything
                     return;
            }
            // Ensure that it is a number and stop the keypress
            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                e.preventDefault();
            }
         });			
	});
});

function endDate(){
	var m = moment(sd);
	m.add(parseInt($("#days").val().trim()), 'days').calendar();
	m.add(parseInt($("#hours").val().trim()), 'hours').calendar();
	m.add(parseInt($("#minutes").val().trim()), 'minutes').calendar();
	return m.format('YYYY-MM-DD hh:mm A');
}

$("#challenge").on('click','#timeChall',function() {
	var sendInfo = {
		sessionToken : session,
		/*agentId : agent,*/
		startDate : $("#startDate").val().trim(),
		endDate : endDate(),
		inout : $('input[name="inoutV6"]:checked').val(),
		required : "true"
	};
	$("#challenge").html('<span class="contact100-form-title">Time Challenge</span>');
	$.ajax({
		url : base_url + "/timeChallenge",
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