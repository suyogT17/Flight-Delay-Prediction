$(document).ready(function() {
	
	 $.ajax({
         type: "get",
         url: "aprioriresults",
         contentType: "application/json",
 		  
         success: function (data)
        	 {
       	
               //$("#displayrecord")[0].reset();
              
               var data2 = JSON.parse(data);
               console.log("THe data is"+data);
   			   $("#tabledata").dataTable({
   	             responsive: true,
   	             destroy: true,
   	             data: data2,
   	             columns: [
   	                        { data: 'setno' },
   	                        {data:'minsupport'},
   	                        {data:'productIds'}
   	                   	]
   	             
   	        		});
       
     		}
   	});
	 
	 


		});


    
    
 
  
 