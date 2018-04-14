package org.admin.dao;

import org.admin.beans.scolarite.Mention;
import org.admin.dao.DaoException;
import java.util.List;

public interface MentionDao{
	List<Mention> getAllMention() throws DaoException;
	String getNomMention(int id_mention) throws DaoException;
	List<Mention> getMentionByAuthorized() throws DaoException;
}