package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.admin.beans.Candidat;
//import org.admin.beans.CandidatAnomalie;

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
@WebServlet("/modifier_obs_etudiant")
public class UpdateObsCandidatServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CandidatDao candidatDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateObsCandidatServlet() {
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
			//int annee=2017;
			
			
            int id_record=Integer.parseInt(request.getParameter("id_record"));
            String obs=request.getParameter("obs");
            String ddn=request.getParameter("ddn");
            Candidat candidat=new Candidat();
            candidat.setId_record(id_record);
            candidat.setObs(obs);
            candidat.setDdn(ddn);
            //CandidatAnomalie candidat=new Candidat();
			try
			{
				
				candidatDao.updateObs(candidat);
				
				
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
