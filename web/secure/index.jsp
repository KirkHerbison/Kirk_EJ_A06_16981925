
<%-- 
    Document   : index
    Created on : Jan 20, 2022, 8:53:20 AM
    Author     : Kirk Herbison
    @author Kirk Herbison
    Kirk_BJ_A03_B_16981925
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:import url="../includes/secure_header.jsp">
    <c:param name="title" value="SecureHome"/>
    <c:param name="css" value="employee.css"/>
</c:import>
<link rel="stylesheet" href="js/jquery-ui-1.12.1.custom/jquery-ui.min.css">

<main>
    <div id="tabs">
        <ul>
            <li><a href="#tabs-1">Employee</a></li>
            <li><a href="#tabs-2">Vehicle</a></li>
            <li><a href="#tabs-3">Credit Card</a></li>
        </ul>
        <div id="tabs-1">
            <div class='top'>
                <div class="inputGroup">
                    <label>First Name</label>
                    <input type="text" name="firstName" id="firstName">
                    <p style='color:red;'>*</p>
                </div>   
                <div class="inputGroup">
                    <label>Last Name</label>
                    <input type="text" name="lastName" id="lastName">
                    <p style='color:red;'>*</p>
                </div>   
                <div class="inputGroup">
                    <label>Phone</label>
                    <input type="text" name="phone" id="phone">
                    <p style='color:red;'>*</p>
                </div>   
                <div class="inputGroup">
                    <label for="employeeRole">Employee's Role</label>
                    <select name="employeeRole" id="employeeRole">
                        <option value="1">Please Choose...</option>
                        <option value="2">Manager</option>
                        <option value="3">Developer</option>
                        <option value="4">Staff Employee</option>
                    </select>
                    <p style='color:red;'>*</p>
                </div>
                <div class="inputGroup">
                    <label></label>
                    <input type="submit" value="Add Employee"/> 
                </div>
                
            </div>
            <br>
            <hr>
            <h2>Default/Generated Data</h2>
            <div class='bottom'>
                <div class="inputGroup">
                    <label>Employee ID</label>
                    <input type="text" name="employeeId" id="employeeId" disabled>
                </div>
                <div class="inputGroup">
                    <label>Username</label>
                    <input type="text" name="username" id="username" disabled>
                </div>
                <div class="inputGroup">
                    <label>Email</label>
                    <input type="text" name="email" id="email" disabled> 
                </div>
                <div class="inputGroup">
                    <label>Password</label>
                    <input type="text" name="password" id="password" disabled>
                </div>
            </div>
        </div>
        <div id='tabs-2'>
            <figure>
                <form action="admin" method="POST">
                    <input type="hidden" name="requestAction" value="updateVehicle">
                    <table>
                        <thead>
                            <tr> 
                                <th>Short Desc</th>
                                <th>Long Desc</th>
                                <th>Cost Per Day</th>                     
                                <th>Status</th>
                                <th>Edit Vehicle</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach varStatus="rowIndex" var="vehicle" items="${vehicleTypes}">
                            
                                <tr>
                                    <td>
                                        <span class="info"><c:out value='${vehicle.shortDesc}'/></span>
                                    </td>
                                    <td>
                                        <span class="info"><c:out value='${vehicle.vehicleType}'/></span>
                                    </td>
                                    <td>
                                        <input type="text" name="costPerDay" id="costPerDay" value="<c:out value='${vehicle.costPerDay}'/>"> 
                                    </td>
                                    <td>
                                        <select name="isActiveVehicle" id="isActiveVehicle">
                                            <option value="1">Active</option>
                                            <option value="2"<c:if test="${vehicle.isActive != true}">selected</c:if>>Inactive</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="hidden" name="edit_id" value="<c:out value='${vehicle.ID}'/>">
                                        <button type="submit" name="edit_vehicle" value="${rowIndex.index}">Update</button> 
                                    </td>
                                </tr>
                                
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
            </figure>
        </div>
        <div id='tabs-3'>
            <figure>
            <table class="sortable" id="vehicle_table">
                <thead>
                    <tr> 
                        <th>Short Desc</th>
                        <th>Long Desc</th>                  
                        <th>Status</th>
                        <th>Edit Vehicle</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="creditCardType" items="${creditCardTypes}">
                        <tr>
                            <td>
                                <span class="info"><c:out value='${creditCardType.shortDesc}'/></span>
                            </td>
                            <td>
                                <span class="info"><c:out value='${creditCardType.longDesc}'/></span>
                            </td>
                            <td>
                                <select name="isActiveCreditCard" id="isActiveCreditCard">
                                     <option value="1">Active</option>
                                     <option value="0"<c:if test="${creditCardType.isActive != true}">selected</c:if>>Inactive</option>
                                 </select>
                            </td>                          
                            <td>
                                <input type="submit" value="Update"/> 
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </figure>            
        </div>
    </div>
</main>
<script>
    $(document).ready(() => {
        $("#tabs").tabs();
    });
</script>
    <c:import url="../includes/footer.jsp">
        <c:param name="page" value="secure/index.jsp"/>
    </c:import>