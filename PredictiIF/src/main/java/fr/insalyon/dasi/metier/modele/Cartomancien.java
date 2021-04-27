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
public class Cartomancien extends Medium {

	private static final long serialVersionUID = 1L;

	public Cartomancien(String denomination, Boolean genre, String presentation) {
		super(denomination, genre, presentation);
	}

	public Cartomancien() {
	}

	@Override
	public String toString() {
		String res="Cartomancien" + super.toString()+ '}';
		return res;
	}

}
