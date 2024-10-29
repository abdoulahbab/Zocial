<%@page import="com.azienda.zocial.util.Costanti"%>
<%
		String formAction = request.getContextPath() + "/regi";
		String errorUsername = (String) request.getAttribute(Costanti.CHIAVE_ERRORE_USERNAME);
		String errorMessageU = errorUsername != null ? errorUsername : "";
		
		String errorPassword = (String) request.getAttribute(Costanti.CHIAVE_ERRORE_PASSWORD);
		String errorMessageP = errorPassword != null ? errorPassword : "";
		
		String errorEmail = (String) request.getAttribute(Costanti.CHIAVE_ERRORE_EMAIL);
		String errorMessageE = errorEmail != null ? errorEmail : "";
		
		String errorData = (String) request.getAttribute(Costanti.CHIAVE_ERRORE_DATA);
		String errorMessageD = errorData != null ? errorData : "";	
		
		String errorUsernamePresente = (String) request.getAttribute(Costanti.CHIAVE_ERRORE_UTENTE_PRESENTE);
		String errorMessagePr = errorUsernamePresente != null ? errorUsernamePresente : "";
	%> 


<form method="post" action =<%=formAction%>>
    <label for ="username">username</label>
    <input type="text" id="un" name="username" required >
	<div style="color: red"><%=errorMessageU%></div>
	
	<div style="color: red"><%=errorMessagePr%></div>
	
    <label for ="password">password</label>
    <input type="password" id="pw" name="password" required >
	<div style="color: red"><%=errorMessageP%></div>
	
    <label for="email">email</label>
    <input type="email" id="email" name="email" required >
	<div style="color: red"><%=errorMessageE%></div>
	
    <label for="datadinasita">datadinasita</label>
    <input type="date" id="datadinasita" name="datadinasita" required>
    <div style="color: red"><%=errorMessageD%></div>
    
    <label for="genere">genere</label>
    <select id="genere" name="genere" required>
        <option value=""disabled selected>Seleziona il genere</option>
        <option value="maschio">Uomo</option>
        <option value="femmina">Donna</option>
        <option value="altro">Altro</option>
    </select>
    
  <input  type="submit" value="Registrati" class="submit-button">
   </form>