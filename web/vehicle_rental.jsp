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
    <c:param name="title" value="vehicle_rental"/>
    <c:param name="css" value="vehicle_rental.css"/>
</c:import>
<link href="<c:url value='js/jquery-ui-1.12.1.custom/jquery-ui.min.css'/>" rel="stylesheet"/>
<script defer src="<c:url value='js/vehicle_rental.js'/>"></script>
<main>
    <br><br>
    <form action="controller" method="POST">
    <fieldset>
        <legend>Rental Information:</legend>   
        <div class="user_input">
            <label>Name:</label>
            <p><c:out value='${endUserLogedin.endUserFirstName}'/> <c:out value='${endUserLogedin.endUserLastName}'/></p>
        </div>
        <div class="user_input">
            <label>Phone:</label>
            <p><c:out value='${formatedPhone}'/></p>
        </div>
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
    </fieldset>

        <input type="hidden" name="requestAction" value="rent">
        <fieldset>
            <legend>Credit Card Information:</legend>
            <c:if test = "${errorMessage != null}" ><p class="error_message">${errorMessage}</p></c:if>           
            <br>

            <div class="user_input">
                <label for="cars">Credit Card:</label>
                <c:set var="cardType" value="${endUserin.creditCardType.getShortDesc()}" />
                <select name="creditCardTypes" id="creditCardTypes">
                    <c:forEach var="item" items="${creditCardTypes}">
                        <option value="${item.shortDesc}" ${cardType == item.shortDesc ? 'selected' : ''}><c:out value='${item.longDesc}'/></option>
                    </c:forEach>
                </select>
            </div>

            <div class="user_input">
                <label>Card Number:</label>

                <input type="text" name="cardNumber" maxlength="16" minlength="13"
                       value="<c:out value='${endUserLogedin.creditCard.getCardNumber()}'/>">
            </div>

            <div class="user_input">
                <label class="parent">CVV: <span id="label_CVV_Information" style="font-weight: normal">?</span></label>
                <div class="positionable" id="cvv_dialog" title="CVV" style="display: none;">
                    <p>The 3 to 4 digit CVV number can be found on the back of your credit card. </p>
                </div>
                <input type="text" name="cvvNumber" maxlength="4" minlength="3"
                       value="<c:out value='${endUserLogedin.creditCard.getCardCvv()}'/>">
            </div> 

            <div class="user_input">
                <label>Card Expiry:</label>
                <input type="text" name="cardExpire" maxlength="7" minlength="7"
                       placeholder="00/0000" value="<c:out value='${endUserLogedin.creditCard.getCardExpiry()}'/>">
            </div>    
            <div class="user_input">
                <label></label>
                <input type="submit" value="Rent Vehicle"/> 
            </div> 
        </fieldset>
    </form>
</main>
<c:import url="includes/footer.jsp">
    <c:param name="page" value="vehicle_rental.jsp"/>
</c:import>

