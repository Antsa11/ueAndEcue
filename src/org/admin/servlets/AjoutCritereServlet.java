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


import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/ajout_critere")
public class AjoutCritereServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CritereDao critereDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutCritereServlet() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		critereDao=daoFactory.getCritereDao();
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
		/*String description="";
		String condition="";
		String portail="";*/
		if(session.getAttribute("utilisateur")==null)
		{
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
		}
		
		else
		{
			//String content="etudiant/inscription.jsp";
			String content="../preinscription/selection.jsp";
			
			
			
			request.setAttribute ("content",content);
			request.setAttribute ("title", "Anomalie");
			/*
			Utilisateur utilisateur= (Utilisateur) session.getAttribute("utilisateur");
			String fonction=utilisateurDao.getUserFonction(utilisateur);
			if(fonction.equals("Pat"))
				request.setAttribute("menu","../layout/menu_pat.jsp");
				
			else
				request.setAttribute("menu","../layout/full_menu.jsp");
			*/
		
			String description="";
			String condition="";
			String portail=""; 
			String critere="";
			String anneBac="";
			String serie="";
			String mentBac=""; 
			float noteMath=0;
			float notePc=0;
			float noteSvt=0;
			float moyenne=0; 
			String opera1="";
			String opera2="";
			String opera3="";
			String opera4="";
			String opera5="";

			anneBac=(String) request.getParameter("anne_baccAdd");
			serie = request.getParameter("serieAdd");
			mentBac = request.getParameter("mentionAdd");
			noteMath= Float.parseFloat(request.getParameter("note_mathAdd"));
			notePc= Float.parseFloat(request.getParameter("note_pcAdd"));
			noteSvt= Float.parseFloat(request.getParameter("note_svtAdd"));
			opera1 = (String) request.getParameter("opera1Add");
			opera2= (String) request.getParameter("opera2Add");
			opera3 = (String) request.getParameter("opera3Add");
			opera4 = (String) request.getParameter("opera4Add");
			opera5 = (String) request.getParameter("opera5Add");
			description=request.getParameter("descriptionAdd");
			portail = request.getParameter("portailAdd");
			int id_vague=Integer.parseInt(request.getParameter("id_vagueAdd"));
			
			if(noteMath>=10 && !portail.isEmpty() && !opera1.isEmpty() && !serie.isEmpty() && !opera2.isEmpty() && !anneBac.isEmpty() && !opera3.isEmpty() && !mentBac.isEmpty() && !opera4.isEmpty()){
				
				condition="CHOIX="+"'"+portail+"'"+ " "+opera1+ " SERIE="+"'"+serie+"'"+" "+ opera2 + " annee='"+anneBac+"' "+
				opera3+" MENTION=" + "'"+mentBac+"'"+ " " +opera4+ " math>=10";
			
			}else{

				response.sendRedirect(base_url+"/selection?v="+id_vague);
			}

			Critere c= new Critere();
			c.setDescription(description);
			c.setCondition(condition);
			c.setPortail(portail);
			c.setId_vague(id_vague);

			try
			{
				//List<Critere> criteres=critereDao.getCriteresWithCandidat();
				if(!portail.equals("Portail") || !condition.equals("") || !description.equals(""))
				critereDao.ajouter(c);
				//request.setAttribute("criteres",criteres);
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
			//this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
			response.sendRedirect(base_url+"/selection?v="+id_vague);
		}
		
	}

}
