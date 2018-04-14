package org.admin.dao;

import org.admin.dao.DaoException;
import org.admin.beans.scolarite.Inscription;
import org.admin.beans.scolarite.Carte;

import java.util.List;
import java.util.ArrayList;

public interface CarteDao{
	// List<Inscription> getCarte(int id_etudiant) throws DaoException;
	List<Carte> getCarte(int id_etudiant) throws DaoException;
	List<Carte> getCarte() throws DaoException;
}