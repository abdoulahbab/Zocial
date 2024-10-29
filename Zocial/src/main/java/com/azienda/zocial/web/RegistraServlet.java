package com.azienda.zocial.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.azienda.zocial.businesslogic.Service;
import com.azienda.zocial.exception.DataException;
import com.azienda.zocial.exception.EmailException;
import com.azienda.zocial.exception.PasswordException;
import com.azienda.zocial.exception.UserException;
import com.azienda.zocial.exception.UtenteGiaPresente;
import com.azienda.zocial.util.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regi")
public class RegistraServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			String email=req.getParameter("email");
			String dataDiNascita=req.getParameter("datadinasita");
			LocalDate dataNascita=LocalDate.parse(dataDiNascita, DateTimeFormatter.ISO_DATE);
			String genere=req.getParameter("genere");
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			service.insertUtente(username, password, email, dataNascita, genere);
			req.setAttribute("registrationSuccess", "true");
			req.getRequestDispatcher("/jsp/areaPubblica/accedi.jsp").forward(req, resp);

		} catch (DataException e) {
			e.printStackTrace();
			req.setAttribute(Costanti.CHIAVE_ERRORE_DATA, "campo non valido");
			req.getRequestDispatcher("/jsp/areaPubblica/accedi.jsp").forward(req, resp);
		} catch (EmailException e) {
			e.printStackTrace();
			req.setAttribute(Costanti.CHIAVE_ERRORE_EMAIL, "campo non valido");
			req.getRequestDispatcher("/jsp/areaPubblica/accedi.jsp").forward(req, resp);
		} catch (PasswordException e) {
			e.printStackTrace();
			req.setAttribute(Costanti.CHIAVE_ERRORE_PASSWORD, "campo non valido");
			req.getRequestDispatcher("/jsp/areaPubblica/accedi.jsp").forward(req, resp);	

		} catch (UserException e) {
			e.printStackTrace();
			req.setAttribute(Costanti.CHIAVE_ERRORE_USERNAME, "campo non valido");
			req.getRequestDispatcher("/jsp/areaPubblica/accedi.jsp").forward(req, resp);
			
		} catch (UtenteGiaPresente e) {
			e.printStackTrace();
			req.setAttribute(Costanti.CHIAVE_ERRORE_UTENTE_PRESENTE, "username già presente ");
			req.getRequestDispatcher("/jsp/areaPubblica/accedi.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute(Costanti.CHIAVE_ERRORE_REGISTRAZIONE, "errore nella registrazione");
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
