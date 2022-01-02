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


    <h2>GAMES PLAYED</h2>

        <table id="statsTable" class="table table-striped">
            <thead>
                <tr>
                    <th>Creator</th>
                    <th>Date</th>
                    <th>Durations</th>
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
                                <c:out value="${game.duration} segundos"/>
                            </td>

                        </tr>
                </c:forEach> 
            </tbody>
        </table>
        <div class=row>
			<div class=col-md-12>
				<h2>
					Game with min duration:
					<c:out value="${minDuration} segundos"/>
				</h2>

			</div>
		</div>
		        <div class=row>
			<div class=col-md-12>
				<h2>
					Game with max duration:
					<c:out value="${maxDuration} segundos" />
				</h2>

			</div>
		</div>
		        <div class=row>
			<div class=col-md-12>
				<h2>
					Average durations games
					<c:out value="${averageDuration} segundos" />
				</h2>

			</div>
		</div>
      

    
</petclinic:layout>