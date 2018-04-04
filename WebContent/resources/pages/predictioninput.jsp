<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<!-- Page Content -->
	<div class="container-fluid">
		<div class="row">
		
			<!--  Start designing your Html body from here -->
			
<br><br><br>	
<div class="col-lg-2"></div>
	<div class="col-lg-8">
			<h1 class="page-header">Prediction Input:</h1>
				
				<div class="panel panel-primary">

					<div class="panel-body">
						<form:form id="add-serv-form" method="post"
							modelAttribute="predictionInput" action="viewresult.html">
					
							
					<c:forEach items="${categoryList}" var="c">													
						<div class="col-lg-4">	
							<div class="form-group">

								<label>${c.categoryName}:</label><br>
									
									<form:select class="${c.categoryName}" path="predictionInputList" multiple="multiple">
									
									<c:forEach items="${ c.categoryAttr }" var="v">													
										<form:option value="${v.categoryAttrId }">${v.categoryAttrName } </form:option>
									</c:forEach>
										
									</form:select>
									<font color='red'>
									<div id="erruserIds"></div>
									</font>
							</div>
						</div>	
					</c:forEach>
						
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



 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/bootstrap-multiselect.css">
<style>
#categoryUl{

	display: none;
}

</style>
 <!-- jQuery -->
 <script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/dist/js/bootstrap-datepicker.min.js"></script>
<script	src="${pageContext.request.contextPath}/resources/dist/js/metisMenu.js"></script>
<script	src="${pageContext.request.contextPath}/resources/dist/js/bootstrap-multiselect.js"></script>

<script>
	$(document).ready(function() {
	    	   
	    	    $("select").multiselect({
	   			enableFiltering: true,
	   			enableCaseInsensitiveFiltering: true,
	   			maxHeight: 200
	   		}); 

		
		

	
	});
</script>
