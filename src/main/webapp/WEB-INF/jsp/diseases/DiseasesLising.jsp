<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="diseases">
    <h2>Diseases</h2>

    <table id="diseasesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
            <th style="width: 200px;">Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="disease">
            <tr>
                <td>
                    <spring:url value="/diseases" var="diseaseUrl">
                        <spring:param name="diseaseId" value="${disease.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(diseaseUrl)}"><c:out value="${disease.name}"/></a>
                </td>
                <td>
                    <c:out value="${owner.description}"/>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
