package org.admin.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import org.admin.dao.DaoFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

public interface UrlDao{
	void ajouter(String url, boolean pat, boolean enseignant, boolean doyen);
	List<String> listAll() throws DaoException;
	List<String> listAllPatUrl() throws DaoException;
	List<String> listAllEnseignantUrl() throws DaoException;
	boolean validAccess(HttpSession session, String url) throws DaoException;
}