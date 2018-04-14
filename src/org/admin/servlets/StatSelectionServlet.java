package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.admin.beans.Critere;
import org.admin.dao.DaoFactory;
import org.admin.dao.CritereDao;
import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;
import org.admin.dao.UrlDao;


import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/selection")
public class StatSelectionServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CritereDao critereDao;
    private UrlDao urlDao;
   
    public StatSelectionServlet() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		critereDao=daoFactory.getCritereDao();
		urlDao = daoFactory.getUrlDao();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		
		try{
			HttpSession session=request.getSession();
			System.out.println("This is the session status " + session.getAttribute("status"));
			boolean valid = (urlDao.validAccess(session, "/selection"));
			if(valid == false)
			{
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
			}
			
			else
			{
				//String content="etudiant/inscription.jsp";
				String content="../preinscription/selection.jsp";
				request.setAttribute ("content",content);
				request.setAttribute ("title", "Anomalie");
				String v=request.getParameter("v");
				int vague=0;
	            if(v!=null)
	            vague=Integer.parseInt(v);
				try
				{
					//List<Critere> criteres=critereDao.getCriteresWithCandidat();
					List<Critere> criteres=critereDao.getCriteresWithCandidat(vague);
					request.setAttribute("criteres",criteres);
				}
				catch(DaoException e)
				{
					request.setAttribute("erreur", e.getMessage());
				}
				request.setAttribute("id_vague",vague);
				this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
			}
			
		}catch(DaoException e){
			e.getMessage();
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
