package com.azienda.zocial.web;

import java.io.IOException;
import java.util.List;

import com.azienda.zocial.businesslogic.Service;
import com.azienda.zocial.model.Image;
import com.azienda.zocial.util.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/setHomeImg")
public class HomeSetImgServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			Image img = service.setHomepageImage((String) request.getAttribute("idImg"));
			if( img != null && img.getImmagine().length != 0) {
			request.setAttribute(Costanti.CHIAVE_ADMIN_Homepage, img);
			} 
			request.getRequestDispatcher("/jsp/admin/immagineHomepage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Costanti.CHIAVE_ERRORE, "Errore imprevisto nel caricamento delle immagini");
			request.getRequestDispatcher("/jsp/admin/immagineHomepage.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
