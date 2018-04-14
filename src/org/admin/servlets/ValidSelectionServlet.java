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


import org.admin.utils.Pagination;


/*import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;*/


/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/valid_selection_from_critere")
public class ValidSelectionServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CandidatDao candidatDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidSelectionServlet() {
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
		request.setAttribute("base_url",base_url);
		HttpSession session=request.getSession();
		/*
		if(session.getAttribute("utilisateur")==null)
		{
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
		}
		
		else
		{
			
			String content="../preinscription/result_critere.jsp";
			request.setAttribute ("content",content);
			
			String id=request.getParameter("id");
			int id_critere=Integer.parseInt(id);
			
			int page = 1;
			int recordsPerPage = 5000;
			if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
			try
			{
				
				//System.out.println("Valid_selection_servlet");
				candidatDao.validSelectionFromCritere(id_critere);
				
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
			//this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
			response.sendRedirect(base_url+"/login");
		}*/
		
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
			
			String content="../preinscription/result_critere.jsp";
			request.setAttribute ("content",content);
			
			String id=request.getParameter("id_critere");
			int id_critere=Integer.parseInt(id);
			
			
			try
			{
				
				//System.out.println("Valid_selection_servlet");
				candidatDao.validSelectionFromCritere(id_critere);
				
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
			//this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
			response.sendRedirect(base_url+"/resultat_preinscription");
		}
		
		
	}

}
