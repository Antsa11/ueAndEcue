package org.admin.servlets.scolarite;

import org.admin.servlets.BaseServlet;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.admin.beans.scolarite.Mention;
import org.admin.beans.scolarite.Parcours;
import org.admin.beans.scolarite.Ue;
import org.admin.dao.ParcoursDao;
import org.admin.dao.MentionDao;
import org.admin.dao.EcueDao;
import org.admin.dao.UeDao;
import org.admin.dao.DaoException;
import org.admin.dao.DaoFactory;

@WebServlet("/ajoutEcue")
public class InsereEcueServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	private MentionDao mentionDao;
	private ParcoursDao parcoursDao;
	private EcueDao ecueDao;
	private UeDao ueDao;

	public InsereEcueServlet(){
		super();
	}

	public void init(){

		DaoFactory daoFactory = DaoFactory.getInstance();
		mentionDao = daoFactory.getMentionDao();
		parcoursDao = daoFactory.getParcoursDao();
		ecueDao = daoFactory.getEcueDao();
		ueDao = daoFactory.getUeDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		String content = "../preinscription/list_parcours.jsp";
		request.setAttribute ("content",content);
		request.setAttribute ("title", "Parcours");

		//Récupération des valeurs
		String nomEcue = request.getParameter("nomEcue");
		float credit = Float.parseFloat(request.getParameter("credite"));
		int id_parcours = Integer.parseInt(request.getParameter("id_parcours"));
		String nom_Resp = request.getParameter("nomRespEc");
		String niveau = request.getParameter("niveau");
		String ue = request.getParameter("valUe");
		System.out.println(ue);
		// int id_ue = Integer.parseInt(request.getParameter("ue"));
		// String mention = request.getParameter("mention");
		// System.out.println(mention);
		int id_mention = Integer.parseInt(request.getParameter("mention"));
		float sommeCre=0;
		try
		{
			HttpSession session = request.getSession();
			if(session.getAttribute("utilisateur") == null){
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
			}
			else{
				try{
					int id_ue = ueDao.getIdUe(ue, niveau);
					sommeCre=ecueDao.getSommeCreditEcue(id_parcours,niveau);
					if(sommeCre<=30){
						ecueDao.addEcue(nomEcue, id_ue, credit, nom_Resp, id_parcours, niveau);
						System.out.println("mety ka tafiditra");
					}else{
						content = "../erreur/erreur.jsp";
						request.setAttribute("content",content);
						this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request,response);
						System.out.println("efa mihoatra ilay credit nao fa jereo aloha ");
					}
					
				}
				catch(DaoException e){
					// throw DaoException("eerreur "+e.getMessage());
					System.out.println("efa manomboka");
				}
				List<Parcours> parcours = parcoursDao.getAllParcours(id_mention);
				List<Ue> listUe = ueDao.getAllUeByMention(id_mention);
				request.setAttribute("ue", listUe);
				request.setAttribute("parcours", parcours);
			}
			
			
		}catch(DaoException e){
			request.setAttribute("erreur", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
	}
}