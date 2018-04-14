package org.admin.dao.impl;

import java.sql.*;

import org.admin.dao.DerogationDao;
import org.admin.dao.DaoFactory;
import org.admin.dao.DaoException;

import java.util.List;
import java.util.ArrayList;

import org.admin.beans.Derogation;



public class DerogationDaoImpl implements DerogationDao
{
	private DaoFactory daoFactory;



	public DerogationDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory=daoFactory;
	}




	public List<Derogation> getAllDerogation()throws DaoException
	{
		List<Derogation> derogations = new ArrayList<Derogation>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;


		String query=null;



		query= "SELECT *  FROM  \"Vue_derogation\" ";



		try{
			connexion = daoFactory.getConnection();
			// statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement(query);
			resultat = preparedStatement.executeQuery();
			// resultat = statement.executeQuery(query);

			while(resultat.next())
			{

				int id_derogation=resultat.getInt("id_derogation");
				String acceptedOn=resultat.getString("acceptedOn");
				String userName=resultat.getString("userName");
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
				String choix=resultat.getString("choix");
		        String obs=	resultat.getString("obs");
				Derogation derogation=new Derogation();
				derogation.setId_derogation(id_derogation);
				derogation.setAcceptedOn(acceptedOn);
				derogation.setUserName(userName.trim().toUpperCase());
				derogation.setNom(nom);
				derogation.setPrenoms(prenoms);
				derogation.setChoix(choix);
                derogation.setObs(obs);
				derogations.add(derogation);
			}

			resultat.close();
			preparedStatement.close();
			// statement.close();
			connexion.close();

		}
		catch (SQLException e)
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}

		return derogations;
	}
    public List<Derogation> getAllDerogationPerPortail(String choix)throws DaoException
	{
		List<Derogation> derogations = new ArrayList<Derogation>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;


		String query=null;



		// query= "SELECT *  FROM  \"Vue_derogation\" where choix='"+choix
		query= "SELECT *  FROM  \"Vue_derogation\" where choix= ? ";


		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, choix);
			System.out.println(preparedStatement);
			resultat = preparedStatement.executeQuery();
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query);

			while(resultat.next())
			{

				int id_derogation=resultat.getInt("id_derogation");
				String acceptedOn=resultat.getString("acceptedOn");
				String userName=resultat.getString("userName");
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
                String obs=	resultat.getString("obs");
				//String choix=resultat.getString("choix");

				Derogation derogation=new Derogation();
				derogation.setId_derogation(id_derogation);
				derogation.setAcceptedOn(acceptedOn);
				derogation.setUserName(userName.trim().toUpperCase());
				derogation.setNom(nom);
				derogation.setPrenoms(prenoms);
				derogation.setChoix(choix);
                derogation.setObs(obs);
				derogations.add(derogation);
			}
				System.out.println("Recherche dans la liste de dérogation quand choix seulement est rempli");
				System.out.println("-----------------------------------------------------------------");

			resultat.close();
			preparedStatement.close();
			// statement.close();
			connexion.close();

		}
		catch (SQLException e)
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}

		return derogations;
	}
    public int countEtudiantDerogatedPerPortail(String choix) throws DaoException
	{
		int countRecords=0;
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		//String query="SELECT count(*) as count *from t_import_saisie where \"isSelected\"=true";
		String query;
		try{
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			query="SELECT count(distinct (nom,prenoms,choix)) as count from \"Vue_derogation\" where  choix like '%"+choix+"%'";
			//System.out.println(query);
			resultat = statement.executeQuery(query);
			resultat.next();
			countRecords=resultat.getInt("count");
			resultat.close();
			statement.close();
			connexion.close();
		}
		catch (SQLException e)
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		//System.out.println(countRecords);
		return countRecords;

	}

    public List<Derogation> getAllDerogationPerPortail(String choix, String search) throws DaoException{
    	List<Derogation> candidatDerogation = new ArrayList<Derogation>();
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultat = null;
    	search = search.replace("'","\'");

    	String query = "SELECT * FROM \"Vue_derogation\" WHERE choix = ? AND (nom like ? OR prenoms like ? )";
    	try{
    		connexion = daoFactory.getConnection();
    		preparedStatement = connexion.prepareStatement(query);
    		preparedStatement.setString(1, choix);
    		preparedStatement.setString(2, "%" + search.toUpperCase() + "%");
    		preparedStatement.setString(3, "%" + search.toUpperCase() + "%");
    		System.out.println(preparedStatement);
    		resultat = preparedStatement.executeQuery();
    		while(resultat.next()){
    			Derogation candidat = new Derogation();
    			candidat.setNom(resultat.getString("nom"));
    			candidat.setPrenoms(resultat.getString("prenoms"));
    			candidat.setUserName(resultat.getString("username"));
    			candidat.setAcceptedOn(resultat.getString("acceptedOn"));
    			candidat.setChoix(resultat.getString("choix"));
    			candidatDerogation.add(candidat);
    			// System.out.println(resultat.getString("nom"));
    		}
    			System.out.println("Recherche dans la liste de dérogation si choix et input de recherche rempli");
    			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    	connexion.close();
    	preparedStatement.close();
    	resultat.close();
    	}catch (SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}

		return candidatDerogation;
    }

	public List<Derogation> getAllDerogationBySearch(String search)throws DaoException{


		List<Derogation> derogations = new ArrayList<Derogation>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		String query= "SELECT *  FROM  \"Vue_derogation\" where nom like ? OR prenoms like ?";


		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, "%" + search.toUpperCase() + "%");
			preparedStatement.setString(2, "%" + search.toUpperCase() + "%");
			System.out.println(preparedStatement);
			resultat = preparedStatement.executeQuery();
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query);

			while(resultat.next())
			{

				Derogation candidat = new Derogation();
    			candidat.setNom(resultat.getString("nom"));
    			candidat.setPrenoms(resultat.getString("prenoms"));
    			candidat.setUserName(resultat.getString("username"));
    			candidat.setAcceptedOn(resultat.getString("acceptedOn"));
    			candidat.setChoix(resultat.getString("choix"));
    			derogations.add(candidat);
			}
    			System.out.println("Recherche dans la liste déérogation quand search seulement est rempli");
    			System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");

			resultat.close();
			preparedStatement.close();
			connexion.close();

		}catch (SQLException e){
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}

		return derogations;


	}



}
