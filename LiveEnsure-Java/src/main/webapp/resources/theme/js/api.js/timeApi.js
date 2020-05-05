var sd=null;
$(function() {
	$("#challenge").on('click','#time',function() {
		$("#challenge").html('<div class="row"><h2><span class="label label-success">Time Challenge</span></h2></div>'
				+ '<div class="row"><div class="col-md-3"><h5><span class="label label-warning">Start Time :</span></h5></div><div class="col-md-9"><input type="text" class="form-control" id="startDate" /></div></div>'
				+ '<div class="row"><h4><span class="label label-warning">Add Validation Period :</span></h4></div>'
				+ '<div class="row"><div class="col-md-3"><h5><span class="label label-warning">Day : </span></h5></div><div class="col-md-1"><span class="input-group-btn"><button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[1]"><span class="glyphicon glyphicon-minus"></span></button></span></div>'
		        + '<div class="col-md-2"><input type="text" name="quant[1]" id="days" class="form-control input-number" value="0" min="0" max="10"></div>'
		        + '<div class="col-md-1"><span class="input-group-btn"><button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[1]"><span class="glyphicon glyphicon-plus"></span></button></span></div></div>'
		        + '<div class="row"><div class="col-md-3"><h5><span class="label label-warning">Hour(s) : </span></h5></div><div class="col-md-1"><span class="input-group-btn"><button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[2]"><span class="glyphicon glyphicon-minus"></span></button></span></div>'
		        + '<div class="col-md-2"><input type="text" name="quant[2]" id="hours" class="form-control input-number" value="0" min="0" max="23"></div>'
		        + '<div class="col-md-1"><span class="input-group-btn"><button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[2]"><span class="glyphicon glyphicon-plus"></span></button></span></div></div>'
		        + '<div class="row"><div class="col-md-3"><h5><span class="label label-warning">Minute(s) : </span></h5></div><div class="col-md-1"><span class="input-group-btn"><button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[3]"><span class="glyphicon glyphicon-minus"></span></button></span></div>'
		        + '<div class="col-md-2"><input type="text" name="quant[3]" id="minutes" class="form-control input-number" value="1" min="1" max="59"></div>'
		        + '<div class="col-md-1"><span class="input-group-btn"><button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[3]"><span class="glyphicon glyphicon-plus"></span></button></span></div></div>'
		        + '<div class="row"><div class="col-md-1"><input type="radio" name="inout" value="true" class="form-control"/></div><div class="col-md-2"><span class="label label-warning">In</span></div>'
		        + '<div class="col-md-3"></div>'
				+ '<div class="col-md-1"><input type="radio" name="inout" value="false" class="form-control"/></div><div class="col-md-2"><span class="label label-warning">Out</span></div></div>'
				+ '<div class="row"><input id="timeChall" type="button" value="Submit" class="form-control btn success"/></div>');
		
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
	$("#challenge").hide();
	var sendInfo = {
		sessionToken : session,
		agentId : agent,
		startDate : $("#startDate").val().trim(),
		endDate : endDate(),
		inout : $('input[name="inout"]:checked').val(),
		required : "true"
	};
	$.ajax({
		url : base_url + "/timeChallenge",
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