
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
    <c:param name="title" value="Employee"/>
    <c:param name="css" value="home.css"/>
    
</c:import>
        <main>
            <h1>Employee Management Page</h1>       
        </main>
    <c:import url="../includes/footer.jsp">
        <c:param name="page" value="secure/employee.jsp"/>
    </c:import>