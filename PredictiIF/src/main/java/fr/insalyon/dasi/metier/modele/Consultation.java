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

    public Consultation() {
    }

    public Consultation(String dateConsultation, String commentaire, Integer note) {
        this.dateConsultation = dateConsultation;
        this.commentaire = commentaire;
        this.note = note;
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

    public void setDateConsultation(String dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

}
