package org.admin.dao;

import java.util.List;

import org.admin.beans.Personnel;
import org.admin.dao.DaoException;

public interface PersonnelDao
{
	void ajouterPersonnel(Personnel personnel)throws DaoException;
	List<Personnel> getListPersonnel();
	//boolean isValid(Utilisateur utilisateur);
	//int getUserId(Utilisateur utilisateur);
}
