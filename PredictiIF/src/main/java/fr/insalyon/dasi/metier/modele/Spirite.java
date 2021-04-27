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
public class Spirite extends Medium {

	private static final long serialVersionUID = 1L;
	private String support;

	public Spirite(String support, String denomination, Boolean genre, String presentation) {
		super(denomination, genre, presentation);
		this.support = support;
	}

	public Spirite() {
	}

	@Override
	public String toString() {
		String res="Spirite" + super.toString()+" support=" + support+ '}';
		return res;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}
}
