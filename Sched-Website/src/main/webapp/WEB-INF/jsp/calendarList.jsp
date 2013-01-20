<%-- 
    Document   : calendarList
    Created on : Aug 5, 2012, 6:45:33 PM
    Author     : Mike
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel='stylesheet' type='text/css' href='css/structure.css' />
        <title>Appointment Calendar</title>
    </head>
    <body>
        <h1>Select an Available Appointment</h1>
        <c:forEach var='day' items='${days}' >
        <table>
            <tr>
                <th><fmt:formatDate pattern="MM-dd-yyyy " value="${day.date}"/></th>
            </tr>
            <c:forEach var='slot' items='$day.appointments}' >
            <tr>
                <td id="avail">id</td>
            </tr>
      
            </c:forEach>
        </table>
        </c:forEach>
    </body>
</html>
