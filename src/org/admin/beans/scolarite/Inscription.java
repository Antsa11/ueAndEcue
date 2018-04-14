package org.admin.beans.scolarite;

public class Inscription {
	private int id_inscription;
	private int id_etudiant;
	private int id_parcours;
	private String niveau;
	private String annee_u;
	private String situation;
	private String obs;
	private String specialite;
	private String insererLe;
	public int getId_inscription() {
		return id_inscription;
	}
	
	
	
	public Inscription(int id_etudiant, int id_parcours, String niveau, String annee_u, String situation, String obs,
			String specialite) {
		super();
		this.id_etudiant = id_etudiant;
		this.id_parcours = id_parcours;
		this.niveau = niveau;
		this.annee_u = annee_u;
		this.situation = situation;
		this.obs = obs;
		this.specialite = specialite;
	}



	public void setId_inscription(int id_inscription) {
		this.id_inscription = id_inscription;
	}
	public int getId_etudiant() {
		return id_etudiant;
	}
	public void setId_etudiant(int id_etudiant) {
		this.id_etudiant = id_etudiant;
	}
	public int getId_parcours() {
		return id_parcours;
	}
	public void setId_parcours(int id_parcours) {
		this.id_parcours = id_parcours;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getAnnee_u() {
		return annee_u;
	}
	public void setAnnee_u(String annee_u) {
		this.annee_u = annee_u;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public String getInsererLe() {
		return insererLe;
	}
	public void setInsererLe(String insererLe) {
		this.insererLe = insererLe;
	}



	@Override
	public String toString() {
		return "Inscription [id_inscription=" + id_inscription + ", id_etudiant=" + id_etudiant + ", id_parcours="
				+ id_parcours + ", niveau=" + niveau + ", annee_u=" + annee_u + ", situation=" + situation + ", obs="
				+ obs + ", specialite=" + specialite + ", insererLe=" + insererLe + "]";
	}
	
	
	
}
