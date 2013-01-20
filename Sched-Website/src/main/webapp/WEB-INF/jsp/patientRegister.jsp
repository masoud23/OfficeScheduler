<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Business Template</title>
<link type="text/css" rel="stylesheet" href="css/structure.css"/>
</head>
<body>
<div class="wrapper">
  <div class="upperhead">
    <div class="tel">Tel: 01875  340 573</div>
  </div>
  <div class="banner"><a href="http://www.free-css.com/"><img src="images/logo_10.jpg" alt="free css template" width="279" height="73" border="0" /></a></div>
  <div class="underbanner">
    <label>
    <input name="textfield" type="text" class="input" value="Search" />
    </label>
    <label>
    <input type="submit" name="Submit" class="button"  value="GO" />
    </label>
  </div>
  <div class="content">
    <div class="leftcolumn">
      <div id="navvy">
        <ul id="navvylist">
          <li><a href="http://www.free-css.com/">Homepage</a></li>
          <li><a href="http://www.free-css.com/">Services</a></li>
          <li><a href="http://www.free-css.com/">About</a></li>
          <li><a href="http://www.free-css.com/">Contact</a></li>
          <li><a href="http://www.free-css.com/">News Updates </a></li>
        </ul>
      </div>
      <div class="box">Welcome to the Scheduler. This website will allow patients to schedule their exams online.  </div>
    </div>
    <div class="rightcolumn">
      <div class="article">
          
          <h3>Please create an account</h3>
<form:form commandName="patient">
      <table>
          <tr>
              <td>Username:</td>
              <td><form:input path="username" /></td>
          </tr> 
          <tr>
              <td>Password:</td>
              <td><form:password path="password" /></td>
          </tr>          
          <tr>
              <td>First Name:</td>
              <td><form:input path="first" /></td>
          </tr>
          <tr>
              <td>Middle Name:</td>
              <td><form:input path="middle" /></td>
          </tr>          
          <tr>
              <td>Last Name:</td>
              <td><form:input path="last" /></td>
          </tr>
          <tr>
              <td>Birth Date:</td>
              <td><form:input path="birthDate" /></td>
          </tr>          
          <tr>
              <td>Address:</td>
              <td><form:input path="address" /></td>
          </tr>   
          <tr>
              <td>City:</td>
              <td><form:input path="city" /></td>
          </tr>    
          <tr>
              <td>State:</td>
              <td><form:input path="state" /></td>
          </tr>
          <tr>
              <td>Zip:</td>
              <td><form:input path="zip" /></td>
          </tr>          
          <tr>
              <td>Phone:</td>
              <td><form:input path="phone" /></td>
          </tr>          
          <tr>
              <td>Email:</td>
              <td><form:input path="email" /></td>
          </tr>          
          <tr>
              <td colspan="2">
                  <input type="submit" value="Save Changes" />
              </td>
          </tr>
      </table>
  </form:form>
      </div>
    </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="footer">Template By <a href="http://www.freecss.info">FreeCSS.info </a></div>
</div>
<div style="font-size: 0.8em; text-align: center; margin-top: 1.0em; margin-bottom: 1.0em;"> Design downloaded from <a href="http://www.freewebtemplates.com/">Free Templates</a> - your source for free web templates </div>
</body>
</html>