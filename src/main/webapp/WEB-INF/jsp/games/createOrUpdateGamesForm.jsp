<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <h2>
		Game
    </h2>
    <form:form method="POST" modelAttribute="games" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">            
        <select name="isPublic">
            <option value="0">Privado</option>
            <option value="1" selected>Publico</option>
        </select>   
 
 
 
                  
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">

                <a href="/games">
                    <button class="btn btn-default" type="submit">Create</button>
                </a>
                  
            </div>
        </div>
    </form:form>
</petclinic:layout> 