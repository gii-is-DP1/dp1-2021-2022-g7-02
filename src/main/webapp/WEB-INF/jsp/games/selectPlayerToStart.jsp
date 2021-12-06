<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="users">
	<table id="ownersTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 100%;">Username</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.username}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form:form action=""  modelAttribute="games/selectPlayerToStart/" method="POST" >
		<div class=row>
			<div class="col-md-12 text-center" style="margin-top: 5%;">
				<button class="btn btn-default" type="submit">Select Player</button> 
			</div>
		</div>
	</form:form>


</petclinic:layout>
