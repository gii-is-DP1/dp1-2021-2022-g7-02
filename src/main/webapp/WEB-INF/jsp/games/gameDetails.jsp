<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="games">

    <h2>Detalles de partida</h2>

    <p style="color: brown;">
        Creador:  <c:out value="${game.creator}"/>
    </p>
    <p style="color: brown;">
        ¿Está en progreso?:  <c:out value="${game.isInProgress ? 'Sí' : 'No'}" />
    </p>
    <h3  style="margin-top: 5%;">Jugadores</h3>
    <table id="ownersTable" class="table table-striped">
        <thead>
            <tr>
                <th style="width: 25%;">Nombre</th>
                <th style="width: 25%;">Apellido</th>    
                <th style="width: 25%;">Username</th>
                <th style="width: 25%;">Email</th>    
            </tr>
         </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
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
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
</petclinic:layout>