package org.admin.dao;

import org.admin.dao.DaoException;
import org.admin.beans.scolarite.Inscription;
import org.admin.beans.scolarite.Cursus;

import java.util.List;
import java.util.ArrayList;

public interface CursusDao{
	// List<Inscription> getCursus(int id_etudiant) throws DaoException;
	List<Cursus> getCursus() throws DaoException;
}