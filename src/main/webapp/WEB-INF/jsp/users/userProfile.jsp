<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>




<petclinic:layout pageName="userProfile">

<div th:if="${message}" th:text="${message}"/>


    <h2>User details</h2>

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
    
    <h2>Number of games</h2>
    <table id="statsTable" class="table table-striped">
            <thead>
                <tr>
                    <th>This Week</th>
                    <th>This Month</th>
                    <th>Media by day</th>
                </tr>
            </thead>
            <tbody>
                    <tr>
                        <td>
                            <c:out value="${Week}"/>
                        </td>
                        <td>
                            <c:out value="${Month}"/>
                        </td>
                        <td>
                            <c:out value="${Week/7}"/>
                        </td>
                    </tr>
            </tbody>
    </table> 
        
    <h2>Your stats</h2>

    <c:if test="${empty games}">  
        <p>The user has not played any game yet.</p> 
    </c:if>
    <c:if test="${!empty games}">
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
    </c:if>  
    
    <h2>Users you have played against</h2>

    <c:if test="${empty games}">  
        <p>The user has not played any game yet.</p> 
    </c:if>
    <c:if test="${!empty games}">
        <table id="statsTable" class="table table-striped">
            <thead>
                <tr>
                    <th>User</th>
                    <th>Number of games</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${players}" var="player">
                    <tr>
                        <td>
                            <c:out value="${player.key}"/>
                        </td>
                        <td>
                            <c:out value="${player.value}"/>
                        </td>
                    </tr>
                </c:forEach> 
            </tbody>
        </table>
    </c:if> 
 
     <p>
    	<a href="/users/profile/gameDuration" class="btn  btn-success"><span class="glyphicon glyphicon" aria-hidden="true"></span>Game durations</a>
   		<a href="/users/profile/gameStats" class="btn  btn-success"><span class="glyphicon glyphicon" aria-hidden="true"></span>Game Stats</a>
    </p>

</petclinic:layout>
