<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
String id = request.getParameter("userid");
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "something";
String database = "something";
String userid = "something";
String password = "";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>traccar stats & data</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600&display=swap" rel="stylesheet" />
    <link href="fontawesome/css/all.min.css" rel="stylesheet" />
    <link href="css/tooplate-chilling-cafe.css" rel="stylesheet" />
<!--
Tooplate 2118 Chilling Cafe
https://www.tooplate.com/view/2118-chilling-cafe
-->
  </head>
  <body>
    <div class="tm-container">
      <div class="tm-text-white tm-page-header-container">
        <i class='fas fa-user-cog' style='font-size:36px'></i>
        <h1 class="tm-page-header">Traccar GPS</h1>
      </div>
      <div class="tm-main-content">
        <div id="tm-intro-img"></div>
        <!-- System info -->
        <section class="tm-section">
          <h2 class="tm-section-header">System info</h2>
          <div class="tm-responsive-table">
            <table>
             <%
			 try{
			 connection = DriverManager.getConnection(connectionUrl+database, userid, password);
			 statement=connection.createStatement();
			 String sql ="select * from systemdatinfo";
			 resultSet = statement.executeQuery(sql);
			 while(resultSet.next()){
			 %>
              <tr>
                <td class="tm-text-left">Operating system's name</td>
                <td><%=resultSet.getString("Operating_system's_name") %></td>
                
              </tr>
              <tr>
                <td class="tm-text-left">Operating system's version</td>
                <td><%=resultSet.getString("Operating_system's_version") %></td>
                </tr>
              <tr>
                <td class="tm-text-left">Operating system's architecture</td>
                <td><%=resultSet.getString("Operating_system's_architecture") %></td>
                </tr>
              <tr>
                <td class="tm-text-left">Java runtime's name</td>
                <td><%=resultSet.getString("Java_runtime's_name") %></td>
                </tr>
              <tr>
                <td class="tm-text-left">Java runtime's vendor</td>
                <td><%=resultSet.getString("Java_runtime's_vendor") %></td>
                </tr>
              <tr>
                <td class="tm-text-left">Java runtime's version</td>
                <td><%=resultSet.getString("Java_runtime's_version") %></td>
                </tr>
              <tr>
                <td class="tm-text-left">Memory limit's heap</td>
                <td><%=resultSet.getString("Memory_limit's_heap") %></td>
                </tr>
			  <tr>
                <td class="tm-text-left">Memory limit's non heap</td>
                <td><%=resultSet.getString("Memory_limit's_non_heap") %></td>
                </tr>
			  <tr>
                <td class="tm-text-left">Character encoding</td>
                <td><%=resultSet.getString("Character_encoding") %></td>
                </tr>
				<%
				}
				connection.close();
				} catch (Exception e) {
				e.printStackTrace();
				}
				%>
            </table>
          </div>
        </section>

        <!-- User Menu -->
        <section class="tm-section">
          <h2 class="tm-section-header">General info about Users</h2>
          <div class="tm-responsive-table">
            <table>
			<%
			 try{
			 connection = DriverManager.getConnection(connectionUrl+database, userid, password);
			 statement=connection.createStatement();
			 String sql ="select * from usersStats";
			 resultSet = statement.executeQuery(sql);
			 while(resultSet.next()){
			 %>
              <tr>
                <td class="tm-text-left">Version</td>
                <td><%=resultSet.getString("version") %></td>
                </tr>
              <tr>
                <td class="tm-text-left">Capture Time</td>
                <td><%=resultSet.getString("captureTime") %></td>
                </tr>
              <tr>
                <td class="tm-text-left">Active Users</td>
                <td><%=resultSet.getString("activeUsers") %></td>
               </tr>
              <tr>
                <td class="tm-text-left">Active Devices</td>
                <td><%=resultSet.getString("activeDevices") %></td>
                </tr>
              <tr>
                <td class="tm-text-left">Requests</td>
                <td><%=resultSet.getString("requests") %></td>
                </tr>
              <tr>
                <td class="tm-text-left">Messages Received</td>
                <td><%=resultSet.getString("messagesReceived") %></td>
                </tr>
			  <tr>
                <td class="tm-text-left">Messages Stored</td>
                <td><%=resultSet.getString("messagesStored") %></td>
                </tr>
			  <tr>
                <td class="tm-text-left">Mail Sent</td>
                <td><%=resultSet.getString("mailSent") %></td>
               </tr>
			  <tr>
                <td class="tm-text-left">Sms Sent</td>
                <td><%=resultSet.getString("smsSent") %></td>
                </tr>
			  <tr>
                <td class="tm-text-left">Geocoder Requests</td>
                <td><%=resultSet.getString("geocoderRequests") %></td>
                </tr>
			  <tr>
                <td class="tm-text-left">Geolocation Requests</td>
                <td><%=resultSet.getString("geolocationRequests") %></td>
                </tr>
				<%
				}
				connection.close();
				} catch (Exception e) {
				e.printStackTrace();
				}
				%>
            </table>
          </div>
        </section>

        <hr />
        <!-- Visist us -->
        <section class="tm-section tm-section-small">
          <h2 class="tm-section-header">Wanna see more?</h2>
          <p class="tm-mb-0">
		    Reach us via
            <a href="https://www.traccar.org" class="tm-contact-link">traccar.org</a>
            .Thank you!!!
          </p>
         </section>
      </div>
     </div>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script>
      $(function() {
        // Adjust intro image height based on width.
        $(window).resize(function() {
          var img = $("#tm-intro-img");
          var imgWidth = img.width();

          // 640x425 ratio
          var imgHeight = (imgWidth * 425) / 640;

          if (imgHeight < 300) {
            imgHeight = 300;
          }

          img.css("min-height", imgHeight + "px");
        });
      });
    </script>
  </body>
</html>