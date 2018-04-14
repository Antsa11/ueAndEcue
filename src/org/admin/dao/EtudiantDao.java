package org.admin.dao;

import java.util.List;
import org.admin.beans.Etudiant;
import org.admin.beans.EtudiantPortail;
import org.admin.beans.Utilisateur;

public interface EtudiantDao
{
	void confirmerInscription(Etudiant etudiant, String annee_univ, Utilisateur userConnected, String num_recu) throws DaoException;
    void validerInscription(EtudiantPortail etudiant, String annee_univ, Utilisateur userConnected, String num_recu) throws DaoException;
	Etudiant getDetailsFrom(Etudiant etudiant) throws DaoException;
	void insertEtudiant(Etudiant etudiant) throws DaoException;
	void confirmInfoSaisieEtudiant(Etudiant etudiant) throws DaoException;
}
