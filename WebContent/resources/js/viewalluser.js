$(document).ready(function() {
      
       $.ajax({
         type: "get",
         url: "viewalluser.html",
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
                               { data: 'username' },
                               {data:'firstname'},
                               {data:'lastname'},
                               {data:'contactnumber'},
                               {data:'emailid'}
                            ]
                    
                            });
      
              }
       });
       
       $(".table-striped").on("click",'tbody tr', function() {
           
    	   var userId= $(this).children('td:first').text();
    	   var userName=$(this).children('td:first').next().text();
    	   var updateUserUrl = "updateuser.html?username="+userId;
    	   var deleteUserUrl="userDeprovisionrequest.html?userName="+userName;
           $('.updateuser').attr('href',updateUserUrl);
           $('.deleteuser').attr('href',deleteUserUrl);
           //do all your operation populate the modal and open the modal now. DOnt need to use show event of modal again
           $('#myModal').modal('show');
         



       }); 
});
 