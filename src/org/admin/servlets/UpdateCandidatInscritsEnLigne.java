package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.admin.beans.Candidat;
import org.admin.beans.stat.StatPreinscription;

import org.admin.dao.DaoFactory;
import org.admin.dao.CandidatDao;

import org.admin.dao.stat.StatPreinscriptionDao;

import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;


import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/updateOnlineStudent")
public class UpdateCandidatInscritsEnLigne extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CandidatDao candidatDao;
    
    private StatPreinscriptionDao statPreinscriptionDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCandidatInscritsEnLigne() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		candidatDao=daoFactory.getCandidatDao();
		statPreinscriptionDao=daoFactory.getStatPreinscriptionDao();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String base_url="http://localhost/sciences";
		request.setAttribute("base_url",base_url);
		HttpSession session=request.getSession();
		
		if(session.getAttribute("utilisateur")==null)
		{
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
		}
		
		else
		{
			//String content="etudiant/inscription.jsp";
			//String content="../preinscription/stat_preinscrit.jsp";
			String content="../preinscription/enligne.jsp";
			request.setAttribute ("content",content);
			int page = 1;
			int recordsPerPage = 5;
			if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
            
            String nom=null,prenoms=null,obs=null, tel=null;
            nom=request.getParameter("nom");
            prenoms=request.getParameter("prenoms");
            obs=request.getParameter("obs");
            tel=request.getParameter("tel");
            Candidat candidat=new Candidat();
            candidat.setNom(nom);
            candidat.setPrenoms(prenoms);
            candidat.setObs(obs);
            candidat.setTel(tel);
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				
				candidatDao.updateCandidatInscritsEnLigne(candidat);
				List<Candidat> candidats = candidatDao.inscritsEnLigne((page-1)*recordsPerPage,recordsPerPage);
				StatPreinscription stat=statPreinscriptionDao.getStatPreinscription();
				int noOfRecords = candidatDao.getNoOfRecords();
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				Pagination pageNumber=new Pagination(page,recordsPerPage, noOfPages);
				request.setAttribute("start", pageNumber.getStart());
				request.setAttribute("end", pageNumber.getEnd());
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				request.setAttribute("count_student",candidats.size());
				request.setAttribute("etudiants",candidats);
				//request.setAttribute("stat",stat);
			}
			catch(DaoException e)
			{
				
				System.out.println("erreur"+e.getMessage());
				//request.setAttribute("erreur", e.getMessage());
			}
				this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		//String base_url="http://localhost/sciences";
		request.setAttribute("base_url",base_url);
		HttpSession session=request.getSession();
		
		if(session.getAttribute("utilisateur")==null)
		{
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
		}
		
		else
		{
			//String content="etudiant/inscription.jsp";
			//String content="../preinscription/stat_preinscrit.jsp";
			String content="../preinscription/enligne.jsp";
			request.setAttribute ("content",content);
			int page = 1;
			int recordsPerPage = 5;
			if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
            
            String nom=null,prenoms=null,obs=null, tel=null,choix=null;
            int annee=2017;
            nom=request.getParameter("nom");
            prenoms=request.getParameter("prenom");
            obs=request.getParameter("obs");
            tel=request.getParameter("tel");
            choix=request.getParameter("choix");
            annee=Integer.parseInt(request.getParameter("annee"));
            Candidat candidat=new Candidat();
            candidat.setNom(nom);
            candidat.setPrenoms(prenoms);
            candidat.setObs(obs);
            candidat.setTel(tel);
            candidat.setChoix(choix);
            candidat.setAnnee(annee);
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				
				candidatDao.updateCandidatInscritsEnLigne(candidat);
				List<Candidat> candidats = candidatDao.inscritsEnLigne((page-1)*recordsPerPage,recordsPerPage);
				StatPreinscription stat=statPreinscriptionDao.getStatPreinscription();
				int noOfRecords = candidatDao.getNoOfRecords();
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				Pagination pageNumber=new Pagination(page,recordsPerPage, noOfPages);
				request.setAttribute("start", pageNumber.getStart());
				request.setAttribute("end", pageNumber.getEnd());
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				request.setAttribute("count_student",candidats.size());
				request.setAttribute("etudiants",candidats);
				//request.setAttribute("stat",stat);
			}
			catch(DaoException e)
			{
				
				System.out.println("erreur"+e.getMessage());
				//request.setAttribute("erreur", e.getMessage());
			}
				this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
		}
		
	}

}
