<%@page import="com.azienda.zocial.util.Costanti"%>
<%
		String formAction = request.getContextPath() + "/logout";
		String errorUsername = (String) request.getAttribute(Costanti.CHIAVE_ERRORE_USERNAME);
		String errorMessageU = errorUsername != null ? errorUsername : "";
		
		String errorPassword = (String) request.getAttribute(Costanti.CHIAVE_ERRORE_PASSWORD);
		String errorMessageP = errorPassword != null ? errorPassword : "";
%>

<aside class="sidebar">
	<button class="sidebar-btn" id="myBtn">
		<i class="fas fa-pencil-alt"></i> Create Post
	</button>
	<button class="sidebar-btn" id="mySearch">
		<i class="fas "></i> Search
	</button>
	<button class="sidebar-btn">
		<i class="fas fa-chart-bar"></i> View Stats
	</button>
	<form method="get" action =<%=formAction%>>
	<button type="submit" class="sidebar-btn">
		<i class="fa-solid fa-power-off"></i>Logout
	</button>
	</form>
</aside>