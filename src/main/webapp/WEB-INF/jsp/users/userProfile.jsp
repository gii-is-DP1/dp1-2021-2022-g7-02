<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="userProfile">

<div th:if="${message}" th:text="${message}"/>


    <h2>Your User</h2>

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
            
                    </tbody>
    </table>

    <h2>Your stats</h2>
    <table id="statsTable" class="table table-striped">
        <thead>
            <tr>
                <th>Winner</th>
                <th>Duration</th>
                <th>Participants</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${games}" var="game">
                    <tr>
                        <td>
                            <c:out value="${game.winner}"/>
                        </td>
                        <td>
                            <c:out value="${game.duration}"/>
                        </td>
                        <td>    
                            <c:out value="${game.users}"/>
                        </td>
                    </tr>
            </c:forEach> 
        </tbody>
    </table>
    
</petclinic:layout>
