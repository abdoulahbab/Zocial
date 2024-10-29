package com.azienda.zocial.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.zocial.model.Comment;


public class CommentDao implements InterfacciaDao<Comment> {

	private EntityManager manager;


	public CommentDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void create(Comment ref) {

		manager.persist(ref);

	}

	@Override
	public List<Comment> retrieve() {
		return manager.createQuery("SELECT x FROM Comment x ORDER BY x.dataDiPubblicazion DESC",Comment.class).getResultList();
	}

	@Override
	public void update(Comment ref) {

		manager.persist(ref);

	}

	@Override
	public void delete(Comment ref) {
		manager.remove(ref);

	}

}
