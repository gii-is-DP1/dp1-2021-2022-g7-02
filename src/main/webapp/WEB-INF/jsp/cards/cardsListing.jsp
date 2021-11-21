<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>


<petclinic:layout pageName="GameCards">

<div th:if="${message}" th:text="${message}"/>


	<h2>Game Cards</h2>

	<table id="CardsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 10%;">Name</th>
				<th style="width: 10%;">Type</th>
				<th style="width: 5%;">Life</th>
				<th style="width: 5%;">Glory</th>
				<th style="width: 5%;">Gold</th>
				<th style="width: 80%;">Skill</th>
				<th style="width: 5%;">Extraglory</th>
				<th style="width: 10%;">Color</th>
				<th style="width: 5%;">DeckId</th>
				<th style="width: 5%;"></th>
				<th style="width: 5%;"></th>


			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cards}" var="card">
				<tr>
					<td><c:out value="${card.name}" /></td>
					<td><c:out value="${card.type}" /></td>
					<td><c:out value="${card.life}" /></td>
					<td><c:out value="${card.glory}" /></td>
					<td><c:out value="${card.gold}" /></td>
					<td><c:out value="${card.skill}" /></td>
					<td><c:out value="${card.extraglory}" /></td>
					<td><c:out value="${card.color}" /></td>
					<td><c:out value="${card.deckid}" /></td>
					<td><a href="/cards/${card.id}/edit"> <span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</a></td>
					<td><a href="/cards/${card.id}/delete"> <span
							class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					</a></td>
				</tr>


			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="/cards/new" class="btn  btn-success"><span
			class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add Card</a>
	</p>
</petclinic:layout>