<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="user">

<div th:if="${message}" th:text="${message}"/>

    <h2>Register User</h2>

    <table id="userTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 20%;">Name</th>
            <th style="width: 20%;">LastName</th>
            <th style="width: 20%;">Username</th>
            <th style="width: 60%x;">email</th>
            <th style="width: 5%;"></th>
            <th style="width: 5%;"></th>
            <th style="width: 5%;"></th>
            
            
        </tr>
         </thead>
        <tbody>
        <c:forEach items="${user}" var="user">
            <tr>
                <!-- <td>
                   <spring:url value="/jugadores" var="jugadoresUrl">
                        <spring:param name="cartaId" value="${carta.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(cartaUrl)}"><c:out value="${carta.name}"/></a>
                </td>-->
                <td>
                    <c:out value="${user.name}"/>
                </td>
                <td>
                    <c:out value="${user.lastname}"/>
                </td>
                <td>
                    <c:out value="${user.username}"/>
                </td>
                <td>
                    <c:out value="${user.email}"/>
                </td>
                <td>
                	<a href="/users/${user.id}/edit">
                	<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                	</a>
                </td>
                <td>
                	<a href="/users/${user.id}/delete">
                	<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                	</a>
                </td>
                <td>
                	<a href="/users/${user.id}/details">
                	<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
                	</a>
                </td>                      
            </tr>
            
            
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${pag != 0}">      
		<td>
			<a href="/users/${pag-1}"> <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Previous page</a>
		</td>
	</c:if>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<c:if test="${pag != lastPag}">      
	<td>
		<a href="/users/${pag+1}"> <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>Next Page</a>
	</td>
	</c:if>
	<br><br><br>
    <p>
    	<a href="/users/new" class="btn  btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add User</a>
    </p>
</petclinic:layout>
