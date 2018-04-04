<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>




<!-- Page Content -->
	<div class="container-fluid">
		<div class="row">
		
			<!--  Start designing your Html body from here -->
			<div class="col-lg-2"></div>

			<div class="col-lg-8">
			<h1 class="page-header">Register</h1>
				
				<div class="panel panel-primary">

					<div class="panel-body">
						<form:form id="add-serv-form" method="post"
							modelAttribute="register" action="register.html">

							<div class="form-group">
								<label for="formSName">First Name:</label>
								<form:input type="text" path="firstName" id="firstname"
									class="form-control" />
							</div>
							
							<div class="form-group">
								<label for="formSName">Last Name:</label>
								<form:input type="text" path="lastName" id="lastname"
									class="form-control" />
							</div>
							
							<div class="form-group">
								<label for="formSName">User Name:</label>
								<form:input type="text" path="userName" id="username"
									class="form-control" />
							</div>
							
							<div class="form-group">
								<label for="formSName">Password:</label>
								<form:input type="password" path="password" id="password"
									class="form-control" />
							</div>
							
							<div class="form-group">
								<label for="formSName">Email-Id:</label>
								<form:input type="text" path="emailId" id="emailid"
									class="form-control" />
							</div>
							<div class="form-group">
								<label for="formSName">Phone No.:</label>
								<form:input type="text" path="contactNumber" id="phoneno"
									class="form-control" />
							</div>
							
							
							<button type="submit" class="btn btn-primary" id="submitbtn">Submit</button>

						</form:form>
					</div>



				</div>
			</div>
		</div>
		<!--end of col-md-6-->

	</div>
	<!-- /.row -->

<!-- /.container-fluid -->


<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/bootstrap-multiselect.css">
<script src="${pageContext.request.contextPath}/resources/dist/js/bootstrap-datepicker.min.js"></script>
<script	src="${pageContext.request.contextPath}/resources/dist/js/metisMenu.js"></script>
<script	src="${pageContext.request.contextPath}/resources/dist/js/bootstrap-multiselect.js"></script>

<script>
	$(document).ready(function() {
		//Initialize Select2 Elements
 
		$("#projectName").attr("readonly",true);
		$("#roleName").attr("readonly",true);
		$('#userIds').multiselect({
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true,
			maxHeight: 200
		});

		
		//Date picker
		$('.inline-datepicker').datepicker({
			todayHighlight : true
		});
		//iCheck for checkbox and radio inputs

		$("#submitbtn").click(function() {

			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var userIds=$("#userIds").val();
			var err = 0;

			if(userIds==null){
				$("#erruserIds").text("Select Atleast one Employee");
				err++;
			}

			
			if (startDate == " " || startDate.length == 0) {
				$("#errstartDate").text("Select Start Date");
				err++;
			}
			if (endDate == " " || endDate.length == 0) {
				$("#errendDate").text("Select End Date");
				err++;
			}

			var start = Date.parse(startDate);
			var end = Date.parse(endDate);
			var d=new Date();
			var currentDateStr=(d.getMonth()+1)+"/"+d.getDate()+"/"+d.getFullYear();
			console.log(currentDateStr);
			var currentDate=Date.parse(currentDateStr);
			
			if(start < currentDate)
			{
				$("#errstartDate").text("You have selected Incorrect date");
				err++;	
			}			
			if(end < currentDate){
				$("#errendDate").text("You have selected Incorrect date");
				err++;
			}
			if (start > end) {
				$("#errstartDate").text("Select Valid Dates");
				$("#errendDate").text("Select Valid Dates");
				err++;
			}

			if (err != 0) {
				return false;
			}
 
		});
		
		
		$("#userIds").change(function() {
			$("#erruserIds").text("");
		});
		

		$("#startDate").focus(function() {
			$("#errstartDate").text("");
		});
		$("#endDate").focus(function() {
			$("#errendDate").text("");
		});

	});
</script>
