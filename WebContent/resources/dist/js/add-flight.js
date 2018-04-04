$(function(){

    $("#flight-add").validate({
        
        rules: {
        	flightno: "required",
           origin: {
                required: true,
             
            },
            destination: {
                required : true,
                min:1
               
            },
            ftype: "required",
            flightstop:"required"
                
        },
            
        messages: 
        {
        	flightno: 
                {
                    required: "Please Enter Flight Number"
                },
                origin: {
                required: "Please Enter Origin ",
                 
            },
            destination: {
                required: "Please Enter  destination",
                 
               
            },
            ftype: {
                required: "Please Select Flight Type"
            },
            flightstop: {
                required: "Please Select Flight Stop"
            }
        }
    })

    
});