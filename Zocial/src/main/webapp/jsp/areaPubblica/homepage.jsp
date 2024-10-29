<%@page import="com.azienda.zocial.util.Costanti"%>
<%@page import="org.apache.tomcat.jakartaee.bcel.classfile.ConstantInteger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Zocial</title>
<jsp:include page="/jsp/componenti/head.jsp"></jsp:include>
</head>
<body>
<%
		String formAction = request.getContextPath() + "/acc";
		String errorUsername = (String) request.getAttribute(Costanti.CHIAVE_ERRORE_USERNAME);
		String errorMessageU = errorUsername != null ? errorUsername : "";
		
		String errorPassword = (String) request.getAttribute(Costanti.CHIAVE_ERRORE_PASSWORD);
		String errorMessageP = errorPassword != null ? errorPassword : "";
%>
	  <!-- Navbar -->
      <jsp:include page="/jsp/componenti/menu.jsp"></jsp:include>         
 
    <!-- Main Section -->
    <div class="main-section">
    <!-- Registration Success Message -->
        <aside class="aside-sidebar" id="login-form" style="display:block;">
            <h2>Bentornato!</h2>
            <p style="margin-bottom:1rem;">Accedi con username e password:</p>
            <!-- Login Form -->
            <form method="post" action="<%= formAction %>">
                <input type="text" name="username" placeholder="Username" required>
                <input type="password" name="password" placeholder="Password" required>
                <button type="submit">Accedi</button>
                <button type="submit" onclick="showPasswordRecoveryForm()">Recupera password</button>
            </form>
        </aside>
        <!-- Registration Form -->
        
        <aside class="aside-sidebar" id="registration-form" style="display:none;">
            <h2>Registrazione</h2>
            <jsp:include page="/jsp/componenti/registrazione.jsp"></jsp:include>     
        </aside>
        <!-- Password Recovery Form (Initially Hidden) -->
        <aside class="aside-sidebar" id="password-recovery-form" style="display:none;">
            <h2>Recupera password</h2>
            <jsp:include page="/jsp/componenti/recuperaPassword.jsp"></jsp:include>     
        </aside>
        <!-- Card Section -->
        <section class="card-section">
            <div class="card"></div>
            <jsp:include page="/jsp/componenti/footer.html"></jsp:include> 
        </section>
    </div>
    <script>// Show registration form on "Sign In" click
    function showRegistrationForm() {
        document.getElementById('password-recovery-form').style.display = 'none';
        document.getElementById('registration-form').style.display = 'block';
        document.getElementById('login-form').style.display = 'none';}
     // Handle login submission
       
        function showPasswordRecoveryForm() {
            document.getElementById('password-recovery-form').style.display = 'block';
            document.getElementById('registration-form').style.display = 'none';
            document.getElementById('login-form').style.display = 'none';
    }</script>
    
</body>
</html>