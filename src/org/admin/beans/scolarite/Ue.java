package org.admin.beans.scolarite;
public class Ue extends Student{

	private String nomUe;
	private String nomResp;
	private int id_ue ;
	private float credit;
	// private int id_parcours;

	public Ue(String nomUe, String nomResp, int id_ue, float credit){
		this.nomUe = nomUe;
		this.nomResp = nomResp;
		this.id_ue = id_ue;
		this.credit = credit;
	}

	public void setNomUe(String nomUe){
		this.nomUe=nomUe;
	}
	public String getNomUe(){
		return this.nomUe;
	}
	public void setIdUE(int id_ue){
		this.id_ue=id_ue;
	}
	public int getIdUe(){
		return this.id_ue;
	}
	public void setNomResp(String nomResp){
	 	this.nomResp=nomResp;
	}
	public String getNomResp(){
		return this.nomResp;
	}
	public void setCredit(int credit){
		this.credit = credit;
	}
	public float getCredit(){
		return this.credit;
	}
	// private void setId_parcours(int id_parcours){
	// 	this.id_parcours = id_parcours;
	// }
	// private int getId_parcours(){
	// 	return this.id_parcours;
	// }

	public String toString(){
		String retour = "nom de l'ue : " + nomUe + " responsable : " + nomResp + " id_ue =" + id_ue;
		return retour;
	}


}