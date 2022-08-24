<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>




<petclinic:layout pageName="playersRanking">

    <h2>Ranking</h2>

    <p>
    	<a href="/games/playersRankingHeroes" class="btn btn-success"><span  aria-hidden="true"></span>Ranking Heroes</a>
    </p>

    <table id="rankingTable" class="table table-striped">
        <thead>
            <tr>
                <th class="width20">Posición</th>
                <th class="width80">Usuario</th>
                <th class="width20">Victorias</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${ranking}" var="player" varStatus="status">
                <tr>
                    <td>
                        <c:out value="${status.count}"/>
                    </td>
                    <td>
                        <c:out value="${player.get(0)}"/>
                    </td>
                    <td>
                        <c:out value="${player.get(1)}"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    
    
</petclinic:layout>