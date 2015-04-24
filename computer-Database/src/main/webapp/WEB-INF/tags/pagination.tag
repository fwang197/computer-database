<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ attribute name="target" required="false" type="java.lang.String"%>
<%@ attribute name="search" required="false" type="java.lang.String"%>
<%@ attribute name="order" required="false" type="java.lang.String"%>
<%@ attribute name="field" required="false" type="java.lang.String"%>

 <div class="container text-center">
            <ul class="pagination">
                <li> 
                <c:choose>
            	<c:when test="${(page.pageNum-1) >= 0}">
                    <mylib:link target="${target}" body="<span aria-hidden='true'>&laquo;</span>" pageNum="${page.pageNum-1}" ariaLabel="Previous" search="${search}" order="${order}" field="${field}"/>
                     </c:when>
            	 	<c:otherwise>
<%--             	 	<mylib:link target="" body="<span aria-hidden='true'>&laquo;</span>" pageNum="${page.pageNum}" ariaLabel="Previous" search="${search}" order="${order}" field="${field}"/> --%>
            	 	</c:otherwise>
            	 </c:choose>
              </li>
              
              <c:forEach var="i" begin="1" end="1">
             	 <c:choose>
            		 <c:when test="${(page.pageNum-i) >= 0}">
            			 <li><mylib:link target="${target}" body="${page.pageNum-i+1}" pageNum="${page.pageNum-i}" search="${search}" order="${order}" field="${field}"/></li>
            	
            	 	</c:when>
            	 </c:choose>
              </c:forEach>
              
              <li><mylib:link target="${target}" body="${page.pageNum-i+1}" pageNum="${page.pageNum-i}" search="${search}" order="${order}" field="${field}"/></li>
              
              <c:forEach var="i" begin="1" end="1">
             	 <c:choose>
            		 <c:when test="${(page.pageNum+i)*page.range < page.nb}">
            			 <li><mylib:link target="${target}" body="${page.pageNum+i+1}" pageNum="${page.pageNum+i}" search="${search}" order="${order}" field="${field}"/></li>
            	 	</c:when>
            	 </c:choose>
              </c:forEach>
   
              <li>
              <c:choose>
            	<c:when test="${(page.pageNum+1)*page.range < page.nb}">
                 <mylib:link target="${target}" body="<span aria-hidden='true'>&raquo;</span>" pageNum="${page.pageNum+1}" ariaLabel="Next" search="${search}" order="${order}" field="${field}"/>
                   
                </c:when>
            	 	<c:otherwise>
<%--                  <mylib:link target="${target}" body="<span aria-hidden='true'>&raquo;</span>" pageNum="${page.pageNum}" ariaLabel="Next" search="${search}" order="${order}" field="${field}"/> --%>
            	 	</c:otherwise>
            	 </c:choose>
                </a>
            </li>
        </ul>


        <div class="btn-group btn-group-sm pull-right" role="group" >
         <mylib:link target="${target}" body="<button type='button' class='btn btn-default' >10</button>" range="10" pageNum="0" search="${search}" order="${order}" field="${field}"/>
         <mylib:link target="${target}" body="<button type='button' class='btn btn-default' >50</button>" range="50" pageNum="0" search="${search}" order="${order}" field="${field}"/>
         <mylib:link target="${target}" body="<button type='button' class='btn btn-default' >100</button>" range="100" pageNum="0" search="${search}" order="${order}" field="${field}"/>
 </div>