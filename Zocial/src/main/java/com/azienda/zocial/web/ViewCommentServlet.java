package com.azienda.zocial.web;


import java.io.IOException;
import java.util.List;

import com.azienda.zocial.businesslogic.Service;
import com.azienda.zocial.model.Comment;
import com.azienda.zocial.model.Post;
import com.azienda.zocial.util.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/commenti")
public class ViewCommentServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Service service = (Service) getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);

		try {
			List<Post> posts = (List<Post>) req.getAttribute(Costanti.CHIAVE_POSTSbacheca);
			List<Comment> comments = service.retrieveComments();

			req.setAttribute(Costanti.CHIAVE_POSTSbacheca, posts);			
			req.setAttribute(Costanti.CHIAVE_COMMENTS, comments);
			req.getRequestDispatcher("/jsp/areaPrivata/privata.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/areaPubblica/errore.jsp").forward(req, resp);
		}
	}
}
