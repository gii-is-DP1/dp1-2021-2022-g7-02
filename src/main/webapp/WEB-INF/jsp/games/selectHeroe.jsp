<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
    <form:form name="heroe" action="" method="POST">
        <div class=row>
            <div class=col-md-3>
                <img src="\resources\images\Taheral.png">
                <input type="radio" id="taheral" name="heroe" value="Taheral">
                <label for="taheral">Taheral</label>   
            </div>
            <div class=col-md-3>
                <img src="\resources\images\Idril.png">
                <input type="radio" id="idril" name="heroe" value="Idril">
                <label for="idril">Idril</label>   
            </div>
            <div class=col-md-3>
                <img src="\resources\images\Feldon.png">
                <input type="radio" id="feldon" name="heroe" value="Feldon">
                <label for="feldon">Feldon</label>   
            </div>
            <div class=col-md-3>
                <img src="\resources\images\Lisavette.png">
                <input type="radio" id="lisavette" name="heroe" value="Lisavette">
                <label for="lisavette">Lisavette</label>   
            </div>
        </div>
        <div class=row>
            <div class=col-md-3>
                <img src="\resources\images\Aranel.png">
                <input type="radio" id="aranel" name="heroe" value="Aranel">
                <label for="aranel">Aranel</label>   
            </div>
            <div class=col-md-3>
                <img src="\resources\images\Beleth.png">
                <input type="radio" id="belethil" name="heroe" value="Beleth-Il">
                <label for="belethil">Beleth-Il</label>   
            </div>
            <div class=col-md-3>
                <img src="\resources\images\Neddia.png">
                <input type="radio" id="neddia" name="heroe" value="Neddia">
                <label for="neddia">Neddia</label>   
            </div>
            <div class=col-md-3>
                <img src="\resources\images\Valerys.png">
                <input type="radio" id="valerys" name="heroe" value="Valerys">
                <label for="valerys">Valerys</label>   
            </div>
        </div>
        <div class=row>
            <div class="col-md-12 text-center" style="margin-top: 5%;">
                <c:if test="${!hasSelected}">
                    <input class="btn btn-default" type="submit" value="Select heroe"/>
                </c:if>
                
            </div>
            
        </div>
        
       
        
    </form:form>
    
</petclinic:layout> 