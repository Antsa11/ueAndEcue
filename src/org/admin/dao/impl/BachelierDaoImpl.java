package org.admin.dao.impl;

import java.sql.*;
import org.admin.dao.BachelierDao;
import java.util.List;
import java.util.ArrayList;
import org.admin.beans.Bachelier;
import org.admin.beans.CritereBac;

import org.admin.dao.DaoFactory;
import org.admin.dao.DaoException;

public class BachelierDaoImpl implements BachelierDao
{
	private DaoFactory daoFactory;
	private int noOfRecords;
	
	public BachelierDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	public void preinscrire(Bachelier bachelier)
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO utilisateur (login,pwd) VALUES(?,?);");
			
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	public List<Bachelier> lister() throws DaoException
	{
		List<Bachelier> bacheliers = new ArrayList<Bachelier>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		ResultSet resultat = null;
 		/*Etudiant test= new Etudiant();
	        test.setNum_bacc(123456);
       	        test.setNom_prenom("koto be");
		etudiants.add(test);*/	       
		try{
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT *  FROM t_candidat;");
			resultat = preparedStatement.executeQuery();
			// resultat = statement.executeQuery("SELECT *  FROM t_candidat;");
			
			while(resultat.next())
			{
				int id_candidat=resultat.getInt("id_candidat");
				int num_bacc=resultat.getInt("num_bacc");
				String nom_prenom=resultat.getString("nom_prenom");
				String serie=resultat.getString("serie");
				String centre=resultat.getString("centre");
				float math=resultat.getFloat("math");
				float pc=resultat.getFloat("pc");
				float sn=resultat.getFloat("sn");
				float moyenne=resultat.getFloat("moyenne");
				String mention=resultat.getString("mention");
				String date_naissance=resultat.getString("date_naissance");
				//String province=resultat.getString("province");
				int annee=resultat.getInt("annee");
				//int id_portail=resultat.getInt("id_portail");
				String cree_le=resultat.getString("cree_le");
				//int id_status=resultat.getInt("id_status");
				String importer_par=resultat.getString("creer_par");
				//String importer_le=resultat.getString("importer_le");
				String num_fact=resultat.getString("num_fact");

				Bachelier bachelier = new Bachelier();
				bachelier.setId_candidat(id_candidat);
				bachelier.setNum_bacc(num_bacc);
				bachelier.setNom_prenom(nom_prenom);
				bachelier.setSerie(serie);
				bachelier.setCentre(centre);
				bachelier.setMath(math);
				bachelier.setPc(pc);
				bachelier.setSn(sn);
				bachelier.setMoyenne(moyenne);
				bachelier.setMention(mention);
				bachelier.setDate_naissance(date_naissance);
				//bachelier.setProvince(province);
				bachelier.setAnnee(annee);
				//bachelier.setId_portail(id_portail);
				bachelier.setCree_le(cree_le);
				//bachelier.setId_status(id_status);
				bachelier.setImporter_par(importer_par);
				//bachelier.setImporter_le(importer_le);
				bachelier.setNum_fact(num_fact);
				
				bacheliers.add(bachelier);
				
			}
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return bacheliers;
	}
	public List<Bachelier> search(CritereBac critere)throws DaoException
	{
		List<Bachelier> bacheliers = new ArrayList<Bachelier>();
		Connection connexion = null;
		//Statement statement = null;
		PreparedStatement preparedStatement = null;	    
		ResultSet resultat = null;
 		/*Etudiant test= new Etudiant();
	        test.setNum_bacc(123456);
       	        test.setNom_prenom("koto be");
		etudiants.add(test);*/	       
		try{
			connexion = daoFactory.getConnection();
			//statement = connexion.createStatement();
			String query="SELECT *  FROM t_candidat where nom_prenom like '%"+critere.getNom_prenom()+"%' OR mention=? order by nom_prenom";
			preparedStatement=connexion.prepareStatement(query);
			preparedStatement.setString(1,critere.getNom_prenom());
			//preparedStatement.setString(2,critere.getNom_prenom());
			//preparedStatement.setString(2,critere.getSerie());
			//preparedStatement.setInt(3,critere.getAnnee());
			//preparedStatement.setString(4,critere.getMention());
			//preparedStatement.setString(5,critere.getCentre());
					
			
			resultat = preparedStatement.executeQuery();
			
			while(resultat.next())
			{
				int id_candidat=resultat.getInt("id_candidat");
				int num_bacc=resultat.getInt("num_bacc");
				String nom_prenom=resultat.getString("nom_prenom");
				String serie=resultat.getString("serie");
				String centre=resultat.getString("centre");
				float math=resultat.getFloat("math");
				float pc=resultat.getFloat("pc");
				float sn=resultat.getFloat("sn");
				float moyenne=resultat.getFloat("moyenne");
				String mention=resultat.getString("mention");
				String date_naissance=resultat.getString("date_naissance");
				//String province=resultat.getString("province");
				int annee=resultat.getInt("annee");
				//int id_portail=resultat.getInt("id_portail");
				String cree_le=resultat.getString("cree_le");
				//int id_status=resultat.getInt("id_status");
				String importer_par=resultat.getString("creer_par");
				//String importer_le=resultat.getString("importer_le");
				String num_fact=resultat.getString("num_fact");

				Bachelier bachelier = new Bachelier();
				bachelier.setId_candidat(id_candidat);
				bachelier.setNum_bacc(num_bacc);
				bachelier.setNom_prenom(nom_prenom);
				bachelier.setSerie(serie);
				bachelier.setCentre(centre);
				bachelier.setMath(math);
				bachelier.setPc(pc);
				bachelier.setSn(sn);
				bachelier.setMoyenne(moyenne);
				bachelier.setMention(mention);
				bachelier.setDate_naissance(date_naissance);
				//bachelier.setProvince(province);
				bachelier.setAnnee(annee);
				//bachelier.setId_portail(id_portail);
				bachelier.setCree_le(cree_le);
				//bachelier.setId_status(id_status);
				bachelier.setImporter_par(importer_par);
				//bachelier.setImporter_le(importer_le);
				bachelier.setNum_fact(num_fact);
				
				bacheliers.add(bachelier);
				
			}
			this.noOfRecords=this.lister().size();
			resultat.close();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return bacheliers;
	}
	
	public List<Bachelier> afficher(int offset, int noOfRecords) throws DaoException
	{
		List<Bachelier> bacheliers = new ArrayList<Bachelier>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
 		/*Etudiant test= new Etudiant();
	        test.setNum_bacc(123456);
       	        test.setNom_prenom("koto be");
		etudiants.add(test);*/	       
		try{
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT *  FROM t_candidat limit "
                 + noOfRecords + " OFFSET " + offset);
			
			while(resultat.next())
			{
				int id_candidat=resultat.getInt("id_candidat");
				int num_bacc=resultat.getInt("num_bacc");
				String nom_prenom=resultat.getString("nom_prenom");
				String serie=resultat.getString("serie");
				String centre=resultat.getString("centre");
				float math=resultat.getFloat("math");
				float pc=resultat.getFloat("pc");
				float sn=resultat.getFloat("sn");
				float moyenne=resultat.getFloat("moyenne");
				String mention=resultat.getString("mention");
				String date_naissance=resultat.getString("date_naissance");
				//String province=resultat.getString("province");
				int annee=resultat.getInt("annee");
				//int id_portail=resultat.getInt("id_portail");
				String cree_le=resultat.getString("cree_le");
				//int id_status=resultat.getInt("id_status");
				String importer_par=resultat.getString("creer_par");
				//String importer_le=resultat.getString("creer_le");
				String num_fact=resultat.getString("num_fact");

				Bachelier bachelier = new Bachelier();
				bachelier.setId_candidat(id_candidat);
				bachelier.setNum_bacc(num_bacc);
				bachelier.setNom_prenom(nom_prenom);
				bachelier.setSerie(serie);
				bachelier.setCentre(centre);
				bachelier.setMath(math);
				bachelier.setPc(pc);
				bachelier.setSn(sn);
				bachelier.setMoyenne(moyenne);
				bachelier.setMention(mention);
				bachelier.setDate_naissance(date_naissance);
				//bachelier.setProvince(province);
				bachelier.setAnnee(annee);
				//bachelier.setId_portail(id_portail);
				bachelier.setCree_le(cree_le);
				//bachelier.setId_status(id_status);
				bachelier.setImporter_par(importer_par);
				//bachelier.setImporter_le(importer_le);
				bachelier.setNum_fact(num_fact);
				
				bacheliers.add(bachelier);
				//statement.close();
				//connexion.close();
			}
			this.noOfRecords=this.lister().size();
			resultat.close();
			statement.close();
			connexion.close();
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return bacheliers;
	}
	public int getNoOfRecords() {
        return noOfRecords;
    }
	

}
