package com.azienda.zocial.web;

import java.io.IOException;
import java.time.ZonedDateTime;

import com.azienda.zocial.businesslogic.Service;
import com.azienda.zocial.model.Post;
import com.azienda.zocial.model.Utente;
import com.azienda.zocial.util.Costanti;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/addpost")
@MultipartConfig
public class AddPostServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			String titolo = req.getParameter("title");
			String testo = req.getParameter("text");
			String visibilita = req.getParameter("visibilita");
			ZonedDateTime data = ZonedDateTime.now();
			Part immagine =req.getPart("imagePost");
			Utente utente = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_UTENTE_LOGGATO);

			byte[] immagineCaricata = null;
			if (immagine != null && immagine.getSize() > 0) {
				immagineCaricata = immagine.getInputStream().readAllBytes(); // Leggi tutti i byte dell'immagine
			} else {
				System.out.println("Nessuna immagine caricata.");
			}

			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			service.addPost(new Post(titolo, testo, Boolean.parseBoolean(visibilita), data.toLocalDateTime(), immagineCaricata), utente);

			req.getRequestDispatcher("/bacheca").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute(Costanti.CHIAVE_ERRORE, "Errore imprevisto nel caricamento del post");
			req.getRequestDispatcher("/jsp/areaPubblica/errore.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
