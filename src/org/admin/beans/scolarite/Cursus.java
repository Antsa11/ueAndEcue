package org.admin.beans.scolarite;

	public class Cursus {
		private int id_etudiant;
		private String nom;
		private String prenoms;
		private String nom_parcours;
		private String nom_mention;
		private String niveau;
		private String annee_u;
		private String situation;

	public Cursus(int id_etudiant, String nom, String prenoms, String nom_parcours, String nom_mention, String niveau, String annee_u, String situation){
		this.id_etudiant = id_etudiant;
		this.nom = nom;
		this.prenoms = prenoms;
		this.nom_parcours = nom_parcours;
		this.nom_mention = nom_mention;
		this.niveau = niveau;
		this.annee_u = annee_u;
		this.situation = situation;
	}

	public int getId_etudiant() {
		return id_etudiant;
	}
	public void setId_etudiant(int id_etudiant) {
		this.id_etudiant = id_etudiant;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	public String getNom(){
		return nom;
	}
	public void setPrenom(String prenoms){
		this.prenoms = prenoms;
	}
	public String getPrenom(){
		return prenoms;
	}
	public String getNom_parcours() {
		return nom_parcours;
	}
	public void setNom_parcours(String nom_parcours) {
		this.nom_parcours = nom_parcours;
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
	public void setNom_mention(String nom_mention){
		this.nom_mention = nom_mention;
	}
	public String getNom_mention(){
		return nom_mention;
	}


	@Override
	public String toString() {
		return "Cursus [id_etudiant=" + id_etudiant + ", nom_parcours="
				+ nom_parcours + ", niveau=" + niveau + ", annee_u=" + annee_u + ", situation=" + situation + ", nom="
				+ nom + ", prenoms=" + prenoms + ", nom_mention=" + nom_mention + "]";
	}
	
	
	
}
