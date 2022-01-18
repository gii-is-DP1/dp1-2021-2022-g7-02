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


	<h2>Skill Cards</h2>

	<table id="CardsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 10%;">Name</th>
				<th style="width: 10%;">Image</th>
				<th style="width: 70%;">Description</th>


			</tr>
		</thead>
		<tbody>
			<c:forEach items="${skills}" var="skill">
				<tr>
					<td><c:out value="${skill.name}" /></td>
					<td><img src="${skill.url}" style="height: 4cm; width: auto;"></td>
					<td><c:out value="${skill.description}" /></td>
				</tr>


			</c:forEach>
		</tbody>
	</table>
	     <c:if test="${pag != 0}">      
	<td>
		<a href="/cards/skills/${pag-1}"> <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Previous page</a>
	</td>
	</c:if>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<td>
		<a href="/cards/skills/${pag+1}"> <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>Next Page</a>
	</td>

</petclinic:layout>