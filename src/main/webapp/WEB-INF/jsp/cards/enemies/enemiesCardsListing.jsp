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
				
				<th style="width: 5%;"></th>
				<th style="width: 5%;"></th>


			</tr>
		</thead>
		<tbody>
			<c:forEach items="${enemies}" var="enemy">
				<tr>
					<td><c:out value="${enemy.name}" /></td>
					<td><img src="${enemy.url}" style="height: 4cm; width: auto;"></td>
					<td><c:out value="${enemy.isboss}" /></td>
					<td><c:out value="${enemy.maxHealth}" /></td>
					<td><c:out value="${enemy.glory}" /></td>
					<td><c:out value="${enemy.extraGlory}" /></td>
					<td><c:out value="${enemy.extraGold}" /></td>
					
					
					
					<td><a href="/cards/enemies/${enemy.id}/edit"> <span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</a></td>
					<td><a href="/cards/enemies/${enemy.id}/delete"> <span
							class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					</a></td>
				</tr>


			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="/cards/enemies/new" class="btn  btn-success"><span
			class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add enemy</a>
	</p>
</petclinic:layout>