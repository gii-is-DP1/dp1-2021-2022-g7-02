<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="defendGame">

	<h2>ENEMIGOS</h2>
	<div class=row>
		<c:forEach items="${enemies}" var="enemyCard">
			<div class=col-md-4>
				<img src="${enemyCard.url}">
				<h2>
					Vida :
					<c:out value="${enemyCard.healthInGame}" />
				</h2>
			</div>
		</c:forEach>
	</div>

	<c:if test="${game.getUserPlaying() == user}">
		<h2>HEROE</h2>
		<div class=row>
			<div class=col-md-4>
				<img src="${heroes.url}">
				<h2>
					Vida restante:
					<c:out value="${player.getHeroeHealth()}" />
				</h2>
			</div>
		</div>

		<div class=row>
			<div class=col-md-4>
				<h2>
					Cartas en el mazo:
					<c:out value="${numberOfSkillCards}" />
				</h2>

			</div>
		</div>
	</c:if>

	<c:if test="${game.getUserPlaying() == user}">
		<a class="btn btn-default" href="/games/${game.id}/">Ir a mercado</a>
	</c:if>



</petclinic:layout>