<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="games">
    <h2>Partidas</h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th class="width20">Creador</th>
            <th class="width10">Fecha</th>
            <th class="width8">Duracion</th>
            <th class="width8">Está en progreso</th>
            <th class="width8">Visibilidad</th>
            <th class="width8">Codigo Invitacion</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr>
                <td>
                	<c:out value="${game.creator!=null ? game.creator : '?'}"/>
                </td>
                <td>
                    <c:out value="${game.date}"/>
                </td>
                <td>
                    <c:out value="${game.duration}"/>
                </td>
                <td>
                    <c:out value="${game.isInProgress ? 'Sí' : 'No'}"/>
                </td>
                <td>
                    <c:out value="${game.isPublic ? 'Público' : 'Privado'}"/>
                </td>
                <td>
                    <c:out value="${game.joinCode}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>
    	<a href="/games/new" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>New Game</a>
    </p>
    
</petclinic:layout>
