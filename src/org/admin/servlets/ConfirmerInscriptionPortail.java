package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.admin.beans.Etudiant;
import org.admin.beans.EtudiantPortail;
import org.admin.beans.Utilisateur;

import org.admin.dao.DaoFactory;
import org.admin.dao.EtudiantDao;
import org.admin.dao.InscriptionDao;
import org.admin.dao.UtilisateurDao;


import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;


import org.admin.utils.Pagination;

/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/confirmer_inscription")
public class ConfirmerInscriptionPortail extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private EtudiantDao etudiantDao;
    private InscriptionDao inscriptionDao;
    private UtilisateurDao utilisateurDao;
    //private StatPreinscriptionDao statPreinscriptionDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmerInscriptionPortail() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		etudiantDao=daoFactory.getEtudiantDao();
		inscriptionDao=daoFactory.getInscriptionDao();
		utilisateurDao=daoFactory.getUtilisateurDao();
		//statPreinscriptionDao=daoFactory.getStatPreinscriptionDao();
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
			String content="../preinscription/list_confirmation_inscription.jsp";
			request.setAttribute ("content",content);
			
			Utilisateur utilisateur= (Utilisateur) session.getAttribute("utilisateur");
			String fonction=utilisateurDao.getUserFonction(utilisateur);
			if(fonction.equals("Pat"))
				request.setAttribute("menu","../layout/menu_pat.jsp");
				
			else
				request.setAttribute("menu","../layout/full_menu.jsp");
			try
			{
				List<EtudiantPortail> etudiants=inscriptionDao.getAllInscriptionOnPortail();
				request.setAttribute("etudiants",etudiants);
				request.setAttribute("count_svt",inscriptionDao.countEtudiantConfirmedPerPortail("SVT"));
				request.setAttribute("count_mi",inscriptionDao.countEtudiantConfirmedPerPortail("MI"));
				request.setAttribute("count_pc",inscriptionDao.countEtudiantConfirmedPerPortail("PC"));
			}
			catch(DaoException e)
			{
				
				System.out.println("erreur"+e.getMessage());
				
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
			String content="../preinscription/list_confirmation_inscription.jsp";
			request.setAttribute ("content",content);
			
			
			Utilisateur utilisateur= (Utilisateur) session.getAttribute("utilisateur");
			String fonction=utilisateurDao.getUserFonction(utilisateur);
			if(fonction.equals("Pat"))
				request.setAttribute("menu","../layout/menu_pat.jsp");
				
			else
				request.setAttribute("menu","../layout/full_menu.jsp");
			
			
			int id_record=Integer.parseInt(request.getParameter("id_record"));
            String num_recu=request.getParameter("num_recu");
            String obs=request.getParameter("obs");
            if(num_recu.isEmpty())
            {
				obs="Sans numéro de reçu";
				obs=obs.toUpperCase();
			}
            String choix=request.getParameter("choix");
            Utilisateur userConnected=(Utilisateur) session.getAttribute("utilisateur");
            String annee_univ="2017-2018";
            Etudiant etudiant=new Etudiant();
            //candidat.setNom(nom);
            etudiant.setId_record(id_record);
            etudiant.setObs(obs.trim());
            //etudiant.setTel(num_recu);
            etudiant.setChoix(choix.trim());
            
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				
				etudiantDao.confirmerInscription(etudiant,annee_univ.trim(), userConnected, num_recu);
				List<EtudiantPortail> etudiants=inscriptionDao.getAllInscriptionOnPortail();
				
				request.setAttribute("etudiants",etudiants);
				request.setAttribute("count_svt",inscriptionDao.countEtudiantConfirmedPerPortail("SVT"));
				request.setAttribute("count_mi",inscriptionDao.countEtudiantConfirmedPerPortail("MI"));
				request.setAttribute("count_pc",inscriptionDao.countEtudiantConfirmedPerPortail("PC"));
			}
			catch(DaoException e)
			{
				
				System.out.println("erreur"+e.getMessage());
				
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
		}
		
	}

}
