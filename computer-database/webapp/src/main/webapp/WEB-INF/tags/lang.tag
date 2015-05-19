<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="compId" required="false" type="java.lang.Integer" %>

<spring:message code="lang.language"/>: <a href="?lang=en&compId=${compId}"><img src="images/english_flag.png" alt="<spring:message code="lang.english"/>" height="30" width="30"/> </a><a
				href="?lang=fr&compId=${compId}"><img src="images/french_flag.png" alt="<spring:message code="lang.french"/>" height="30" width="30"/></a>