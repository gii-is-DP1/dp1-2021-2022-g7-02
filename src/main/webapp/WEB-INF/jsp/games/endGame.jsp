<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<petclinic:layout pageName="endGame">

    <c:if test="${hordaDerrotada == true}">
        <div class="text-align: center;">
            <h2>Enhorabuena, habeis derrotado a la horda.</h2>
            <h2>Clasificacion</h2>
        </div>
        
        <table id="statsTable" class="table table-striped">
            <thead>
                <tr>
                    <th>Position</th>
                    <th>User</th>
                    <th>Glory</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${players}" var="player" varStatus="status">
                    <tr>
                        <td>
                            <c:out value="${status.count}"></c:out>
                        </td>
                        <td>
                            <c:out value="${player.getUser()}"/>
                        </td>
                        <td>
                            <c:out value="${player.getGlory()}"/>
                        </td>
                    </tr>
                </c:forEach> 
            </tbody>
        </table>
    </c:if>
    <c:if test="${hordaDerrotada == false}">
        <div class="text-align: center;">
            <h2>Habeis perdido!</h2>
        </div>
    </c:if>
    
    
</petclinic:layout>