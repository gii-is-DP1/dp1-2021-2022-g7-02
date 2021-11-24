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


	<h2>Market Cards</h2>

	<table id="CardsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 10%;">Name</th>
				<th style="width: 25%;">Url</th>
				<th style="width: 25%;">Cost</th>
				<th style="width: 70%;">Description</th>
				<th style="width: 5%;"></th>
				<th style="width: 5%;"></th>


			</tr>
		</thead>
		<tbody>
			<c:forEach items="${market}" var="market">
				<tr>
					<td><c:out value="${market.name}" /></td>
					<td><c:out value="${market.url}" /></td>
					<td><c:out value="${market.cost}" /></td>
					<td><c:out value="${market.description}" /></td>
					
					<td><a href="/market/${market.id}/edit"> <span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</a></td>
					<td><a href="/market/${market.id}/delete"> <span
							class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					</a></td>
				</tr>


			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="/market/new" class="btn  btn-success"><span
			class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add Market Card</a>
	</p>
</petclinic:layout>