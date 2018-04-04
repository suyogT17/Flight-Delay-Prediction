<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Flight Frequency </title>
</head>
<body>

  <div id="wrapper">
 <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">SB Admin v2.0</a>
            </div>
            <!-- /.navbar-header -->

           
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="index.html"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                       
                   
                        
                        <li>
                           
                           
                            <li>
                                    <a href="flightadd.html"> Flight Details</a>
                                </li>
                                <li>
                                    <a href="blank.html">Flight Frequency</a>
                                </li>
                                <li>
                                    <a href="flightsch.html">Flight Schedule</a>
                                </li>
                         
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

 
        <div id="page-wrapper">
            <div class="row">
            <div class="col-lg-12">
            <h1 class="page-header">Add Flight Schedulde</h1>
        </div>
            </div>
            <div class="row">
            <div class="col-md-3">
            
            </div>
            <div class="col-md-6">
            <div class="panel panel-primary">
    <div class="panel-heading">Flight Schedulde</div>
    <div class="panel-body">
        <form method="post" modelAttribute="flight" action="saveflight.html" >
          
      <div class="form-group">

      <label for="formSName">FlightNo</label>
      <select class="form-control" id="sel1">
    
    <option>1</option>
    <option>2</option>
     <option>3</option>
    </select>

                       </div>
<div class="form-group">
<label for="formSName">Start Time</label>
<input type="time" class="form-control" name="startime">

</div>     

<div class="form-group">
<label for="formSName">End Time</label>
<input type="time" class="form-control" name="startime">

</div>          
         <div class="form-group">

      <label for="formSName">Class Type</label>
      <select class="form-control" id="sel1">
    
    <option>Economy</option>
    <option>Business</option>
    </select>

                       </div>                       
                 <div class="form-group">

      <label for="formSName">Price</label>
      <input type="number" class="form-control">

                       </div>  
 <div class="form-group">

      <label for="formSName">Category</label>
      <select class="form-control" id="sel1">
    
    <option>Morning</option>
    <option>Afternoon</option>
    <option>Evening</option>
    <option>Night</option>
    </select>

                       </div>    
                       <div class="form-group">
                       <label for="fornsname">FlightStop</label>
                       <input type="text" class="form-control">
                       </div>                            
				  <div class="input-group-btn"> 
						<input type="submit" class="btn btn-primary add-more"><i class="glyphicon glyphicon-plus"></i> >
					  </div>
			

        </form>
            
            
            
            </div>
            
            
            
            
            
            
            
            
            
            
            
             
                </div>
                </div>

</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/dist/js/bootstrap-datepicker.min.js"></script>
<script	src="${pageContext.request.contextPath}/resources/dist/js/metisMenu.js"></script>

<script	src="${pageContext.request.contextPath}/resources/dist/js/bootstrap-multiselect.js"></script>
<script	src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/bootstrap-multiselect.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/metisMenu/dist/metisMenu.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/sb-admin-2.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datetimepicker.min.css">





</body>
</html>