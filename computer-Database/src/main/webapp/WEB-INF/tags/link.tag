<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="range" required="false" type="java.lang.Integer"%>
<%@ attribute name="target" required="true" type="java.lang.String"%>
<%@ attribute name="body" required="false" type="java.lang.String"%>
<%@ attribute name="pageNum" required="false" type="java.lang.Integer"%>
<%@ attribute name="ariaLabel" required="false" type="java.lang.String"%>
<%@ attribute name="search" required="false" type="java.lang.String" %>

<a href="${target}?range=${range}&pageNum=${pageNum}&search=${search}" aria-label="${ariaLabel}">${body}</a>
	