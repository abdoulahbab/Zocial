package com.azienda.zocial.web;
import com.azienda.zocial.businesslogic.Service;

import com.azienda.zocial.model.Utente;
import com.azienda.zocial.util.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
		Utente utente = (Utente) request.getSession().getAttribute(Costanti.CHIAVE_UTENTE_LOGGATO);
		String postId = request.getParameter("postId");
		String contenuto = request.getParameter("contenuto");
		
		Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
		service.addComment(contenuto, utente, postId);
		

		request.getRequestDispatcher("/bacheca").forward(request, response);
		
			
		
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/jsp/areaPubblica/errore.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}