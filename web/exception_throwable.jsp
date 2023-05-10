<%-- 
    Document   : exception_throwable
    Created on : Mar 31, 2022, 7:41:50 AM
    Author     : Kirk Herbison
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:import url="includes/header.jsp">
    <c:param name="title" value="Exception"/>
    <c:param name="css" value="exception_throwable.css"/>
</c:import>
        <main>
            <h1>Java Exception</h1>
            <p>An exception was thrown by the application</p>
            <h3>Exception Type:</h3>
            <p>${pageContext.exception["class"]}</p>
            <h3>Exception Message:</h3>
            <p>${pageContext.exception.message}</p>
            <h3>Stack Trace:</h3>
            <c:forEach var="trace" items="${pageContext.exception.stackTrace}">
                <p>${trace}</p>
            </c:forEach>
        </main>
    <c:import url="includes/footer.jsp">
        <c:param name="page" value="exception_throwable.jsp"/>
    </c:import>
