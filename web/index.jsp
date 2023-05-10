
<%-- 
    Document   : index
    Created on : Jan 20, 2022, 8:53:20 AM
    Author     : Kirk Herbison
    @author Kirk Herbison
    Kirk_BJ_A03_B_16981925
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:import url="includes/header.jsp">
    <c:param name="title" value="Home"/>
    <c:param name="css" value="home.css"/>
</c:import>
        <main>
            <a href="<c:url value='/controller?requestAction=vehicles'/>"><img src="images/rental_cars.jpg" alt="rental cars"></a>        
        </main>
    <c:import url="includes/footer.jsp">
        <c:param name="page" value="index.jsp"/>
    </c:import>