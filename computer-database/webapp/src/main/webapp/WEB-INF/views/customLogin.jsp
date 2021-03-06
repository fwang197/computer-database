<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<spring:message code="form.username" var="user" />
<spring:message code="form.password" var="pwd" />
<spring:message code="form.login" var="login" />
</head>
<body onload='document.loginForm.username.focus();'>
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
		</div>
		<c:if test="${not empty error}">
			<div class="col-xs-4 col-xs-offset-4 box">
				<div class="form-signing text-center">
					<div class="alert alert-danger">
						<div class="has-error">${error}</div>
					</div>
				</div>
			</div>
		</c:if>
		
		<div id="loginbox"
			class="mainbox col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">

			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title text-center">${login}</div>
				</div>

				<div class="panel-body">

					<form name='loginForm'
						action="<c:url value='j_spring_security_check' />" method='POST'>

						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="username"
								class="form-control" type="text" name="username"
								placeholder="${user}" required />
						</div>

						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="password"
								class="form-control" type="password" name="password"
								placeholder="${pwd}" required />
						</div>

						<div class="form-group">
							<!-- Button -->
							<div class="col-sm-12 controls">
								<input class="btn btn-primary pull-right" name="submit"
									type="submit" value="${login}" /> <input type="hidden"
									name="${_csrf.parameterName}" value="${_csrf.token}" />
							</div>
						</div>

					</form>

				</div>
			</div>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>
</body>
</html>