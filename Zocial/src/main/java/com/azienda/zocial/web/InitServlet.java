package com.azienda.zocial.web;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.azienda.zocial.businesslogic.Service;
import com.azienda.zocial.dao.CommentDao;
import com.azienda.zocial.dao.ImageDao;
import com.azienda.zocial.dao.PostDao;
import com.azienda.zocial.dao.UtenteDao;
import com.azienda.zocial.util.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(value="/start",loadOnStartup = 1)
public class InitServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private EntityManager manager=null;
	
	@Override
	public void init() throws ServletException {
		try {
			EntityManagerFactory emf= Persistence.createEntityManagerFactory("prova");
			manager=emf.createEntityManager();
			PostDao postDao=new PostDao(manager);
			CommentDao commentDao=new CommentDao(manager);
			UtenteDao utenteDao=new UtenteDao(manager);
			ImageDao imageDao = new ImageDao(manager);
			Service service =new Service(manager, utenteDao, postDao, commentDao, imageDao);
			getServletContext().setAttribute(Costanti.CHIAVE_SERVICE, service);	
		} catch (Exception e) {
			e.printStackTrace();		
			if(manager !=null) {
				manager.close();
			}
			System.exit(0);
		}
	}
	
}
