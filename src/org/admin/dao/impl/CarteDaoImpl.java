package org.admin.dao.impl;

import java.sql.*;

import org.admin.dao.CarteDao;
import org.admin.dao.DaoFactory;
import org.admin.dao.DaoException;
import org.admin.beans.scolarite.Inscription;
import org.admin.beans.scolarite.Carte;

import java.util.List;
import java.util.ArrayList;

public class CarteDaoImpl implements CarteDao{
	private DaoFactory daoFactory;

	public CarteDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	public List<Carte> getCarte(int id_etudiant) throws DaoException{
		List<Carte> carte = new ArrayList<Carte>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			// String query = "SELECT * FROM t_inscription WHERE id_etudiant = ?";
			String query = "SELECT * FROM \"Vue_carte\" WHERE id_etudiant = ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_etudiant);
			resultat = preparedStatement.executeQuery();

			while(resultat.next()){
				int id = resultat.getInt("id_etudiant");
				String nom = resultat.getString("nom");
				String prenoms = resultat.getString("prenoms");
				String nom_parcours = resultat.getString("nom_parcours");
				String nom_mention = resultat.getString("nom_mention");
				String niveau = resultat.getString("niveau");
				String annee_u = resultat.getString("annee_u");
				String situation = resultat.getString("situation");
				Carte unCarte = new Carte(id, nom, prenoms, nom_parcours, nom_mention, niveau, annee_u, situation);
				System.out.println(unCarte.toString());

				carte.add(unCarte);
			}

		}catch (SQLException e){
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}

		return carte;
	}


	public List<Carte> getCarte() throws DaoException{
		List<Carte> carte = new ArrayList<Carte>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM \"Vue_carte\"; ";
			preparedStatement = connexion.prepareStatement(query);
			resultat = preparedStatement.executeQuery();

			while(resultat.next()){
				int id_etudiant = resultat.getInt("id_etudiant");
				String nom = resultat.getString("nom");
				String prenoms = resultat.getString("prenom");
				String nom_parcours = resultat.getString("nom_parcours");
				String nom_mention = resultat.getString("nom_mention");
				String niveau = resultat.getString("niveau");
				String annee_u = resultat.getString("annee_u");
				String situation = resultat.getString("situation");
				Carte unCarte = new Carte(id_etudiant, nom, prenoms, nom_parcours, nom_mention, niveau, annee_u, situation);
				System.out.println(unCarte.toString());

				carte.add(unCarte);
			}

			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch (SQLException e){
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}

		return carte;
	}
}