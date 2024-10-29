package com.azienda.zocial.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String testo;
	private LocalDateTime dataDiPubblicazion;
	
	@ManyToOne
	@JoinColumn(name="commenti_post")
	private Post post;
	
	@ManyToOne
	@JoinColumn(name="commenti_utente")
	private Utente utente;
	
	public Comment() {
		super();
	}
	
	public Comment(String testo, LocalDateTime dataDiPubblicazion) {
		super();
		this.testo = testo;
		this.dataDiPubblicazion = dataDiPubblicazion;
	}
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public LocalDateTime getDataDiPubblicazion() {
		return dataDiPubblicazion;
	}
	public void setDataDiPubblicazion(LocalDateTime dataDiPubblicazion) {
		this.dataDiPubblicazion = dataDiPubblicazion;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", testo=" + testo + ", dataDiPubblicazion=" + dataDiPubblicazion + "]";
	}
}
