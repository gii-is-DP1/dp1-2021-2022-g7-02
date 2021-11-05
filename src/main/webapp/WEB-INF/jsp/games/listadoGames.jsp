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
            <th style="width: 150px;">Creador</th>
            <th style="width: 200px;">Fecha</th>
            <th style="width: 150px;">Duracion</th>
            <th style="width: 200px;">Está en progreso</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr>
                <td>
                	<c:out value="${game.creator}"/>
                </td>
                <td>
                    <c:out value="${game.date}"/>
                </td>
                <td>
                    <c:out value="${game.duration}"/>
                </td>
                <td>
                    <c:out value="${game.isInProgress}"/>
                </td>
                <td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
