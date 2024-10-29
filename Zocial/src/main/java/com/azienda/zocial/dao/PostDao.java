package com.azienda.zocial.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.zocial.model.Post;
import com.azienda.zocial.model.Utente;

public class PostDao implements InterfacciaDao<Post> {

	private EntityManager manager;


	public PostDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void create(Post ref) {
		manager.persist(ref);
	}

	@Override
	public List<Post> retrieve() {
		return manager.createQuery("SELECT x FROM Post x ORDER BY x.dataDiPubblicazione DESC",Post.class).getResultList();
	}

	public Post retrieveById(Integer id) {
		return manager.createQuery("SELECT x FROM Post x WHERE x.id = :postId",Post.class)
				.setParameter("postId", id)
				.getSingleResult();
	}

	@Override
	public void update(Post ref) {
		manager.persist(ref);
	}

	@Override
	public void delete(Post ref) {
		manager.remove(ref);
	}

	public void removeLikeFromPost(Integer postId, Integer userId) {
		Post post = retrieveById(postId);
		Utente user = manager.find(Utente.class, userId);
		if (post != null && user != null) {

			post.removeLike(user); 
			manager.persist(post); 
		}
	}

}
