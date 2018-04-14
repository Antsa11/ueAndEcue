package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.admin.beans.Bachelier;
import org.admin.dao.DaoFactory;
import org.admin.dao.BachelierDao;
import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;


import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/result")
public class Bac extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private BachelierDao bachelierDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bac() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		bachelierDao=daoFactory.getBachelierDao();
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
			String content="../bac/result.jsp";
			request.setAttribute ("content",content);
			int page = 1;
			int recordsPerPage = 10;
			if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				List<Bachelier> bacheliers = bachelierDao.afficher((page-1)*recordsPerPage,recordsPerPage);
				int noOfRecords = bachelierDao.getNoOfRecords();
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				Pagination pageNumber=new Pagination(page,recordsPerPage, noOfPages);
				request.setAttribute("start", pageNumber.getStart());
				request.setAttribute("end", pageNumber.getEnd());
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				request.setAttribute("count_student",bacheliers.size());
				request.setAttribute("etudiants",bacheliers);
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
				this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
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
