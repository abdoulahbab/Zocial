package com.azienda.zocial.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;



@Entity
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String username;
	private String password;
	private LocalDate dataDiNascita;
	private String email;
	private String genere;
	
	@OneToMany(mappedBy = "utente")
	private List<Comment> comment = new ArrayList<Comment>();
	
	@OneToMany(mappedBy = "utente")
	private List<Post> post = new ArrayList<Post>();
	public List<Post> getPost() {
		return post;
	}
	
	
	@ManyToMany(mappedBy = "utente")
	private List<Post>dislikePost=new ArrayList<Post>();
	
	@ManyToMany(mappedBy = "utente")
	private List<Post>likePost=new ArrayList<Post>();

	
	public Utente() {
		super();
	}

	public Utente(String username, String password, LocalDate dataDiNascita, String email, String genere) {
		super();
		this.username = username;
		this.password = password;
		this.dataDiNascita = dataDiNascita;
		this.email = email;
		this.genere = genere;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", password=" + password + ", dataDiNascita="
				+ dataDiNascita + ", email=" + email + ", genere=" + genere + "]";
	}
	
	
}
