<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="cartasLoc">
    <h2>Cartas</h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
            <th style="width: 200px;">Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cartasloc}" var="carta">
            <tr>
                <td>
                    <spring:url value="/cartasLoc/{cartaId}" var="cartaUrl">
                        <spring:param name="cartaId" value="${carta.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(cartaUrl)}"><c:out value="${carta.name}"/></a>
                </td>
                <td>
                    <c:out value="${carta.description}"/>
                </td>
                <td>
                	<a href="/cartasloc/${carta.id}/edit">
                	<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                	</a>
                </td>     
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
