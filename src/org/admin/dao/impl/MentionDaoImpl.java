package org.admin.dao.impl;

import java.sql.*;

import org.admin.dao.DaoFactory;
import org.admin.dao.DaoException;
import org.admin.dao.MentionDao;
import org.admin.beans.scolarite.Mention;
import java.util.List;
import java.util.ArrayList;



public class MentionDaoImpl implements MentionDao{
	private DaoFactory daoFactory;

	public MentionDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}

	public List<Mention> getAllMention() throws DaoException{
		List<Mention> mentions = new ArrayList<Mention>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			//String query = "SELECT * FROM t_mention";
			String query = "SELECT * from \"Vue_mention_responsable_n_etudiant\"";
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			resultat = preparedStatement.executeQuery();

			while(resultat.next()){
				String nom_mention = resultat.getString("nom_mention");
				int id_mention = resultat.getInt("id_mention");
				String responsable=resultat.getString("responsable");
				String phone=resultat.getString("phone");
				int n_etudiant=resultat.getInt("n_etudiant");
				Mention mention = new Mention(id_mention, nom_mention);
				// mention.setId_mention();
				// mention.setNom_mention();
				//System.out.println(mention.getId_mention());
				//System.out.println(mention.getNom_mention());
				mention.setN_etudiant(n_etudiant);
				mention.setNom_responsable(responsable);
				mention.setTel(phone);
				//System.out.println(mention);
				mentions.add(mention);
			}


			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch (SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}

		return mentions;
	}

	public String getNomMention(int id_mention) throws DaoException{
		String nom;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		try{
			String query = "SELECT * FROM\"Vue_mention_responsable_n_etudiant\" WHERE id_mention = ?";
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_mention);
			resultat = preparedStatement.executeQuery();
			resultat.next();
			nom = resultat.getString("nom_mention");

			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch (SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		System.out.println(nom);
		return nom;
	}

	public List<Mention> getMentionByAuthorized() throws DaoException{
		List<Mention> mentions = new ArrayList<Mention>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		try{
			String query = "SELECT * FROM \"Vue_n_apte_mention\";";
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			resultat = preparedStatement.executeQuery();
			while(resultat.next()){
				String nom_mention = resultat.getString("nom_mention");
				int id_mention = resultat.getInt("id_mention");
				int n_etudiant = resultat.getInt("n_etudiant");

				Mention mention = new Mention(id_mention, nom_mention);
				mention.setN_etudiant(n_etudiant);
				mentions.add(mention);
			}
			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch (SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}

		return mentions;
	}
}
