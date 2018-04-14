package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.admin.beans.EtudiantPortail;
import org.admin.dao.DaoFactory;
import org.admin.dao.InscriptionDao;
import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;
import org.admin.beans.CritereRecherche;

import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/search_confirm")
public class ConfirmSearchServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
     private InscriptionDao inscriptionDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmSearchServlet() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		inscriptionDao=daoFactory.getInscriptionDao();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String base_url="http://localhost/sciences";
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//this.getServletContext().getRequestDispatcher("/vue/admin.jsp").forward(request, response);
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		HttpSession session=request.getSession();
		
		if(session.getAttribute("utilisateur")==null)
		{
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
		}
		
		else
		{
			String content="../preinscription/list_confirmation_inscription.jsp";
			request.setAttribute ("content",content);
			request.setAttribute ("title", "Résultat");
			String anneeU="";
			String choix="";
			String search="";
			if((request.getParameter("annee")!=null) && (!request.getParameter("annee").equals("Année")))
			anneeU=request.getParameter("annee");
			
			if((request.getParameter("choix")!=null)  && (!request.getParameter("choix").equals("Choix")))
			choix=request.getParameter("choix");
			
			if((request.getParameter("search")!=null)  && (!request.getParameter("search").equals("")))
			search=request.getParameter("search");
			
			
			CritereRecherche critere= new CritereRecherche(anneeU,choix,search);
			//System.out.println("Choix:"+choix);
			
			
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				List<EtudiantPortail> etudiants = inscriptionDao.getAllInscriptionOnPortail(critere);
				//request.setAttribute("count_student",candidats.size());
				request.setAttribute("etudiants",etudiants);
				request.setAttribute("count_svt",inscriptionDao.countEtudiantConfirmedPerPortail("SVT"));
				request.setAttribute("count_mi",inscriptionDao.countEtudiantConfirmedPerPortail("MI"));
				request.setAttribute("count_pc",inscriptionDao.countEtudiantConfirmedPerPortail("PC"));
				/*
				request.setAttribute("count_c",candidatDao.countCandidatSelectedPerSerie("C"));
				request.setAttribute("count_d",candidatDao.countCandidatSelectedPerSerie("D"));
				request.setAttribute("count_s",candidatDao.countCandidatSelectedPerSerie("S"));
				request.setAttribute("count_tgi",candidatDao.countCandidatSelectedPerSerie("TGI"));
				*/
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
				this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
		}
		
	}

}
