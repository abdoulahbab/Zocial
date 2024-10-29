package com.azienda.zocial.web;


import java.io.IOException;
import java.util.List;

import com.azienda.zocial.businesslogic.Service;

import com.azienda.zocial.model.Utente;
import com.azienda.zocial.util.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/serachUser")
public class SearchUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String username=req.getParameter("username");
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			List<Utente> utenti = service.findByUsername(username);
			
			if(utenti!=null) {
				req.setAttribute("user", utenti);
			}else {
				req.setAttribute("message", "Utente non trovato");
			}
			req.getRequestDispatcher("/jsp/componenti/userResult");
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/areaPubblica/errore.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
