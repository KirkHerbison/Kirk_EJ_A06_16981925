<%-- 
    Document   : index
    Created on : Jan 20, 2022, 8:53:20 AM
    Author     : Kirk Herbison
    @author Kirk Herbison
    Kirk_BJ_A06_B_16981925
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:import url="includes/header.jsp">
    <c:param name="title" value="Vehicles"/>
    <c:param name="css" value="vehicles.css"/>
</c:import>
        <main>
            <p class="error_message"><c:out value='${errorMessageVehicles}'/></p>
            <form action="controller" method="POST">
            <input type="hidden" name="requestAction" value="rental">   
            <div>
            <figure>
                <table class="sortable" id="vehicle_table">
                    <thead>
                        <tr> 
                            <th>Year</th>
                            <th>Make</th>
                            <th>Model</th>                     
                            <th>Capacity</th>
                            <th>Category</th>
                            <th>Cost Per Day</th>
                            <th>Select Vehicle</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="vehicle" items="${vehicleTypes}">
                            <tr>
                                <td>
                                    <span class="info"><c:out value='${vehicle.vehicleYear}'/></span>
                                </td>
                                <td>
                                    <span class="info"><c:out value='${vehicle.make}'/></span>
                                </td>
                                <td>
                                    <span class="info"><c:out value='${vehicle.model}'/></span>
                                </td>
                                <td>
                                    <span class="info"><c:out value='${vehicle.capacity}'/></span>
                                </td>
                                <td>
                                    <span class="info"><c:out value='${vehicle.vehicleType}'/></span>
                                </td>
                                <td>
                                    <span class="info"><c:out value='${vehicle.formatedCost}'/></span>
                                </td>
                                <td>
                                    <c:set var="radioValue" value = "${radioValue}"/>
                                    <c:set var="vehicleId" value = "${vehicle.ID}"/>
                                    <c:choose>
                                        <c:when test="${radioValue == vehicleId}">
                                            <input type="radio" name = "vehicle_id" value="<c:out value='${vehicle.ID}'/>" checked>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" name = "vehicle_id" value="<c:out value='${vehicle.ID}'/>">
                                        </c:otherwise>
                                    </c:choose>   
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </figure>
            </div>
            <div id="dates">
                <br>
                    <c:choose>
                        <c:when test="${dateStartStored != null}">
                                <c:set var = "start" value = "${dateStartStored}"/>
                                <c:set var = "end" value = "${dateEndStored}"/>
                        </c:when>
                        <c:otherwise>
                                <c:set var = "start" value = "${today}"/>       
                                <c:set var = "end" value = "${tomorrow}"/>   
                        </c:otherwise>
                    </c:choose> 
                <label>Start Date: </label>
                    <input type="date" name="startTime"
                        value="<c:out value='${start}'/>"
                        min="<c:out value='${today}'/>"
                        max="<c:out value='${todayMax}'/>">
                <br>
                <label>Return Date: </label>
                    <input type="date" name="endTime"
                        value="<c:out value='${end}'/>"
                        min="<c:out value='${tomorrow}'/>"
                        max="<c:out value='${tomorrowMax}'/>">
                <input type="submit" value="Select"/> 
            </div>
            </form>
        </main>
    <c:import url="includes/footer.jsp">
        <c:param name="page" value="vehicles.jsp"/>
    </c:import>
