package org.admin.beans;
public class Utilisateur
{
	private String login;
	private String password;
	private int id_utilisateur;
	

	
	public Utilisateur()
	{
	}
	public Utilisateur(String login,String password)
	{
		this.login=login;
		this.password=password;
	}
	
	public void setLogin(String login)
	{
		this.login=login;
	}
	public String getLogin()
	{
		return this.login;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	public String getPassword()
	{
		return this.password;
	}
	
	public void setId_utilisateur(int id_utilisateur)
	{
		this.id_utilisateur=id_utilisateur;
	}
	
	public int getId_utilisateur()
	{
		return this.id_utilisateur;
	}
	
	public String toString()
	{
		return "Login:"+this.login+" Password:"+this.password;
	}
	
}
