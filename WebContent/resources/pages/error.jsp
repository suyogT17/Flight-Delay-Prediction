<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
.node circle {
	fill: #fff;
	stroke: steelblue;
	stroke-width: 3px;
}

.node text {
	font: 15px sans-serif;
}

.link {
	fill: none;
	stroke: #ccc;
	stroke-width: 2px;
}

.data {
	display: none;
}

p {
	display: none;
}
</style>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/d3v3.js"></script>
<!-- Page Content -->
<br>
<br>
<br>
<br>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<h1 class="page-header">Flights Delay Prediction</h1>

					<div class="alert alert-danger" id="message">
						<h2 id="fdelay">Sorry You Have not Selected Any Input</h2>
						<h3>Go back by Clicking Here<a href="predictioninput.html"> <button type="button" class="btn btn-primary" id="submitbtn">Go back</button></a>
						 </h3>
					</div>
	<!-- /.row -->
</div>
</div>
</div>
<!-- /.container-fluid -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/dist/css/bootstrap-multiselect.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/treant-js-master/Treant.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/treant-js-master/vendor/perfect-scrollbar/perfect-scrollbar.css">



<script
	src="${pageContext.request.contextPath}/resources/dist/js/bootstrap-datepicker.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/dist/js/metisMenu.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/dist/js/bootstrap-multiselect.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/treant-js-master/Treant.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/treant-js-master/vendor/raphael.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/treant-js-master/examples/super-simple/super-simple.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/dist/js/jquery.validate.js"></script>

