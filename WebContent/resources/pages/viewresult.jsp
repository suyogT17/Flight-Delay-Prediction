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

			<c:choose>
				<c:when test="${'yes' eq resultTreeDtos.delay}">
					<div class="alert alert-danger" id="message">
						<h2 id="fdelay">Flight Delay: ${resultTreeDtos.delay}</h2>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-success" id="message">
						<h2 id="fdelay">Flight Delay: ${resultTreeDtos.delay}</h2>
					</div>





					<div class="panel panel-default">
						<div class="panel-heading">

							<h4>Inputs :${resultTreeDtos.input}</h4>
						</div>
						<div class="panel-body">
							<div class="col-lg-12">
								<div class="table-responsive">
									<table class="table table table-striped">
										<thead>
											<tr>

												<th>Delay Element</th>
												<th>Probability</th>

											</tr>
										</thead>

										<c:forEach items="${resultTreeDtos.results}" var="c"
											varStatus="theCount">


											<tr>

												<td>${c.set }</td>
												<td>${c.percent }%</td>


											</tr>




										</c:forEach>
									</table>
								</div>
							</div>
							<!--  Panel ends -->
						</div>


					</div>
					<!--  div end -->
					<div class="data">
						<input type="hidden" id="resulttreedtos" value="${resultTreeDtos}">

						<h1>
							<label>Input</label> : ${resultTreeDtos.input}
						</h1>
						<!-- show it as a main node along with delay value -->
						<h2>
							<label>Delay </label> : ${resultTreeDtos.delay }
						</h2>
						<c:forEach items="${resultTreeDtos.results}" var="c"
							varStatus="theCount">

							<h3>
								<label>${c.set }</label> ${c.percent }%
							</h3>



						</c:forEach>


					</div>
					<button type="button" class="btn btn-primary" id="showtree">Show Tree</button>
						
						<div class="demo"></div>
					

				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- /.row -->
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

<script>
	var count = 0;
	var J = [ {
		"name" : "Top Level",
		"parent" : "null",
		"children" : []
	} ];

	function addMenuItem(title, arr) {
		arr.push({

			"name" : title,
			"parent" : "Top Level",
			"children" : []
		});
		count++;
	}
	function addChild(child, arr, pa) {

		arr.push({

			"name" : child,
			"parent" : pa

		});
	}

	var mh = new Array();
	var myobj;
	myobj = {
		"parent" : "",
		"children" : []

	}
	
				
				$(document).ready(function() {
						$(".demo").hide();
				
						
					$("#showtree").click(function(){
						
						var list = $("h2").text();
						console.log(list);
						$("h3").each(
								function(i, f) { //parent list
									mh.push($(this).text());
									var parent = $(this).text()
									addMenuItem($(this).text(), J[0].children);

									console.log("parent " + $(this).text()
											+ "\nchilds:\n");
									var childs = $(".list" + i).text(); //child list according to parent
									//console.log(childs);
									myobj.parent = $(this).text();
									$(".list" + i).each(
											function(id, g) {
												addChild($(this).text(),
														J[0].children[i].children,
														parent);
												console.log($(this).text());

											});

								});

						var myJSON = JSON.stringify(J);

						var margin = {
							top : 20,
							right : 120,
							bottom : 20,
							left : 320
						}, width = 960 - margin.right - margin.left, height = 500
								- margin.top - margin.bottom;

						var i = 0, duration = 750, root;

						var tree = d3.layout.tree().size([ height, width ]);

						var diagonal = d3.svg.diagonal().projection(function(d) {
							return [ d.y, d.x ];
						});

						var svg = d3.select("body").append("svg").attr("width",
								width + margin.right + margin.left).attr("height",
								height + margin.top + margin.bottom).append("g").attr(
								"transform",
								"translate(" + margin.left + "," + margin.top + ")");

						root = J[0];
						root.x0 = height / 2;
						root.y0 = 0;

						update(root);

						d3.select(self.frameElement).style("height", "500px");

						function update(source) {

							// Compute the new tree layout.
							var nodes = tree.nodes(root).reverse(), links = tree
									.links(nodes);

							// Normalize for fixed-depth.
							nodes.forEach(function(d) {
								d.y = d.depth * 180;
							});

							// Update the nodes…
							var node = svg.selectAll("g.node").data(nodes, function(d) {
								return d.id || (d.id = ++i);
							});

							// Enter any new nodes at the parent's previous position.
							var nodeEnter = node.enter().append("g").attr("class",
									"node").attr(
									"transform",
									function(d) {
										return "translate(" + source.y0 + ","
												+ source.x0 + ")";
									}).on("click", click);

							nodeEnter.append("circle").attr("r", 1e-6).style("fill",
									function(d) {
										return d._children ? "lightsteelblue" : "#fff";
									});

							nodeEnter.append("text").attr("x", function(d) {
								return d.children || d._children ? -13 : 13;
							}).attr("dy", ".35em").attr("text-anchor", function(d) {
								return d.children || d._children ? "end" : "start";
							}).text(function(d) {
								return d.name;
							}).style("fill-opacity", 1e-6);

							// Transition nodes to their new position.
							var nodeUpdate = node.transition().duration(duration).attr(
									"transform", function(d) {
										return "translate(" + d.y + "," + d.x + ")";
									});

							nodeUpdate.select("circle").attr("r", 10).style("fill",
									function(d) {
										return d._children ? "lightsteelblue" : "#fff";
									});

							nodeUpdate.select("text").style("fill-opacity", 1);

							// Transition exiting nodes to the parent's new position.
							var nodeExit = node.exit().transition().duration(duration)
									.attr(
											"transform",
											function(d) {
												return "translate(" + source.y + ","
														+ source.x + ")";
											}).remove();

							nodeExit.select("circle").attr("r", 1e-6);

							nodeExit.select("text").style("fill-opacity", 1e-6);

							// Update the links…
							var link = svg.selectAll("path.link").data(links,
									function(d) {
										return d.target.id;
									});

							// Enter any new links at the parent's previous position.
							link.enter().insert("path", "g").attr("class", "link")
									.attr("d", function(d) {
										var o = {
											x : source.x0,
											y : source.y0
										};
										return diagonal({
											source : o,
											target : o
										});
									});

							// Transition links to their new position.
							link.transition().duration(duration).attr("d", diagonal);

							// Transition exiting nodes to the parent's new position.
							link.exit().transition().duration(duration).attr("d",
									function(d) {
										var o = {
											x : source.x,
											y : source.y
										};
										return diagonal({
											source : o,
											target : o
										});
									}).remove();

							// Stash the old positions for transition.
							nodes.forEach(function(d) {
								d.x0 = d.x;
								d.y0 = d.y;
							});
						}

						// Toggle children on click.
						function click(d) {
							if (d.children) {
								d._children = d.children;
								d.children = null;
							} else {
								d.children = d._children;
								d._children = null;
							}
							update(d);
						}

						
						$(".demo").show("fast");

					})	
					
						
						
						
				//Initialize Select2 Elements
				
			});
</script>