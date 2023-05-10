<%-- 
    Document   : register
    Created on : Sep 1, 2022, 3:33:09 PM
    Author     : Kirk Herbison
    @author Kirk Herbison
    Kirk_BJ_A01_B_16981925
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:import url="includes/header.jsp">
    <c:param name="title" value="results"/>
    <c:param name="css" value="vehicle_confirmation.css"/>
</c:import>
<main>
    <br><br>
    <form action="controller" method="POST">
    <fieldset>
        <legend>User Verification:</legend>
        <div class="user_input">
            <label>User ID:</label>
            <p><c:out value='${endUserLogedin.id}'/></p>
        </div>                   
        <div class="user_input">
            <label>Name:</label>
            <p><c:out value='${endUserLogedin.endUserFirstName}'/> <c:out value='${endUserLogedin.endUserLastName}'/></p>
        </div>
        <div class="user_input">
            <label>User Name:</label>
            <p><c:out value='${endUserLogedin.username}'/></p>
        </div> 
        <div class="user_input">
            <label>Phone:</label>
            <p><c:out value='${formatedPhone}'/></p>
        </div>
        <c:if test ="${endUserLogedin.endUserText == true}">
            <div class="user_input">
                <label>Text:</label>
                <p><c:out value='${endUserLogedin.endUserText}'/></p>
            </div>
        </c:if>
        
        <div class="user_input">
            <label>Email:</label>        
        
                    <c:choose>
                        <c:when test="${endUserLogedin.endUserEmail != ''}">
                                <p><c:out value='${endUserLogedin.endUserEmail}'/></p>
                        </c:when>
                        <c:otherwise>
                                <p>no email on record</p>                  
                        </c:otherwise>
                    </c:choose> 
         </div>    
    </fieldset>

        <fieldset>
            <legend>Vehicle Selection:</legend>
            <div class="user_input">
                <label>Vehicle:</label>
                <p class="info">
                    <c:out value='${rental.vehicleYear}'/>
                    <c:out value='${rental.make}'/>
                    <c:out value='${rental.model}'/>
                </p>
            </div>    
            <div class="user_input">
                <label>Pick Up Date:</label>
                <p><c:out value='${rental.startTime}'/></p>
            </div>
            <div class="user_input">
                <label>Return Date:</label>
                <p><c:out value='${rental.endTime}'/></p>
            </div>                   
            <div class="user_input">
                <label>Per Day Cost:</label>
                <p><c:out value='${rental.formatedCostPerDay}'/></p>
            </div>
            <div class="user_input">
                <label>Days:</label>
                <p><c:out value='${rental.totalDays}'/></p>
            </div>  
            <div class="user_input">
                <label>Total Cost:</label>
                <p><c:out value='${rental.totalCost}'/></p>
            </div>

            <div class="user_input">
                <label></label>
                <input type="submit" name="edit_vehicle_button" value="Edit Vehicle"/> 
            </div> 
            <div class="user_input">
                <label></label>
                <input type="submit" name="rent_vehicle_button" value="Rent Vehicle"/> 
            </div> 
        </fieldset>
    </form>
</main>
<c:import url="includes/footer.jsp">
    <c:param name="page" value="results
             .jsp"/>
</c:import>
