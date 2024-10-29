<%@page import="com.azienda.zocial.model.Utente"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Risultati della Ricerca Utenti</title>
</head>
<body>
<h1>Risultati della Ricerca Utenti</h1>

<% List<Utente> utenti = (List<Utente>) request.getAttribute("username"); %>

<% if (utenti != null && !utenti.isEmpty()) { %>
    <ul>
        <% for (Utente utente : utenti) { %>
            <li><%= utente.getUsername() %></li>
        <% } %>
    </ul>
<% } else { %>
    <p>Nessun utente trovato con questo username.</p>
<% } %>
</body>
</html>