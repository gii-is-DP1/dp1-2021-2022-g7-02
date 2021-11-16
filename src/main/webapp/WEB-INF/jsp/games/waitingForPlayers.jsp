<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="UsersInGame">
    <h2>Waiting for users</h2>


        <p>Join code:  <c:out value="${game.joinCode}" /></p>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 100%;">Username</th>    
        </tr>
         </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>
                    <c:out value="${user.username}"/>
                </td>                           
            </tr>
            
            
        </c:forEach>
        </tbody>
    </table>
    <p>
    	<a class="btn btn-default">Start Game</a>
    </p>
</petclinic:layout>