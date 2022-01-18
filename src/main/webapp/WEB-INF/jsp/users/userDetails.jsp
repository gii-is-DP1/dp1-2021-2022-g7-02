<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="UserDetails">
	<h2>User details</h2>

	<table id="ownersTable" class="table table-striped">
		<tr>
			<th>Name</th>
			<td><b><c:out value="${user.name}" /></b></td>
		</tr>
		<tr>
			<th>Last Name</th>
			<td><b><c:out value="${user.lastname}" /></b></td>
		</tr>
		<tr>
			<th>Username</th>
			<td><c:out value="${user.username}" /></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><c:out value="${user.email}" /></td>
		</tr>
	</table>

	<td><a href="/users/0"> <span
			class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
	</a></td>

</petclinic:layout>