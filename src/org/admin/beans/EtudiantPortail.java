package org.admin.beans;

public class EtudiantPortail extends Etudiant
{
	
	private int id_inscription;
	
	private String nom;
	
	private String prenoms;
	
	private String portail;
	private String confirmedByUser;
	 
	private String confirmerdOn;
	
	private String obs;
	
	private String annee_univ;
	
	private String num_recu;
	
	private boolean isSubscribed;
	
	public EtudiantPortail()
	{
		super();
	}
	
	public String getNom() {
		return this.nom;
	}
	
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}
	public String getPrenoms() {
		return this.prenoms;
	}
	
	public void setId_inscription(int id_inscription)
	{
		this.id_inscription=id_inscription;
	}
	
	public int getId_inscription()
	{
		return this.id_inscription;
	}
	
	
	public void setConfirmedByUser(String confirmedByUser)
	{
		this.confirmedByUser=confirmedByUser;
	}
	
	public String getConfirmedByUser()
	{
		return this.confirmedByUser;
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
	
	public void setIsSubsribed(boolean isSubscribed)
	{
		this.isSubscribed=isSubscribed;
	}
	public boolean getIsSubsribed()
	{
		return this.isSubscribed;
	}
	
}
