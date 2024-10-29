<%
String formAction = request.getContextPath() + "/addpost"; 
%>
 <div  id="myModal_post" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close " id="close_post">&times;</span>
                <h2>Create post</h2>
            </div>
            <div class="modal-body">
                <form method="post" action=<%=formAction%> enctype="multipart/form-data">
                    <label for="titlePost">Titolo</label>
                    <input type="text" id="titlePost" name="title" required>
                    <label for="text">Scrivi il tuo post</label>
                    <input type="text" id="text" name="text" required>
                    <label for="img">Inserisci immagine</label>
                    <input type="file" id="img" name="imagePost" accept="image/*" required>
                    <input type="submit" value="Pubblica">
                </form>
            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div>