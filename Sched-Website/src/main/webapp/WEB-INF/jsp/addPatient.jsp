<%-- 
    Document   : addPatient
    Created on : Oct 10, 2012, 8:22:23 PM
    Author     : Masoud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Schedule It</title>
    <meta charset="UTF-8" />
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/structure.css">
</head>
<body>
    <div id="head">
    <header id="main"> 
        <h1> Preventative Services Scheduler</h1>
    </header>
    </div>

         <div>
             <fieldset class="boxBody">
         <form:form method="POST" commandName="patient" action="" class="box">
             <form:errors path="*" id="errors"/>   
   <table id="chad">
    <caption>Please Register Here</caption>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td style="vertical-align: middle" align="center" width="100px">User Name :</td>
        <td><form:input path="username" /></td>
        <td width="30px">&nbsp;</td>
    </tr>
    <tr>
        <td style="vertical-align: middle" align="center" width="100px">Password :</td>
        <td><form:password path="password" /></td>
    </tr>

    <tr>
        <td style="vertical-align: middle" align="center" width="100px">First Name :</td>
        <td><form:input path="first" /></td>
        <td><form:errors path="first" cssClass="error" /></td>
    </tr>
    <tr>
        <td style="vertical-align: middle" align="center" width="100px">Middle Name :</td>
        <td><form:input path="middle" /></td>
    </tr>
    
    <tr>
        <td style="vertical-align: middle" align="center" width="100px">Last Name :</td>
        <td><form:input path="last" /></td>
        <td><form:errors path="last" cssClass="error" /></td>
    </tr>
    
    <tr>
        <td style="vertical-align: middle" align="center" width="100px">Birth Date :</td>
        <td><form:input path="birthDate" /></td>
        <td><form:errors path="birthDate" cssClass="error" /></td>
    </tr>  
    
    <tr>
        <td style="vertical-align: middle" align="center" width="100px">Address :</td>
        <td><form:input path="address" /></td>
        <td><form:errors path="address" cssClass="error" /></td>
    </tr>
    
    <tr>
        <td style="vertical-align: middle" align="center" width="100px">City :</td>
        <td><form:input path="city" /></td>
        <td><form:errors path="city" cssClass="error" /></td>
    </tr>    
    
    <tr>
        <td style="vertical-align: middle" align="center" width="100px">State :</td>
        <td><form:input path="state" /></td>
    </tr>    
    
    <tr>
        <td style="vertical-align: middle" align="center" width="100px">Zip :</td>
        <td><form:input path="zip" /></td>
    </tr>    
    <tr>
        <td style="vertical-align: middle" align="center" width="100px">Email :</td>
        <td><form:input path="email" /></td>
        <td><form:errors path="email" cssClass="error" /></td>
    </tr>  
    <tr>
        <td style="vertical-align: middle" align="center" width="100px">Phone :</td>
        <td><form:input path="phone" /></td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td align="center"><input type="submit" value="Register">&nbsp;<input type="reset" /></td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
</table>
</form:form>
    </div>
    </body>
</html>