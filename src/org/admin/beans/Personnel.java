package org.admin.beans;

public class Personnel extends Utilisateur
{
	private String nom;
	private String prenoms;
	private String fonction;
	private boolean isActive;
	private int id_personnel;
	
	public Personnel()
	{
		
	}
	
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenoms() {
		return this.prenoms;
	}
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}
	public String getFonction()
	{
		return this.fonction;
	}
	public void setFonction(String fonction)
	{
		this.fonction=fonction;
	}
	
	public void setIsActive(boolean isActive)
	{
		this.isActive=isActive;
	}
	public boolean getIsActive()
	{
		return this.isActive;
	}
	
	public void setId_personnel(int id_personnel)
	{
		this.id_personnel=id_personnel;
	}
	public int getId_personnel()
	{
		return id_personnel;
	}
}
