package com.azienda.zocial.web;

import java.io.IOException;
import java.util.Base64;

import com.azienda.zocial.businesslogic.Service;
import com.azienda.zocial.util.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/homepage")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Service service = (Service) getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
            byte[] immagine = service.getHomepageImg(); 
            if (immagine != null) {
                String immagineBase64 = Base64.getEncoder().encodeToString(immagine);
                req.setAttribute(Costanti.CHIAVE_ADMIN_Homepage, immagineBase64);
            }
            req.getRequestDispatcher("/homepage.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute(Costanti.CHIAVE_ERRORE, "Errore nel caricamento dell'immagine della homepage");
            resp.sendRedirect(req.getContextPath() + "/jsp/areaPubblica/errore.jsp");
        }
    }
}
