package com.azienda.zocial.web;

import java.io.IOException;

import com.azienda.zocial.businesslogic.Service;
import com.azienda.zocial.util.Costanti;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/addImageAdmin")
@MultipartConfig
public class AdminImageServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			Part immagine =req.getPart("image");
				byte[] immagineCaricata = null;
			if (immagine != null && immagine.getSize() > 0) {
				immagineCaricata = immagine.getInputStream().readAllBytes(); // Leggi tutti i byte dell'immagine
				service.addImage(immagineCaricata);
			} else {
				req.setAttribute(Costanti.CHIAVE_ADMIN_ERRORE, "Nessuna immagine caricata");
			}
			req.getRequestDispatcher("/AdminViewImage").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute(Costanti.CHIAVE_ERRORE, "Errore imprevisto nel caricamento del post");
			req.getRequestDispatcher("/jsp/areaPubblica/errore.jsp").forward(req, resp);
		}
	}
}
