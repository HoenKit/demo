<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css151/style.css"/>
        <title>Search Results</title>
    </head>
    <body>
        <h1>Search Results</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="result" items="${searchResults}">
                    <tr>
                        <td>${result.firstName}</td>
                        <td>${result.lastName}</td>
                        <td>${result.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p>
            <a href="StudentController"> Back to the List</a>
        </p>
    </body>
</html>
