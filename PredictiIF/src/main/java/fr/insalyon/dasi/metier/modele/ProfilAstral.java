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
public class ProfilAstral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String signeZoodiaque;
    private String signeAstrologiqueChinois;
    private String couleurPorteBonheur;
    private String animalTotem;

    public ProfilAstral() {
    }

    public ProfilAstral(String signeZoodiaque, String signeAstrologiqueChinois, String couleurPorteBonheur, String animalTotem) {
        this.signeZoodiaque = signeZoodiaque;
        this.signeAstrologiqueChinois = signeAstrologiqueChinois;
        this.couleurPorteBonheur = couleurPorteBonheur;
        this.animalTotem = animalTotem;
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
        if (!(object instanceof ProfilAstral)) {
            return false;
        }
        ProfilAstral other = (ProfilAstral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metier.modele.ProfilAstral[ id=" + id + " ]";
    }
    
    public Long getId() {
        return id;
    }
    
    public String getSigneZoodiaque() {
        return signeZoodiaque;
    }

    public String getSigneAstrologiqueChinois() {
        return signeAstrologiqueChinois;
    }

    public String getCouleurPorteBonheur() {
        return couleurPorteBonheur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setSigneZoodiaque(String signeZoodiaque) {
        this.signeZoodiaque = signeZoodiaque;
    }

    public void setSigneAstrologiqueChinois(String signeAstrologiqueChinois) {
        this.signeAstrologiqueChinois = signeAstrologiqueChinois;
    }

    public void setCouleurPorteBonheur(String couleurPorteBonheur) {
        this.couleurPorteBonheur = couleurPorteBonheur;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }
    
}