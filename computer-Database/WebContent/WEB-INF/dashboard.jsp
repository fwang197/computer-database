<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="DashboardServlet"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                ${nb} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="AddComputerServlet">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                    
                   	<c:forEach var="i" items="${list}">	
							<tr>
								<td class="editMode"><input type="checkbox" name="cb" class="cb" value="0"></td>
								<td><a href="editComputer.html" onclick="">${i.name}</a></td>
								<td>${i.introduced}</td>
								<td>${i.discontinued}</td>
								<td>${i.company_name}</td>
							</tr>
					</c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                <li> 
                <c:choose>
            	<c:when test="${(pageNum-1) >= 0}">
                    <a href="DashboardServlet?pageNum=${pageNum-1 }" aria-label="Previous">
                     </c:when>
            	 	<c:otherwise>
            	 	<a href="DashboardServlet?pageNum=${pageNum}" aria-label="Previous">
            	 	</c:otherwise>
            	 </c:choose>
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              
              <c:forEach var="i" begin="0" end="2">
             	 <c:choose>
            		 <c:when test="${(pageNum+i-2) > 0}">
            			 <li><a href="DashboardServlet?pageNum=${pageNum+i-2}">${pageNum+i-2}</a></li>
            	 	</c:when>
            	 </c:choose>
              </c:forEach>
              
              <c:forEach var="i" begin="0" end="2">
             	 <c:choose>
            		 <c:when test="${(pageNum+i)*range < nb}">
            			 <li><a href="DashboardServlet?pageNum=${pageNum+i}">${pageNum+i+1}</a></li>
            	 	</c:when>
            	 </c:choose>
              </c:forEach>
   
              <li>
              <c:choose>
            	<c:when test="${(pageNum+1)*range < nb}">
                <a href="DashboardServlet?pageNum=${pageNum+1}" aria-label="Next">
                </c:when>
            	 	<c:otherwise>
            	 	 <a href="DashboardServlet?pageNum=${pageNum}" aria-label="Next">
            	 	</c:otherwise>
            	 </c:choose>
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>

        <div class="btn-group btn-group-sm pull-right" role="group" >
        <a href="DashboardServlet?range=10"> <button type="button" class="btn btn-default" >10</button></a>
        <a href="DashboardServlet?range=50"> <button type="button" class="btn btn-default" >50</button></a>
        <a href="DashboardServlet?range=100"> <button type="button" class="btn btn-default" >100</button></a>
        </div>

    </footer>
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/dashboard.js"></script>

</body>
</html>