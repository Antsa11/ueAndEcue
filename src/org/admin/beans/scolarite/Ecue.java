package org.admin.beans.scolarite;

public class Ecue extends Student{
	private String nom_ecue;
	private String responsable;
	private int id_ue;
	private float credit;
	private String niveau;

	public Ecue(String nom_ecue, String responsable, int id_ue, float credit, String niveau){
		this.nom_ecue = nom_ecue;
		this.responsable = responsable;
		this.id_ue = id_ue;
		this.credit = credit;
		this.niveau = niveau;
	}

	public void setNomEcue(String nom_ecue){
		this.nom_ecue = nom_ecue;
	}
	public String getNomEcue(){
		return nom_ecue;
	}

	public void setResponsable(String responsable){
		this.responsable = responsable;
	}
	public String getResponsable(){
		return responsable;
	}

	public void setIdUe(int id_ue){
		this.id_ue = id_ue;
	}
	public int getIdUe(){
		return id_ue;
	}

	public void setCredit(float credit){
		this.credit = credit;
	}
	public float getCredit(){
		return credit;
	}

	public void setNiveau(String niveau){
		this.niveau = niveau;
	}
	public String getNiveau(){
		return niveau;
	}
}