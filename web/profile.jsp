<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:import url="includes/header.jsp">
    <c:param name="title" value="profile"/>
    <c:param name="css" value="results.css"/>
</c:import>
        <main>
            <br><br>
                <fieldset>
                    <legend>User Profile - <c:out value='${endUserLogedin.userType}'/>:</legend>                     
                    <div class="user_input"> 
                        <label>End User ID:</label>
                        <p><c:out value='${endUserLogedin.id}'/></p>
                    </div>                   
                    <div class="user_input">
                        <label>First Name:</label>
                        <input type="text" required name="endUserFirstName" maxlength="20"value="<c:out value='${endUserLogedin.endUserFirstName}'/>">
                    </div>
                    <div class="user_input">
                        <label>Last Name:</label>
                        <input type="text" required name="endUserLastName" maxlength="20"value="<c:out value='${endUserLogedin.endUserLastName}'/>">
                    </div>
                    <div class="user_input">
                        <label>Username:</label>
                        <input type="text" required name="endUserUsername" minlength="6"value="<c:out value='${endUserLogedin.username}'/>">
                    </div> 
                    <div class="user_input">
                        <label>Phone Number:</label>
                        <input type="text" required name="endUserPhone" maxlength="20" value="<c:out value='${endUserLogedin.endUserPhone}'/>">
                    </div>                       
                    <div class="user_input">
                        <label>Receive Texts: </label>
                            <input type="checkbox" name="endUserText" <c:if test="${endUserLogedin.endUserText == true}">checked="checked"</c:if>>
                    </div>
                    <div class="user_input">
                    <label>Email:</label>
                        <input type="text" required name="endUserEamil" maxlength="20" value="<c:out value='${endUserLogedin.endUserEmail}'/>">
                    </div>
                </fieldset>
        </main>
    <c:import url="includes/footer.jsp">
        <c:param name="page" value="results
                 .jsp"/>
    </c:import>
