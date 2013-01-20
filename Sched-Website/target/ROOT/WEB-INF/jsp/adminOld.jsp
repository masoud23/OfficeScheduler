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
    <div class="tel">Tel: 801-555-5555</div>
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
          <li><a href="index.html">Homepage</a></li>
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
          
       <h1>Create a Calendar!</h1>
        <form:form commandName="createCal">
      <table>
          <tr>
              <td>Start Hour (Int):</td>
              <td><form:input path="startHour" /></td>
          </tr> 
           <tr>
              <td>Start Min (Int):</td>
              <td><form:input path="startHour" /></td>
          </tr>
          <tr>
              <td>End Hour (Int):</td>
              <td><form:input path="endHour" /></td>
          </tr>   
          <tr>
              <td>End Min (Int):</td>
              <td><form:input path="endMin" /></td>
          </tr>    
          <tr>
              <td>App Length:</td>
              <td><form:input path="appLength" /></td>
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