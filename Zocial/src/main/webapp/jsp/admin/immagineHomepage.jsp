<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="com.azienda.zocial.model.Image"%>
<%@page import="com.azienda.zocial.util.Costanti"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Impostazioni admin</title>
<jsp:include page="/jsp/componenti/head.jsp"></jsp:include>
</head>
<body>

<%
	String formAction = request.getContextPath() + "/addImageAdmin"; 
	String select = request.getContextPath() + "/AdminViewImage"; 
	String home = request.getContextPath() + "/setHomeImg"; 
%>
			<div class="modal-body">
				<form method="post" action=<%=formAction%>
					enctype="multipart/form-data">
					<label for="img">Inserisci immagine homepage pubblica</label> 
					<input type="file" id="img" name="image" accept="image/*" required> 
					<input type="submit" value="Carica immagine">
				</form>
			</div>
<%
	List<Image> immagini = (List<Image>) request.getAttribute(Costanti.CHIAVE_ADMIN_IMMAGINI); 
	if(immagini == null || immagini.size() == 0) {
%> 
	<p>Nessuna immagine caricata</p>
	<a href="<%= select%>">Ricarica pagina</a>
<%
} else {
	for(Image img : immagini) {
	%>
    <div class="adminImg" style="background-image: url('data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(img.getImmagine()) %>');">
    </div>
    <form action="<%= select%>">
    <input type="hidden" name="idImg" value="<%= img.getId()%>">
    <button type="submit">Seleziona come foto per la Homepage</button>
    </form>
<%
}}
%>



<%
Image img = (Image) request.getAttribute(Costanti.CHIAVE_ADMIN_Homepage);
%>
<div>
	<div class="adminImg" style="background-image: url('data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(img.getImmagine()) %>');">
    </div>
</div>
</body>
</html>