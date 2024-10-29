package com.azienda.zocial.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titolo;
	private String testo;
	private Boolean visibilita;
	private LocalDateTime dataDiPubblicazione;
	
	@Lob
	private byte[] immagine;

	@ManyToOne
	@JoinColumn(name="post_utente")
	private Utente utente;

	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	@OneToMany(mappedBy = "post")
	private List<Comment> comment=new ArrayList<Comment>();


	@ManyToMany
	@JoinTable(name = "dislike_post", joinColumns = @JoinColumn (name = "utente_id"),
	inverseJoinColumns = @JoinColumn(name ="post_id"))
	private List<Utente> dislikeUtente = new ArrayList<Utente>();

	@ManyToMany
	@JoinTable(name = "like_post", joinColumns = @JoinColumn (name = "utente_id"),
	inverseJoinColumns = @JoinColumn(name ="post_id"))
	private List<Utente> likeUtente = new ArrayList<Utente>();


	public Post(String titolo, String testo, boolean visibilita, LocalDateTime dataDiPubblicazione, byte[] immagine) {
		super();
		this.titolo = titolo;
		this.testo = testo;
		this.visibilita = visibilita;
		this.dataDiPubblicazione = dataDiPubblicazione;
		this.immagine = immagine;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public LocalDateTime getDataDiPubblicazione() {
		return dataDiPubblicazione;
	}
	public void setDataDiPubblicazione(LocalDateTime dataDiPubblicazione) {
		this.dataDiPubblicazione = dataDiPubblicazione;
	}
	public byte[] getImmagine() {
		return immagine;
	}
	public void setImmagine(byte[] immagine) {
		this.immagine = immagine;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", titolo=" + titolo + ", testo=" + testo + ", dataDiPubblicazione="
				+ dataDiPubblicazione + ", immagine=" + immagine + "]";
	}
	public byte[] getImageData() {
		return immagine;
	}
	public Boolean getVisibilita() {
		return visibilita;
	}
	public void setVisibilita(Boolean visibilita) {
		this.visibilita = visibilita;
	}
	
	public void addLike(Utente user) {
        if (!likeUtente.contains(user)) {
            likeUtente.add(user);

            dislikeUtente.remove(user);
        }
    }


    public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public List<Utente> getDislikeUtente() {
		return dislikeUtente;
	}
	public void setDislikeUtente(List<Utente> dislikeUtente) {
		this.dislikeUtente = dislikeUtente;
	}
	public List<Utente> getLikeUtente() {
		return likeUtente;
	}
	public void setLikeUtente(List<Utente> likeUtente) {
		this.likeUtente = likeUtente;
	}
	public void removeLike(Utente user) {
        likeUtente.remove(user);
    }
    public void addDislike(Utente user) {
        if (!dislikeUtente.contains(user)) {
            dislikeUtente.add(user);

            likeUtente.remove(user);
        }
    }
    public void removeDislike(Utente user) {
        dislikeUtente.remove(user);
    }

}
