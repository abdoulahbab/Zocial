
<%
String formAction = request.getContextPath() + "/modifyPost"; 
%>


<form method="post" action=<%=formAction%>>
	<label for="text">Modifica il contenuto del tuo post</label> <input
		type="text" id="text" name="text" required> <input
		type="hidden" name="postId" value="<%=post.getId()%>"> <input
		type="submit" value="Modifica">
</form>

<form method="post" action=<%=formAction%>>


	<input type="submit" value="Elimina post">
</form>


