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
    <div class="header">
        <header id="main"> 
            <h1> Preventative Services Scheduler </h1>
        </header>
    </div>
    <div class="main">
        <div id="message" class="error">${message}</div>
        <form:form method="POST" commandName="user" action="login.html" class="box login">
            <fieldset class="boxBody">
                <!--<label>Username</label>-->
                <form:input path="username" tabindex="1" placeholder = "Username"/>
                <form:errors path="username" cssClass="error"/>
                <form:password path="password" tabindex="2" placeholder = "Password"/> 
                <form:errors path="password" cssClass="error"/>
            </fieldset>
            <footer>
                <label><input type="checkbox" tabindex="3"/>Keep me logged in</label>
                <input type="submit" class="btnLogin" value="Login" tabindex="4" />

            </footer>
            <a href="#" class="rLink" tabindex="5">Forget your password?</a><br />
            <a href="patient.html" class="rLink" tabindex="6">Register Here</a>
       </form:form>
    </div>
    <footer id="main">
        <!--<a href="http://wwww.cssjunction.com">Massouds Tigers</a> | <a href="http://www.premiumpixels.com">PSD by Premium Pixels</a>-->
        <h1> Massoud's Tigers </h1>
    </footer>
</body>
</html>
