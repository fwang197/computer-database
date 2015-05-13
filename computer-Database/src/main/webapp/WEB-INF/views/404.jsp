<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Computer Database</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${context}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${context}/css/font-awesome.css" rel="stylesheet" media="screen">
    <link href="${context}/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="${context}/Dashboard"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="alert alert-danger">
              	<spring:message code="error.404"/>
                <br/>
                <!-- stacktrace -->
            </div>
        </div>
    </section>

    <script src="${context}/js/jquery.min.js"></script>
    <script src="${context}/js/bootstrap.min.js"></script>
    <script src="${context}/js/dashboard.js"></script>

</body>
</html>