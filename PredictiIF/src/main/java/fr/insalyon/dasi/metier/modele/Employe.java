/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author B3202-B3205
 */
@Entity
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String genre;
    private String motDePasse;
    private String numeroDeTelephone;
    private Boolean estDisponible;
    private Long nbConsultations;
    @Column(unique=true)
    private String mail;

    public Employe(String nom, String prenom, String genre, String motDePasse, String numeroDeTelephone, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.motDePasse = motDePasse;
        this.numeroDeTelephone = numeroDeTelephone;
        this.mail = mail;
        this.estDisponible = true;
        this.nbConsultations = 0L;
    }

    public Employe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.insalyon.dasi.metier.modele.Employe[ id=" + id + " ]";
    }
    
}
