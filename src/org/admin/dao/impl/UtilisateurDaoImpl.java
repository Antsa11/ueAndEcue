package org.admin.dao.impl;

import java.sql.*;
import org.admin.dao.UtilisateurDao;
import java.util.List;
import java.util.ArrayList;
import org.admin.beans.Utilisateur;
import org.admin.beans.Personnel;


import org.admin.dao.DaoFactory;
import org.admin.dao.DaoException;

public class UtilisateurDaoImpl implements UtilisateurDao
{
	private DaoFactory daoFactory;
	
	public UtilisateurDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	public void ajouter(Personnel utilisateur)
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO utilisateurs (username,password) VALUES(?,?);");
			preparedStatement.setString(1,utilisateur.getLogin());
			preparedStatement.setString(2,utilisateur.getPassword());

			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}


	}
    public List<Personnel> getAllPersonnel() throws DaoException
    {
		List<Personnel> personnels = new ArrayList<Personnel>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM \"Vue_utilisateur_personnel\" ");
			resultat = preparedStatement.executeQuery();			// statement = connexion.createStatement();
			// resultat = statement.executeQuery("SELECT * FROM \"Vue_utilisateur_personnel\" ");

			while(resultat.next())
			{
				int id_personnel = resultat.getInt("id_personnel");
				
				int id_utilisateur= resultat.getInt("id_user");
				
				String login = resultat.getString("username");
				String password = resultat.getString("password");
				String nom = resultat.getString("nom");
				String prenoms = resultat.getString("prenoms");
				String fonction = resultat.getString("fonction");
				boolean isActive= resultat.getBoolean("isActive");
				
				//System.out.println("id_personnel "+id_personnel+" login:"+login+" id_user"+id_utilisateur);
				//System.out.println("id_personnel "+id_personnel+" login:"+login);
				
				Personnel personnel = new Personnel();
				personnel.setLogin(login);
				personnel.setPassword(password);
				personnel.setNom(nom.trim().toUpperCase());
				personnel.setPrenoms(prenoms);
				personnel.setFonction(fonction.trim().toUpperCase());
				personnel.setIsActive(isActive);
				personnel.setId_personnel(id_personnel);
				personnel.setId_utilisateur(id_utilisateur);
				personnels.add(personnel);
				
			}
			resultat.close();
			preparedStatement.close();
			// statement.close();
			connexion.close();
		}
		catch (SQLException e) 
		{
		        e.printStackTrace();
		}
		return personnels;
	}
	public List<Utilisateur> lister()
	{
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM utilisateurs; ");
			resultat = preparedStatement.executeQuery();
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery("SELECT * FROM utilisateurs; ");

			while(resultat.next())
			{
				String login = resultat.getString("login");
				String password = resultat.getString("password");

				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setLogin(login);
				utilisateur.setPassword(password);
				utilisateurs.add(utilisateur);
			}
			resultat.close();
			preparedStatement.close();
			// statement.close();
			connexion.close();
		}
		catch (SQLException e) 
		{
		        e.printStackTrace();
		}
		return utilisateurs;
	}
	public int getUserId(Utilisateur utilisateur)
	{
		int userId=0;
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM utilisateurs WHERE username=? AND password=? ;");
		    preparedStatement.setString(1,utilisateur.getLogin());
			preparedStatement.setString(2,utilisateur.getPassword());
			resultat=preparedStatement.executeQuery();
			if(resultat.next())
			userId=resultat.getInt("id_user");
			
			resultat.close();
			preparedStatement.close();
			connexion.close();
			
		}

		catch (SQLException e)
		{
		        e.printStackTrace();
		}

		return userId;
	}
	
	public String getUserFonction(Utilisateur utilisateur)
	{
		String fonction=null;
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM \"Vue_utilisateur_personnel\" WHERE id_user=? ;");
		    preparedStatement.setInt(1,utilisateur.getId_utilisateur());
			//preparedStatement.setString(2,utilisateur.getPassword());
			resultat=preparedStatement.executeQuery();
			if(resultat.next())
			fonction=resultat.getString("fonction");
			
			resultat.close();
			preparedStatement.close();
			connexion.close();
			
		}

		catch (SQLException e)
		{
		        e.printStackTrace();
		}

		return fonction;
	}
	
	public boolean isValid(Utilisateur utilisateur)
	{	
		boolean status = false;
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;
		try{
			connexion = daoFactory.getConnection();
			//statement = connexion.createStatement();
			//resultat = statement.executeQuery("SELECT * FROM utilisateurs; ");
			preparedStatement = connexion.prepareStatement("SELECT * FROM utilisateurs WHERE username=? AND password=? AND \"isActive\"=true;");
	        preparedStatement.setString(1,utilisateur.getLogin());
			preparedStatement.setString(2,utilisateur.getPassword());
			resultat=preparedStatement.executeQuery();
			status=resultat.next();
			
			resultat.close();
			preparedStatement.close();
			connexion.close();
		}	

		catch (SQLException e)
		{
		        e.printStackTrace();
		}

		return status;
	}
	
	public void delete(int id_utilisateur)
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE from utilisateurs where id_user=?;");
			preparedStatement.setInt(1,id_utilisateur);
			preparedStatement.executeUpdate();
			
			preparedStatement = connexion.prepareStatement("DELETE from t_personnel where id_user=?;");
			preparedStatement.setInt(1,id_utilisateur);
			preparedStatement.executeUpdate();

			
			preparedStatement.close();
			connexion.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
