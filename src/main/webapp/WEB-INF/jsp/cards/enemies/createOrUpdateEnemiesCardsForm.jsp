<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="EnemiesCards">
    <h2>
		Heroes Card
    </h2>
    <form:form method="POST" modelAttribute="enemies" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Type" name="name"/>
            <petclinic:inputField label="Url" name="url"/>
            <petclinic:inputField label="IsBoss" name="isBoss"/>
            <petclinic:inputField label="MaxHealth" name="maxHealth"/>
            <petclinic:inputField label="Glory" name="glory"/>                        
            <petclinic:inputField label="Extraglory" name="extraGlory"/>                        
            <petclinic:inputField label="Extragold" name="extraGold"/>                        
                                  
                                             
                                               
                                             
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                  <button class="btn btn-default" type="submit">Enter</button>
            </div>
        </div>
    </form:form>
</petclinic:layout> 