<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
		
			<!--  Start designing your Html body from here -->
			<div class="col-lg-2"></div>

			<div class="col-lg-8">
				<h1 class="page-header">Create Project</h1>
				<div class="panel panel-primary">

					<div class="panel-body">
						<form:form id="add-serv-form" method="post"
							modelAttribute="projectAdd" action="projectmanage.html">


							<div class="form-group">

								<label for="formSName">Name:</label>
								<form:input type="text" path="projectName" id="projectName"
									class="form-control" name="formSName" />
								<font color='red'>
									<div id="errprojectName"></div>
								</font>

							</div>

							<div class="form-group">

								<label for="formSCost">Start Date</label>
								<div class="input-group date" data-date-autoclose="true"
									data-provide="datepicker">
									<form:input type="text" path="startdate" id="startDate"
										class="form-control" />
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span>
								</div>
								<font color='red'>
									<div id="errstartDate"></div>
								</font>
							</div>

							<div class="form-group">

								<label for="formSCost">End Date</label>
								<div class="input-group date" data-date-autoclose="true"
									data-provide="datepicker">
									<form:input type="text" path="enddate" id="endDate"
										class="form-control" />
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span>
								</div>
								<font color='red'>
									<div id="errendDate"></div>
								</font>
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
</div>
<!-- /.container-fluid -->
</div>

<script
	src="${pageContext.request.contextPath}/resources/dist/js/bootstrap-datepicker.min.js"></script>

<script>
	$(document).ready(function() {
		//Initialize Select2 Elements

		//Date picker
		$('.inline-datepicker').datepicker({
			todayHighlight : true
		});
		//iCheck for checkbox and radio inputs

		$("#submitbtn").click(function() {

			var name = $("#projectName").val();
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var err = 0;
			if (name == " " || name.length == 0) {
				$("#errprojectName").text("Enter the Project Name");
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

		$("#projectName").focus(function() {
			$("#errprojectName").text("");
		});
		$("#startDate").focus(function() {
			$("#errstartDate").text("");
		});
		$("#endDate").focus(function() {
			$("#errendDate").text("");
		});

	});
</script>
