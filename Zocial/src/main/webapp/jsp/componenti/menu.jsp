<%@page import="com.azienda.zocial.util.Costanti"%>
<%@page import="com.azienda.zocial.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String baseUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String searchUrl = baseUrl + "/jsp/areaPubblica/accedi.jsp";
	String primoEsempioUrl = baseUrl + "/bacheca";
	String admin = baseUrl + "/AdminViewImage";

	String registrati = baseUrl + "/jsp/areaPubblica/registrazione.jsp";
	
	Utente utente = (Utente) session.getAttribute(Costanti.CHIAVE_UTENTE_LOGGATO);
%>



<nav class="navbar"> 
        <div class="brand">Zocial</div>
        
          <%
  if(utente == null) { 
  %>
        <div class="nav-items">
            <jsp:include page="/jsp/componenti/accedi.jsp"></jsp:include>
          <!--   <a href="<%=registrati%>" class="button">registrati</a> --> 
        </div>
    <% } else { %>
    	<a href="<%= admin%>">Admin</a>
    <% } %>
</nav>