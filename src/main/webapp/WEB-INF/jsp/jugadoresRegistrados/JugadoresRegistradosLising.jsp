<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="jugadorRegistrado">
    <h2>Jugadores Registrados</h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 20%;">Name</th>
            <th style="width: 20%;">LastName</th>
            <th style="width: 20%;">Username</th>
            <th style="width: 60%x;">email</th>
        </tr>
         </thead>
        <tbody>
        <c:forEach items="${jugadores}" var="jugador">
            <tr>
                <!-- <td>
                   <spring:url value="/jugadores" var="jugadoresUrl">
                        <spring:param name="cartaId" value="${carta.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(cartaUrl)}"><c:out value="${carta.name}"/></a>
                </td>-->
                <td>
                    <c:out value="${jugador.name}"/>
                </td>
                <td>
                    <c:out value="${jugador.lastname}"/>
                </td>
                <td>
                    <c:out value="${jugador.username}"/>
                </td>
                <td>
                    <c:out value="${jugador.email}"/>
                </td>
                <td>
                	<a href="/jugadores/${jugador.id}/edit">
                	<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                	</a>
                </td>
                <td>
                	<a href="/jugadores/${jugador.id}/delete">
                	<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                	</a>
                </td>        
            </tr>
            
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
