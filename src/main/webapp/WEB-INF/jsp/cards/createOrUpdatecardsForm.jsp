<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="cards">
    <h2>
		Card
    </h2>
    <form:form method="POST" modelAttribute="cards" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
            <petclinic:inputField label="Type" name="type"/> 
            <petclinic:inputField label="Life" name="life"/>            
            <petclinic:inputField label="Glory" name="glory"/>  
            <petclinic:inputField label="Gold" name="gold"/>
            <petclinic:inputField label="Skill" name="skill"/>  
            <petclinic:inputField label="Extraglory" name="extraglory"/>                                   
            <petclinic:inputField label="Color" name="color"/>                                   
            <petclinic:inputField label="DeckId" name="deckid"/>                        
                                             
                                               
                                             
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                  <button class="btn btn-default" type="submit">Enter</button>
            </div>
        </div>
    </form:form>
</petclinic:layout> 