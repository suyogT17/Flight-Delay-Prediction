<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<!-- Page Content -->
<div class="container-fluid">
	<div class="row">

		<!--  Start designing your Html body from here -->

		<br>
		<br>
		<br>
		<div class="col-lg-3"></div>
		<div class="col-lg-6">
			<h1 class="page-header">Prediction Input:</h1>

			<div class="panel panel-primary">

				<div class="panel-body">
					<form:form id="add-serv-form" method="post"
						modelAttribute="pricesearch" action="viewflight.html">

						<div class="form-group">

							<label for="formSName ">Origin</label> <br>

							<form:select class="form-control selectClass" path="origin" required="required">

								<c:forEach items="${ cityList }" var="v">
									<form:option value="${v.cityId }">${v.cityName } </form:option>
								</c:forEach>

							</form:select>


						</div>
						<div class="form-group">

							<label for="formSName">Destination</label> <br>
							<form:select class="form-control selectClass" path="destination" required="required" >

								<c:forEach items="${ cityList }" var="v">
									<form:option value="${v.cityId }">${v.cityName } </form:option>
								</c:forEach>

							</form:select>

						</div>


						<div class="form-group">

							<label for="formSName">Class Type</label> 
							<form:select
								class="form-control" path="classType" id="sel1">

								<form:option value="economy">Economy</form:option>
								<form:option value="business">Business</form:option>
							</form:select>

						</div>
						<div class="form-group">
							<label for="fornsname">Date</label> 
							<form:input type="date" path="date" class="form-control" required="required"/>
						</div>



						<div class="col-lg-12">
							<button type="submit" class="btn btn-primary" id="submitbtn">Search</button>
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

<script>
	$(document).ready(function() {


	});
	
	
	$(".selectClass").change(function() {
		$("select option").prop("disabled", false);
		$(".selectClass").not($(this)).find(
				"option[value='" + $(this).val() + "']").prop(
				"disabled", true);
	});
</script>





