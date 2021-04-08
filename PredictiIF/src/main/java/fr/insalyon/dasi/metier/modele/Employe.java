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
    private Boolean genre;
    private String motDePasse;
    private String numeroDeTelephone;
    private Boolean estDisponible;
    private Integer nbConsultations;
    @Column(unique=true)
    private String mail;

    public Employe(String nom, String prenom, Boolean genre, String motDePasse, String numeroDeTelephone, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.motDePasse = motDePasse;
        this.numeroDeTelephone = numeroDeTelephone;
        this.mail = mail;
        this.estDisponible = true;
        this.nbConsultations = 0;
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
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + ", motDePasse=" + motDePasse + ", numeroDeTelephone=" + numeroDeTelephone + ", estDisponible=" + estDisponible + ", nbConsultations=" + nbConsultations + ", mail=" + mail + '}';
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Boolean getGenre() {
        return genre;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public Boolean getEstDisponible() {
        return estDisponible;
    }

    public Integer getNbConsultations() {
        return nbConsultations;
    }

    public String getMail() {
        return mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setGenre(Boolean genre) {
        this.genre = genre;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setNumeroDeTelephone(String numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public void setEstDisponible(Boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    public void setNbConsultations(Integer nbConsultations) {
        this.nbConsultations = nbConsultations;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
