package com.azienda.zocial.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.zocial.model.Image;

public class ImageDao implements InterfacciaDao<Image> {

	private EntityManager manager;


	public ImageDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void create(Image ref) {
		manager.persist(ref);
	}

	@Override
	public List<Image> retrieve() {
		return manager.createQuery("SELECT x FROM Image x ",Image.class).getResultList();
	}
	public List<Image> retrieveById(Integer id) {
		return manager.createQuery("SELECT x FROM Image x WHERE Image.id IS :ImageId",Image.class)
				.setParameter("imageId", id)
				.getResultList();
	}

	public List<Image> retrieveByPagina(String pagina) {
		return manager.createQuery("SELECT x FROM Image x WHERE Image.pagina IS :ImageId",Image.class)
				.setParameter("imageId", pagina)
				.getResultList();
	}

	@Override
	public void update(Image ref) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Image ref) {
		// TODO Auto-generated method stub
		
	}

	public Image setPaginaById (String pagina, String id) {
		List<Image> i = manager.createQuery("SELECT x FROM Image x WHERE Image.id IS :ImageId",Image.class)
		.setParameter("imageId", Integer.parseInt(id))
		.getResultList();
	
		i.get(Integer.parseInt(id)).setPagina(pagina);
		
		return i.get(0);
	}

}
