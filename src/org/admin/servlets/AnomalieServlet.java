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
import org.admin.dao.UrlDao;


import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/anomalie")
public class AnomalieServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CandidatDao candidatDao;
    private UrlDao urlDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnomalieServlet() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		candidatDao=daoFactory.getCandidatDao();
		urlDao = daoFactory.getUrlDao();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String base_url="http://localhost/sciences";
		request.setAttribute("base_url",base_url);
		// HttpSession session=request.getSession();
		
		try{
			
			HttpSession session=request.getSession();
			System.out.println("This is the session status " + session.getAttribute("status"));
			boolean valid = (urlDao.validAccess(session, "/anomalie"));
			if(valid == false)
			{
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
			}
			
			else
			{
				//String content="etudiant/inscription.jsp";
				String content="../preinscription/anomalie.jsp";
				request.setAttribute ("content",content);
				request.setAttribute ("title", "Anomalie");
				int page = 1;
				int recordsPerPage = 10;
				if(request.getParameter("page") != null)
	            page = Integer.parseInt(request.getParameter("page"));
				try
				{
					//List<Etudiant> etudiants=etudiantDao.lister();
					List<CandidatAnomalie> candidats = candidatDao.printAnomalie((page-1)*recordsPerPage,recordsPerPage);
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
			
		}catch(DaoException e){
			request.setAttribute("erreur", e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//this.getServletContext().getRequestDispatcher("/vue/admin.jsp").forward(request, response);
		request.setCharacterEncoding("UTF-8");
		
	}

}
