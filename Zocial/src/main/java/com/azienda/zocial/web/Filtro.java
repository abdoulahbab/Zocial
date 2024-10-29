package com.azienda.zocial.web;

import java.io.IOException;

import com.azienda.zocial.model.Utente;
import com.azienda.zocial.util.Costanti;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/jsp/areaPrivata/*")
public class Filtro implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		try {
			HttpServletRequest req=(HttpServletRequest)arg0;
			HttpServletResponse res = (HttpServletResponse) arg1;
			HttpSession session = req.getSession();
			
			Utente utente=(session!=null) ? (Utente) session.getAttribute(Costanti.CHIAVE_UTENTE_LOGGATO):null;
			if (utente!=null) {
				arg2.doFilter(arg0, arg1);
	        } else {
	        	arg0.getRequestDispatcher("/jsp/areaPubblica/accedi.jsp").forward(arg0,arg1);
	        }
			
			
		} catch (Exception e) {
			arg0.getRequestDispatcher("/jsp/areaPubblica/errore.jsp").forward(arg0,arg1);
			e.printStackTrace();
		}

	}
	
	
}
