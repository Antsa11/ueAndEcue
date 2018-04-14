package org.admin.dao.stat.impl;

import java.sql.*;
import org.admin.dao.stat.StatPreinscriptionDao;
import org.admin.dao.DaoException;
import org.admin.dao.DaoFactory;

import org.admin.beans.stat.StatPreinscription;

public class StatPreinscriptionDaoImpl implements StatPreinscriptionDao
{
	
	private DaoFactory daoFactory;
	
	public StatPreinscriptionDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory=daoFactory;
	}
	
	private int countRecordsParPortail(String portail) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;
		int countRecords=0;
		String query="SELECT count(distinct (nom_prenom,choix)) as count from \"Vue_preinscrits\"";
 			       
		try{
			connexion = daoFactory.getConnection();
			// statement = connexion.createStatement();
			// query="SELECT count(distinct (nom_prenom,choix)) as count from \"Vue_preinscrits\" where  choix='"+portail+"'";
			// resultat = statement.executeQuery(query);
			query="SELECT count(distinct (nom_prenom,choix)) as count from \"Vue_preinscrits\" where  choix=?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, portail);
			resultat = preparedStatement.executeQuery();
			resultat.next();
			countRecords=resultat.getInt("count");

			connexion.close();
			preparedStatement.close();
			resultat.close();
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return countRecords;
	}
	
	private int countRecordsParSerie(String serie) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;
		int countRecords=0;
		String query="SELECT count(distinct (nom_prenom,choix)) as count from \"Vue_preinscrits\"";
 			       
		try{
			connexion = daoFactory.getConnection();
			// statement = connexion.createStatement();
			// query="SELECT count(distinct (nom_prenom,choix)) as count from \"Vue_preinscrits\" where  serie='"+serie+"'";
			// resultat = statement.executeQuery(query);
			query="SELECT count(distinct (nom_prenom,choix)) as count from \"Vue_preinscrits\" where  serie= ? ";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, serie);
			resultat = preparedStatement.executeQuery();
			resultat.next();
			countRecords=resultat.getInt("count");

			connexion.close();
			preparedStatement.close();
			resultat.close();
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return countRecords;
	}
	public StatPreinscription getStatPreinscription() throws DaoException
	{
		StatPreinscription stat=new StatPreinscription();
		stat.setPc(countRecordsParPortail("PC"));
		stat.setMi(countRecordsParPortail("MI"));
		stat.setSvt(countRecordsParPortail("SVT"));
		
		stat.setC(countRecordsParSerie("C"));
		stat.setD(countRecordsParSerie("D"));
		stat.setS(countRecordsParSerie("S"));
		stat.setTgi(countRecordsParSerie("TGI"));
		
		return stat;
	}
}
