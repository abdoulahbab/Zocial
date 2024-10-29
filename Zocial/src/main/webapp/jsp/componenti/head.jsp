  <%
	String baseUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String style = baseUrl + "/css/style.css";
	String script = baseUrl + "/js/script.js";
%>
<link rel="stylesheet" href="<%= style%>">
<script src="<%= script %>"></script> 
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:ital,opsz,wght@0,6..12,200..1000;1,6..12,200..1000&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Handlee&display=swap"
	rel="stylesheet">


