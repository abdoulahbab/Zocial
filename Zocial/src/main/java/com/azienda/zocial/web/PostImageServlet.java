package com.azienda.zocial.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

import com.azienda.zocial.businesslogic.Service;
import com.azienda.zocial.model.Post;
import com.azienda.zocial.util.Costanti;

@WebServlet("/post/image")
public class PostImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Service service = (Service) getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			String postId = req.getParameter("id");
			Post post = service.getPostById(postId); 

			resp.setContentType("image/jpeg"); 
			resp.setContentLength(post.getImageData().length);

			try (OutputStream out = resp.getOutputStream()) {
				out.write(post.getImageData());
				out.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/areaPubblica/errore.jsp").forward(req, resp);
		}
	}
}
