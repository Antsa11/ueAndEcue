package org.admin.dao;
import java.util.List;
import org.admin.beans.EtudiantImport;
import org.admin.beans.EtudiantPortail;
import org.admin.beans.Utilisateur;

public interface EtudiantImportDao{
	List<EtudiantImport> validateInscription()throws DaoException;
//	List<EtudiantImport> anomalieInscription()throws DaoException;
}
