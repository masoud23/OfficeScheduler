<%-- 
    Document   : confirmAppointment
    Created on : Aug 6, 2012, 10:44:00 PM
    Author     : Mike
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='css/structure.css' />
        <title>Appointment Confirmation</title>
    </head>
    <body>
        <h1>Confirm Your Appointment</h1>
        <div>
            <h3>Are you sure you want to confirm an appointment on 
                <fmt:formatDate pattern="EEEE MM/dd/yyyy" value="${slot.dateTime}" /> at 
                <fmt:formatDate pattern="hh:mm a" value="${slot.dateTime}" />
            </h3>
                <form action="confirmApp.html" method="post">
                        <button type="submit" name="confirm" value="yes">Yes</button>
                        <button type="submit" name="confirm" value="no">No</button>
                </form>
        </div>
    </body>
</html>
