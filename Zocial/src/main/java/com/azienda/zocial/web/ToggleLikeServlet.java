package com.azienda.zocial.web;

import java.io.IOException;

import com.azienda.zocial.businesslogic.Service;
import com.azienda.zocial.model.Utente;
import com.azienda.zocial.util.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/toggleLikeDislike")
public class ToggleLikeServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		int postId = Integer.parseInt(request.getParameter("postId"));
    		String action = request.getParameter("action"); // "like" o "dislike"
    		Utente utente = (Utente) request.getSession().getAttribute(Costanti.CHIAVE_UTENTE_LOGGATO);
    		
    		Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
    		service.likeDislike(utente, postId, action);    		
    		request.getRequestDispatcher("/comment").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/jsp/areaPubblica/errore.jsp").forward(request, response);
		}
    }
}