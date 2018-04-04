<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<br><br><br><br>
<!-- Page Content -->
<div class="container-fluid" style="background-color: white;">
	<div class="row">

		<!--  Start designing your Html body from here -->


		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<h1 class="page-header">Flights Available</h1>
		
	
   		
   		
   	<c:choose>
    <c:when test="${'yes' eq msg}">
        <div class="alert alert-danger" id ="message">
		             <h2> Sorry no Flights Available.</h2>
		 </div>
    </c:when>    
    <c:otherwise>
        
    
		
		<c:forEach items="${ result }" var="v">													

				
				<div class="panel panel-primary">

					<div class="panel-body">
					
						<h4>${ v.flightNumber }</h4>
						<h5> <label>${v.origin } - ${v.destination}</label> </h5>
						<h5> <label>Price :</label>  ${v.price} </h5>
						<h5> <label>Start: </label> ${v.startTime } </h5> 
						<h5> <label>End:</label> ${v.endTime } </h5> 
					
					</div>
				</div>

		</c:forEach>
			
							
							
	</c:otherwise>
	</c:choose>						

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

<script>
	$(document).ready(function() {

	});
</script>
