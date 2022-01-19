<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<petclinic:layout pageName="attackGame">
    <form:form action="" method="POST">
    <h2>ENEMIGOS</h2>
        <div class=row>
            <input type="checkbox" id="${enemyCard.getId()}" name="enemySelected" value="0" hidden="true" checked="true">
            <c:forEach items="${enemies}" var="enemyCard">
                <div class=col-md-4>
                    <img src="${enemyCard.url}">
                    <h2> Vida : <c:out value="${enemyCard.healthInGame}" /></h2>
                    <c:if test="${game.getUserPlaying() == user}">
                        <input type="checkbox" id="${enemyCard.getId()}" name="enemySelected" value="${enemyCard.getId()}">
                    </c:if>    
                </div>
            </c:forEach>
        </div>
        <c:if test="${game.getUserPlaying() == user && player.getHeroeHealth()!=0}">
            <h2>HABILIDADES</h2>     
            <div class=row>
                <c:forEach items="${skillCardsOnHand}" var="skillCard">
                    <div class=col-md-3>
                        <img src="${skillCard.url}">
                        <label for="${skillCard.getId()}">
                            <c:out value="${skillCard}" />
                        </label>
                        <input type="radio" id="${skillCard.getId()}" name="skillUsed" value="${skillCard.getId()}" checked="checked">
                    </div>
                </c:forEach>
            </div>

            <div class=row>
                <c:if test="${skillCardsOnHand.size() > 0}">
                    <div class="col-md-5 text-center" style="margin-top: 5%;">
                        <input class="btn btn-default" name="use" type="submit" value="Usar" />
                    </div>
                </c:if>
                    <div class="col-md-5 text-center" style="margin-top: 5%;">
                        <a class="btn btn-default"  href="/games/${game.id}/defense">Pasar a la siguiente fase</a>
                    </div>
                <div class=col-md-2>
                    <c:if test="${hasEscapeToken}">
                        <a class="btn btn-danger"  href="/games/${game.id}/escape">Usar ficha de escape</a>
                    </c:if>
                    
                </div>
            </div>
        </c:if>
        <c:if test="${player.getHeroeHealth()==0}">
            <h2>HAS MUERTO ESPERA A QUE LA PARTIDA TERMINE</h2>
        </c:if>
        </form:form>
        

    
    <c:if test="${game.getUserPlaying() != user}">
        <a class="btn btn-default" href="/games/${game.id}">Es el turno de <c:out value="${game.getUserPlaying()}"/>, pulsa para recargar</a>
    </c:if>
        

</petclinic:layout>