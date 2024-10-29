package com.azienda.zocial.businesslogic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.zocial.dao.CommentDao;
import com.azienda.zocial.dao.ImageDao;
import com.azienda.zocial.dao.PostDao;
import com.azienda.zocial.dao.UtenteDao;
import com.azienda.zocial.exception.DataException;
import com.azienda.zocial.exception.EmailException;
import com.azienda.zocial.exception.PasswordException;
import com.azienda.zocial.exception.UserException;
import com.azienda.zocial.exception.UtenteException;
import com.azienda.zocial.exception.UtenteGiaPresente;
import com.azienda.zocial.model.Comment;
import com.azienda.zocial.model.Image;
import com.azienda.zocial.model.Post;
import com.azienda.zocial.model.Utente;
import com.azienda.zocial.util.Costanti;

public class Service {

	private EntityManager manager;

	private UtenteDao utenteDao;
	private PostDao postDao;
	private CommentDao commentDao;
	private ImageDao imageDao;

	public Service(EntityManager manager, UtenteDao utenteDao, PostDao postDao, CommentDao commentDao, ImageDao imageDao) {
		super();
		this.manager = manager;
		this.utenteDao = utenteDao;
		this.postDao = postDao;
		this.commentDao = commentDao;
		this.imageDao = imageDao;
	}
	
	public void insertUtente(String username,String password,String email,LocalDate dataNascita,String genere)throws UserException,PasswordException,DataException,EmailException,UtenteGiaPresente{
		try {
			manager.getTransaction().begin();
			if(username==null || username.trim().isEmpty()) {
				throw new UserException("campo non valido", null);
			}
			if(password==null || password.trim().isEmpty()) {
				throw new PasswordException("campo non valido", null);
			}
			if(email==null || email.trim().isEmpty()) {
				throw new EmailException("campo non valido", null);
			}
			if(dataNascita==null) {
				throw new DataException("campo non valido", null);
			}
			List<Utente>utente=new ArrayList<Utente>();
			utente = utenteDao.findByUsername(username);

			if(utente.size()!=0) {
				throw new UtenteGiaPresente("utente già presente", null);
			}
			utenteDao.create(new Utente(username,password,dataNascita,email,genere));
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

	public Utente validateUtente(String userName,String password)throws Exception {
		try {
			manager.getTransaction().begin();

			Utente utente= utenteDao.findByUsernameAndPassword(userName, password);


			manager.getTransaction().commit();
			return utente;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}

	}


	public void addPost(Post post, Utente utente){
		try {
			manager.getTransaction().begin();
			postDao.create(post);
			post.setUtente(utente);
			//			utente.getPost().add(post);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

	public List<Post> retrievePosts() {
		try {
			manager.getTransaction().begin();
			List<Post> posts= postDao.retrieve();
			manager.getTransaction().commit();
			return posts;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public Post getPostById(String postId) {
		try {
			manager.getTransaction().begin();
			Integer n = Integer.parseInt(postId);
			Post post = postDao.retrieveById(n);
			manager.getTransaction().commit();
			return post;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public void addComment(String s, Utente utente, String postId) {
		try {
			manager.getTransaction().begin();

			Comment nuovoCommento = new Comment();
			nuovoCommento.setTesto(s);
			nuovoCommento.setUtente(utente);
			Integer n = Integer.parseInt(postId);
			nuovoCommento.setPost(postDao.retrieveById(n));
			nuovoCommento.setDataDiPubblicazion(LocalDateTime.now());
			commentDao.create(nuovoCommento);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

	public List<Comment> retrieveComments() {
		try {
			manager.getTransaction().begin();
			List<Comment> comments= commentDao.retrieve();
			manager.getTransaction().commit();
			return comments;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public void likeDislike(Utente user, Integer postId, String action) {
        try {
        	manager.getTransaction().begin();
        	 Post post = postDao.retrieveById(postId);

             if (user != null && post != null) {
                 if ("like".equals(action)) {
                     post.addLike(user);
                 } else if ("dislike".equals(action)) {
                     post.addDislike(user);
                 }
             }

             postDao.update(post);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
       
	}

	

	public void removeLikeFromPost(Integer postId, Integer userId) {
		try {
			 manager.getTransaction().begin();
			 postDao.removeLikeFromPost(postId, userId);
			 manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}	
	}

	public List<Utente> findByUsername(String username) throws UtenteException {
		try {
			manager.getTransaction().begin();
			if(username==null || username.isEmpty()) {
				throw new UtenteException("Errore nella ricerca", null);
			}
			manager.getTransaction().commit();
			return utenteDao.findByUsername(username);
		} catch (UtenteException u) {
			manager.getTransaction().rollback();
			throw u;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

//	public void searchLikeDislike(Utente utente, int postId, String action) {
//		try {
//        	manager.getTransaction().begin();
//        	 Post post = postDao.retrieveById(postId);
//        	 List<Utente> utentel = post.getLikeUtente();
//        	 List<Utente> utented = post.getDislikeUtente();
//        	 
//             if (utente != null && post != null) {
//                 if ("like".equals(action)) {
//                	 for (Utente user: utentel) {
//                		 
//                	 }
//                	 
//                	 if()
//                	 
//                     post.addLike(user);
//                 } else if ("dislike".equals(action)) {
//                     post.addDislike(user);
//                 }
//             }
//
//             postDao.update(post);
//			manager.getTransaction().commit();
//		} catch (Exception e) {
//			manager.getTransaction().rollback();
//			throw e;
//		}
//	}

//	public void modifyTextPost(String testo, String visibilita, Utente utente) {
//		 try {
//	        	manager.getTransaction().begin();
//	        	 Post post = postDao.retrieveById(utente.getId());
//
//	             if (user != null && post != null) {
//	                 if ("like".equals(action)) {
//	                     post.addLike(user);
//	                 } else if ("dislike".equals(action)) {
//	                     post.addDislike(user);
//	                 }
//	             }
//
//	             postDao.update(post);
//				manager.getTransaction().commit();
//			} catch (Exception e) {
//				manager.getTransaction().rollback();
//				throw e;
//			}
//	}

	public void addImage(byte[] immagineCaricata) {
		try {
			manager.getTransaction().begin();
			imageDao.create(new Image(immagineCaricata));
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public List<Image> showImagesAdmin() {
		try {
			manager.getTransaction().begin();
			List<Image> i = imageDao.retrieve();
			manager.getTransaction().commit();
			return i;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public Image setHomepageImage(String idImage) {
		try {
			manager.getTransaction().begin();
			List<Image> i = imageDao.retrieve();
			for (Image im: i) {
				if(Costanti.CHIAVE_ADMIN_Homepage.equals(im.getPagina())) {
					im.setPagina(null);
				}
			}
			Image im = imageDao.setPaginaById(Costanti.CHIAVE_ADMIN_Homepage, idImage);
			manager.getTransaction().commit();
			return im;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

	public byte[] getHomepageImg() {
		try {
			manager.getTransaction().begin();
			List<Image> i = imageDao.retrieveByPagina(Costanti.CHIAVE_ADMIN_Homepage);
			List<byte[]> b = new ArrayList<byte[]>();
			for(Image im : i) {
				b.add(im.getImmagine());
			}
			manager.getTransaction().commit();
			return b.getFirst();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	


}