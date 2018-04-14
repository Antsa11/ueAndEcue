package org.admin.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import org.admin.dao.DaoFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.admin.dao.DaoException;
// import org.admin.dao.DaoException;



public class UrlDaoImpl implements UrlDao{
	private DaoFactory daoFactory;
	private List<String> listUrlPat = new ArrayList<String>();
	private List<String> listUrlEnseignant = new ArrayList<String>();
	private List<String> listUrl = new ArrayList<String>();
	private boolean isValid;

	public UrlDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
		this.isValid = false;
	}

	public void setIsValid(boolean var){
		this.isValid = var;
	}

	public boolean getIsValid(){
		return this.isValid;
	}

	public List<String> listAll() throws DaoException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT url FROM \" liste_url\" ";
			preparedStatement = connexion.prepareStatement(query);
			resultat = preparedStatement.executeQuery();
			while(resultat.next()){
				this.listUrl.add(resultat.getString("url"));
			}
			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch (SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return this.listUrl;
	}

	public List<String> listAllPatUrl() throws DaoException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT url FROM \" liste_url\" WHERE pat=true ";
			preparedStatement = connexion.prepareStatement(query);
			resultat = preparedStatement.executeQuery();
			while(resultat.next()){
				this.listUrlPat.add(resultat.getString("url"));
			}
			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch (SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return this.listUrlPat;
	}

	public List<String> listAllEnseignantUrl() throws DaoException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT url FROM \" liste_url\" WHERE enseignant=true";
			preparedStatement = connexion.prepareStatement(query);
			resultat = preparedStatement.executeQuery();
			while(resultat.next()){
				this.listUrlEnseignant.add(resultat.getString("url"));
			}
			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch (SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return this.listUrlEnseignant;
	}
	public void ajouter(String url, boolean pat, boolean enseignant, boolean doyen){

	}

	public boolean validAccess(HttpSession session, String url) throws DaoException{
		String user = String.valueOf(session.getAttribute("status"));
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT* FROM liste_url WHERE nom_url = ?";
			System.out.println("user query: "+query);
			System.out.println("url req: "+url);
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, url);
			resultat = preparedStatement.executeQuery();
			resultat.next();
			System.out.println("res: "+resultat);
			// setIsValid(resultat.getBoolean(user));
			this.isValid = resultat.getBoolean(user);
			System.out.println("resultat booléen : "+resultat.getBoolean(user));

			// connexion.close();
			// preparedStatement.close();
			// resultat.close();
			// if(resultat.getBoolean(user) == true);
		}catch (SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}

		// return getIsValid();
		return this.isValid;
	}

}