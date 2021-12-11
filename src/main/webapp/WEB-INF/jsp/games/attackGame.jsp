<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="attackGame">

    <form:form name="enemies" action="" method="POST">
        <div class=row>
                <div class=col-md-4>
                    <img src="\resources\images\Taheral.png">
                    <input type="radio" id="enemy1" name="enemy" value="Enemy1">
                    <label for="enemy1">Enemy1</label> 
                    <h2> Vida :</h2>  
                </div>
                <div class=col-md-4>
                    <img src="\resources\images\Idril.png">
                    <input type="radio" id="enemy2" name="enemy" value="Enemy2">
                    <label for="enemy2">Enemy2</label>
                    <h2> Vida :</h2>      
                </div>
                <div class=col-md-4>
                    <img src="\resources\images\Feldon.png">
                    <input type="radio" id="enemy3" name="enemy" value="Enemy3">
                    <label for="enemy3">Enemy3</label>
                    <h2> Vida :</h2>   
                </div>
        </div>
        <div class=row>
            <div class="col-md-12 text-center" style="margin-top: 5%;">
               <input class="btn btn-default" type="submit" value="Attack"/>
            </div>
        </div> 
    </form:form>
    <br>
    <br>
    <br>
    <form:form name="skills" action="" method="POST">
    	<div class=row>
                <div class=col-md-4>
                    <img src="\resources\images\Taheral.png">
                    <input type="radio" id="skill1" name="skill" value="Skill1">
                    <label for="skill1">Skill1</label>   
                </div>
                <div class=col-md-4>
                    <img src="\resources\images\Idril.png">
                    <input type="radio" id="skill2" name="skill" value="Skill2">
                    <label for="skill2">Skill2</label>   
                </div>
                <div class=col-md-4>
                    <img src="\resources\images\Feldon.png">
                    <input type="radio" id="skill3" name="skill" value="Skill3">
                    <label for="skill3">Skill3</label>   
                </div>
        </div>
  
        <div class=row>
            <div class="col-md-12 text-center" style="margin-top: 5%;">
                    <input class="btn btn-default" type="submit" value="Use"/>
            </div>
       	</div> 
     </form:form>
      
</petclinic:layout> 