package org.admin.dao;

import java.util.List;
import org.admin.beans.Utilisateur;
import org.admin.beans.Personnel;


public interface UtilisateurDao
{
	void ajouter(Personnel utilisateur);
	List<Utilisateur> lister();
	boolean isValid(Utilisateur utilisateur);
	int getUserId(Utilisateur utilisateur);
	String getUserFonction(Utilisateur utilisateur);
	void delete(int id_utilisateur) throws DaoException;
	List<Personnel> getAllPersonnel() throws DaoException;
}
