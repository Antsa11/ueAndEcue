package org.admin.beans.scolarite;

public class Student {
	private int id_etudiant;
	private int id_parcours;
	private int id_mention;

	private String nom;
	private String prenom;
	private String ddn;
	private String adresse;
	private String lieu_naissance;
	private String tel;
	private String nom_mention;
	private String nom_parcours;
	private String niveau;
	private String situation;
	private String annee_u;
	private String photo;
	
	public Student() {
		super();
		
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

	public int getId_mention() {
		return id_mention;
	}

	public void setId_mention(int id_mention) {
		this.id_mention = id_mention;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDdn() {
		return ddn;
	}

	public void setDdn(String ddn) {
		this.ddn = ddn;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getLieu_naissance() {
		return lieu_naissance;
	}

	public void setLieu_naissance(String lieu_naissance) {
		this.lieu_naissance = lieu_naissance;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getNom_mention() {
		return nom_mention;
	}

	public void setNom_mention(String nom_mention) {
		this.nom_mention = nom_mention;
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
	
	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getAnnee_u() {
		return annee_u;
	}

	public void setAnnee_u(String annee_u) {
		this.annee_u = annee_u;
	}

	public String getPhoto(){

		return this.photo;
	}

	public void setPhoto(String photo){

		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Student [nom=" + nom + ", prenom=" + prenom + ", ddn=" + ddn + ", adresse=" + adresse
				+ ", lieu_naissance=" + lieu_naissance + ", tel=" + tel + ", nom_mention=" + nom_mention
				+ ", nom_parcours=" + nom_parcours + ", niveau=" + niveau + ", annee_u=" + annee_u + "]";
	}
	
	
}
