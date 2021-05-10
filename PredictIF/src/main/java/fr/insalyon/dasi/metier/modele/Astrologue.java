/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author B3202-B3205
 */
@Entity
public class Astrologue extends Medium {

	private static final long serialVersionUID = 1L;
	private String formation;
	private String promotion;

	public Astrologue(String formation, String promotion, String denomination, Boolean genre, String presentation) {
		super(denomination, genre, presentation);
		this.formation = formation;
		this.promotion = promotion;
	}

	@Override
	public String toString() {
		String res="Astrologue" + super.toString()+" formation=" + formation + ", promotion=" + promotion + '}';
		return res;
	}

	public Astrologue() {
	}

	public String getFormation() {
		return formation;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

}
