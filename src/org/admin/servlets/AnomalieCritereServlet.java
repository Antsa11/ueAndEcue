package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.admin.beans.CandidatAnomalie;
import org.admin.dao.DaoFactory;
import org.admin.dao.CandidatDao;
import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;
import org.admin.beans.CritereAnomalie;

import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/anomalie_critere")
public class AnomalieCritereServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CandidatDao candidatDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnomalieCritereServlet() {
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
			//String content="etudiant/inscription.jsp";
			String content="../preinscription/anomalie_critere.jsp";
			request.setAttribute ("content",content);
			request.setAttribute ("title", "Anomalie");
			int annee=0;
			String choix="";
			String search="";
			if((request.getParameter("annee")!=null) && (!request.getParameter("annee").equals("Année")))
			annee=Integer.parseInt(request.getParameter("annee"));
			
			if((request.getParameter("choix")!=null)  && (!request.getParameter("choix").equals("Choix")))
			choix=request.getParameter("choix");
			
			if((request.getParameter("search")!=null)  && (!request.getParameter("search").equals("")))
			search=request.getParameter("search");
			
			
			CritereAnomalie critere= new CritereAnomalie(annee,choix,search);
			//System.out.println("Choix:"+choix);
			
			
			int page = 1;
			int recordsPerPage = 10;
			if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				List<CandidatAnomalie> candidats = candidatDao.afficheAnomalieCritere(critere,(page-1)*recordsPerPage,recordsPerPage);
				int noOfRecords = candidatDao.getNoOfRecords();
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				Pagination pageNumber=new Pagination(page,recordsPerPage, noOfPages);
				request.setAttribute("start", pageNumber.getStart());
				request.setAttribute("end", pageNumber.getEnd());
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				request.setAttribute("count_student",candidats.size());
				request.setAttribute("etudiants",candidats);
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
				this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
		}
		
	}

}
