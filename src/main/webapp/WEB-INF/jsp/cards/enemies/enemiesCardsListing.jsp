<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>


<petclinic:layout pageName="EnemiesCards">

<div th:if="${message}" th:text="${message}"/>


	<h2>Game Cards</h2>

	<table id="CardsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 20%;">Type</th>
				<th style="width: 10%;">Image</th>
				<th style="width: 10%;">IsBoss</th>
				<th style="width: 10%;">Life</th>
				<th style="width: 10%;">Glory</th>
				<th style="width: 10%;">Extraglory</th>
				<th style="width: 10%;">Extragold</th>


			</tr>
		</thead>
		<tbody>
			<c:forEach items="${enemies}" var="enemy">
				<tr>
					<td><c:out value="${enemy.name}" /></td>
					<td><img src="${enemy.url}" style="height: 4cm; width: auto;"></td>
					<td><c:out value="${enemy.isBoss}" /></td>
					<td><c:out value="${enemy.maxHealth}" /></td>
					<td><c:out value="${enemy.glory}" /></td>
					<td><c:out value="${enemy.extraGlory}" /></td>
					<td><c:out value="${enemy.extraGold}" /></td>
				</tr>


			</c:forEach>
		</tbody>
	</table>
     <c:if test="${pag != 0}">      
	<td>
		<a href="/cards/enemies/${pag-1}"> <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Previous page</a>
	</td>
	</c:if>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${pag != lastPag-1}">      
	<td>
		<a href="/cards/enemies/${pag+1}"> <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>Next Page</a>
	</td>
	</c:if>

</petclinic:layout>