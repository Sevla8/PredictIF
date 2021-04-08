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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author B3202-B3205
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Medium implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String denomination;
    private Boolean genre;
    private String presentation;
    private Integer nbConsultations;

    public Medium(String denomination, Boolean genre, String presentation) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
        this.nbConsultations = 0;
    }

    public Medium() {
    }

    public Long getId() {
        return id;
    }

    public Integer getNbConsultations() {
        return nbConsultations;
    }

    public String getDenomination() {
        return denomination;
    }

    public Boolean getGenre() {
        return genre;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setGenre(Boolean genre) {
        this.genre = genre;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public void setNbConsultations(Integer nbConsultations) {
        this.nbConsultations = nbConsultations;
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
        if (!(object instanceof Medium)) {
            return false;
        }
        Medium other = (Medium) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Medium{" + "id=" + id + ", denomination=" + denomination + ", genre=" + genre + ", presentation=" + presentation + ", nbConsultations=" + nbConsultations + '}';
    }
}
