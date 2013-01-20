
<%-- 
    Document   : calendar
    Created on : Aug 3, 2012, 4:56:23 PM
    Author     : Mike
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='css/fullcalendar.css' />
        <script type='text/javascript' src="js/jquery-1.7.1.min.js"></script>
        <script type='text/javascript' src='js/jquery-ui-1.8.17.custom.min.js'></script>
    <!--    <script type='text/javascript' src='js/jqueryCalendar.js'></script>  -->
        <script type='text/javascript' src='js/fullcalendar.min.js'></script>
        <title>Appointment Calendar</title>
        <style type='text/css'>

          	body {
          		margin-top: 40px;
          		text-align: center;
          		font-size: 14px;
          		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
          		}
          
          	#calendar {
          		width: 900px;
          		margin: 0 auto;
          		}
	</style>
        <script>
            $(document).ready(function() {
	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		$('#calendar').fullCalendar({
                    
                    
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'agendaWeek,agendaDay'
                                
                                
			},
			editable: false,
                        weekends: true,
			events: {
                                  url: '${url}',
                                  type: 'GET',
                                  allDay: false
        
                        }
		});
		
	});
        </script> 
    </head>
    <body>
        
        <h1>Open Appointments Calendar</h1>
        <p>Please click on an open appointment to select it.</p>
        <div id="calendar"></div>
    </body>
</html>
