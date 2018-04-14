package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.admin.beans.Personnel;


import org.admin.dao.DaoFactory;
import org.admin.dao.UtilisateurDao;



import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;


import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/delete_user")
public class UserDelServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private UtilisateurDao utilisateurDao;
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDelServlet() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		utilisateurDao=daoFactory.getUtilisateurDao();
		
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
			//String content="../preinscription/stat_preinscrit.jsp";
			String content="../utilisateur/user.jsp";
			request.setAttribute ("content",content);
			int id_utilisateur=Integer.parseInt(request.getParameter("id_utilisateur"));
			try
			{
				utilisateurDao.delete(id_utilisateur);
				List<Personnel> personnels = utilisateurDao.getAllPersonnel();
				
				request.setAttribute("personnels",personnels);
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
				this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
		}
	
		
	}

}
