<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>




<petclinic:layout pageName="userProfile">

	<div th:if="${message}" th:text="${message}" />


	<h2>GAMES STATS</h2>
	<br>
	
	<h2>FAVORITE HEROE</h2>
	<div class=row>
		<div class=col-md-4>
			<img src="${heroe.url}">
		</div>
	</div>
	<c:if test="${heroe == null}">
	 
		<div class=row>
		<div class=col-md-4>
			<c:out value="YOU DON'T HAVE FAVORITE HEROE"/>
		</div>
	</div>
	</c:if>
	<br>
	<br>
	<div class=row>
		<div class=col-md-12>
			<h2>
				MaxGold:
				<c:out value="${AllGold}" />
			</h2>

		</div>
	</div>
	<br>
	<br>

	<div class=row>
		<div class=col-md-12>
			<h2>
				MaxGlory:
				<c:out value="${AllGlory}" />
			</h2>

		</div>
	</div>
		<br>
	<br>

	<div class=row>
		<div class=col-md-12>
			<h2>
				Games This Week:
				<c:out value="${Week}" />
			</h2>

		</div>
	</div>
		<br>
	<br>

	<div class=row>
		<div class=col-md-12>
			<h2>
				Games This Month:
				<c:out value="${Month}" />
			</h2>

		</div>
	</div>





</petclinic:layout>