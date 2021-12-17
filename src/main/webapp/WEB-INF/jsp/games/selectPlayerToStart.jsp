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

	<h2>Jugadores que han elegido heroe:</h2>

	<table id="ownersTable" class="table table-striped">
		<thead>
			<tr>
				<th class="width50">Username</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>
						<c:out value="${user.username}" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${!empty game.userPlaying}">
		<h2>El jugador elegido es: <c:out value="${game.userPlaying}" /></h2>
	</c:if>

	<c:if test="${game.getCreator() == loggedUser && empty game.userPlaying && game.users.size() == users.size()}">
		<form:form action="" modelAttribute="games/selectPlayerToStart/" method="POST">
			<div class=row>
				<div class="col-md-12 text-center" style="margin-top: 5%;">
					<button class="btn btn-default" type="submit">Sortear primer jugador</button>
				</div>
			</div>
		</form:form>
	</c:if>

	<c:if test="${empty game.userPlaying && game.getCreator() != loggedUser && game.users.size() == users.size()}">
		<h2>Esperando a que se elija el primer jugador a jugar</h2>
	</c:if>

	<c:if test="${!empty game.userPlaying && game.users.size() == users.size()}">
		<p>
			<a class="btn btn-default" href="/games/${game.getId()}">Ir a la
				partida</a>
		</p>
	</c:if>

</petclinic:layout>