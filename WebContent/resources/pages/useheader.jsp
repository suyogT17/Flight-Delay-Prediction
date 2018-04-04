<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="home.html">Shopping Mania</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="about.html">About</a>
                    </li>
                    <li>
                        <a href="service.html">Services</a>
                    </li>
                    <li>
                        <a href="contactus.html">Contact</a>
                    </li>
                    
                    <li>
                        <a href="portfolio.html">Portfolio</a>
                    </li>
                    
                      <li>
                        <a href="faq.html">FAQ</a>
                   	 </li>
                    
                    
                   
                    <li class="dropdown" id="shopcart">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="fa fa-2x fa-shopping-cart "></i><span id="badge" class="badge badge-notify" style="background-color: red;">${fn:length(list)}</span><b class="caret"></b></a>
                        <ul class="dropdown-menu" id ="cartul">
                        	<c:set var="count" value="0" scope="page" />
                        	<c:forEach var ="user" items="${list}">
                        		<li>
                        		<a href="getproduct.html?id=${user.id }">${user.productName}</a>
                        		</li>
                        		<c:set var="count" value="${count+1 }" scope="page" />
                        		
                        	</c:forEach>
                        	
                        	<c:if test="${count > 0}">
                        		<div class="divider"></div>
                        		<li>
                        			
                        			<a href="ordersummary.html">Place Order</a>
                        		
                        		</li>
                        	</c:if>
                        	
                            <!-- <li>
                                <a href="blog-home-1.html">Blog Home 1</a>
                            </li>
                            <li>
                                <a href="blog-home-2.html">Blog Home 2</a>
                            </li>
                            <li>
                                <a href="blog-post.html">Blog Post</a>
                            </li> -->
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-2x fa-user "></i> <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="userprofile.html">My Profile</a>
                            </li>
                            <li>
                                <a href="changepassword.html">Change Password</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href=login.html">Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    