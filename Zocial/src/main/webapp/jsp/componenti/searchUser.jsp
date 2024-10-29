 <%@page import="com.azienda.zocial.model.Utente"%>
<%@page import="java.util.List"%>
<div id="myModal_search" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close " id="close_search">&times;</span>
                <h2>Cerca</h2>
            </div>
            <div class="modal-body">
                <form action="searchUser" method="GET">
                    <input type="text" name="username">
                    <button type="submit">Cerca</button>
                </form>
                <h1>Risultati della Ricerca Utenti</h1>
                <% List<Utente> utenti = (List<Utente>) request.getAttribute("username"); %>
                    <% if (utenti !=null && !utenti.isEmpty()) { %>
                         <ul>
                             <% for (Utente utente : utenti) { %>
                                 <li>
                                    <%= utente.getUsername() %>
                                 </li>
                                <% } %>
                            </ul>
                            <% } else { %>
                                <p>Nessun utente trovato con questo username.

                                </p> <% } %>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>