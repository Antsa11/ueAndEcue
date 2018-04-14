package org.admin.dao;

import org.admin.dao.DaoException;
import java.util.List;
import org.admin.beans.scolarite.Ue;
public interface UeDao{
	void addUe(String nom, String niveau, int parcours, String responsable, float credit, int id_mention) throws DaoException;
	int getIdUe(String nom, String niveau) throws DaoException;
	List<Ue> getAllUeByParcours(int id_parcours) throws DaoException;
	List<Ue> getAllUeByMention(int id_mention) throws DaoException;
	float getSommeCredit(int id_pacours,String niveau) throws DaoException;
}