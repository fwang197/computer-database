<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
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
			<a class="navbar-brand" href="Dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<mylib:lang />
			<p/>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<c:url var="logoutUrl" value="/logout" />
				<form action="${logoutUrl}" method="post">
					<input class="btn btn-danger" type="submit"
						value="<spring:message
							code="logout.message" />" /> <input
						type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</c:if>
			<h1 id="homeTitle">${page.nb}
				<spring:message code="dash.nb.computer" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="Dashboard" method="GET"
						class="form-inline">

						<input type="hidden" value="${page.range}" name="range"> <input
							type="search" id="searchbox" name="search" class="form-control"
							placeholder="<spring:message code="dash.search"/>" /> <input
							type="submit" id="searchsubmit"
							value="<spring:message code="dash.filter"/>"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">

					<security:authorize access="hasRole('ROLE_ADMIN')">
						<a class="btn btn-success" id="addComputer" href="AddComputer"><spring:message
								code="add.computer" /></a>
						<a class="btn btn-default" id="editComputer" href="#"
							onclick="$.fn.toggleEditMode();"><spring:message
								code="edit.message" /></a>
					</security:authorize>
				</div>
			</div>
		</div>

		<security:authorize access="hasRole('ROLE_ADMIN')">
			<form id="deleteForm" action="DeleteComputer" method="POST">
				<input type="hidden" name="selection" value=""> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</security:authorize>
		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<th class="editMode" style="width: 60px; height: 22px;"><input
								type="checkbox" id="selectall" /> <span
								style="vertical-align: top;"> - <a href="#"
									id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
										class="fa fa-trash-o fa-lg"></i>
								</a>
							</span></th>

						</security:authorize>
						<th><spring:message code="computer.name" /> <mylib:link
								target="Dashboard" order="desc" field="computer.name"
								body="&uarr;" search="${page.search}" pageNum="${page.pageNum}"
								range="${page.range }" /> <mylib:link target="Dashboard"
								order="asc" field="computer.name" body="&darr;"
								search="${page.search}" pageNum="${page.pageNum}"
								range="${page.range }" /></th>
						<th><spring:message code="computer.intro" /> <mylib:link
								target="Dashboard" order="desc" field="computer.introduced"
								body="&uarr;" search="${page.search}" pageNum="${page.pageNum}"
								range="${page.range }" /> <mylib:link target="Dashboard"
								order="asc" field="computer.introduced" body="&darr;"
								search="${page.search}" pageNum="${page.pageNum}"
								range="${page.range }" /></th>
						<!-- Table header for Discontinued Date -->
						<th><spring:message code="computer.discon" /> <mylib:link
								target="Dashboard" order="desc" field="computer.discontinued"
								body="&uarr;" search="${page.search}" pageNum="${page.pageNum}"
								range="${page.range }" /> <mylib:link target="Dashboard"
								order="asc" field="computer.discontinued" body="&darr;"
								search="${page.search}" pageNum="${page.pageNum}"
								range="${page.range }" /></th>
						<!-- Table header for Company -->
						<th><spring:message code="company.name" /> <mylib:link
								target="Dashboard" order="desc" field="computer.company.name"
								body="&uarr;" search="${page.search}" pageNum="${page.pageNum}"
								range="${page.range }" /> <mylib:link target="Dashboard"
								order="asc" field="computer.company.name" body="&darr;"
								search="${page.search}" pageNum="${page.pageNum}"
								range="${page.range }" /></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach var="i" items="${page.lcomp}">
						<tr>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<td class="editMode"><input type="checkbox" name="cb"
									class="cb" value="${i.compId}"></td>
							</security:authorize>
							<security:authorize access="hasRole('ROLE_ADMIN')" var="admin"/>
							<td><c:choose>
									<c:when test="${admin}">
										<mylib:link target="EditComputer" body="${i.name}"
											compId="${i.compId}" />
									</c:when>
									<c:otherwise>${i.name}</c:otherwise>
								</c:choose></td>
							<td>${i.introduced}</td>
							<td>${i.discontinued}</td>
							<td>${i.companyName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<mylib:pagination target="Dashboard" />

	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>