<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<petclinic:layout pageName="attackGame">

    <h2>ENEMIGOS</h2>
    <div class=row>
        <c:forEach items="${enemies}" var="enemyCard">
            <div class=col-md-4>
                <img src="${enemyCard.url}">
                <h2> Vida :</h2>
            </div>
        </c:forEach>
    </div>
    <c:if test="${game.getFirstPlayer() == user}">
        <h2>HABILIDADES</h2>
        <form:form name="skills" action="" method="POST">
            <div class=row>
                <c:forEach items="${skills}" var="skillCard">
                    <div class=col-md-3>
                        <img src="${skillCard.url}">
                        <label for="taheral">
                            <c:out value="${skillCard}" />
                        </label>
                        <input type="radio" id="skill1" name="skill" value="${skillCard.getId()}">
                    </div>
                </c:forEach>
            </div>

            <div class=row>
                <div class="col-md-12 text-center" style="margin-top: 5%;">
                    <input class="btn btn-default" type="submit" value="Use" />
                </div>
            </div>
        </form:form>
    </c:if>
    <c:if test="${game.getFirstPlayer() != user}">
        <a class="btn btn-default" href="/games/${game.id}">Es el turno de <c:out value="${game.getFirstPlayer()}"/>, pulsa para recargar</a>
    </c:if>
        

</petclinic:layout>