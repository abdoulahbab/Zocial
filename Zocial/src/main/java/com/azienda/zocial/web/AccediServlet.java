package com.azienda.zocial.web;

import java.io.IOException;

import com.azienda.zocial.businesslogic.Service;
import com.azienda.zocial.exception.UtenteGiaPresente;
import com.azienda.zocial.model.Utente;
import com.azienda.zocial.util.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/acc")
public class AccediServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			Utente utente = service.validateUtente(username, password);
			
			req.getSession().setAttribute(Costanti.CHIAVE_UTENTE_LOGGATO, utente);
			req.getRequestDispatcher("/jsp/areaPrivata/privata.jsp").forward(req, resp);

		
		} catch (UtenteGiaPresente e) {
			e.printStackTrace();
			req.setAttribute(Costanti.CHIAVE_ERRORE_USERNAME, "credenziali errate");
			req.getRequestDispatcher("/jsp/areaPubblica/accedi.jsp").forward(req, resp);
		
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
