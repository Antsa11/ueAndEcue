package org.admin.beans;

/*
 * 
 * SELECT t_derogation."acceptedOn",
    t_derogation.id_derogation,
    t_derogation."acceptedByUserId",
    t_derogation.id_record,
    utilisateurs.id_user,
    utilisateurs.username,
    t_import_saisie.nom,
    t_import_saisie.prenoms,
    t_import_saisie.choix
   FROM t_derogation,
    t_import_saisie,
    utilisateurs
  WHERE t_import_saisie.id_record = t_derogation.id_record AND utilisateurs.id_user = t_derogation."acceptedByUserId"
  ORDER BY utilisateurs.username;
 * 
 * 
 * */


public class Derogation
{
	private int id_derogation;
	private int id_record;
	private String acceptedOn;
	private int acceptedByUserId;
	
	private String userName;
	private String nom;
	
	private String prenoms;
	private String choix;
    
    private String obs;
    
    public void setObs(String obs)
	{
		this.obs=obs;
	}
	public String getObs()
	{
		return this.obs;
	}
	
	public int getId_record() {
		return id_record;
	}
	public void setId_record(int id_record) {
		this.id_record = id_record;
	}
	
	public void setId_derogation(int id_derogation)
	{
		this.id_derogation=id_derogation;
	}
	public int getId_derogation()
	{
		return this.id_derogation;
	}
	
	public void setAcceptedOn(String acceptedOn)
	{
		this.acceptedOn=acceptedOn;
	}
	public String getAcceptedOn()
	{
		return this.acceptedOn;
	}
	
	public void setAcceptedByUserId(int acceptedByUserId)
	{
		this.acceptedByUserId=acceptedByUserId;
	}
	public int getAcceptedByUserId()
	{
		return acceptedByUserId;
	}
	
	public void setUserName(String userName)
	{
		this.userName=userName;
	}
	public String getUserName()
	{
		return userName;
	}
	
	public String getNom() {
		return this.nom;
	}
	public String getPrenoms() {
		return this.prenoms;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}
	public String getChoix() {
		return choix;
	}
	public void setChoix(String choix) {
		this.choix = choix;
	}
	
	
	
	
}
