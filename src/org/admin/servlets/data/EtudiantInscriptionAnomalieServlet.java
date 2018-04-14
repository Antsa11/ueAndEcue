package org.admin.servlets.data;

import org.admin.servlets.BaseServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;

import org.admin.beans.EtudiantImport;

import org.admin.dao.DaoFactory;
import org.admin.dao.EtudiantImportDao;
import org.admin.utils.Outils;

import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;


import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 *
 */
@WebServlet("/etudiant_inscr_anomalie")
public class EtudiantInscriptionAnomalieServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	EtudiantImportDao etudiantImportDao ;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EtudiantInscriptionAnomalieServlet() {

        super();
    }

    public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
	 	etudiantImportDao=daoFactory.getEtudiantImportDao();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String base_url="http://localhost/sciences";
		request.setAttribute("base_url",base_url);

		String content="/WEB-INF/inscription/liste_anomalie_inscription.jsp";
		request.setAttribute ("content",content);
		/*
		try{
			HttpSession session=request.getSession();
			if(session.getAttribute("utilisateur")==null)
			{

				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
			}else
			{
				List<EtudiantImport> etuList = etudiantImportDao.anomalieInscription();
				request.setAttribute("student",etuList);

			}
		}
		catch(DaoException e)
		{
				request.setAttribute("erreur", e.getMessage());
		}
		*/
		this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//this.getServletContext().getRequestDispatcher("/vue/admin.jsp").forward(request, response);
		request.setCharacterEncoding("UTF-8");

	}

}
