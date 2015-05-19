<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<spring:message code="computer.name" var="name" />
<spring:message code="computer.intro.format" var="introFormat" />
<spring:message code="computer.discon.format" var="disconFormat" />
<spring:message code="computer.intro" var="intro" />
<spring:message code="computer.discon" var="discon" />
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
			<mylib:lang compId="${computerDto.compId}" />
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computerDto.compId}</div>
					<h1>
						<spring:message code="edit.computer" />
					</h1>

					<form:form action="EditComputer" method="POST"
						commandName="computerDto">
						<input type="hidden" value="${computerDto.compId}" name="compId" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">${name}</label>
								<form:input type="text" class="form-control"
									placeholder="${name}" path="name" />
							</div>
							<div class="form-group text-center"><form:errors path="name" class="alert alert-danger" /></div>
							<div class="form-group">
								<label for="introduced">${intro}</label>
								<form:input type="date" class="form-control"
									placeholder="${intro }" path="introduced" />
							</div>
							<div class="form-group text-center"><form:errors path="introduced" class="alert alert-danger" /></div>
							<div class="form-group">
								<label for="discontinued">${discon}</label>
								<form:input type="date" class="form-control"
									placeholder="${discon}" path="discontinued" />
							</div>
							<div class="form-group text-center"><form:errors path="discontinued" class="alert alert-danger" /></div>
							<div class="form-group">
								<label for="companyId"><spring:message
										code="company.name" /></label> <select class="form-control"
									id="companyId" name="companyId">
									<option value="${computerDto.companyId}">${computerDto.companyName}</option>

									<option value="0">--</option>
									<c:forEach var="comp" items="${list}">
										<c:choose>
											<c:when test="${comp.name != computerDto.companyName}">
												<option value="${comp.id}">${comp.name}</option>
											</c:when>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value=<spring:message code="edit.message"/>
								class="btn btn-primary">
							<spring:message code="or.message" />
							<a href="Dashboard" class="btn btn-default"><spring:message
									code="cancel.message" /></a>
						</div>
					</form:form>
					<script type="text/javascript" src="js/jquery.min.js"></script>
					<script type="text/javascript" src="js/jquery.validate.min.js"></script>
					<script src="js/script.js"></script>
				</div>
			</div>
		</div>
	</section>
</body>
</html>