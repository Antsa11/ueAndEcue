package org.admin.dao.impl;


import org.admin.dao.DaoFactory;
import org.admin.dao.EtudiantDao;
import org.admin.dao.InscriptionDao;
import org.admin.dao.UtilisateurDao;

import org.admin.beans.Etudiant;
import org.admin.beans.EtudiantPortail;
import org.admin.beans.Inscription;

import org.admin.beans.Utilisateur;


import org.admin.dao.DaoException;
import java.sql.*;


public class EtudiantDaoImpl implements EtudiantDao
{
	private DaoFactory daoFactory;
	private InscriptionDao inscriptionDao;
	private UtilisateurDao utilisateurDao;
	
	public EtudiantDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
		inscriptionDao=daoFactory.getInscriptionDao();
		utilisateurDao=daoFactory.getUtilisateurDao();
	}
	
	public void confirmerInscription(Etudiant etudiant,String annee_univ, Utilisateur userConnected, String num_recu) throws DaoException
	{
		Etudiant etudiantDetails=this.getDetailsFrom(etudiant);
		
		this.insertEtudiant(etudiantDetails);
		
		//System.out.println(userConnected);
		
		//System.out.println("Last record="+this.getLastIdEtudiant());
		Inscription inscription=new Inscription();
		inscription.setId_etudiant(this.getLastIdEtudiant());
		inscription.setAnnee_univ(annee_univ);
		inscription.setConfirmedByUserId(utilisateurDao.getUserId(userConnected));
		inscription.setObs(etudiant.getObs());
		inscription.setPortail(etudiant.getChoix());
		inscription.setNum_recu(num_recu);
		inscriptionDao.addInscription(inscription);
		
		this.confirmInfoSaisieEtudiant(etudiant);
		
	}
    
    public void validerInscription(EtudiantPortail etudiant,String annee_univ, Utilisateur userConnected, String num_recu) throws DaoException
	{
		//Etudiant etudiantDetails=this.getDetailsFrom(etudiant);
		
		//this.insertEtudiant(etudiantDetails);
		
		//System.out.println(userConnected);
		
		//System.out.println("Last record="+this.getLastIdEtudiant());
		
        /*
        Inscription inscription=new Inscription();
		inscription.setId_etudiant(this.getLastIdEtudiant());
		inscription.setAnnee_univ(annee_univ);
		inscription.setConfirmedByUserId(utilisateurDao.getUserId(userConnected));
		inscription.setObs(etudiant.getObs());
		inscription.setPortail(etudiant.getChoix());
		inscription.setNum_recu(num_recu);
		inscriptionDao.addInscription(inscription);
		
		this.confirmInfoSaisieEtudiant(etudiant);
		*/
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		String query="";
		try{
			connexion = daoFactory.getConnection();
			
			query="update t_inscription_portail SET \"isSubscribed\"=true where id_inscription=?";
			
			//System.out.println(query+" Id="+candidat.getId_record());
			preparedStatement = connexion.prepareStatement(query);
			
			preparedStatement.setInt(1,etudiant.getId_inscription());
			preparedStatement.executeUpdate();
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
		
	
	private int getLastIdEtudiant()
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		String query="";
		int id_record=0;
		Etudiant etudiantFound=null;
		try{
			connexion = daoFactory.getConnection();
			
			query="select * from t_etudiant";
			
			preparedStatement = connexion.prepareStatement(query);
			resultat=preparedStatement.executeQuery();
			while(resultat.next())
			{
				id_record=resultat.getInt("id_etudiant");
			}
			resultat.close();
			preparedStatement.close();
			connexion.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return id_record;
	}
	
	public void insertEtudiant(Etudiant etudiant) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		//ResultSet resultat = null;
		String query="";
		Etudiant etudiantFound=null;
		try{
			connexion = daoFactory.getConnection();
			
			query="Insert into t_etudiant  (id_selection,nom,prenoms,date_naissance,lieu_naissance, nom_pere,nom_mere,adresse,tel,serie_bac,annee_bac)"+
			" VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1,etudiant.getId_record());
			preparedStatement.setString(2,etudiant.getNom());
			preparedStatement.setString(3,etudiant.getPrenoms());
			preparedStatement.setString(4,etudiant.getDdn());
			preparedStatement.setString(5,etudiant.getLieu_naissance());
			preparedStatement.setString(6,etudiant.getNom_pere());
			preparedStatement.setString(7,etudiant.getNom_mere());
			preparedStatement.setString(8,etudiant.getAdresse());
			preparedStatement.setString(9,etudiant.getTel());
			preparedStatement.setString(10,etudiant.getSerie());
			preparedStatement.setInt(11,etudiant.getAnnee());
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connexion.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		//return null;
	}
	public void confirmInfoSaisieEtudiant(Etudiant etudiant) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		String query="";
		try{
			connexion = daoFactory.getConnection();
			
			query="update t_import_saisie SET \"isConfirmed\"=true where id_record=?";
			
			//System.out.println(query+" Id="+candidat.getId_record());
			preparedStatement = connexion.prepareStatement(query);
			
			preparedStatement.setInt(1,etudiant.getId_record());
			preparedStatement.executeUpdate();
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public Etudiant getDetailsFrom(Etudiant etudiant) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		String query="";
		Etudiant etudiantFound=null;
		try{
			connexion = daoFactory.getConnection();
			
			query="SELECT * from \"Vue_resultat_selection\" where id_record=?";
			
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1,etudiant.getId_record());
			//preparedStatement.setInt(2,id_record);
			//preparedStatement.executeUpdate();
			resultat=preparedStatement.executeQuery();
			resultat.next();
			if(resultat.getInt("id_record")>0)
			{
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
				String ddn=resultat.getString ("ddn");
				String adresse=resultat.getString ("adresse");
				String nom_pere=resultat.getString("nom_pere");
				String nom_mere=resultat.getString("nom_mere");
				String lieu_naissance=resultat.getString("lieu_naissance");
				String serie=resultat.getString("serie");
				String tel=resultat.getString("tel");
				String choix=resultat.getString("choix");
				int annee=resultat.getInt("annee");
				
				
				etudiantFound = new Etudiant();
				etudiantFound.setId_record(etudiant.getId_record());
				etudiantFound.setNom(nom);
				etudiantFound.setPrenoms(prenoms);
				etudiantFound.setDdn(ddn);
				etudiantFound.setAdresse(adresse);
				etudiantFound.setLieu_naissance(lieu_naissance);
				etudiantFound.setSerie(serie);
				etudiantFound.setTel(tel);
				etudiantFound.setNom_mere(nom_mere);
				etudiantFound.setNom_pere(nom_pere);
				etudiantFound.setAnnee(annee);
				etudiantFound.setChoix(choix);
				
			}
			resultat.close();
			preparedStatement.close();
			connexion.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return etudiantFound;
	}
	

}
