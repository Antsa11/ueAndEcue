package org.admin.beans;

public class Etudiant extends Candidat
{
	private String num_etudiant;
	private String photo_file;
	
	private String adresse;
	
	private String lieu_naissance;
	
	private String nom_pere;
	private String nom_mere;
	
	
	
	public Etudiant()
	{
		super();
	}
	
	public void setNum_etudiant(String num_etudiant)
	{
		this.num_etudiant=num_etudiant;
	}
	
	public String getNum_etudiant()
	{
		return this.num_etudiant;
	}
	
	public void setPhoto_file(String photo_file)
	{
		this.photo_file=photo_file;
	}
	
	public String getPhoto_file()
	{
		return this.photo_file;
	}
	
	public void setAdresse(String adresse)
	{
		this.adresse=adresse;
	}
	public String getAdresse()
	{
		return this.adresse;
	}
	
	public void setLieu_naissance(String lieu_naissance)
	{
		this.lieu_naissance=lieu_naissance;
	}
	
	public String getLieu_naissance()
	{
		return this.lieu_naissance;
	}
	
	public void setNom_pere(String nom_pere)
	{
		this.nom_pere=nom_pere;
	}
	
	public String getNom_pere()
	{
		return this.nom_pere;
	}
	
	public void setNom_mere(String nom_mere)
	{
		this.nom_mere=nom_mere;
	}
	
	public String getNom_mere()
	{
		return this.nom_mere;
	}
	
	public String toString()
	{
		return "Nom"+this.getNom()+" Prenoms:"+this.getPrenoms()+" Serie:"+this.getSerie();
	}
	
	
}
