<%-- 
    Document   : updateStudent-form
    Created on : Oct 27, 2023, 1:40:09 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <form action="StudentController" method="GET">
        <input type="hidden" name="command" value="UPDATE" />
        <input type="hidden" name="studentID" value="${THE_STUDENT.id}"/>
        <table border="0">
        <tbody>
        <tr>
        <td><label>First Name: </label></td>
        <td> <input type="text" name="firstName" value="${THE_STUDENT.firstName}" /></td>
        </tr>
        <tr>
        <td><label>Last Name: </label></td>
        <td><input type="text" name="lastName" value="${THE_STUDENT.lastName}" /></td>
        </tr>
        <tr>
        <td><label>Email: </label> </td>
        <td><input type="text" name="email" value="${THE_STUDENT.email}" /></td>
        </tr>
        <tr> 
        <td><input type="submit" value="Save" class="save" /></td>
        </tr>
        </tbody>
        </table> 
        </form>

        <p>
        <a href="StudentController"> Back to the List</a>
        </p>
    </body>
</html>
