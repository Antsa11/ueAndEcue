package org.admin.dao;

import java.util.List;
import org.admin.beans.Bachelier;
import org.admin.beans.CritereBac;

public interface BachelierDao
{
	void preinscrire(Bachelier bachelier);
	List<Bachelier> lister() throws DaoException;
	int getNoOfRecords();
	List<Bachelier> afficher(int offset, int noOfRecords) throws DaoException;
	List<Bachelier> search(CritereBac critere)throws DaoException;
}
