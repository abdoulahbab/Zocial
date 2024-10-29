package com.azienda.zocial.web;

import java.io.IOException;

import com.azienda.zocial.util.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/logout")
public class Logout extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.getSession().removeAttribute(Costanti.CHIAVE_UTENTE_LOGGATO);
		    req.getRequestDispatcher("/jsp/areaPubblica/accedi.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/areaPubblica/accedi.jsp").forward(req, resp);

		}
	}
	
}
