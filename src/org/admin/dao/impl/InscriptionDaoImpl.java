package org.admin.dao.impl;

import java.sql.*;

import org.admin.dao.InscriptionDao;
import org.admin.dao.DaoFactory;
import org.admin.dao.DaoException;

import java.util.List;
import java.util.ArrayList;

import org.admin.beans.Etudiant;
import org.admin.beans.Utilisateur;
import org.admin.beans.Inscription;
import org.admin.beans.EtudiantPortail;
import org.admin.beans.CritereRecherche;


public class InscriptionDaoImpl implements InscriptionDao
{
	private DaoFactory daoFactory;
	
	
	
	public InscriptionDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory=daoFactory;
	}


	public void addInscription(Inscription inscription)throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		//ResultSet resultat = null;
		String query="";
		Etudiant etudiantFound=null;
		//=request.getSession()
		try{
			connexion = daoFactory.getConnection();
			
			query="Insert into t_inscription_portail  (id_etudiant,portail,confirmedbyuserid,obs,annee_univ,num_recu)"+
			" VALUES(?,?,?,?,?,?)";
			
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, inscription.getId_etudiant());
			preparedStatement.setString(2,inscription.getPortail());
			preparedStatement.setInt(3,inscription.getConfirmedByUserId());
			preparedStatement.setString(4,inscription.getObs());
			preparedStatement.setString(5,inscription.getAnnee_univ());
			preparedStatement.setString(6,inscription.getNum_recu());
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connexion.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<EtudiantPortail> getAllInscriptionOnPortail()throws DaoException
	{
		List<EtudiantPortail> etudiants = new ArrayList<EtudiantPortail>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;	       
		
		
		String query=null;
		
		
		
		query= "SELECT *  FROM  \"Vue_confirmation_inscription\" order by nom,prenoms";

		
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			resultat = preparedStatement.executeQuery();
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query);
			
			while(resultat.next())
			{
				int id_inscription=resultat.getInt("id_inscription");
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
				String ddn=""+resultat.getString("date_naissance");
				String portail=resultat.getString("portail");
				String nom_mere=resultat.getString("nom_mere");
				String nom_pere=resultat.getString("nom_pere");
				String adresse=resultat.getString("adresse");
				String tel=resultat.getString("tel");
				ddn=ddn.replace("VERS ","01/01/");
				String obs=resultat.getString("obs");
				String num_recu=resultat.getString("num_recu");
                
				EtudiantPortail etudiant = new EtudiantPortail();
										
				etudiant.setId_inscription(id_inscription);
				etudiant.setNom(nom);
				etudiant.setPrenoms(prenoms);
				etudiant.setDdn(ddn);
				etudiant.setPortail(portail);
				etudiant.setObs(obs);
				etudiant.setNom_mere(nom_mere);
				etudiant.setNom_pere(nom_pere);
				etudiant.setAdresse(adresse);
				etudiant.setTel(tel);
                etudiant.setNum_recu(num_recu);
			    etudiants.add(etudiant);
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
		
		return etudiants;
	}
    
    public List<EtudiantPortail> getAllFinalInscriptionOnPortail()throws DaoException
	{
		List<EtudiantPortail> etudiants = new ArrayList<EtudiantPortail>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;	       
		
		
		String query=null;
		
		
		
		query= "SELECT *  FROM  \"Vue_inscription_finale\" order by nom,prenoms";

		
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			resultat = preparedStatement.executeQuery();
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query);
			
			while(resultat.next())
			{
				int id_inscription=resultat.getInt("id_inscription");
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
				String ddn=""+resultat.getString("date_naissance");
				String portail=resultat.getString("portail");
				String nom_mere=resultat.getString("nom_mere");
				String nom_pere=resultat.getString("nom_pere");
				String adresse=resultat.getString("adresse");
				String tel=resultat.getString("tel");
				ddn=ddn.replace("VERS ","01/01/");
				String obs=resultat.getString("obs");
				String num_recu=resultat.getString("num_recu");
				boolean isSubscribed=resultat.getBoolean("isSubscribed");
                
				EtudiantPortail etudiant = new EtudiantPortail();
										
				etudiant.setId_inscription(id_inscription);
				etudiant.setNom(nom);
				etudiant.setPrenoms(prenoms);
				etudiant.setDdn(ddn);
				etudiant.setPortail(portail);
				etudiant.setObs(obs);
				etudiant.setNom_mere(nom_mere);
				etudiant.setNom_pere(nom_pere);
				etudiant.setAdresse(adresse);
				etudiant.setTel(tel);
                etudiant.setNum_recu(num_recu);
                etudiant.setIsSubsribed(isSubscribed);
                
			    etudiants.add(etudiant);
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
		
		return etudiants;
	}
	
	public List<EtudiantPortail> getAllInscriptionOnPortail(CritereRecherche critere)throws DaoException
	{
		List<EtudiantPortail> etudiants = new ArrayList<EtudiantPortail>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;	       
		
		
		//String query=null;
		
		
		String query=null;
		try{
			connexion = daoFactory.getConnection();
			if(critere.getAnneeU()=="Année-Universitaire")
			{
				if(critere.getChoix().equals(""))
				{
					query= "SELECT *  FROM  \"Vue_confirmation_inscription\" order by nom,prenoms";
					preparedStatement = connexion.prepareStatement(query);
				}
				else
				{
					query= "SELECT *  FROM  \"Vue_confirmation_inscription\" where choix = ? order by nom,prenoms ";
					preparedStatement = connexion.prepareStatement(query);
					preparedStatement.setString(1, critere.getChoix());
					// query= "SELECT *  FROM  \"Vue_confirmation_inscription\" where choix ='"+critere.getChoix()+"' order by nom,prenoms ";
				}
				
			}
			else
			{
				if(critere.getChoix().equals(""))
				{
					query= "SELECT *  FROM  \"Vue_confirmation_inscription\" where  annee_univ = ? order by nom,prenoms ";
					preparedStatement = connexion.prepareStatement(query);
					preparedStatement.setString(1, critere.getAnneeU());
					// query= "SELECT *  FROM  \"Vue_confirmation_inscription\" where  annee_univ = '"+critere.getAnneeU()+"' order by nom,prenoms ";
				}
				else
				{
					query= "SELECT *  FROM  \"Vue_confirmation_inscription\" where  annee_univ = ? AND choix = ? order by nom,prenoms ";
					preparedStatement = connexion.prepareStatement(query);
					preparedStatement.setString(1, critere.getAnneeU());
					preparedStatement.setString(2, critere.getChoix());
					// query= "SELECT *  FROM  \"Vue_confirmation_inscription\" where  annee_univ = '"+critere.getAnneeU()+"' AND choix ='"+critere.getChoix()+"' order by nom,prenoms ";
				}
				
			}
			
			if(!critere.getSearch().equals(""))
			{
				query= "SELECT *  FROM  \"Vue_confirmation_inscription\" where  (nom like ? OR prenoms like ?) ORDER BY nom,prenoms";
				preparedStatement = connexion.prepareStatement(query);
				String var = critere.getSearch();
				var = var.replace("'","\'");
				preparedStatement.setString(1, "%" + var.toUpperCase() + "%");
				preparedStatement.setString(2, "%" + var.toUpperCase() + "%");

					// query= "SELECT *  FROM  \"Vue_confirmation_inscription\" where  (nom like '%"+critere.getSearch().toUpperCase()+"%' OR prenoms like '%"+critere.getSearch().toUpperCase()+"%') ORDER BY nom,prenoms";

			}
			
			//query= "SELECT *  FROM  \"Vue_confirmation_inscription\" order by nom,prenoms";
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query);
			resultat = preparedStatement.executeQuery();
			while(resultat.next())
			{
				
				int id_inscription=resultat.getInt("id_inscription");
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
				String ddn=""+resultat.getString("date_naissance");
				String portail=resultat.getString("portail");
				String nom_mere=resultat.getString("nom_mere");
				String nom_pere=resultat.getString("nom_pere");
				String adresse=resultat.getString("adresse");
				String tel=resultat.getString("tel");
				ddn=ddn.replace("VERS ","01/01/");
				String obs=resultat.getString("obs");
                String num_recu=resultat.getString("num_recu");
				
				EtudiantPortail etudiant = new EtudiantPortail();
										
				etudiant.setId_inscription(id_inscription);
				etudiant.setNom(nom);
				etudiant.setPrenoms(prenoms);
				etudiant.setDdn(ddn);
				etudiant.setPortail(portail);
				etudiant.setObs(obs);
				etudiant.setNom_mere(nom_mere);
				etudiant.setNom_pere(nom_pere);
				etudiant.setAdresse(adresse);
				etudiant.setTel(tel);
                etudiant.setNum_recu(num_recu);
                etudiants.add(etudiant);
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
		
		return etudiants;
	}
	
	
	public int countEtudiantConfirmedPerPortail(String choix) throws DaoException
	{
		int countRecords=0;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;
		//String query="SELECT count(*) as count *from t_import_saisie where \"isSelected\"=true";
		String query;
		try{
			connexion = daoFactory.getConnection();
			// statement = connexion.createStatement();
			// query="SELECT count(distinct (nom,prenoms,date_naissance,obs,portail)) as count from \"Vue_confirmation_inscription\" where  portail like '%"+choix+"%'";
			//System.out.println(query);
			query="SELECT count(distinct (nom,prenoms,date_naissance,obs,portail)) as count from \"Vue_confirmation_inscription\" where  portail like ? ";
			preparedStatement = connexion.prepareStatement(query);
			String choice = choix;
			choice = choice.replace("'", "\'");
			preparedStatement.setString(1, "%" + choice + "%");
			resultat = preparedStatement.executeQuery();
			// resultat = statement.executeQuery(query);
			resultat.next();
			countRecords=resultat.getInt("count");
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
		//System.out.println(countRecords);
		return countRecords;
		
	}
	public int countEtudiantSubsribedPerPortail(String choix) throws DaoException
	{
		int countRecords=0;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;
		//String query="SELECT count(*) as count *from t_import_saisie where \"isSelected\"=true";
		String query;
		try{
			connexion = daoFactory.getConnection();
			// statement = connexion.createStatement();
			// query="SELECT count(distinct (nom,prenoms,date_naissance,obs,portail)) as count from \"Vue_inscription_finale\" where  portail like '%"+choix+"%'";
			//System.out.println(query);
			query="SELECT count(distinct (nom,prenoms,date_naissance,obs,portail)) as count from \"Vue_inscription_finale\" where  portail like ?";
			preparedStatement = connexion.prepareStatement(query);
			String choice = choix;
			choice = choice.replace("'", "\'");
			preparedStatement.setString(1, "%" + choice + "%");
			resultat = preparedStatement.executeQuery();
			// resultat = statement.executeQuery(query);
			
			resultat.next();
			countRecords=resultat.getInt("count");
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
		//System.out.println(countRecords);
		return countRecords;
	}
	

	
}


