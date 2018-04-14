package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.admin.beans.Candidat;
import org.admin.dao.DaoFactory;
import org.admin.dao.CandidatDao;
import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;
import org.admin.beans.CritereRecherche;

import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/resultat_search")
public class ResultatSearchServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CandidatDao candidatDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultatSearchServlet() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		candidatDao=daoFactory.getCandidatDao();
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
			String content="../preinscription/result_selection.jsp";
			request.setAttribute ("content",content);
			request.setAttribute ("title", "Résultat");
			int annee=0;
			String choix="";
			String search="";
			if((request.getParameter("annee")!=null) && (!request.getParameter("annee").equals("Année")))
			annee=Integer.parseInt(request.getParameter("annee"));
			
			if((request.getParameter("choix")!=null)  && (!request.getParameter("choix").equals("Choix")))
			choix=request.getParameter("choix");
			
			if((request.getParameter("search")!=null)  && (!request.getParameter("search").equals("")))
			search=request.getParameter("search");
			
			
			CritereRecherche critere= new CritereRecherche(annee,choix,search);
			//System.out.println("Choix:"+choix);
			
			
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				List<Candidat> candidats = candidatDao.getAllCandidatSelected(critere);
				request.setAttribute("count_student",candidats.size());
				request.setAttribute("etudiants",candidats);
				request.setAttribute("count_svt",candidatDao.countCandidatSelectedPerPortail("SVT"));
				request.setAttribute("count_mi",candidatDao.countCandidatSelectedPerPortail("MI"));
				request.setAttribute("count_pc",candidatDao.countCandidatSelectedPerPortail("PC"));
				
				request.setAttribute("count_c",candidatDao.countCandidatSelectedPerSerie("C"));
				request.setAttribute("count_d",candidatDao.countCandidatSelectedPerSerie("D"));
				request.setAttribute("count_s",candidatDao.countCandidatSelectedPerSerie("S"));
				request.setAttribute("count_tgi",candidatDao.countCandidatSelectedPerSerie("TGI"));
				
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
				this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
		}
		
	}

}
