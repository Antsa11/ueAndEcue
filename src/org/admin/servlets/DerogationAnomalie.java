package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.admin.beans.Candidat;
import org.admin.beans.Utilisateur;
import org.admin.beans.Derogation;

import org.admin.dao.DaoFactory;
import org.admin.dao.CandidatDao;
import org.admin.dao.DerogationDao;


import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;
import org.admin.beans.CritereAnomalie;

import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/derogation_anomalie")
public class DerogationAnomalie extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CandidatDao candidatDao;
    
    private DerogationDao derogationDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DerogationAnomalie() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		candidatDao=daoFactory.getCandidatDao();
		derogationDao=daoFactory.getDerogationDao();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		HttpSession session=request.getSession();
		
		if(session.getAttribute("utilisateur")==null)
		{
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
		}
		
		else
		{
			//String content="etudiant/inscription.jsp";
			String content="../preinscription/list_derogation.jsp";
			request.setAttribute ("content",content);
			request.setAttribute ("title", "Dérogation");
			//int annee=2017;
			
	        //CandidatAnomalie candidat=new Candidat();
			try
			{
				
				List<Derogation> etudiants=derogationDao.getAllDerogation();
                request.setAttribute("count_svt",derogationDao.countEtudiantDerogatedPerPortail("SVT"));
				request.setAttribute("count_mi",derogationDao.countEtudiantDerogatedPerPortail("MI"));
				request.setAttribute("count_pc",derogationDao.countEtudiantDerogatedPerPortail("PC"));
				request.setAttribute("etudiants",etudiants);
				
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
			//	response.sendRedirect(base_url+"/resultat_preinscription");
		}
		
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
			//String content="etudiant/inscription.jsp";
			String content="../preinscription/list_derogation.jsp";
			request.setAttribute ("content",content);
			request.setAttribute ("title", "Dérogation");
			//int annee=2017;
			int vague=2;
			
			Utilisateur userConnected=(Utilisateur) session.getAttribute("utilisateur");
            int id_record=Integer.parseInt(request.getParameter("id_record"));
            vague=Integer.parseInt(request.getParameter("vague"));
            String obs=request.getParameter("obs");
            Candidat candidat=new Candidat();
            candidat.setId_record(id_record);
            candidat.setObs(obs);
            //CandidatAnomalie candidat=new Candidat();
			try
			{
				
				//candidatDao.updateObs(candidat);
				candidatDao.validDerogationAnomalie(candidat,userConnected,vague);
				List<Derogation> etudiants=derogationDao.getAllDerogation();
                request.setAttribute("count_svt",derogationDao.countEtudiantDerogatedPerPortail("SVT"));
				request.setAttribute("count_mi",derogationDao.countEtudiantDerogatedPerPortail("MI"));
				request.setAttribute("count_pc",derogationDao.countEtudiantDerogatedPerPortail("PC"));
				request.setAttribute("etudiants",etudiants);
				
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
			//response.sendRedirect(base_url+"/resultat_preinscription");
		}
		
	}

}
