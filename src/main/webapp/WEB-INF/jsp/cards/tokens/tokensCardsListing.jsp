<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>


<petclinic:layout pageName="GameCards">

<div th:if="${message}" th:text="${message}"/>


	<h2>Tokens</h2>

	<table id="CardsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 10%;">Name</th>
				<th style="width: 25%;">Url</th>
				<th style="width: 80%;">Usability</th>


			</tr>
		</thead>
		<tbody>
			<tr>
				<td><c:out value="Oro" /></td>
				<td><c:out value=""/></td>
				<td><c:out value="Úsalo para comprar artilugios en la tienda. Además, por cada 3 monedas de oro que tengas al final 
                    de la partida, obtendrás un punto de gloria extra." /></td>
			</tr>
            <tr>
				<td><c:out value="Gloria" /></td>
				<td><c:out value=""/></td>
				<td><c:out value="Obtendrás puntos de gloria por derrotar enemigos en la batalla. Reúne cuantos más puedas 
                    para ganar la partida y ser el héroe con más gloria." /></td>
			</tr>
            <tr>
				<td><c:out value="Vida" /></td>
				<td><c:out value=""/></td>
				<td><c:out value="Representa los puntos de vida que le quedan tanto a tu héroe como a los enemigos de la horda contra 
                    los que te enfrentes." /></td>
			</tr>
            <tr>
				<td><c:out value="Scape token" /></td>
				<td><c:out value=""/></td>
				<td><c:out value="Úsalo para saltar turno si te ves muy apurado. Cuidado, es de un solo uso por partida." /></td>
			</tr>
		</tbody>
	</table>
</petclinic:layout>