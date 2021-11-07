<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="RegisterPlayer">
    <h2>Register Player</h2>

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
        <c:forEach items="${players}" var="player">
            <tr>
                <!-- <td>
                   <spring:url value="/jugadores" var="jugadoresUrl">
                        <spring:param name="cartaId" value="${carta.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(cartaUrl)}"><c:out value="${carta.name}"/></a>
                </td>-->
                <td>
                    <c:out value="${player.name}"/>
                </td>
                <td>
                    <c:out value="${player.lastname}"/>
                </td>
                <td>
                    <c:out value="${player.username}"/>
                </td>
                <td>
                    <c:out value="${player.email}"/>
                </td>
                <td>
                	<a href="/players/${player.id}/edit">
                	<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                	</a>
                </td>
                <td>
                	<a href="/players/${player.id}/delete">
                	<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                	</a>
                </td>        
            </tr>
            
        </c:forEach>
        </tbody>
    </table>
    <p>
    	<a href="/players/new" class="btn  btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add Player</a>
    </p>
</petclinic:layout>
