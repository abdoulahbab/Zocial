<%@page import="com.azienda.zocial.model.Comment"%>
<%@page import="com.azienda.zocial.util.Costanti"%>
<%@page import="com.azienda.zocial.model.Post"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/jsp/componenti/head.jsp"></jsp:include>
</head>
<body>

	<%
	String formAction = request.getContextPath() + "/logout";	
	%>

	<div>
		<jsp:include page="/jsp/componenti/menu.jsp"></jsp:include>
	</div>

	
	<jsp:include page="/jsp/componenti/addPost.jsp"></jsp:include>
	
	

    <!-- Main Grid Layout -->
    <div class="main-grid">
        <!-- Sidebar -->
	<div>
		<jsp:include page="/jsp/componenti/sidebar.jsp"></jsp:include>
	</div>

		<!-- Post Feed -->
        <section class="post-feed">
            <!-- Search Bar -->
            <div class="search-bar">
                <input type="text" placeholder="Search for users...">
            </div>
		
			<div>
				<jsp:include page="/jsp/componenti/viewPosts.jsp"></jsp:include>
			</div>
			<div>
				<jsp:include page="/jsp/componenti/searchUser.jsp"></jsp:include>
			</div>
		</section>
    </div>
    
    
	<script>
        document.getElementById("myBtn").addEventListener("click", () => {
            document.getElementById("myModal_post").style.display = "block";
        });
        document.getElementById("close_post").addEventListener("click", () => {
            document.getElementById("myModal_post").style.display = "none";
        });
        document.getElementById("mySearch").addEventListener("click", () => {
            document.getElementById("myModal_search").style.display = "block";
        });
        document.getElementById("close_search").addEventListener("click", () => {
            document.getElementById("myModal_search").style.display = "none";
        });
    </script>

</body>
</html>