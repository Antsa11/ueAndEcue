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
@WebServlet("/valider_inscription")
public class ValiderInscriptionPortail extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private EtudiantDao etudiantDao;
    private InscriptionDao inscriptionDao;
    private UtilisateurDao utilisateurDao;
    //private StatPreinscriptionDao statPreinscriptionDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiderInscriptionPortail() {
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
			String content="../preinscription/list_inscription_finale.jsp";
			request.setAttribute ("content",content);
			
			
			Utilisateur utilisateur= (Utilisateur) session.getAttribute("utilisateur");
			String fonction=utilisateurDao.getUserFonction(utilisateur);
			if(fonction.equals("Pat"))
				request.setAttribute("menu","../layout/menu_pat.jsp");
				
			else
				request.setAttribute("menu","../layout/full_menu.jsp");
			
			
			int id_inscription=Integer.parseInt(request.getParameter("id_record"));
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
            EtudiantPortail etudiant=new EtudiantPortail();
            //candidat.setNom(nom);
            etudiant.setId_inscription(id_inscription);
            etudiant.setObs(obs.trim());
            //etudiant.setTel(num_recu);
            etudiant.setChoix(choix.trim());
            etudiant.setNum_recu(num_recu);
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				
				etudiantDao.validerInscription(etudiant,annee_univ.trim(), userConnected, num_recu);
				List<EtudiantPortail> etudiants=inscriptionDao.getAllFinalInscriptionOnPortail();
				
				request.setAttribute("etudiants",etudiants);
				request.setAttribute("count_svt",inscriptionDao.countEtudiantSubsribedPerPortail("SVT"));
				request.setAttribute("count_mi",inscriptionDao.countEtudiantSubsribedPerPortail("MI"));
				request.setAttribute("count_pc",inscriptionDao.countEtudiantSubsribedPerPortail("PC"));
			}
			catch(DaoException e)
			{
				
				System.out.println("erreur"+e.getMessage());
				
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
		}
		
	}

}
