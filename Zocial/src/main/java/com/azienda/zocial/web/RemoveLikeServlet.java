package com.azienda.zocial.web;

import java.io.IOException;

import com.azienda.zocial.businesslogic.Service;
import com.azienda.zocial.util.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/removeLike")
public class RemoveLikeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			  Integer postId = Integer.parseInt(req.getParameter("postId"));
		      Integer userId = Integer.parseInt(req.getParameter("userId"));
		      Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
		      service.removeLikeFromPost(postId, userId);
		      
		} catch (Exception e) {
			
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
