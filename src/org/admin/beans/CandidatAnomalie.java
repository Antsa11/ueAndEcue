package org.admin.beans;

public class CandidatAnomalie extends Candidat
{
	private String obs;
	private String serie_saisie;
	private String operateur;
	
	public CandidatAnomalie(){
		super();
	}
	
	public void setObs(String obs){
		this.obs=obs;
	}
	
	public String getObs()
	{
		return obs;
	}
	
	public void setSerie_saisie(String serie_saisie)
	{
		this.serie_saisie=serie_saisie;
	}
	
	public String getSerie_saisie()
	{
		return serie_saisie;
	}
	
	public void setOperateur(String operateur)
	{
		this.operateur=operateur;
	}
	
	public String getOperateur()
	{
		return operateur;
	}
}
