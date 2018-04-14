package org.admin.beans;

public class CritereRecherche
{
	private int annee;
	private String choix;
	private String search;
	
	private String anneeU;
	
	public CritereRecherche()
	{
		
	}
	public CritereRecherche(int annee, String choix, String search)
	{
		this.annee=annee;
		this.choix=choix;
		this.search=search;
	}
	public CritereRecherche(String anneeU, String choix, String search)
	{
		this.anneeU=anneeU;
		this.choix=choix;
		this.search=search;
	}
	
	public void setAnnee(int annee)
	{
		this.annee=annee;
	}
	public int getAnnee()
	{
		return this.annee;
	}
	public void setChoix(String choix)
	{
		this.choix=choix;
	}
	public String getChoix()
	{
		return this.choix;
	}
	
	public String getSearch()
	{
		return this.search;
	}
	
	public void setSearch(String search)
	{
		this.search=search;
	}
	public void setAnneeU(String anneeU)
	{
		this.anneeU=anneeU;
	}
	public String getAnneeU()
	{
		return this.anneeU;
	}
}
