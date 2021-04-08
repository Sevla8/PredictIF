/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    private String dateConsultation;
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
    
    public String getDateConsultation() {
        return dateConsultation;
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

    public void setDateConsultation(String dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

}
