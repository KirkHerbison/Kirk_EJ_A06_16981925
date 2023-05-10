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
    <c:param name="css" value="results.css"/>
</c:import>
        <main>
            <br><br>
                <fieldset>
                    <legend>User Verification - <c:out value='${endUserLogedin.userType}'/>:</legend>                     
                    <div class="user_input"> 
                        <label>End User ID:</label>
                        <p><c:out value='${endUserLogedin.id}'/></p>
                    </div>                   
                    <div class="user_input">
                        <label>Name:</label>
                        <p><c:out value='${endUserLogedin.endUserFirstName} ${endUserLogedin.endUserLastName}'/></p>
                    </div>
                    <div class="user_input">
                        <label>Username:</label>
                        <p><c:out value='${endUserLogedin.username}'/></p>
                    </div> 
                    <div class="user_input">
                        <label>Phone Number:</label>
                        <p><c:out value='${formatedPhone}'/></p>
                    </div>
                    <c:if test ="${endUserLogedin.endUserText == true}">
                    <div class="user_input">
                        <label>Okay To Text:</label>
                        <p><c:out value='${endUserLogedin.endUserText}'/></p>
                    </div>
                    </c:if>
                    <div class="user_input">
                    <label>Email:</label>
                    <c:choose>
                        <c:when test="${endUserLogedin.endUserEmail != null}">
                                <p><c:out value='${endUserLogedin.endUserEmail}'/></p>
                        </c:when>
                        <c:otherwise>
                                <p>no email on record</p>                  
                        </c:otherwise>
                    </c:choose>  
                                </div>
                </fieldset>
        </main>
    <c:import url="includes/footer.jsp">
        <c:param name="page" value="results
                 .jsp"/>
    </c:import>
