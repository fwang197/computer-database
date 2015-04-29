<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
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
                ${page.nb} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="DashboardServlet" method="GET" class="form-inline">
						
      				    <input type="hidden" value="${page.range}" name="range">
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

        <form id="deleteForm" action="DeleteComputerServlet" method="POST">
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
                         
                          <mylib:link target="DashboardServlet" order="desc" field="computer.name" body="&uarr;"/>
                          <mylib:link target="DashboardServlet" order="asc" field="computer.name" body="&darr;"/>
                        </th>
                        <th>
                            Introduced date
                            
                          <mylib:link target="DashboardServlet" order="desc" field="introduced" body="&uarr;"/>
                          <mylib:link target="DashboardServlet" order="asc" field="introduced" body="&darr;"/>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                            
                          <mylib:link target="DashboardServlet" order="desc" field="discontinued" body="&uarr;"/>
                          <mylib:link target="DashboardServlet" order="asc" field="discontinued" body="&darr;"/>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                            
                          <mylib:link target="DashboardServlet" order="desc" field="company.name" body="&uarr;"/>
                          <mylib:link target="DashboardServlet" order="asc" field="company.name" body="&darr;"/>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                    
                   	<c:forEach var="i" items="${page.lcomp}">	
							<tr>
								<td class="editMode"><input type="checkbox" name="cb" class="cb" value="${i.id}"></td>
								<td><mylib:link target="EditComputerServlet" body="${i.name}" compId="${i.id}"/></td>
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
       <mylib:pagination target="DashboardServlet"/>

    </footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>