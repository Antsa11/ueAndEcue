package org.admin.dao.impl;

import java.sql.*;

import java.util.List;
import java.util.ArrayList;

import org.admin.beans.Candidat;
import org.admin.beans.Etudiant;
import org.admin.beans.CandidatAnomalie;
import org.admin.beans.Critere;
import org.admin.beans.CritereAnomalie;
import org.admin.beans.Bachelier;
import org.admin.beans.CritereRecherche;
import org.admin.beans.Utilisateur;


import org.admin.dao.DaoFactory;
import org.admin.dao.CandidatDao;
import org.admin.dao.CritereDao;
import org.admin.dao.UtilisateurDao;


import org.admin.dao.DaoException;

public class CandidatDaoImpl implements CandidatDao
{
	private DaoFactory daoFactory;
	private CritereDao critereDAO;
	private UtilisateurDao utilisateurDao;
	
	private int noOfRecords;
	
	public CandidatDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	public List<Etudiant> searchInPreinsrits(String keyword)throws DaoException
	{
		List<Etudiant> candidats = new ArrayList<Etudiant>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		ResultSet resultat = null;
 			       
		try{
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			keyword = keyword.replace("'","\'");
			// String query="SELECT *  FROM \"Vue_preinscrits\" where nom_prenom like '%"+keyword.trim().toUpperCase()+"%'";
			// String query="SELECT *  FROM \"Vue_preinscrits\" where nom_prenom = '" + keyword + "'";
			String query="SELECT *  FROM \"Vue_preinscrits\" where nom_prenom like ? ";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, "%" + keyword.trim().toUpperCase() +"%");
			System.out.println(query);
			resultat = preparedStatement.executeQuery();
			// resultat = statement.executeQuery(query);
			//System.out.println(query);
			while(resultat.next())
			{
				int id_record=resultat.getInt("id_record");
				int n_dossier=resultat.getInt("n_dossier");
				String nom_prenom=resultat.getString("nom_prenom");
				String choix=resultat.getString("choix");
				int annee=resultat.getInt("annee");
				String serie=resultat.getString("serie");
				
				int num_bacc=resultat.getInt("num_bacc");
				//int annee=resultat.getInt("annee");
				float math=resultat.getFloat("math");
				float pc=resultat.getFloat("pc");
				float sn=resultat.getFloat("sn");
				float moyenne=resultat.getFloat("moyenne");
				String mention=resultat.getString("mention");
				String ddn=resultat.getString("ddn");
				String nom_mere=resultat.getString("nom_mere");
				String nom_pere=resultat.getString("nom_pere");
				String adresse=resultat.getString("adresse");
				String lieu_naissance=resultat.getString("lieu_naissance");
				String tel=resultat.getString("tel");
				String centre=resultat.getString("centre");
				boolean isSelected=resultat.getBoolean("isSelected");
				
				
				Etudiant candidat = new Etudiant();
				candidat.setN_dossier(n_dossier);
				candidat.setNom_prenom(nom_prenom);
				candidat.setChoix(choix);
				candidat.setAnnee(annee);
				candidat.setSerie(serie);				
				candidat.setId_record(id_record);			
				
				candidat.setNum_bacc(num_bacc);
				candidat.setMath(math);
				candidat.setPc(pc);
				candidat.setSn(sn);
				candidat.setMoyenne(moyenne);
				candidat.setMention(mention);
				candidat.setDdn(ddn);
				candidat.setNom_pere(nom_pere);
				candidat.setNom_mere(nom_mere);
				candidat.setAdresse(adresse);
				candidat.setLieu_naissance(lieu_naissance);
				candidat.setTel(tel);
				candidat.setCentre(centre);
				candidat.setIsSelected(isSelected);
				candidat.setAnnee(annee);
				
				candidats.add(candidat);
				
			}
			resultat.close();
			statement.close();
			connexion.close();
			
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		//System.out.println("Isa "+candidats.size());
		return candidats;
	}
	public void preinscrire(Candidat candidat)
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO utilisateur (login,pwd) VALUES(?,?);");
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	public void reorienterFrom(int id_critere) throws DaoException
	{
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;	    
		critereDAO=daoFactory.getCritereDao();   
		// String query="SELECT * from \"Vue_preinscrits\" where "+critereDAO.getCritereFromId(id_critere).getCondition();
	//	List<int> id_records=new ArrayList<int>();
		String query="SELECT * from \"Vue_preinscrits\" where ?";
		try{
			String where = critereDAO.getCritereFromId(id_critere).getCondition();
			where = where.replace("'","\'");
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, where);
			resultat = preparedStatement.executeQuery();
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query);
			String updateQuery="";
			while(resultat.next())
			{
				int id_record=resultat.getInt("id_record");
				String choix=resultat.getString("choix");
				//id_records.add(id_record);
				String choix1="PC";
				if(choix.equals("PC"))
				choix1="SVT";
				
				updateQuery="Update t_import_saisie set choix0=?,choix=? where id_record=?"; 
				//System.out.println(updateQuery);
				preparedStatement=connexion.prepareStatement(updateQuery);
				preparedStatement.setString(1,choix);
				preparedStatement.setString(2,choix1);
				preparedStatement.setInt(3,id_record);
				preparedStatement.executeUpdate();
				preparedStatement.close();
				
			}
			resultat.close();
			statement.close();
			connexion.close();
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		//String queryUpdate="SELECT id_record from t_import_saisie where id_record IN ("+query+") ";
		
		//System.out.println("Vita ny réorientation");
	}
	public List<Candidat> getAllListFromCritere (int id_critere) throws DaoException
	{
		List<Candidat> candidats = new ArrayList<Candidat>();
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;	    
		critereDAO=daoFactory.getCritereDao();   
		// String query="SELECT * from \"Vue_preinscrits\" where "+critereDAO.getCritereFromId(id_critere).getCondition();
		String query="SELECT * from \"Vue_preinscrits\" where ?";
		//String query="SELECT * from \"Vue_preinscrits\" WHERE "+critereDAO.getCritereFromId(id_critere).getCondition();
		//System.out.println("SQL= "+query);
		/*String query = query_0 +" limit "+ noOfRecords + " OFFSET " + offset;
		System.out.println("SQL= "+query);*/
		try{
			connexion = daoFactory.getConnection();
			String where = critereDAO.getCritereFromId(id_critere).getCondition();
			where = where.replace("'","\'");
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, where);
			resultat = preparedStatement.executeQuery();
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query);
			
			while(resultat.next())
			{
				int n_dossier=resultat.getInt("n_dossier");
				String nom_prenom=resultat.getString("nom_prenom");
				String choix=resultat.getString("choix");
				String mention=resultat.getString("mention");
				String moyenne=resultat.getString("moyenne");
				//int annee=resultat.getInt("annee");
				String serie=resultat.getString("serie");
				String sn=resultat.getString("sn");
				String ddn=resultat.getString("ddn");
				
				System.out.println("date de naissance:"+ddn);
				Candidat candidat = new Candidat();
				
				

				candidat.setN_dossier(n_dossier);
				candidat.setNom_prenom(nom_prenom);
				candidat.setChoix(choix);
				//candidat.setAnnee(annee);
				candidat.setSerie(serie);
				candidat.setMention(mention);		
				candidat.setSn(Float.parseFloat(sn));
				candidat.setMoyenne(Float.parseFloat(moyenne));
				candidat.setDdn(ddn);					
				candidats.add(candidat);
				
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
		
		return candidats;
	}
	public void validSelectionFromCritere(int id_critere)throws DaoException
	{
		//List<Candidat> candidats = new ArrayList<Candidat>();
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;	    
		critereDAO=daoFactory.getCritereDao();   
		// String query="SELECT * from \"Vue_preinscrits\" where "+critereDAO.getCritereFromId(id_critere).getCondition();
		String query="SELECT * from \"Vue_preinscrits\" where ?";		
		try{
			connexion = daoFactory.getConnection();
			String where = critereDAO.getCritereFromId(id_critere).getCondition();
			where = where.replace("'","\'");
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, where);
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query);
			
			while(resultat.next())
			{
				int id_record=resultat.getInt("id_record");
				int id_vague=critereDAO.getCritereFromId(id_critere).getId_vague();
				selectCandidatOnRecord(id_record,id_vague);	
				
				
			}
		
			resultat.close();
			statement.close();
			connexion.close();
			critereDAO.publishedResulatFrom(id_critere);
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		
	}
	public 	List<Candidat> getAllCandidatSelected () throws DaoException
	{
		List<Candidat> candidats = new ArrayList<Candidat>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;	       
		try{
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("select *from t_import_saisie where \"isSelected\"=true order by choix,nom,prenoms");
			
			while(resultat.next())
			{
				int n_dossier=resultat.getInt("n_dossier");
				int id_record=resultat.getInt("id_record");
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
				boolean isConfirmed=resultat.getBoolean("isConfirmed");
				String choix=resultat.getString("choix");
			
				String serie=resultat.getString("serie_saisie");
				String obs=resultat.getString("obs");
				Candidat candidat = new Candidat();
				
				candidat.setId_record(id_record);
				candidat.setN_dossier(n_dossier);
				candidat.setNom_prenom(nom+" "+prenoms);
				candidat.setChoix(choix);
				candidat.setIsConfirmed(isConfirmed);
				candidat.setSerie(serie);	
				
				candidat.setObs(obs);						
				candidats.add(candidat);
				
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
		
		return candidats;
		
	}
	public 	List<Candidat> getAllCandidatSelected (int id_vague) throws DaoException
	{
		List<Candidat> candidats = new ArrayList<Candidat>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;	       
		try{
			connexion = daoFactory.getConnection();
			String query = "select *from t_import_saisie where \"isSelected\"=true and id_vague= ? order by choix,nom,prenoms";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_vague);
			resultat = preparedStatement.executeQuery();
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery("select *from t_import_saisie where \"isSelected\"=true and id_vague='"+id_vague+"'order by choix,nom,prenoms");
			
			while(resultat.next())
			{
				int n_dossier=resultat.getInt("n_dossier");
				int id_record=resultat.getInt("id_record");
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
				boolean isConfirmed=resultat.getBoolean("isConfirmed");
				String choix=resultat.getString("choix");
			
				String serie=resultat.getString("serie_saisie");
				String obs=resultat.getString("obs");
				Candidat candidat = new Candidat();
				
				candidat.setId_record(id_record);
				candidat.setN_dossier(n_dossier);
				candidat.setNom_prenom(nom+" "+prenoms);
				candidat.setChoix(choix);
				candidat.setIsConfirmed(isConfirmed);
				candidat.setSerie(serie);	
				
				candidat.setObs(obs);						
				candidats.add(candidat);
				
			}
			this.noOfRecords=this.lister().size();
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
		
		return candidats;
		
	}
	public List<Candidat> getAllCandidatConfirmed (String choix) throws DaoException
	{
		List<Candidat> candidats = new ArrayList<Candidat>();
		Connection connexion = null;
		// Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;	       
		
		
		String query=null;
		
		
		
		//query= "SELECT distinct nom_prenom,ddn,obs,choix  FROM  \"Vue_resultat_selection\" where \"isConfirmed\"=true AND choix='"+choix+"' order by nom_prenom";
		query= "SELECT distinct nom,prenoms,date_naissance,obs,portail,num_recu  FROM  \"Vue_confirmation_inscription\" where portail= ? order by nom,prenoms";
		
		
		
		try{
			connexion = daoFactory.getConnection();
			// statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, choix);
			resultat = preparedStatement.executeQuery();
			// resultat = statement.executeQuery(query);
			
			while(resultat.next())
			{
				//int n_dossier=resultat.getInt("n_dossier");
				int id_record=resultat.getInt("id");
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
				String ddn=""+resultat.getString("date_naissance");
				//if(ddn!=null || !ddn.isEmpty())
				ddn=ddn.replace("VERS ","01/01/");
				//String mention=resultat.getString("mention");
				
				String portail=resultat.getString("portail");
				//int annee=resultat.getInt("annee");
				String num_recu=resultat.getString("num_recu");
				String obs=resultat.getString("obs");
				
				//boolean isConfirmed=resultat.getBoolean("isConfirmed");
				
				Candidat candidat = new Candidat();
				
				//candidat.setN_dossier(n_dossier);
				candidat.setId_record(id_record);
				candidat.setNom(nom);
				candidat.setPrenoms(prenoms);
				candidat.setChoix(portail);
				//candidat.setAnnee(annee);
				candidat.setNum_recu(num_recu);
			    candidat.setObs(obs);
				//candidat.setMention(mention);
				//candidat.setId_record(id_record);	
				candidat.setDdn(ddn);	
				//candidat.setIsConfirmed(isConfirmed);
				if(!isDoublon(candidats,candidat))					
				candidats.add(candidat);
				
			}
			this.noOfRecords=this.lister().size();
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
		
		return candidats;
	}
	
	
	public List<Candidat> getAllCandidatSelected (String choix, int id_vague) throws DaoException
	{
		List<Candidat> candidats = new ArrayList<Candidat>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;	       
		
		
		String query=null;
		
		
		try{
			connexion = daoFactory.getConnection();
			if(id_vague==0)
			{
				// query= "SELECT distinct nom_prenom,ddn,obs,choix  FROM  \"Vue_resultat_selection\" where \"isSelected\"=true AND choix='"+choix+"' order by nom_prenom";
				query= "SELECT distinct nom_prenom,ddn,obs,choix  FROM  \"Vue_resultat_selection\" where \"isSelected\"=true AND choix= ? order by nom_prenom";
				preparedStatement = connexion.prepareStatement(query);
				preparedStatement.setString(1, choix);
			}
			else
			{
				query= "SELECT distinct nom_prenom,ddn,obs,choix  FROM  \"Vue_resultat_selection\" where \"isSelected\"=true AND choix= ? AND id_vague= ? order by nom_prenom";
				preparedStatement = connexion.prepareStatement(query);
				preparedStatement.setString(1, choix);
				preparedStatement.setInt(2, id_vague);
				// query= "SELECT distinct nom_prenom,ddn,obs,choix  FROM  \"Vue_resultat_selection\" where \"isSelected\"=true AND choix='"+choix+"' AND id_vague='"+id_vague+"' order by nom_prenom";
			}
		//System.out.println(query);
		
			
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query);
			resultat = preparedStatement.executeQuery();
			
			while(resultat.next())
			{
				//int n_dossier=resultat.getInt("n_dossier");
				//int id_record=resultat.getInt("id_record");
				String nom_prenoms=resultat.getString("nom_prenom");
				String ddn=""+resultat.getString("ddn");
				//if(ddn!=null || !ddn.isEmpty())
				ddn=ddn.replace("VERS ","01/01/");
				//String mention=resultat.getString("mention");
				
				String choice=resultat.getString("choix");
				//int annee=resultat.getInt("annee");
				//String serie=resultat.getString("serie");
				String obs=resultat.getString("obs");
				
				//boolean isConfirmed=resultat.getBoolean("isConfirmed");
				
				Candidat candidat = new Candidat();
				
				//candidat.setN_dossier(n_dossier);
				candidat.setNom_prenom(nom_prenoms);
				candidat.setChoix(choice);
				//candidat.setAnnee(annee);
				//candidat.setSerie(serie);
			    candidat.setObs(obs);
				//candidat.setMention(mention);
				//candidat.setId_record(id_record);	
				candidat.setDdn(ddn);	
				//candidat.setIsConfirmed(isConfirmed);
				if(!isDoublon(candidats,candidat))					
				candidats.add(candidat);
				
			}
			this.noOfRecords=this.lister().size();
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
		
		return candidats;
	}
	private boolean isDoublon(List<Candidat> candidats, Candidat candidat) 
	{
		boolean found=false;
		for(int i=0; i>candidats.size(); i++)
		{
			if(candidat.getNom_prenom().trim().equals(candidats.get(i).getNom_prenom().trim()))
			{
				found=true;
				break;
			}
		}
		return found;
	}
	
	public 	List<Candidat> getAllCandidatSelected (CritereRecherche critere) throws DaoException
	{
		List<Candidat> candidats = new ArrayList<Candidat>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;	       
		
		
		String query=null;
		try{
			connexion = daoFactory.getConnection();
			if(critere.getAnnee()==0)
			{
				if(critere.getChoix().equals("")){
					query= "SELECT *  FROM t_import_saisie where \"isSelected\"=true  ORDER BY nom,prenoms";
					preparedStatement = connexion.prepareStatement(query);
				}
				else{
					query= "SELECT *  FROM t_import_saisie where \"isSelected\"=true AND choix = ? order by nom,prenoms ";
					//query= "SELECT *  FROM t_import_saisie where \"isSelected\"=true AND choix ='"+critere.getChoix()+"' order by nom,prenoms ";
					preparedStatement = connexion.prepareStatement(query);
					preparedStatement.setString(1, critere.getChoix());
				}
				
			}
			else
			{
				if(critere.getChoix().equals("")){
					query= "SELECT *  FROM t_import_saisie where \"isSelected\"=true AND annee = ? order by nom,prenoms ";
					//query= "SELECT *  FROM t_import_saisie where \"isSelected\"=true AND annee = '"+critere.getAnnee()+"' order by nom,prenoms ";
					preparedStatement = connexion.prepareStatement(query);
					preparedStatement.setInt(1, critere.getAnnee());
				}
				else{
					query= "SELECT *  FROM t_import_saisie where \"isSelected\"=true AND annee = ? AND choix = ? order by nom,prenoms ";
					//query= "SELECT *  FROM t_import_saisie where \"isSelected\"=true AND annee = '"+critere.getAnnee()+"' AND choix ='"+critere.getChoix()+"' order by nom,prenoms ";
					preparedStatement = connexion.prepareStatement(query);
					preparedStatement.setInt(1, critere.getAnnee());
					preparedStatement.setString(2, critere.getChoix());
				}
				
			}
			
			if(!critere.getSearch().equals(""))
			{
				query= "SELECT *  FROM t_import_saisie where \"isSelected\"=true AND (nom like ? OR prenoms like ?) ORDER BY nom,prenoms";
				String name = critere.getSearch();
				name = name.replace("'","\'");
				//query= "SELECT *  FROM t_import_saisie where \"isSelected\"=true AND (nom like '%"+critere.getSearch().toUpperCase()+"%' OR prenoms like '%"+critere.getSearch().toUpperCase()+"%') ORDER BY nom,prenoms";
				preparedStatement = connexion.prepareStatement(query);
				preparedStatement.setString(1, "%" + name.toUpperCase() + "%");
				preparedStatement.setString(2, "%" + name.toUpperCase() + "%");

			}
			
		
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query);
			resultat = preparedStatement.executeQuery();
			
			while(resultat.next())
			{
				int n_dossier=resultat.getInt("n_dossier");
				int id_record=resultat.getInt("id_record");
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
				boolean isConfirmed=resultat.getBoolean("isConfirmed");
				String choix=resultat.getString("choix");
				//int annee=resultat.getInt("annee");
				String serie=resultat.getString("serie_saisie");
				String obs=resultat.getString("obs");
				String ddn=resultat.getString("ddn");
				
				Candidat candidat = new Candidat();
				
				candidat.setN_dossier(n_dossier);
				candidat.setNom_prenom(nom+" "+prenoms);
				candidat.setChoix(choix);
				//candidat.setAnnee(annee);
				candidat.setSerie(serie);
			    candidat.setObs(obs);
			    candidat.setDdn(ddn);
				candidat.setIsConfirmed(isConfirmed);
				candidat.setId_record(id_record);							
				candidats.add(candidat);
				
			}
			this.noOfRecords=this.lister().size();
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
		
		return candidats;
		
	}
	
	
	private void selectCandidatOnRecord(int id_record, int id_vague)
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		//Statement preparedStatement = null;
		String query="";
		//int id_vague=1;
		try{
			connexion = daoFactory.getConnection();
			
			query="update t_import_saisie SET \"isSelected\"=true, id_vague=? where id_record=?";
			
			//System.out.println(query+" Id="+candidat.getId_record());
			//System.out.println(query);
			preparedStatement = connexion.prepareStatement(query);
			//preparedStatement=connexion.createStatement();
			preparedStatement.setInt(1,id_vague);
			preparedStatement.setInt(2,id_record);
			preparedStatement.executeUpdate();
			//preparedStatement.executeQuery(query);
			preparedStatement.close();
			connexion.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void reinitSelection(int id_vague) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		//Statement preparedStatement = null;
		String query="";
		//int id_vague=1;
		try{
			connexion = daoFactory.getConnection();
			
			query="update t_import_saisie SET \"isSelected\"=false where id_vague=?";
			
			//System.out.println(query+" Id="+candidat.getId_record());
			//System.out.println(query);
			preparedStatement = connexion.prepareStatement(query);
			//preparedStatement=connexion.createStatement();
			preparedStatement.setInt(1,id_vague);
			
			preparedStatement.executeUpdate();
			//preparedStatement.executeQuery(query);
			preparedStatement.close();
			connexion.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Candidat> getListFromCritere(int id_critere, int offset, int noOfRecords) throws DaoException
	{
		List<Candidat> candidats = new ArrayList<Candidat>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;	    
		critereDAO=daoFactory.getCritereDao();   
		// String query_0="SELECT * from \"Vue_preinscrits\" where "+critereDAO.getCritereFromId(id_critere).getCondition();
		// String query_0="SELECT * from \"Vue_preinscrits\" where "+critereDAO.getCritereFromId(id_critere).getCondition();
		//String query="SELECT * from \"Vue_preinscrits\" WHERE "+critereDAO.getCritereFromId(id_critere).getCondition();
		//System.out.println("SQL= "+query);
		String query = "SELECT * from \"Vue_preinscrits\" where ? limit ? OFFSET ?";
		System.out.println("SQL= "+query);
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			String where = critereDAO.getCritereFromId(id_critere).getCondition();
			where = where.replace("'","\'");
			preparedStatement.setString(1, where);
			preparedStatement.setInt(2, noOfRecords);
			preparedStatement.setInt(3,offset);
			resultat = preparedStatement.executeQuery();
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query);
			
			while(resultat.next())
			{
				int n_dossier=resultat.getInt("n_dossier");
				String nom_prenom=resultat.getString("nom_prenom");
				String choix=resultat.getString("choix");
				String mention=resultat.getString("mention");
				float moyenne=resultat.getFloat("moyenne");
				//int annee=resultat.getInt("annee");
				String serie=resultat.getString("serie");
				String sn=resultat.getString("sn");
				Candidat candidat = new Candidat();
				
				
				candidat.setMoyenne(moyenne);
				candidat.setN_dossier(n_dossier);
				candidat.setNom_prenom(nom_prenom);
				candidat.setChoix(choix);
				//candidat.setAnnee(annee);
				candidat.setSerie(serie);
				candidat.setMention(mention);		
				candidat.setSn(Float.parseFloat(sn));					
				candidats.add(candidat);
				
			}
			this.noOfRecords=this.lister().size();
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
		
		return candidats;
	}
	
	public int countRecords(Critere critere) throws DaoException
	{
		int countRecords=0;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;
		String query="SELECT count(*) as count from \"Vue_preinscrits\"";
 			       
		try{
			connexion = daoFactory.getConnection();
			// statement = connexion.createStatement();
			// query="SELECT count(distinct (nom_prenom,ddn)) as count from \"Vue_preinscrits\" where "+critere.getCondition();
			//System.out.println(query);
			// resultat = statement.executeQuery(query);
			query="SELECT count(distinct (nom_prenom,ddn)) as count from \"Vue_preinscrits\" where id_vague = ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, critere.getId_vague());
			// preparedStatement.setString(1, critere.getCondition());
			resultat = preparedStatement.executeQuery();
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
		return countRecords;
		
	}
	public int countCandidatSelectedPerSerie(String serie) throws DaoException
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
			// query="SELECT count(*) as count from t_import_saisie where \"isSelected\"=true and serie_saisie='"+serie+"'";
			query="SELECT count(*) as count from t_import_saisie where \"isSelected\"=true and serie_saisie=?";
			//System.out.println(query);
			// resultat = statement.executeQuery(query);
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, serie);
			resultat = preparedStatement.executeQuery();
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
		return countRecords;
		
	}
	
	public int countCandidatSelectedPerPortail(String choix) throws DaoException
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
			// query="SELECT count(distinct (nom_prenom,ddn,obs,choix)) as count from \"Vue_resultat_selection\" where \"isSelected\"=true and choix='"+choix+"'";
			//System.out.println(query);
			query="SELECT count(distinct (nom_prenom,ddn,obs,choix)) as count from \"Vue_resultat_selection\" where \"isSelected\"=true and choix= ? ";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, choix);
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
		return countRecords;
		
	}
	public int countCandidatSelectedPerPortail(String choix, int id_vague) throws DaoException
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
			// query="SELECT count(distinct (nom_prenom,ddn,obs,choix)) as count from \"Vue_resultat_selection\" where \"isSelected\"=true and choix='"+choix+"' and id_vague='"+id_vague+"'";
			//System.out.println(query);
			// resultat = statement.executeQuery(query);
			query="SELECT count(distinct (nom_prenom,ddn,obs,choix)) as count from \"Vue_resultat_selection\" where \"isSelected\"=true and choix= ? and id_vague= ? ";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, choix);
			preparedStatement.setInt(2, id_vague);
			resultat = preparedStatement.executeQuery();
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
		return countRecords;
		
	}
	
	public boolean isBachelierValid(Bachelier bachelier)throws DaoException
	{
		Connection connexion = null;
		//Statement statement = null;
		ResultSet resultat = null;
		boolean found=false;
		String query="";
 		PreparedStatement preparedStatement = null;	       
		try{
			connexion = daoFactory.getConnection();
			
			query="SELECT count(*) as count from t_candidat where nom_prenom=? OR num_bacc=?";
			
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1,bachelier.getNom_prenom());
			preparedStatement.setInt(2,bachelier.getNum_bacc());
			
			resultat=preparedStatement.executeQuery();
			resultat.next();
			if(resultat.getInt("count")>0)
			found=true;
			resultat.close();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		
		return found;

	}
	
	
	public Bachelier getInfoBachelier(Bachelier bachelier)throws DaoException
	{
		Bachelier etudiant=null;
		Connection connexion = null;
		//Statement statement = null;
		ResultSet resultat = null;
		boolean found=false;
		String query="";
 		PreparedStatement preparedStatement = null;	       
		try{
			connexion = daoFactory.getConnection();
			
			query="SELECT * from t_candidat where num_bacc=? and annee=?";
			System.out.println("Num="+bachelier.getNum_bacc()+"and :"+bachelier.getAnnee());
			preparedStatement = connexion.prepareStatement(query);
			
			preparedStatement.setInt(1,bachelier.getNum_bacc());
			preparedStatement.setInt(2,bachelier.getAnnee());
			
			resultat=preparedStatement.executeQuery();
			if(resultat.next())
			{
				//System.out.println("If");
				String nom_prenom=resultat.getString("nom_prenom");
				String mention=resultat.getString("mention");
				String serie=resultat.getString("serie");
				int num_bacc=resultat.getInt("num_bacc");
				int annee=resultat.getInt("annee");
				etudiant=new Bachelier();
				etudiant.setNom_prenom(nom_prenom);
				etudiant.setMention(mention);
				etudiant.setSerie(serie);
				etudiant.setNum_bacc(num_bacc);
				etudiant.setAnnee(annee);
				
			}
			//System.out.println("Tsy if");
			resultat.close();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return etudiant;
	}
	public void insertToSaisieEnline(Bachelier bachelier)throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;	  
		
		//System.out.println("INfo: "+bachelier.getSerie()+ bachelier.getNom_prenom());
		try{
			connexion = daoFactory.getConnection();
			preparedStatement =
			connexion.prepareStatement("INSERT INTO t_saisie_ligne (nom_prenom,num_bacc,choix,tel,annee,serie) VALUES(?,?,?,?,?,?);");
			
			Bachelier etudiant=this.getInfoBachelier(bachelier);
			System.out.println("Nom prenoms:"+bachelier.getNom_prenom());
			preparedStatement.setString(1,bachelier.getNom_prenom());
			preparedStatement.setInt(2,bachelier.getNum_bacc());
			preparedStatement.setString(3,bachelier.getChoix());
			preparedStatement.setString(4,bachelier.getTel());
			preparedStatement.setInt(5,bachelier.getAnnee());
			preparedStatement.setString(6,etudiant.getSerie());
				
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	public boolean isValid(String nom_prenom) throws DaoException
	{
		Connection connexion = null;
		//Statement statement = null;
		ResultSet resultat = null;
		boolean found=false;
		String query="";
 		    
 		PreparedStatement preparedStatement = null;	     
		try{
			connexion = daoFactory.getConnection();
			//statement = connexion.createStatement();
			query="SELECT count(*) as count from t_candidat where nom_prenom=?";
			//System.out.println(query);
			//resultat = statement.executeQuery(query);
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1,nom_prenom);
			resultat=preparedStatement.executeQuery();
			resultat.next();
			if(resultat.getInt("count")>0)
			found=true;
			resultat.close();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		
		return found;

	}
	public boolean isEtranger(int id_record) throws DaoException
	{
		Connection connexion = null;
		//Statement statement = null;
		ResultSet resultat = null;
		boolean found=false;
		String query="";
 		PreparedStatement preparedStatement = null;	       
		try{
			connexion = daoFactory.getConnection();
			//statement = connexion.createStatement();
			query="SELECT count(*) as count from t_import_saisie where id_record=? and obs=?";
			//System.out.println(query);
			//resultat = statement.executeQuery(query);
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1,id_record);
			preparedStatement.setString(2,"BAC ETRANGER");
			
			resultat=preparedStatement.executeQuery();
			resultat.next();
			if(resultat.getInt("count")>0)
			found=true;
			resultat.close();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		
		return found;

	}
	public List<Candidat> lister() throws DaoException
	{
		List<Candidat> candidats = new ArrayList<Candidat>();
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
			resultat = statement.executeQuery("SELECT t_import_saisie.n_dossier,t_candidat.num_bacc,t_candidat.nom_prenom,t_import_saisie.choix,t_candidat.serie,t_candidat.math,t_candidat.pc, t_candidat.sn,t_candidat.moyenne,t_candidat.mention,t_candidat.annee FROM t_import_saisie, t_candidat WHERE t_candidat.nom_prenom::text = concat(t_import_saisie.nom, ' ', t_import_saisie.prenoms) ORDER BY t_import_saisie.n_dossier");
			
			while(resultat.next())
			{
				int n_dossier=resultat.getInt("n_dossier");
				int num_bacc=resultat.getInt("num_bacc");
				String nom_prenom=resultat.getString("nom_prenom");
				String serie=resultat.getString("serie");
				String choix=resultat.getString("choix");
				float math=resultat.getFloat("math");
				float pc=resultat.getFloat("pc");
				float sn=resultat.getFloat("sn");
				float moyenne=resultat.getFloat("moyenne");
				String mention=resultat.getString("mention");
				int annee=resultat.getInt("annee");

				Candidat candidat = new Candidat();
				candidat.setN_dossier(n_dossier);
				candidat.setNum_bacc(num_bacc);
				candidat.setNom_prenom(nom_prenom);
				candidat.setSerie(serie);
				candidat.setChoix(choix);
				candidat.setMath(math);
				candidat.setPc(pc);
				candidat.setSn(sn);
				candidat.setMoyenne(moyenne);
				candidat.setMention(mention);
				candidat.setAnnee(annee);							
				candidats.add(candidat);
				
			}
			resultat.close();
			statement.close();
			connexion.close();
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return candidats;
	}
	
	public List<Candidat> tousLesAnomalies() throws DaoException
	{
		List<Candidat> candidats = new ArrayList<Candidat>();
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
			resultat = statement.executeQuery("SELECT *  FROM \"Vue_anomalie\";");
			
			while(resultat.next())
			{
				int n_dossier=resultat.getInt("n_dossier");
				int id_record=resultat.getInt("id_record");
				
				String nom_prenom=resultat.getString("nom_prenom");
				String choix=resultat.getString("choix");
				int annee=resultat.getInt("annee");
				//boolean isSelected=resultat.getBoolean("isSelected");
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
				
				Candidat candidat = new Candidat();
				//candidat.setIsSelected(isSelected);
				candidat.setId_record(id_record);
				candidat.setN_dossier(n_dossier);
				candidat.setNom_prenom(nom_prenom);
				candidat.setChoix(choix);
				candidat.setAnnee(annee);					
				candidat.setNom(nom);
				candidat.setPrenoms(prenoms);	
				//System.out.println("Nom="+nom+" Prenoms"+prenoms);	
				candidats.add(candidat);
				
			}
			resultat.close();
			statement.close();
			connexion.close();
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return candidats;
	}
	
	private List<CandidatAnomalie> tousLesAnomalies(String query) throws DaoException
	{
		List<CandidatAnomalie> candidats = new ArrayList<CandidatAnomalie>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
 		       
		try{
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery(query);
			
			while(resultat.next())
			{
				int id_record=resultat.getInt("id_record");
				int n_dossier=resultat.getInt("n_dossier");
				String nom_prenom=resultat.getString("nom_prenom");
				String choix=resultat.getString("choix");
				int annee=resultat.getInt("annee");
				String obs=resultat.getString("obs");
				//System.out.println("Observation: "+obs);
				obs=obs.trim().toUpperCase().replace("BAC INTROUVABLE","BI");
				String serie_saisie=resultat.getString("serie_saisie");
				String operateur=resultat.getString("operateur");
				
				String centre=resultat.getString("centre");
				String tel=resultat.getString("tel");
				float math=resultat.getFloat("math");
				float pc=resultat.getFloat("pc");
				float sn=resultat.getFloat("sn");
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
				
				
				
				//String serie=resultat.getString("serie");
				CandidatAnomalie candidat = new CandidatAnomalie();
				candidat.setId_record(id_record);
				candidat.setN_dossier(n_dossier);
				candidat.setNom_prenom(nom_prenom);
				candidat.setChoix(choix);
				candidat.setAnnee(annee);
				candidat.setObs(obs);
				candidat.setSerie_saisie(serie_saisie);
				candidat.setOperateur(operateur);
				
				candidat.setMath(math);
				candidat.setPc(pc);
				candidat.setSn(sn);
				candidat.setCentre(centre);
				candidat.setTel(tel);
				//candidat.setSerie(serie);							
				candidat.setNom(nom);
				candidat.setPrenoms(prenoms);
				candidats.add(candidat);				
			}
			resultat.close();
			statement.close();
			connexion.close();
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return candidats;
	}
	
	private List<Candidat> tousLesCandidatsAnomalies(String query) throws DaoException
	{
		List<Candidat> candidats = new ArrayList<Candidat>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
 		       
		try{
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery(query);
			
			while(resultat.next())
			{
				//int id_record=resultat.getInt("id_record");
				//int n_dossier=resultat.getInt("n_dossier");
				String nom_prenom=resultat.getString("nom_prenom");
				//String choix=resultat.getString("choix");
				//int annee=resultat.getInt("annee");
				//String obs=resultat.getString("obs");
				//System.out.println("Observation: "+obs);
				//obs=obs.trim().toUpperCase().replace("BAC INTROUVABLE","BI");
				//String serie=resultat.getString("serie");
				//String operateur=resultat.getString("operateur");
				
				//String centre=resultat.getString("centre");
				String tel=resultat.getString("tel");
				//float math=resultat.getFloat("math");
				//float pc=resultat.getFloat("pc");
				//float sn=resultat.getFloat("sn");
				//String serie=resultat.getString("serie");
				Candidat candidat = new Candidat();
				//candidat.setId_record(id_record);
				//candidat.setN_dossier(n_dossier);
				candidat.setNom_prenom(nom_prenom);
				//candidat.setChoix(choix);
				//candidat.setAnnee(annee);
				//candidat.setObs(obs);
				//candidat.setSerie(serie);
				//candidat.setOperateur(operateur);
				
				//candidat.setMath(math);
				//candidat.setPc(pc);
				//candidat.setSn(sn);
				//candidat.setCentre(centre);
				candidat.setTel(tel);
				//candidat.setSerie(serie);							
				candidats.add(candidat);	
						
			}
			resultat.close();
			statement.close();
			connexion.close();	
		}
		catch (SQLException e) 
		{
		        //e.printStackTrace();
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return candidats;
	}

	public List<Etudiant> afficher(int offset, int noOfRecords) throws DaoException
	{
		String query= "SELECT *  FROM \"Vue_preinscrits\" limit ";
		return this.getRecordCandidatInscrits(query, noOfRecords, offset);
	}
	
	public List<Candidat> inscritsEnLigne(int offset, int noOfRecords) throws DaoException
	{
		String query= "SELECT *  FROM \"Vue_preinscription_en_ligne\" limit ";
		return this.getRecordCandidatInscritsEnLigne(query, noOfRecords, offset);
	}
	
	public List<CandidatAnomalie> printAnomalie(int offset, int noOfRecords) throws DaoException
	{
		String query= "SELECT *  FROM \"Vue_anomalie\" limit ";
		System.out.println(query);
		List<CandidatAnomalie> candidats=this.getRecordCandidatAnomalies(query, noOfRecords, offset);
		this.noOfRecords=this.tousLesAnomalies().size();
		return candidats;
	}
	public void updateCandidatInscritsEnLigne(Candidat candidat)throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		String query="";
		try{
			connexion = daoFactory.getConnection();
			
			query="insert into t_import_saisie (nom,prenoms,obs,tel,choix,\"isOnline\",annee) values(?,?,?,?,?,?,?);";
			//System.out.println(query+" Nom Prenoms="+candidat.getNom_prenom().trim().toUpperCase());
			preparedStatement = connexion.prepareStatement(query);
			
			preparedStatement.setString(1,candidat.getNom().trim().toUpperCase());
			preparedStatement.setString(2,candidat.getPrenoms().trim().toUpperCase());
			preparedStatement.setString(3,candidat.getObs().trim().toUpperCase());
			preparedStatement.setString(4,candidat.getTel().trim().toUpperCase());
			preparedStatement.setString(5,candidat.getChoix().trim().toUpperCase());
			preparedStatement.setBoolean(6,true);
			preparedStatement.setInt(7,candidat.getAnnee());
			preparedStatement.executeUpdate();
			
			//resultat.close();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void update(CandidatAnomalie candidat)throws DaoException
	{
		if(candidat.getSerie().equals("S")|| isEtranger(candidat.getId_record()) || candidat.getSerie().equals("TI")||candidat.getSerie().equals("TGC")||candidat.getAnnee()==2015)
		{
			if(candidat.getSerie().equals("S"))
			candidat.setNum_bacc(0);
			//if(isEtranger(candidat.getId_record()))
			//candidat.setNum_bacc(1);
			
			//else
			//candidat.setNum_bacc(2);
			if(isEtranger(candidat.getId_record()))
			candidat.setObs("");
			
			addToBac(candidat);
			updateSaisie(candidat);
		}
		else
		{
			updateSaisie(candidat);
		}
		
	}
	public void updateObs(Candidat candidat)throws DaoException
	{
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		String query="";
		try{
			connexion = daoFactory.getConnection();
			
			query="update t_import_saisie SET obs=?,ddn=? where id_record=?";
			
			//System.out.println(query+" Id="+candidat.getId_record());
			preparedStatement = connexion.prepareStatement(query);
			
			preparedStatement.setString(1,candidat.getObs().trim().toUpperCase());
			preparedStatement.setString(2,candidat.getDdn().trim().toUpperCase());
			preparedStatement.setInt(3,candidat.getId_record());
			
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	public void validDerogation(Candidat candidat,Utilisateur userConnected, int vague)throws DaoException
	{
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		String query="";
		utilisateurDao=daoFactory.getUtilisateurDao();
		try{
			connexion = daoFactory.getConnection();
			
			query="update t_import_saisie SET \"isDerogated\"=true, \"isSelected\"=true, id_vague=? where id_record=?";
			
			//System.out.println(query+" Id="+candidat.getId_record());
			preparedStatement = connexion.prepareStatement(query);
			
			preparedStatement.setInt(1,vague);
			preparedStatement.setInt(2,candidat.getId_record());
			preparedStatement.executeUpdate();
			
			
			query="insert into t_derogation (id_record,\"acceptedByUserId\",obs) VALUES (?,?,?);";
			
			//System.out.println(query+" Id="+candidat.getId_record());
			preparedStatement = connexion.prepareStatement(query);
			
			//preparedStatement.setString(1,candidat.get);
			preparedStatement.setInt(1,candidat.getId_record());
			preparedStatement.setInt(2,utilisateurDao.getUserId(userConnected));
            preparedStatement.setString(3,candidat.getObs().trim().toUpperCase());
            //System.out.println("Observation: "+candidat.getObs().trim().toUpperCase());
            
			preparedStatement.executeUpdate();
			
			
			
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void validDerogationAnomalie(Candidat candidat,Utilisateur userConnected, int vague)throws DaoException
	{
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		String query="";
		utilisateurDao=daoFactory.getUtilisateurDao();
		try{
			connexion = daoFactory.getConnection();
			
			query="update t_import_saisie SET \"isDerogated\"=true, \"isSelected\"=true, id_vague=? where id_record=?";
			
			//System.out.println(query+" Id="+candidat.getId_record());
			preparedStatement = connexion.prepareStatement(query);
			
			preparedStatement.setInt(1,vague);
			preparedStatement.setInt(2,candidat.getId_record());
			preparedStatement.executeUpdate();
			
			
			query="insert into t_derogation (id_record,\"acceptedByUserId\",obs) VALUES (?,?,?);";
			
			//System.out.println(query+" Id="+candidat.getId_record());
			preparedStatement = connexion.prepareStatement(query);
			
			//preparedStatement.setString(1,candidat.get);
			preparedStatement.setInt(1,candidat.getId_record());
			preparedStatement.setInt(2,utilisateurDao.getUserId(userConnected));
            preparedStatement.setString(3,candidat.getObs());
			preparedStatement.executeUpdate();
			
			
			
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
		
	
	public void updateSaisie(CandidatAnomalie candidat) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		String query="";
		try{
			connexion = daoFactory.getConnection();
			
			query="update t_import_saisie SET centre=?, serie_saisie=?, mention=?, annee=?, math=?, pc=?, sn=?, moyenne=?, obs=?, nom=?, prenoms=? where id_record=?";
			
			//System.out.println(query+" Id="+candidat.getId_record());
			preparedStatement = connexion.prepareStatement(query);
			
			preparedStatement.setString(1,candidat.getCentre().trim().toUpperCase());
			preparedStatement.setString(2,candidat.getSerie().trim().toUpperCase());
			//preparedStatement.setInt(3,candidat.getNum_bacc());
			//preparedStatement.setString(3,candidat.getNom_prenom().trim().toUpperCase());
					//preparedStatement.setString(4,nom_prenom.trim());
			preparedStatement.setString(3,candidat.getMention().trim().toUpperCase());
			preparedStatement.setInt(4,candidat.getAnnee());
				
					preparedStatement.setFloat(5,candidat.getMath());
					preparedStatement.setFloat(6,candidat.getPc());
					/*if(serie.equals("TGI"))
					preparedStatement.setFloat(9,0);
					else*/
					preparedStatement.setFloat(7,candidat.getSn());
					preparedStatement.setFloat(8,candidat.getMoyenne());
					preparedStatement.setString(9,candidat.getObs().trim().toUpperCase());
					preparedStatement.setString(10,candidat.getNom().trim().toUpperCase());
					preparedStatement.setString(11,candidat.getPrenoms().trim().toUpperCase());	
				preparedStatement.setInt(12,candidat.getId_record());
					//preparedStatement.executeUpdate();
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addToBac(Candidat candidat) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		String query="";
		try{
			connexion = daoFactory.getConnection();
			
			query="insert into t_candidat (centre,serie,num_bacc,nom_prenom,mention,annee, math, pc, sn,moyenne) values(?,?,?,?,?,?,?,?,?,?);";
			//System.out.println(query+" Nom Prenoms="+candidat.getNom_prenom().trim().toUpperCase());
			preparedStatement = connexion.prepareStatement(query);
			
			preparedStatement.setString(1,candidat.getCentre().trim().toUpperCase());
			preparedStatement.setString(2,candidat.getSerie().trim().toUpperCase());
			preparedStatement.setInt(3,candidat.getNum_bacc());
			preparedStatement.setString(4,candidat.getNom_prenom().trim().toUpperCase());
					//preparedStatement.setString(4,nom_prenom.trim());
			preparedStatement.setString(5,this.setMention(candidat.getMoyenne()));
			preparedStatement.setInt(6,candidat.getAnnee());
				
					preparedStatement.setFloat(7,candidat.getMath());
					preparedStatement.setFloat(8,candidat.getPc());
					/*if(serie.equals("TGI"))
					preparedStatement.setFloat(9,0);
					else*/
					preparedStatement.setFloat(9,candidat.getSn());
					preparedStatement.setFloat(10,candidat.getMoyenne());
				   // preparedStartement.setString(11,candidat.getChoix());
				   // preparedStatement.setString(11,setMention(candidat.getMoyenne()));
			
					//preparedStatement.executeUpdate();
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private String setMention(float moyenne)
	{
		String mention="PASSABLE";
		if(moyenne>=12 && moyenne<14)
		mention ="ASSEZ BIEN";
		if(moyenne>=14 && moyenne<16)
		mention ="BIEN";
		if(moyenne>=16)
		mention ="Très bien";
		
		return mention.trim().toUpperCase();
	}
	
	public List<CandidatAnomalie> afficheAnomalieCritere(CritereAnomalie critere, int offset, int noOfRecords) throws DaoException
	{
		String query=null;
		if(critere.getAnnee()==0)
		{
			if(critere.getChoix().equals(""))
				query= "SELECT *  FROM \"Vue_anomalie\"  ORDER BY nom_prenom";
			else
				query= "SELECT *  FROM \"Vue_anomalie\" where choix ='"+critere.getChoix()+"' order by nom_prenom ";
			
		}
		else
		{
			if(critere.getChoix().equals(""))
				query= "SELECT *  FROM \"Vue_anomalie\" where annee = '"+critere.getAnnee()+"' order by nom_prenom ";
			else
				query= "SELECT *  FROM \"Vue_anomalie\" where annee = '"+critere.getAnnee()+"' AND choix ='"+critere.getChoix()+"' order by nom_prenom ";
			
		}
		
		if(!critere.getSearch().equals(""))
		{
				query= "SELECT *  FROM \"Vue_anomalie\"  where nom_prenom like '%"+critere.getSearch().toUpperCase()+"%' ORDER BY nom_prenom";

		}
		
		List<CandidatAnomalie> candidats=this.tousLesAnomalies(query);
		this.noOfRecords=this.tousLesAnomalies().size();
		return candidats;
	}
	
	public List<Candidat> afficheAnomalieVerifAttestation() throws DaoException
	{

		//String query= "SELECT *  FROM \"Vue_anomalie\"  where obs like '%ETRANGER%' ORDER BY nom_prenom";

		String query= "select nom_prenom,tel from \"Vue_Import\" where centre  in ('MORONI','FOMBONI','MUTSAMUDU-COMORES','MUTSAMUDU') group by nom_prenom,tel order by nom_prenom";
		
		List<Candidat> candidats=this.tousLesCandidatsAnomalies(query);
		//this.noOfRecords=this.tousLesAnomalies().size();
		return candidats;
	}
	
	public int getNoOfRecords() {
        return noOfRecords;
    }
    
    private List<Etudiant> getRecordCandidatInscrits(String query, int noOfRecords, int offset) throws DaoException
    {
		List<Etudiant> candidats = new ArrayList<Etudiant>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;	       
		try{
			connexion = daoFactory.getConnection();
			query = query + " ? OFFSET ?";
			preparedStatement = connexion.prepareStatement(query);
			// statement = connexion.createStatement();
			preparedStatement.setInt(1, noOfRecords);
			preparedStatement.setInt(2, offset);
			resultat = preparedStatement.executeQuery();
			// resultat = statement.executeQuery(query
                 // + noOfRecords + " OFFSET " + offset);
			
			while(resultat.next())
			{
				int id_record=resultat.getInt("id_record");
				int n_dossier=resultat.getInt("n_dossier");
				String nom_prenom=resultat.getString("nom_prenom");
				String choix=resultat.getString("choix");
				//int annee=resultat.getInt("annee");
				String serie=resultat.getString("serie");
				
				int num_bacc=resultat.getInt("num_bacc");
				float math=resultat.getFloat("math");
				float pc=resultat.getFloat("pc");
				float sn=resultat.getFloat("sn");
				float moyenne=resultat.getFloat("moyenne");
				String mention=resultat.getString("mention");
				String ddn=resultat.getString("ddn");
				String nom_mere=resultat.getString("nom_mere");
				String nom_pere=resultat.getString("nom_pere");
				String adresse=resultat.getString("adresse");
				String lieu_naissance=resultat.getString("lieu_naissance");
				String tel=resultat.getString("tel");
				String centre=resultat.getString("centre");
				boolean isSelected=resultat.getBoolean("isSelected");
				int annee=resultat.getInt("annee");
				
				Etudiant candidat = new Etudiant();
				candidat.setN_dossier(n_dossier);
				candidat.setNom_prenom(nom_prenom);
				candidat.setChoix(choix);
				candidat.setId_record(id_record);
				//candidat.setAnnee(annee);
				candidat.setSerie(serie);					
				
				candidat.setNum_bacc(num_bacc);
				candidat.setMath(math);
				candidat.setPc(pc);
				candidat.setSn(sn);
				candidat.setMoyenne(moyenne);
				candidat.setMention(mention);
				candidat.setDdn(ddn);
				candidat.setNom_pere(nom_pere);
				candidat.setNom_mere(nom_mere);
				candidat.setAdresse(adresse);
				candidat.setLieu_naissance(lieu_naissance);
				candidat.setTel(tel);
				candidat.setCentre(centre);
				candidat.setIsSelected(isSelected);
				candidat.setAnnee(annee);
						
				candidats.add(candidat);
				
			}
			this.noOfRecords=this.lister().size();
			
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
		
		return candidats;
	}
	private List<Candidat> getRecordCandidatInscritsEnLigne(String query, int noOfRecords, int offset) throws DaoException
    {
		List<Candidat> candidats = new ArrayList<Candidat>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;	       
		try{
			connexion = daoFactory.getConnection();
			query = query + "? OFFSET ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, noOfRecords);
			preparedStatement.setInt(2, offset);
			resultat = preparedStatement.executeQuery();
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query
                 // + noOfRecords + " OFFSET " + offset);
			
			while(resultat.next())
			{
				int num_bacc=resultat.getInt("num_bacc");
				int id_record=resultat.getInt("id_candidat");
				String nom_prenom=resultat.getString("nom_prenom");
				String choix=resultat.getString("choix");
				int annee=resultat.getInt("annee");
				String tel=resultat.getString("tel");
				String serie=resultat.getString("serie");
				String mention=resultat.getString("mention");
				Candidat candidat = new Candidat();
				candidat.setNum_bacc(num_bacc);
				candidat.setNom_prenom(nom_prenom);
				candidat.setChoix(choix);
				//candidat.setAnnee(annee);
				candidat.setSerie(serie);
				candidat.setMention(mention);		
				candidat.setId_record(id_record);
				candidat.setAnnee(annee);
				candidat.setTel(tel);					
				candidats.add(candidat);
				
			}
			this.noOfRecords=this.lister().size();
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
		
		return candidats;
	}
	
	 private List<CandidatAnomalie> getRecordCandidatAnomalies(String query, int noOfRecords, int offset) throws DaoException
    {
		List<CandidatAnomalie> candidats = new ArrayList<CandidatAnomalie>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;	       
		try{
			connexion = daoFactory.getConnection();
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery(query
   //               + noOfRecords + " OFFSET " + offset);
			query = query + "? OFFSET ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, noOfRecords);
			preparedStatement.setInt(2, offset);
			resultat = preparedStatement.executeQuery();
			while(resultat.next())
			{
				int id_record=resultat.getInt("id_record");
				int n_dossier=resultat.getInt("n_dossier");
				String nom_prenom=resultat.getString("nom_prenom");
				String choix=resultat.getString("choix");
				int annee=resultat.getInt("annee");
				String obs=resultat.getString("obs");
				String serie_saisie=resultat.getString("serie_saisie");
				String operateur=resultat.getString("operateur");
				
				String centre=resultat.getString("centre");
				float math=resultat.getFloat("math");
				float pc=resultat.getFloat("pc");
				float sn=resultat.getFloat("sn");
				String tel=resultat.getString("tel");
				
				String nom=resultat.getString("nom");
				String prenoms=resultat.getString("prenoms");
				
				CandidatAnomalie candidat = new CandidatAnomalie();
				candidat.setId_record(id_record);
				candidat.setN_dossier(n_dossier);
				candidat.setNom_prenom(nom_prenom);
				candidat.setChoix(choix);
				candidat.setAnnee(annee);
				candidat.setObs(obs);
				candidat.setSerie_saisie(serie_saisie);
				candidat.setOperateur(operateur);
				
				candidat.setMath(math);
				candidat.setPc(pc);
				candidat.setSn(sn);
				candidat.setCentre(centre);
				candidat.setTel(tel);
				//candidat.setSerie(serie);					
				candidat.setNom(nom);
				candidat.setPrenoms(prenoms);
						
				candidats.add(candidat);
				
			}
			this.noOfRecords=this.lister().size();
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
		
		return candidats;
	}
	
	public void orienterEtudiant(Candidat candidat) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		String query="";
		try{
			connexion = daoFactory.getConnection();
			
			query="update t_import_saisie SET choix0=choix,choix=? where id_record=?";
			
			//System.out.println(query+" Id="+candidat.getId_record());
			preparedStatement = connexion.prepareStatement(query);
			
			preparedStatement.setString(1,candidat.getChoix().trim().toUpperCase());
			
			preparedStatement.setInt(2,candidat.getId_record());
			
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
