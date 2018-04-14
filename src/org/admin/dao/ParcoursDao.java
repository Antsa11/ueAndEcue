package org.admin.dao;

import org.admin.beans.scolarite.Parcours;
import org.admin.beans.scolarite.Inscription;
import org.admin.dao.DaoException;
import java.util.List;

public interface ParcoursDao{
	List<Parcours> getAllParcours(int id_mention) throws DaoException;
	List<Parcours> getAllParcoursByAuthorization(int id_mention) throws DaoException;
	String getNomParcours(int id_parcours) throws DaoException;
	// String getNomParcoursById(Inscription inscirs) throws DaoException;
}