/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author B3202-B3205
 */
@Entity
public class Consultation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date dateDebut;
	private Long duree;
	private String commentaire;
	private Integer note;
	@ManyToOne
	private Medium medium;
	@ManyToOne
	private Client client;
	@ManyToOne
	private Employe employe;

	public Consultation() {
	}

	public Consultation(Medium medium, Client client, Employe employe) {
		this.medium = medium;
		this.client = client;
		this.employe = employe;
	}

	@Override
	public String toString() {
		return "Consultation{" + "id=" + id + ", dateDebut=" + dateDebut + ", duree=" + duree + ", commentaire=" + commentaire + ", note=" + note + ", medium=" + medium + ", client=" + client + ", employe=" + employe + '}';
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public Long getDuree() {
		return duree;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public Integer getNote() {
		return note;
	}

	public Long getId() {
		return id;
	}

	public Medium getMedium() {
		return medium;
	}

	public Client getClient() {
		return client;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public void setDuree(Long duree) {
		this.duree = duree;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public void setNote(Integer note) {
		if(note>5){
			note=5;
		}else if(note<0){
			note=0;
		}
		this.note = note;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMedium(Medium medium) {
		this.medium = medium;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
}
