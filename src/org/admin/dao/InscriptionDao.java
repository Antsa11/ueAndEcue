package org.admin.dao;

import org.admin.dao.DaoException;

import java.util.List;
import org.admin.beans.Etudiant;


import org.admin.beans.Utilisateur;
import org.admin.beans.Inscription;
import org.admin.beans.EtudiantPortail;
import org.admin.beans.CritereRecherche;

public interface InscriptionDao
{
	void addInscription(Inscription inscription)throws DaoException;
	List<EtudiantPortail> getAllInscriptionOnPortail()throws DaoException;
    List<EtudiantPortail> getAllFinalInscriptionOnPortail()throws DaoException;
	List<EtudiantPortail> getAllInscriptionOnPortail(CritereRecherche critere)throws DaoException;
	int countEtudiantConfirmedPerPortail(String choix) throws DaoException;
	int countEtudiantSubsribedPerPortail(String choix) throws DaoException;
}
