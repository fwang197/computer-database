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
			<mylib:lang />
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="add.computer" />
					</h1>
					<form:form action="AddComputer" method="POST"
						commandName="computerDto">
						<fieldset>
							<div class="form-group">
								<label for="computerName">${name}</label>
								<form:input path="name" type="text" class="form-control"
									placeholder="${name}" />
							</div>
							<div class="form-group text-center"><form:errors path="name" class="alert alert-danger" /></div>
							<div class="form-group">
								<label for="introduced">${intro}</label>
								<form:input path="introduced" type="date" class="form-control"
									placeholder="${introFormat}" />
							</div>
							<div class="form-group text-center"><form:errors path="introduced" class="alert alert-danger" /></div>
							<div class="form-group">
								<label for="discontinued">${discon}</label>
								<form:input path="discontinued" type="date" class="form-control"
									placeholder="${disconFormat}" />
							</div>
							<div class="form-group text-center"><form:errors path="discontinued" class="alert alert-danger" /></div>
							<div class="form-group">
								<label for="companyId"><spring:message
										code="company.name" /></label> <select class="form-control"
									id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach var="comp" items="${list}">
										<option value="${comp.id}">${comp.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value=<spring:message code="add.message"/>
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