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
    <c:param name="title" value="Register"/>
    <c:param name="css" value="register.css"/>
</c:import>
<link href="<c:url value='js/jquery-ui-1.12.1.custom/jquery-ui.min.css'/>" rel="stylesheet"/>
<script defer src="<c:url value='js/register.js'/>"></script>
        <main>
            <form action="controller" method="POST">
                
                <c:choose>
                    <c:when test ="${endUserLogedin != null}">
                        <input type="hidden" required name="endUserLogedinId" maxlength="20"
                               value="<c:out value='${endUserLogedin.id}'/>" autofocus>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" required name="endUserLogedinId" maxlength="20"
                               value="-1">
                    </c:otherwise>
                </c:choose> 
   
                <input type="hidden" name="requestAction" value="results">
                <fieldset>
                    <legend>User Information:</legend>
                    <p class="error_message">${errorMessage}</p>
                    <br>
                    <div class="user_input">
                        <label>First Name:</label>
                        <div class = "input_group">
                        <c:choose>
                            <c:when test ="${endUserLogedin != null}">
                                <input type="text" required name="endUserFirstName" maxlength="20"
                                       value="<c:out value='${endUserLogedin.endUserFirstName}'/>" autofocus>
                            </c:when>
                            <c:otherwise>
                                <input type="text" required name="endUserFirstName" maxlength="20"
                                       value="<c:out value='${endUser.endUserFirstName}'/>">
                            </c:otherwise>
                        </c:choose>               
                        <span class="error_message">*</span>
                        </div>
                    </div>
                    <br>
                    <div class="user_input">
                        <label>Last Name:</label>
                        <div class = "input_group">
                        <c:choose>
                            <c:when test ="${endUserLogedin != null}">
                                <input type="text" required id="endUserLastName" name="endUserLastName" maxlength="20"
                                       value="<c:out value='${endUserLogedin.endUserLastName}'/>">
                            </c:when>
                            <c:otherwise>    
                                <input type="text" required name="endUserLastName" maxlength="20"
                                       value="<c:out value='${endUser.endUserLastName}'/>">
                            </c:otherwise>
                        </c:choose>
                        <span class="error_message">*</span>
                        </div>
                    </div>
                    <br>
                    <div class="user_input">
                        <label>Phone:</label>
                        <div class = "input_group">
                        <c:choose>
                            <c:when test ="${endUserLogedin != null}">
                                <input type="text" required name="endUserPhone" maxlength="20"
                                       value="<c:out value='${endUserLogedin.endUserPhone}'/>">
                            </c:when>
                            <c:otherwise>
                                <input type="text" required name="endUserPhone" maxlength="20"
                                       value="<c:out value='${endUser.endUserPhone}'/>">
                            </c:otherwise>
                        </c:choose>
                        <span class="error_message">*</span>
                        </div>
                    </div>
                    <br>
                    <div class="user_input">
                        <label>Receive Texts: </label>
                        <c:choose>
                            <c:when test ="${endUserLogedin != null}">
                                <input type="checkbox" name="endUserText"
                                        accesskey="" ${endUserLogedin.endUserText == true ? 'checked' : ''}>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="endUserText">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <br>
                    <div class="user_input">
                        <div class = "input_group">
                        <label>Email:</label>
                        <c:choose>
                            <c:when test ="${endUserLogedin != null}">
                                <input type="email" name="endUserEmail" maxlength="50" placeholder="optional"
                                       value="<c:out value='${endUserLogedin.endUserEmail}'/>">
                            </c:when>
                            <c:otherwise>
                                <input type="text" name="endUserEmail" maxlength="50" placeholder="optional"
                                       value="<c:out value='${endUser.endUserEmail}'/>">
                            </c:otherwise>
                        </c:choose>
                        </div>
                    </div>
                    <br>
                </fieldset>
                               
                <fieldset>
                    <legend>Login Credentials</legend>
                    <div class="user_input">
                        <div class = "input_group">
                            <label class="parent">Username: </label><span id="label_username_policy">?</span>
                            <div class="positionable" id="username_dialog" title="Username Rules" style="display: none;">
                                <p>Your username must be at least 6 characters long and cannot 
                                    include spaces. Spaces will automatically be removed.
                            </div>

                                <input type="text" required name="username" minlength="6"
                                       value="<c:out value='${username}'/>">
                            <span class="error_message">*</span>
                        </div>
                    </div>
                    <br>
                    <div class="user_input">
 
                        <div class = "input_group">
                            <label class="parent">Password: </label><span id="label_password_policy">?</span>
                            <div class="positionable" id="pass_dialog" title="Password Rules" style="display: none;">
                                <p>Your password must be at least 10 characters long. 
                                    Please include uppercase and lowercase letters, numbers, 
                                    and special characters, such as % or #.</p>
                            </div>

                                <input type="password" required id="password" name="password" minlength="10"
                                       value="<c:out value='${password}'/>">
                            <span class="error_message">*</span>
                        </div>                        
                        <br>
                        <br>
                    <div class="user_input">
                        <div class = "input_group">
                            <label class="parent">Show Password </label>
                            <input type="checkbox" id="show">
                        </div>
                    </div>
                    <br>
                    <br>
                    <div class="user_input">
                        <label></label>
                        <input type="submit" value="Register"/> 
                    </div>
                </fieldset>
            </form>              
        </main>
    <c:import url="includes/footer.jsp">
        <c:param name="page" value="register.jsp"/>
    </c:import>
