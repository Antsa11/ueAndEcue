package org.admin.dao;

import org.admin.dao.DaoException;

import java.util.List;



import org.admin.beans.Derogation;

public interface DerogationDao
{

	List<Derogation> getAllDerogation()throws DaoException;
    int countEtudiantDerogatedPerPortail(String choix) throws DaoException;
    List<Derogation> getAllDerogationPerPortail(String choix)throws DaoException;
     List<Derogation> getAllDerogationBySearch(String search)throws DaoException;
    List<Derogation> getAllDerogationPerPortail(String choix, String search)throws DaoException;
    /*int countEtudiantConfirmedPerPortail(String choix) throws DaoException;
	int countEtudiantSubsribedPerPortail(String choix) throws DaoException;*/
}
