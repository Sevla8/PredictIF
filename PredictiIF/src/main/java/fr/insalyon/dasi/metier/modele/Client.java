/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author B3202-B3205
 */
@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String motDePasse;
    private String prenom;
    private String nom;
    private String adressePostale;
    private String numeroDeTelephone;
    private Boolean genre;
    @Column(unique=true)
    private String mail;
    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;
    @OneToOne
    private ProfilAstral profilAstral;
    @OneToMany(mappedBy="client")
    private List<Consultation> consultations;

    public Client() {
    }

    public Client(String motDePasse, String prenom, String nom, String adressePostale, String numeroDeTelephone, Boolean genre, String mail, Date dateDeNaissance) {
        this.motDePasse = motDePasse;
        this.prenom = prenom;
        this.nom = nom;
        this.adressePostale = adressePostale;
        this.numeroDeTelephone = numeroDeTelephone;
        this.genre = genre;
        this.mail = mail;
        this.dateDeNaissance = dateDeNaissance;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", motDePasse=" + motDePasse + ", prenom=" + prenom + ", nom=" + nom + ", adressePostale=" + adressePostale + ", numeroDeTelephone=" + numeroDeTelephone + ", genre=" + genre + ", mail=" + mail + ", dateDeNaissance=" + dateDeNaissance + ", profilAstral=" + profilAstral + '}';
    }
 
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public void setGenre(Boolean genre) {
        this.genre = genre;
    }

    public void setNumeroDeTelephone(String numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
    
    public Long getId() {
        return id;
    }
            
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public Boolean getGenre() {
        return genre;
    }

    public String getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void ajouterConsultation(Consultation consultation) {
        this.consultations.add(consultation);
    }
}
