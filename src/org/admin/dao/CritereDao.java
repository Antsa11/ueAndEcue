package org.admin.dao;

import java.util.List;
import org.admin.beans.Critere;

public interface CritereDao
{
	/*void preinscrire(Critere Critere);
	List<Bachelier> lister() throws DaoException;
	int getNoOfRecords();
	List<Bachelier> afficher(int offset, int noOfRecords) throws DaoException;*/
	
	void ajouter(Critere critere) throws DaoException;
	void update(Critere critere) throws DaoException;
	void delete(int id_critere) throws DaoException;
	List<Critere> getCriteres() throws DaoException;
	List<Critere> getCriteresWithCandidat() throws DaoException;
	List<Critere> getCriteresWithCandidat(int vague) throws DaoException;
	Critere getCritereFromId(int id_critere)throws DaoException;
	void reinitCriterePublished(int id_vague)throws DaoException;
	void publishedResulatFrom(int id_critere) throws DaoException;
	
}

