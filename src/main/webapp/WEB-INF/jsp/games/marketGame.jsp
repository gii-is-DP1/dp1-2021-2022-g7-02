<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="marketGame">
    <form:form name="itemSelected" action="" method="POST">
    	<table id="CardsTable" class="table table-striped">
    
    		<tbody>
			<c:forEach items="${market}" var="market">
				<div class=col-md-2 style="margin-left: 3%;">
                    <img src="${market.url}">
                    <input type="radio" id="item1" name="itemSelected" value="${market.id}">
                    <label for="item1"> <c:out value="${market.name}" /></label>
                    <h2>Precio: <c:out value="${market.cost}" /></h2>   
                </div>
			</c:forEach>
		</tbody>
                
         	</table>
                
                
        <div class=row>
            <div class="col-md-12 text-center" style="margin-top: 5%;">
                <div class=col-md-3>
                    	<h2>Oro:</h2> 
                    	<h2><c:out value="${gameUser.gold}" /></h2>
                </div>
            </div>
        </div>
    <c:if test="${game.getUserPlaying() == user}">
        <div class=row>
            <div class="col-md-12 text-center" style="margin-top: 5%;">
                    <input class="btn btn-default" type="submit" value="Buy Items"/>
            </div>
        </div>  
        <div class=row>
            <div class="col-md-12 text-center" style="margin-top: 5%;">
            		<a class="btn btn-default" href="/games/${game.id}/endTurn">Siguiente Turno</a>           
            </div>
        </div>  
    </c:if>

	</form:form>

</petclinic:layout> 