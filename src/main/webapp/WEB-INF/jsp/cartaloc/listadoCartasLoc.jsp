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
        <c:forEach items="${cartasLoc}" var="carta">
            <tr>
                <td>
                    <c:out value="${carta.name}"/>
                </td>
                <td>
                    <c:out value="${carta.description}"/>
                </td>     
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
