package org.admin.dao.impl;


import java.sql.*;

import org.admin.dao.DaoFactory;
import org.admin.dao.DaoException;
import org.admin.dao.ParcoursDao;
import org.admin.beans.scolarite.Mention;
import java.util.List;
import java.util.ArrayList;

import org.admin.beans.scolarite.Parcours;

public class ParcoursDaoImpl implements ParcoursDao{
	private DaoFactory daoFactory;

	public ParcoursDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}

	public List<Parcours> getAllParcours(int id_mention) throws DaoException{
		List<Parcours> lesParcours = new ArrayList<Parcours>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM \"Vue_parcours_responsable_n_etudiant\" WHERE id_mention = ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_mention);
			resultat = preparedStatement.executeQuery();

			while(resultat.next()){
				int idMention = resultat.getInt("id_mention");
				int id_parcours = resultat.getInt("id_parcours");
				String nom_parcours = resultat.getString("nom_parcours");
				Parcours parcours = new Parcours(id_parcours, idMention, nom_parcours);
				
				parcours.setN_etudiant(resultat.getInt("n_etudiant"));
				parcours.setNom_responsable(resultat.getString("responsable"));
				parcours.setTel(resultat.getString("phone"));
				parcours.setNiveau(resultat.getString("niveau"));
				lesParcours.add(parcours);

				//System.out.println("id_mention : " + idMention);
				//System.out.println("id_parcours : " + id_parcours);
				//System.out.println("nom_parcours" + nom_parcours);
			}


			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch(SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}

		return lesParcours;
	}

	public List<Parcours> getAllParcoursByAuthorization(int id_mention) throws DaoException{
		List<Parcours> parcours = new ArrayList<Parcours>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM \"Vue_n_apte_parcours\" WHERE id_mention = ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_mention);
			resultat = preparedStatement.executeQuery();
			while(resultat.next()){
				String nom_parcours = resultat.getString("nom_parcours");
				int id_parcours = resultat.getInt("id_parcours");
				int n_etudiant = resultat.getInt("n_etudiant");
				String niveau = resultat.getString("niveau");

				Parcours unParcours = new Parcours(id_parcours, id_mention, nom_parcours);
				unParcours.setNiveau(niveau);
				unParcours.setN_etudiant(n_etudiant);
				parcours.add(unParcours);
				// System.out.println(unParcours.toString());
			}

			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch(SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return parcours;
	}

	public String getNomParcours(int id_parcours) throws DaoException{
		String nom;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		try{
			String query = "SELECT * FROM\"Vue_mention_parcours\" WHERE id_parcours = ?";
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_parcours);
			resultat = preparedStatement.executeQuery();
			resultat.next();
			nom = resultat.getString("nom_parcours");

			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch (SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		System.out.println(nom);
		return nom;
	}
}
