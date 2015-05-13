<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="compId" required="false" type="java.lang.Integer" %>

<spring:message code="lang.language"/>: <a href="?lang=en&compId=${compId}"><spring:message code="lang.english"/> </a>|<a
				href="?lang=fr&compId=${compId}"><spring:message code="lang.french"/> </a>