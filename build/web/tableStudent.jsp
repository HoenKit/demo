<%-- 
    Document   : tableStudent
    Created on : Oct 24, 2023, 1:24:32 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h1>Student Information:</h1>
         <input type="submit" value="Add Student" onclick="window.location.href='addStudent-form.jsp';return
false;"/>
         <form action="StudentController" method="GET">
                    <input class="SearchBox" name="searchKeyword" type="text" size="15" required/>
                    <input class="add-student-button" name="command" type="submit" value="SEARCH"/>
                </form>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${studentList}" var="student">
            <c:url var="tempLink" value="StudentController">
                <c:param name="command" value="LOAD"></c:param>
                <c:param name="studentID" value="${student.id}"></c:param>
            </c:url>
            <c:url var="deleteLink" value="StudentController">
                <c:param name="command" value="DELETE"></c:param>
                <c:param name="studentID" value="${student.id}"></c:param>
            </c:url>
            <tr>
                <td>${student.id}</td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td>${student.email}</td>
                <td><a href="${tempLink}">Update</a>
                    <a href="${deleteLink}" onclick="if(!(confirm('Sure?'))) return false">Delete</a> </td>
            </tr>
        </c:forEach>
    </table>
    </body>
</html>
