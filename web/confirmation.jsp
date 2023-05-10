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
    <c:param name="title" value="confirmation"/>
    <c:param name="css" value="results_form.css"/>
</c:import>
        <main>
            <br><br>
                <fieldset>
                    <legend>Customer Notifications:</legend>
                        <p><c:out value='${customerLogedin.customerFirstName}'/> <c:out value='${customerLogedin.customerLastName}'/></p>
                        <br>
                        <p>Thank you for renting a <b><c:out value='${customerRental.make}'/> <c:out value='${customerRental.model}'/></b> from Reliable Vehicle Rental.</p>
                        <p>Your confirmation number is: <b><c:out value='${confirmation}'/></b></p>
                        <p>Please use this number if you need to adjust your rental.</p>
                        <c:if test="${customerLogedin.customerText == true}">
                            <p>Your confirmation number will be sent to your phone. Remember, data charges may apply.</p>
                        </c:if>
                        <c:if test="${customerLogedin.customerEmail != ''}">
                            <p>You will receive an email containing your rental details.</p>
                        </c:if>
                </fieldset>
        </main>
    <c:import url="includes/footer.jsp">
        <c:param name="page" value="results
                 .jsp"/>
    </c:import>
