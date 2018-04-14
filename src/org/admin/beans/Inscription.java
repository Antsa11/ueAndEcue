package org.admin.beans;


public class Inscription
{
	private int id_inscription;
	
	private int id_etudiant;
	
	private String portail;
	
	private int confirmedByUserId;
	
	private String confirmerdOn;
	
	private String obs;
	
	private String annee_univ;
	
	private String num_recu;
	
	public void setId_inscription(int id_inscription)
	{
		this.id_inscription=id_inscription;
	}
	
	public int getId_inscription()
	{
		return this.id_inscription;
	}
	
	public void setId_etudiant(int id_etudiant)
	{
		this.id_etudiant=id_etudiant;
	}
	
	public int getId_etudiant()
	{
		return this.id_etudiant;
	}
	public void setConfirmedByUserId(int confirmedByUserId)
	{
		this.confirmedByUserId=confirmedByUserId;
	}
	
	public int getConfirmedByUserId()
	{
		return this.confirmedByUserId;
	}
	
	public void setConfirmerdOn(String confirmerdOn)
	{
		this.confirmerdOn=confirmerdOn;
	}
	public String getConfirmedOn()
	{
		return this.confirmerdOn;
	}
	
	public void setObs(String obs)
	{
		this.obs=obs;
	}
	
	public String getObs()
	{
		return this.obs;
	}
	
	public void setAnnee_univ(String annee_univ)
	{
		this.annee_univ=annee_univ;
	}
	
	public String getAnnee_univ()
	{
		return this.annee_univ;
	}
	
	public void setPortail(String portail)
	{
		this.portail=portail;
	}
	
	public String getPortail()
	{
		return this.portail;
	}
	
	public void setNum_recu(String num_recu)
	{
		this.num_recu=num_recu;
	}
	public String getNum_recu()
	{
		return this.num_recu;
	}
}
