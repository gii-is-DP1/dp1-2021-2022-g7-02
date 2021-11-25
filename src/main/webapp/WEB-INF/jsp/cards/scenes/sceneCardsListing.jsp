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


	<h2>Scene Cards</h2>

	<table id="CardsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 10%;">Name</th>
				<th style="width: 25%;">Url</th>
				<th style="width: 70%;">Description</th>
				<th style="width: 5%;"></th>
				<th style="width: 5%;"></th>


			</tr>
		</thead>
		<tbody>
			<c:forEach items="${scenes}" var="scene">
				<tr>
					<td><c:out value="${scene.name}" /></td>
					<td><c:out value="${scene.url}" /></td>
					<td><c:out value="${scene.description}" /></td>
					
					<td><a href="/cards/scenes/${scene.id}/edit"> <span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</a></td>
					<td><a href="/cards/scenes/${scene.id}/delete"> <span
							class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					</a></td>
				</tr>


			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="/cards/scenes/new" class="btn  btn-success"><span
			class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add Scene</a>
	</p>
</petclinic:layout>