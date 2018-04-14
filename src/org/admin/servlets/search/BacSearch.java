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
import org.admin.beans.CritereBac;

import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/bac_search")
public class BacSearch extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private BachelierDao bachelierDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BacSearch() {
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
			String content="../bac/all_result.jsp";
			request.setAttribute ("content",content);
			
			String serie=null,mention=null,nom_prenom=null;
			int annee=2017;
			
			if(!request.getParameter("annee").equals("annee"))
            annee = Integer.parseInt(request.getParameter("annee"));
            
            if(!request.getParameter("serie").equals("Série"))
            serie = request.getParameter("serie");
            
            if(!request.getParameter("mention").equals("Mention"))
            mention = request.getParameter("mention");
            
            if(request.getParameter("search")!=null)
            nom_prenom = request.getParameter("search");
            
            CritereBac critere=new CritereBac();
            critere.setAnnee(annee);
            critere.setSerie(serie);
            critere.setMention(mention);
            critere.setNom_prenom(nom_prenom);
            
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				List<Bachelier> bacheliers = bachelierDao.search(critere);
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
			String content="../bac/all_result.jsp";
			request.setAttribute ("content",content);
			
			String serie=null,mention=null,nom_prenom=null;
			int annee=2017;
			
			/*if(!request.getParameter("annee").equals("annee"))
            annee = Integer.parseInt(request.getParameter("annee"));
            
            if(!request.getParameter("serie").equals("Série"))
            serie = request.getParameter("serie");
            
            if(!request.getParameter("mention").equals("Mention"))
            mention = request.getParameter("mention");
            */
            if(request.getParameter("search")!=null)
            nom_prenom = request.getParameter("search");
            //mention=request.getParameter("search");
            CritereBac critere=new CritereBac();
            //critere.setAnnee(annee);
            //critere.setSerie(serie);
            //critere.setMention(mention);
            critere.setNom_prenom(nom_prenom.trim().toUpperCase());
            
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				List<Bachelier> bacheliers = bachelierDao.search(critere);
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

}
