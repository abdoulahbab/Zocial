<%@page import="com.azienda.zocial.util.Costanti"%>
<%@page import="java.util.List"%>
<%@page import="com.azienda.zocial.model.Utente"%>
<%@page import="com.azienda.zocial.model.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  
    <%
      
        Post post = (Post) request.getAttribute(Costanti.CHIAVE_POST); // ID del post
        Utente utente= (Utente) request.getAttribute(Costanti.CHIAVE_UTENTE_LOGGATO); // Utente loggato

      
        if (post != null && utente != null) {
    %>
        <p>Stai per rimuovere il like dal post:</p>
        <h2><%= post.getTesto()%></h2>
        <p>Pubblicato il: <%= post.getDataDiPubblicazione() %></p>

        <!-- Form per Rimuovere Like -->
        <form action="<%= request.getContextPath() + "/removeLike" %>" method="post">
            <input type="hidden" name="postId" value="<%= post.getId() %>">
            <input type="hidden" name="userId" value="<%= utente.getId() %>">
            <button type="submit">Rimuovi Like</button>
        </form>

    <%
        } else {
    %>
        <p>Post o utente non trovato.</p>
    <%
        }
    %>
</body>
</html>