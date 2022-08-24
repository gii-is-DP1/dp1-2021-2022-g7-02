<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>




<petclinic:layout pageName="playersRankingHeroes">

    <h2>Ranking</h2>

    <p>
    	<a href="/games/playersRanking" class="btn btn-success"><span  aria-hidden="true"></span>Ranking Usuarios</a>
    </p>

    <table id="rankingTable" class="table table-striped">
        <thead>
            <tr>
            	<th class="width20">Posición</th>
                <th class="width20">Heroe</th>
                <th class="width80">Color</th>
                <th class="width20">Número de Veces Seleccionado</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${rankingHeroes}" var="heroe" varStatus="status">
                <tr>
                    <td>
                        <c:out value="${status.count}"/>
                    </td>
                    <td>
                        <c:out value="${heroe.get(0)}"/>
                    </td>
                    <td>
                        <c:out value="${heroe.get(1)}"/>
                    </td>
                    <td>
                        <c:out value="${heroe.get(2)}"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
</petclinic:layout>