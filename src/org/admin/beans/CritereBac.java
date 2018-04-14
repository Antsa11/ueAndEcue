package org.admin.beans;

public class CritereBac{
	
	private int id_critere;
	private String serie;
	private String mention;
	private String nom_prenom;
	private String centre;
	private int annee;
	
	
	private int countRecords;
	
	public CritereBac()
	{
		
	}
	
	public CritereBac(String serie, String mention, String nom_prenom, String centre, int annee)
	{
		this.serie=serie;
		this.mention=mention;
		this.nom_prenom=nom_prenom;
		this.centre=centre;
		this.annee=annee;
	}
	
	public void setAnnee(int annee)
	{
		this.annee=annee;
	}
	public int getAnnee()
	{
		return this.annee;
	}
	
	public void setSerie(String serie)
	{
		this.serie=serie;
	}
	public String getSerie()
	{
		return this.serie;
	}
	
	public void setCountRecords(int countRecords)
	{
		this.countRecords=countRecords;
	}
	
	public int getCountRecords()
	{
		return this.countRecords;
	}
	public String getMention()
	{
		return this.mention;
	}
	public void setMention(String mention)
	{
		this.mention=mention;
	}
	public void setNom_prenom(String nom_prenom)
	{
		this.nom_prenom=nom_prenom;
	}
	public String getNom_prenom()
	{
		return this.nom_prenom;
	}
	
	public void setCentre(String centre)
	{
		this.centre=centre;
	}
	public String getCentre()
	{
		return this.centre;
	}
	
	
}
