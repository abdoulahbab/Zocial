<%@page import="com.azienda.zocial.util.Costanti"%>
<%
		String formAction = request.getContextPath() + "/acc";
		String errorUsername = (String) request.getAttribute(Costanti.CHIAVE_ERRORE_USERNAME);
		String errorMessageU = errorUsername != null ? errorUsername : "";
		
		String errorPassword = (String) request.getAttribute(Costanti.CHIAVE_ERRORE_PASSWORD);
		String errorMessageP = errorPassword != null ? errorPassword : "";
%>

<form method="post" action =<%=formAction%>>
    <input type="text" id="username" placeholder="Username" name="username" required >
    <input type="password" id="password" placeholder="Password" name="password" required >
	<div style="color: red"><%=errorMessageU%></div>
	<button type="submit">Accedi</button>
    <button type="button" onclick="showRegistrationForm()">Registrati</button>
	
</form>
