package org.admin.dao;

import java.util.List;
import org.admin.beans.Candidat;
import org.admin.beans.Etudiant;
import org.admin.beans.Utilisateur;

import org.admin.beans.Bachelier;
import org.admin.beans.CandidatAnomalie;
import org.admin.beans.Critere;
import org.admin.beans.CritereAnomalie;
import org.admin.beans.CritereRecherche;

public interface CandidatDao
{
	void preinscrire(Candidat candidat);
	List<Candidat> lister() throws DaoException;
	int getNoOfRecords();
	
	void reorienterFrom(int id_critere) throws DaoException;
	
	int countRecords(Critere critere) throws DaoException;
	List<Candidat> getListFromCritere(int id_critere, int offset, int noOfRecords) throws DaoException;
	
	void validSelectionFromCritere(int id_critere)throws DaoException;
	
	List<Etudiant> afficher(int offset, int noOfRecords) throws DaoException;
	List<CandidatAnomalie> printAnomalie(int offset, int noOfRecords) throws DaoException;
	List<CandidatAnomalie> afficheAnomalieCritere(CritereAnomalie critere, int offset, int noOfRecords) throws DaoException;
	List<Candidat> getAllListFromCritere (int id_critere) throws DaoException;
	
	void update(CandidatAnomalie candidat)throws DaoException;
	
	void updateObs(Candidat candidat)throws DaoException;
	void validDerogation (Candidat candidat, Utilisateur userConnected, int vague)throws DaoException;
	
	
	boolean isValid(String nom_prenom) throws DaoException;
	boolean isEtranger(int id_record) throws DaoException;
	void addToBac(Candidat candidat) throws DaoException;
	
	List<Candidat> getAllCandidatSelected () throws DaoException;
	List<Candidat> getAllCandidatSelected (int id_vague) throws DaoException;
	int countCandidatSelectedPerPortail(String choix) throws DaoException;
	int countCandidatSelectedPerSerie(String serie) throws DaoException;
	
	boolean isBachelierValid(Bachelier bachelier)throws DaoException;
	
	Bachelier getInfoBachelier(Bachelier bachelier)throws DaoException;
	
	void insertToSaisieEnline(Bachelier bachelier)throws DaoException;
	
	List<Candidat> getAllCandidatSelected (CritereRecherche critere) throws DaoException;
	
	List<Candidat> getAllCandidatSelected (String choix, int id_vague) throws DaoException;
	List<Candidat> getAllCandidatConfirmed (String choix) throws DaoException;
	List<Candidat> inscritsEnLigne(int offset, int noOfRecords) throws DaoException;
	List<Candidat> afficheAnomalieVerifAttestation() throws DaoException;
	
	List<Etudiant> searchInPreinsrits(String keyword)throws DaoException;
	
	void updateCandidatInscritsEnLigne(Candidat candidat)throws DaoException;
	
	int countCandidatSelectedPerPortail(String choix, int id_vague) throws DaoException;
	
	void reinitSelection(int id_vague) throws DaoException;
	void validDerogationAnomalie(Candidat candidat,Utilisateur userConnected, int vague)throws DaoException;
	void orienterEtudiant(Candidat candidat) throws DaoException;
}
