<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="marketGame">

    <form:form name="market" action="" method="POST">
        <div class=row>
                <div class=col-md-4>
                    <img src="\resources\images\Taheral.png">
                    <input type="radio" id="item1" name="item" value="Item1">
                    <label for="item1">Item1</label>
                    <h2>Precio:</h2>   
                </div>
                <div class=col-md-4>
                    <img src="\resources\images\Idril.png">
                    <input type="radio" id="item2" name="item" value="Item2">
                    <label for="item2">Item2</label>
                    <h2>Precio:</h2>   
                </div>
                <div class=col-md-4>
                    <img src="\resources\images\Feldon.png">
                    <input type="radio" id="item3" name="item" value="Item3">
                    <label for="item3">Item3</label>
                    <h2>Precio:</h2>   
                </div>
        </div>
        <div class=row>
        	    <div class=col-md-4>
                    <img src="\resources\images\Lisavette.png">
                    <input type="radio" id="item4" name="item" value="Item4">
                    <label for="item4">Item4</label>
					<h2>Precio:</h2>     
                </div>
                <div class=col-md-4>
                    <img src="\resources\images\Aranel.png">
                    <input type="radio" id="item5" name="item" value="Item5">
                    <label for="item5">Item5</label>
                    <h2>Precio:</h2>     
                </div>
               <div class=col-md-4>
                    <img src="\resources\images\Aranel.png">
                    <input type="radio" id="item6" name="item" value="Item6">
                    <label for="item5">Item6</label> 
                    <h2>Precio:</h2>  
                </div>
        </div>
        <div class=row>
            <div class="col-md-12 text-center" style="margin-top: 5%;">
                <div class=col-md-3>
                    	<h2>Oro:</h2> 
                    	<h2>4</h2>
                </div>
            </div>
        </div>
        <div class=row>
            <div class="col-md-12 text-center" style="margin-top: 5%;">
                    <input class="btn btn-default" type="submit" value="Buy Items"/>
            </div>
        </div>   
    </form:form>

</petclinic:layout> 