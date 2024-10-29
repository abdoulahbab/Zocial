<%@page import="com.azienda.zocial.model.Utente"%>
<%@page import="com.azienda.zocial.model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.azienda.zocial.util.Costanti"%>
<%@page import="com.azienda.zocial.model.Post"%>
<%@page import="java.util.List"%>
<%
Utente utente = (Utente) session.getAttribute(Costanti.CHIAVE_UTENTE_LOGGATO);
String baseUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
List<Post> posts = (List<Post>) request.getAttribute(Costanti.CHIAVE_POSTSbacheca);


String formActionComment = request.getContextPath() + "/comment";

if (posts != null && !posts.isEmpty()) {
	for (Post post : posts) {
%>

<div style="margin-top: 100px" class="post-card">

	<h3><%=post.getTitolo()%></h3>
	<%
	java.time.LocalDateTime localDateTime = post.getDataDiPubblicazione(); // Supponiamo che questo sia LocalDateTime

	// Converte LocalDateTime in Date
	java.util.Date dataDiPubblicazione = java.util.Date
			.from(localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());

	// Formattiamo la data e l'orario
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd MMMM yyyy HH:mm:ss", new java.util.Locale("it"));
	String formattedDateTime = sdf.format(dataDiPubblicazione);
	%>
	<p>
    	Posted by <%= post.getUtente().getUsername() %> on 
    	<%= formattedDateTime %> 
	</p>

	<p><%=post.getTesto()%></p>
	<img src="<%=baseUrl%>/post/image?id=<%=post.getId()%>"
		alt="Immagine del post">

	<div>
		<%
        // Controlla se l'utente ha già messo like o dislike
        boolean liked = post.getLikeUtente().contains(utente);
        boolean disliked = post.getDislikeUtente().contains(utente);
    %>	
		<p>
			Like:
			<%= post.getLikeUtente() %>
			| Unlike:
			<%= post.getDislikeUtente() %>
		</p>
			
		<!--   <a href="visualizzaLikUnlikers?postId=<%= post.getId() %>">Visualizza
			chi ha messo like/unlike</a> -->

		<!-- Form per Like -->
		<form method="POST"
			action="<%= request.getContextPath() + "/toggleLikeDislike" %>">
			<input type="hidden" name="postId" value="<%=post.getId()%>"> 
			<input type="hidden" name="action" value="like">

			<% if (!liked) { %>
			<button class="like-btn" type="submit"><i class="fas fa-heart"></i> Like</button>
			
			<% } else { %>

			<button class="like-btn" type="submit"><i class="fas fa-heart"></i>Remove Like</button>
			<% } %>
		</form>

		<!-- Form per Dislike -->
		<form method="POST"
			action="<%= request.getContextPath() + "/toggleLikeDislike" %>">
			<input type="hidden" name="postId" value="<%=post.getId()%>"> 
			<input type="hidden" name="action" value="Dislike">

			<% if (!disliked) { %>
			<button class="unlike-btn" type="submit">
				<i class="fas fa-thumbs-down"></i> Unlike
			</button>
			
			<% } else { %>

			<button class="unlike-btn" type="submit">
				<i class="fas fa-thumbs-down"></i>Remove Unlike
			</button>
			<% } %>
		</form>
		
	</div>

	<div>
		<button class="comment-btn">
			<i class="fas fa-comment"></i> Comment
		</button>


		<form method="GET" action="<%=formActionComment%>">
			<textarea name="contenuto" placeholder="Scrivi un commento..."
				required></textarea>

			<input type="hidden" name="postId" value="<%=post.getId()%>">
			<input type="submit" value="Aggiungi Commento">
		</form>

		<%
		List<Comment> comments = (List<Comment>) request.getAttribute(Costanti.CHIAVE_COMMENTS);

		if (comments != null && !comments.isEmpty()) {
			for (Comment comment : comments) {
		%>

		<div>
			<div>
				<h3><%=comment.getUtente().getUsername()%></h3>
			</div>
			<div>
				<p><%=comment.getTesto()%></p>
			</div>
		</div>
		<%
		}
		} else if (comments == null) {
		%>

		<p>Nessun commento disponibile nel database.</p>

		<%
		} else {
		%>


		<p>Nessun commento disponibile.</p>

		<%
		}
		%>
	</div>


	<%
	String modify = request.getContextPath() + "/modifyPost";
	String cancel = request.getContextPath() + "/modifyPost";
	%>


	<form method="post" action=<%=modify%>>
		<label for="text">Modifica il contenuto del tuo post</label> 
		<input type="text" id="text" name="text" required> 
		<input type="hidden" name="postId" value="<%=post.getId()%>"> 
		<input type="submit" value="Modifica">
	</form>

	<form method="post" action=<%=cancel%>>
		<input type="submit" value="Elimina post">
	</form>



</div>

<%
}
} else if (posts == null) {
%>

<p>Nessun post disponibile nel database.</p>

<%
} else {
%>


<p>Nessun post disponibile.</p>

<%
} 
%>
