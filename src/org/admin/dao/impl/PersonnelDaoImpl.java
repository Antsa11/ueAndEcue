package org.admin.dao.impl;

import java.sql.*;
import org.admin.dao.UtilisateurDao;
import org.admin.dao.PersonnelDao;
import org.admin.dao.DaoException;

import java.util.List;
import java.util.ArrayList;
import org.admin.beans.Utilisateur;
import org.admin.beans.Personnel;


import org.admin.dao.DaoFactory;

public class PersonnelDaoImpl implements PersonnelDao
{
	private DaoFactory daoFactory;
	private UtilisateurDao utilisateurDao;
	public PersonnelDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
		utilisateurDao=daoFactory.getUtilisateurDao();
	}
	public void ajouterPersonnel(Personnel personnel) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try{
			
			utilisateurDao.ajouter(personnel);
			connexion = daoFactory.getConnection();
					
			preparedStatement = connexion.prepareStatement("INSERT INTO t_personnel (nom,prenoms,fonction,id_user) VALUES(?,?,?,?);");
			preparedStatement.setString(1,personnel.getNom());
			preparedStatement.setString(2,personnel.getPrenoms());
			preparedStatement.setString(3,personnel.getFonction());
			preparedStatement.setInt(4,utilisateurDao.getUserId(personnel));
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}


	}

	public List<Personnel> getListPersonnel()
	{
		List<Personnel> utilisateurs = new ArrayList<Personnel>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM utilisateurs; ");
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery("SELECT * FROM utilisateurs; ");
			resultat = preparedStatement.executeQuery();
			while(resultat.next())
			{
				String login = resultat.getString("login");
				String password = resultat.getString("password");

				Personnel utilisateur = new Personnel();
				utilisateur.setLogin(login);
				utilisateur.setPassword(password);
				utilisateurs.add(utilisateur);
			}

			connexion.close();
			preparedStatement.close();
			resultat.close();
		}
		catch (SQLException e) 
		{
		        e.printStackTrace();
		}
		return utilisateurs;
	}
	
}
