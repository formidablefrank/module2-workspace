<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Buy From Us | Admin Dashboard</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/bootstrap.css" rel="stylesheet">

    <!-- Add custom CSS here -->
    <link href="assets/sb-admin.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/font-awesome.css">
    <!-- Page Specific CSS -->
    <link rel="stylesheet" href="assets/morris-0.4.3.min.css">
  </head>

  <body>

    <div id="wrapper">

      <!-- Sidebar -->
      <nav class="navbar navbar-inverse navbar-fixed-top">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="cust">Customer Page</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav side-nav">
            <li class="active"><a href="cust"><i class="fa fa-edit"></i> Shop</a></li>
            <li><a href="viewCart"><i class="fa fa-table"></i> View Cart</a></li>
          </ul>

          <ul class="nav navbar-nav navbar-right navbar-user">
            <li class="dropdown user-dropdown">
              <a href="" class="dropdown-toggle"><i class="fa fa-user"></i> ${sessionScope.username}</a>
            </li>
            <li class="dropdown user-dropdown"><a href="home">Home Page	</a></li>
            <li class="dropdown user-dropdown"><a href="logout">Log Out</a></li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </nav>

      <div id="page-wrapper">

        <div class="row">
          <div class="col-lg-12">
            <h1>Products List</h1>
            <ol class="breadcrumb">
              <li><a href="cust"><i class="fa fa-dashboard"></i> Customer Page</a></li>
              <li class="active"><i class="fa fa-edit"></i> Shop</li>
            </ol>
          </div>
        </div><!-- /.row -->
        
        <div class="row">
	      <div class="col-lg-4">
            <form action="cust">
               <label>Select category:</label>
               <select class="form-control" name="category" onchange="this.form.submit()">
                 <option>Clear Filter</option>
                 <c:forEach items="${inventory.getCategories()}" var="category">
                   <option>${category.getName()}</option>
                 </c:forEach>
               </select>
            </form>
	      </div>
        </div><!-- /.row -->
        
        <c:if test="${category != null}">
        <div class="row">
          <div class="col-lg-12">
            <h2>${category.getName()}</h2>
            <div class="table-responsive">
              <table class="table table-hover table-bordered table-striped tablesorter">
                <thead>
                  <tr>
                    <th>Name <i class="fa fa-sort"></i></th>
                    <th>Price <i class="fa fa-sort"></i></th>
                    <th>Available Qty <i class="fa fa-sort"></i></th>
                    <th>Image</th>
                    <th>Add to Cart</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${category.getList().keySet()}" var="product">
                  	<tr>
                      <td>${product.getName()}</td>
                      <td>Php ${product.getPrice()}</td>
                      <td>${category.getList().get(product)}</td>
                      <td><img src="${product.getImage()}" width="50" height="50"></img></td>
                      <td>
                      	<div class = "row">
                      	  <div class="col-lg-6">
                      	<form action="addToCart" method="post">
                      	  	<select class="form-control" name="quantity" onchange="this.form.submit()">
                      	  	  <c:forEach begin="0" end="${category.getList().get(product)}" var="q">
                      	  	    <option>${q}</option>
                      	  	  </c:forEach>
                      	  	</select>
                      	    <input type="hidden" name="productName" value="${product.getName()}">
                      	    <input type="hidden" name="category" value="${category.getName()}">
                      	</form>
                      	  </div>
                      	</div>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div><!-- /.row -->
        </c:if>

		<br>
        
        <c:if test="${errorMsg != null}">
        <div class="row">
          <div class="col-lg-6">
            <div class="alert alert-dismissable alert-danger">
              <!--<button type="button" class="close" data-dismiss="alert">&times;</button>-->
              <strong>Oh snap!</strong> ${errorMsg}
            </div>
          </div>
        </div>
        </c:if>
        
        <c:if test="${successMsg != null}">
        <div class="row">
        <div class="col-lg-6">
            <div class="alert alert-dismissable alert-success">
              <!--<button type="button" class="close" data-dismiss="alert">&times;</button>-->
              <strong>${successMsg}</strong> Your <a href="viewCart" class="alert-link">Cart</a> is now updated.
            </div>
          </div>
          </div>
        </c:if>
    </div><!-- /#wrapper -->
    </div>

    <!-- JavaScript -->
    <script src="assets/jquery-1.10.2.js"></script>
    <script src="assets/bootstrap.js"></script>

    <!-- Page Specific Plugins -->
    <script src="assets/raphael-min.js"></script>
    <script src="assets/morris-0.4.3.min.js"></script>
    <script src="assets/morris/chart-data-morris.js"></script>
    <script src="assets/tablesorter/jquery.tablesorter.js"></script>
    <script src="assets/tablesorter/tables.js"></script>

  </body>
</html>