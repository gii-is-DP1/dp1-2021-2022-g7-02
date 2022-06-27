<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="achievements">

<div th:if="${message}" th:text="${message}"/>


	<h2>Achievements</h2>

	<table id="achievementsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 20%;">Name</th>
				<th style="width: 20%;">Description</th>
				<th style="width: 5%;">Achieved</th>

			</tr>
		</thead>
		<tbody>				
			<c:forEach items="${achievement}" var="achievement" varStatus="status">
				<tr>
					<td><c:out value="${achievement.name}" /></td>
					<td><c:out value="${achievement.description}" /></td>
					<c:if test="${results[status.index]==true}"> 
						<td><c:out value="✔️" /></td>
					</c:if>
					<c:if test="${results[status.index]==false}"> 
						<td><c:out value="❌" /></td>
					</c:if>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<c:if test="${pag != 0}">      
	<td>
		<a href="/achievements/${pag-1}"> <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Previous page</a>
	</td>
	</c:if>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<c:if test="${pag != lastPag-1}">      
	<td>
		<a href="/achievements/${pag+1}"> <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>Next Page</a>
	</td>
	</c:if>
	

</petclinic:layout>