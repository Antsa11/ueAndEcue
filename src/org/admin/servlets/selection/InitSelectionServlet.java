package org.admin.servlets.selection;

import org.admin.servlets.BaseServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import org.admin.dao.DaoFactory;
import org.admin.dao.CandidatDao;
import org.admin.dao.CritereDao;
import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;


import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/reinit_selection")
public class InitSelectionServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CandidatDao candidatDao;
    private CritereDao critereDAO;
    public InitSelectionServlet() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		candidatDao=daoFactory.getCandidatDao();
		critereDAO=daoFactory.getCritereDao();
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
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//this.getServletContext().getRequestDispatcher("/vue/admin.jsp").forward(request, response);
		request.setCharacterEncoding("UTF-8");
		String content="../preinscription/selection.jsp";
		request.setAttribute ("content",content);
		request.setAttribute ("title", "Reinitialisation");
		request.setAttribute("base_url",base_url);
		
		int id_vague=Integer.parseInt(request.getParameter("id_vague"));
		
		//candidatDao. 
		try{
		candidatDao.reinitSelection(id_vague);
		critereDAO.reinitCriterePublished(id_vague);
		}
		catch(DaoException e)
		{
			request.setAttribute("error", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
	}

}
