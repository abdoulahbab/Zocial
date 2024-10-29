package com.azienda.zocial.dao;

import java.util.List;

import javax.persistence.EntityManager;


import com.azienda.zocial.model.Utente;

public class UtenteDao implements InterfacciaDao<Utente> {
	
	private EntityManager manager;
	
	
	public UtenteDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void create(Utente ref) {
		manager.persist(ref);
		
	}

	@Override
	public List<Utente> retrieve() {
		
		return  manager.createQuery("SELECT x FROM Utente x",Utente.class).getResultList();
	}

	@Override
	public void update(Utente ref) {
		manager.persist(ref);
		
	}

	@Override
	public void delete(Utente ref) {
		manager.remove(ref);
		
	}
	
	public List<Utente> findByUsername(String user){
		return manager.createQuery("SELECT x FROM Utente x WHERE x.username= :user",Utente.class).
				setParameter("user", user).getResultList();				
	}
	
	public Utente findByUsernameAndPassword(String user,String password){
		return manager.createQuery("SELECT x FROM Utente x WHERE x.username= :user AND x.password= :pass",Utente.class).
				setParameter("user", user)
				.setParameter("pass", password).getSingleResult();
				
	}
}
