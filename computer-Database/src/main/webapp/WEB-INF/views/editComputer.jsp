<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="Dashboard"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${compdto.compId}
                    </div>
                    <h1><spring:message code="edit.computer"/></h1>

                    <form action="EditComputer" method="POST" id="add">
                        <input type="hidden" value="${compdto.compId}" name="compId"/>
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="computer.name"/></label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="computer.name"/>" value="${compdto.name}">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="computer.intro"/></label>
                                <input type="date" class="form-control" id="introduced" name="introduced"placeholder="<spring:message code="computer.intro.format"/>" value="${compdto.introduced}">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="computer.discon"/></label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="<spring:message code="computer.discon.format"/>" value="${compdto.discontinued}">
                            </div>
                            
                            <div class="form-group">
                                <label for="companyId"><spring:message code="company.name"/></label>
                                <select class="form-control" id="companyId" name="companyId" >
                                    <option value="${compdto.companyId}">${compdto.companyName}</option>
                               
                                    <option value="0">--</option>
                                    <c:forEach var="comp" items="${list}">
                                    	<c:choose>
                                    		<c:when test="${comp.name != compdto.companyName}">                                  
                             	   				<option value="${comp.id}">${comp.name}</option>
                                    		</c:when>
                                    	</c:choose>
									</c:forEach>
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value=<spring:message code="edit.message"/> class="btn btn-primary">
                            <spring:message code="or.message"/>
                            <a href="Dashboard" class="btn btn-default"><spring:message code="cancel.message"/></a>
                        </div>
                    </form>
                    <script type="text/javascript" src="js/jquery.min.js"></script>
					<script type="text/javascript" src="js/jquery.validate.min.js"></script>
                    <script src="js/script.js"></script>
                </div>
            </div>
        </div>
    </section>
</body>
</html>