<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<!-- Page Content -->
<div class="container-fluid" style="background-color: white;">
	<div class="row">

		<!--  Start designing your Html body from here -->


		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<h1 class="page-header">Add Flight</h1>

			<div class="panel panel-primary">

				<div class="panel-body">


					<form:form method="post" modelAttribute="addflight"
						action="saveflight.html" id="flightadd" name="flightadd">

						<div class="col-lg-6">
							<div class="form-group">

								<label for="formSName">Flight Number:</label>
								<form:input type="text" path="flightNumber" id="flightno"
									class="form-control" name="flightno" required="required"/>
								<font color='red'>
									<div id="errprojectName"></div>
								</font>

							</div>



							<div class="form-group">

								<label for="formSName">Origin</label> <br>

								<form:select class="form-control selectClass" path="origin"   name="origin" id="origin" required="required">

									<c:forEach items="${ cityList }" var="v">
										<form:option value="${v.cityId }">${v.cityName } </form:option>
									</c:forEach>

								</form:select>


							</div>
							<div class="form-group">

								<label for="formSName">Destination</label> <br>
								<form:select class="form-control selectClass" path="destination"    name="destination" required="required">

									<c:forEach items="${ cityList }" var="v">
										<form:option value="${v.cityId }">${v.cityName } </form:option>
									</c:forEach>

								</form:select>

							</div>
							<div class="form-group">

								<label for="formSName">Type</label> <br>
								<form:select class="form-control" path="type" name="ftype" required="required">

									<c:forEach items="${ ftypesList }" var="v">
										<form:option value="${v.ftypeid }">${v.flightype } </form:option>
									</c:forEach>

								</form:select>


							</div>

							<div class="form-group">

								<label for="formSName">Flight Stop</label><br>
								<form:select class="form-control" path="flightStop" name="flightstop" required="required">
									<form:option value="0">----Select---- </form:option>
									<c:forEach items="${ cityList }" var="v">
										<form:option value="${v.cityId }">${v.cityName } </form:option>
									</c:forEach>

								</form:select>

							</div>



						</div>

						<div class="col-lg-6">
							<div classs="form-group">
								<label for="formSName">Start Time</label><br>
								<form:input type="time" path="startTime" id="starttime"
									class="form-control" name="formSName" required="required" /><br>

							</div>
							
							<div classs="form-group">
								<label for="formSName">End Time</label><br>
								<form:input type="time" path="endTime" id="endttime"
									class="form-control" name="formSName" required="required" /><br>


							</div>
							<div class="form-group">
								
								<label for="formSName">Class Type</label> <br> 
								<label
									class="checkbox-inline"> 
								<form:checkbox path="classType" value="economy" />Economy
								</label> 
								<label class="checkbox-inline"> 
								<form:checkbox path="classType" value="business" />Business
								</label><br>





							</div>
							<div classs="form-group">
								<label for="formSName">Price</label><br>
								<form:input type="number" path="price" id="price"
									class="form-control" name="formSName" required="required"/><br>





							</div>
							<div class="form-group">

								<label for="formSName">Category</label> <br>
								<form:select class="form-control" path="category" required="required">


									<form:option value="morning">Morning</form:option>
									<form:option value="afternoon">Afternoon</form:option>
									<form:option value="evening">Evening</form:option>
									<form:option value="night">Night</form:option>


								</form:select>


							</div>
							</div>
							<div class="col-lg-12">
							<div class="form-group">

								<label for="formSName">Frequency</label> <br> 
								
								<label class="checkbox-inline"> 
									<form:checkbox path="day" value="sun" />Sunday
								</label> 
								
								<label class="checkbox-inline"> 
									<form:checkbox path="day" value="mon" />Monday
								</label> 
								
								<label class="checkbox-inline"> 
									<form:checkbox path="day" value="tue" />Tuesday
								</label> 
								
								<label class="checkbox-inline"> 
									<form:checkbox path="day" value="wed" />Wednesday
								</label> 
								
								<label class="checkbox-inline"> 
									<form:checkbox path="day" value="thu" />Thursday
								</label> 
								
								<label class="checkbox-inline"> 
									<form:checkbox path="day" value="fri" />Friday
								</label> 
								
								<label class="checkbox-inline"> 
									<form:checkbox path="day" value="sat" />Saturday
								</label>



							</div>

						</div>

						<div class="col-lg-12">
							<button type="submit" class="btn btn-primary" id="submitbtn">Submit</button>
						</div>
					</form:form>
				</div>



			</div>
		</div>
	</div>
	<!--end of col-md-6-->

</div>
<!-- /.row -->




<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/dist/css/bootstrap-multiselect.css">
<style>
#categoryUl {
	display: none;
}

body {
	background-color: white;
}
</style>
<!-- jQuery -->
<script
	src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/dist/js/bootstrap-datepicker.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/dist/js/metisMenu.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/dist/js/bootstrap-multiselect.js"></script>
<script	src="${pageContext.request.contextPath}/resources/dist/js/jquery.validate.js"></script>
<script	src="${pageContext.request.contextPath}/resources/dist/js/additional-methods.js"></script>
<!--   <script	src="${pageContext.request.contextPath}/resources/dist/js/add-flight.js"></script> -->
<script>
$(function(){

    $("#flightadd").validate({
        
        rules: {
        	flightno:"required",
           origin: "required",
             
          
            destination: {
                required : true
               
               
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
                required: "Please Enter Origin "
                 
            },
            destination: {
                required: "Please Enter  destination"
                 
               
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


$("select").change(function()
		 {
		     var tr = $(this).closest("tr");
		        tr.find("select option").attr("disabled",""); //enable everything

		     //collect the values from selected;
		     var  arr = $.map
		     (
		        tr.find("select option:selected"), function(n)
		         {
		              return n.value;
		          }
		      );

		    //disable elements
		    tr.find("select option").filter(function()
		    {

		        return $.inArray($(this).val(),arr)>-1; //if value is in the array of selected values
		     }).attr("disabled","disabled");   

		});

	$(".selectClass").change(function() {
				$("select option").prop("disabled", false);
				$(".selectClass").not($(this)).find(
						"option[value='" + $(this).val() + "']").prop(
						"disabled", true);
			});
</script>
