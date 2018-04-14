package org.admin.dao;

import org.admin.dao.DaoException;

public interface EcueDao{
	void addEcue(String nom, int id_ue, float credit, String responsable, int id_parcours, String niveau) throws DaoException;
	float getSommeCreditEcue(int id_parcours,String niveau) throws DaoException;
} 