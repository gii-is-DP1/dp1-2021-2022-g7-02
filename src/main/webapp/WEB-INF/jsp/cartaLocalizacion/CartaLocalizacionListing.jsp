<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="cartasLocalizacion">
    <h2>Cartas de Localización</h2>

    <table id="cartasLocalizacionTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
            <th style="width: 200px;">Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cartasLocalizacion}" var="cartaLocalizacion">
            <tr>
            	<td>
                	<c:out value="${cartaLocalizacion.name}"/>
            	</td>
            	<td>
                	<c:out value="${cartaLocalizacion.description}"/>
            	</td> 
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>