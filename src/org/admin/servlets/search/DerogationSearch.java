package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.admin.beans.EtudiantPortail;
import org.admin.dao.DaoFactory;
import org.admin.dao.DerogationDao;
import org.admin.dao.UrlDao;
import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;
import org.admin.beans.Derogation;
import org.admin.beans.CritereRecherche;

import org.admin.utils.Pagination;

@WebServlet("/search_derogation")
public class DerogationSearch extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private UrlDao urlDao;
	private CritereRecherche critere;
	DerogationDao derogationDao;


	public void init(){
		DaoFactory daoFactory = DaoFactory.getInstance();
		derogationDao = daoFactory.getDerogationDao();
		urlDao = daoFactory.getUrlDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		String content="../preinscription/list_search_derogation.jsp";
		request.setAttribute ("content",content);
		request.setAttribute ("title", "Dérogation");

		try{
			HttpSession session = request.getSession();
			System.out.println("This is the session status " + session.getAttribute("status"));
			boolean valid = (urlDao.validAccess(session, "/derogation"));
			if(valid == false){
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
			}
			else{
				// String choix = request.getParameter("choix");
				// String search = request.getParameter("search");
				if((!request.getParameter("choix").equals("Choix"))){
					String choix = request.getParameter("choix");
					String search = request.getParameter("search");
					List<Derogation> liste = derogationDao.getAllDerogationPerPortail(choix, search);
					request.setAttribute("etudiants", liste);
				}
				if((request.getParameter("search") != null) && (request.getParameter("choix").equals("Choix"))){
					String search = request.getParameter("search");
					List<Derogation> liste = derogationDao.getAllDerogationBySearch(search);
					request.setAttribute("etudiants", liste);

				}
 					request.setAttribute("count_svt",derogationDao.countEtudiantDerogatedPerPortail("SVT"));
					request.setAttribute("count_mi",derogationDao.countEtudiantDerogatedPerPortail("MI"));
					request.setAttribute("count_pc",derogationDao.countEtudiantDerogatedPerPortail("PC"));
				// request.setAttribute("etudiants", liste);


				// if(search == null){
				// 	if(choix != "Choix"){
				// 		List<Derogation> liste = derogationDao.getAllDerogationPerPortail(choix);
				// 	}
				// }
				// if(search != null){
				// 	if(choix == "Choix"){
				// 		List<Derogation> liste = derogationDao.getAllDerogationBySearch(search);
				// 	}
				// 	else{
				// 		List<Derogation> liste = derogationDao.getAllDerogationPerPortail(choix, search);
				// 	}
				// }
				// // if( choix != "Choix" && search != null){
				// 	List<Derogation> liste = derogationDao.getAllDerogationPerPortail(choix, search);
				// }
				// if(search != null && choix == "Choix"){
				// 	List<Derogation> liste = derogationDao.getAllDerogationBySearch(search);
				// }
				// if(search == null && choix != "Choix"){
				// 	List<Derogation> liste = derogationDao.getAllDerogationPerPortail(choix);
				// }


			}
		} catch(DaoException e){
			request.setAttribute("erreur", e.getMessage());
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
	}
}
