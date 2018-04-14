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
import org.admin.dao.UeDao;
import org.admin.dao.ParcoursDao;
import org.admin.dao.MentionDao;
import org.admin.dao.DaoException;
import org.admin.dao.DaoFactory;

@WebServlet("/liste_parcours")
public class ListeParcours extends BaseServlet{
	private static final long serialVersionUID = 1L;
	// private MentionDao mentionDao;
	private ParcoursDao parcoursDao;
	private UeDao ueDao;

	public ListeParcours(){
		super();
	}

	public void init(){
		DaoFactory daoFactory = DaoFactory.getInstance();
		// mentionDao = daoFactory.getMentionDao();
		parcoursDao = daoFactory.getParcoursDao();
		ueDao = daoFactory.getUeDao();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("ceci est une servlet");
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		String content="../preinscription/list_parcours.jsp";
		request.setAttribute ("content",content);
		request.setAttribute ("title", "Parcours");
		try{
			HttpSession session = request.getSession();
			int id_mention = Integer.parseInt(request.getParameter("mention"));
			//System.out.println(id_mention);
			//System.out.println("Après parseInt");
			if(session.getAttribute("utilisateur") == null){
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
			}
			else{
				List<Parcours> parcours = parcoursDao.getAllParcours(id_mention);
				List<Ue> listUe = ueDao.getAllUeByMention(id_mention);
				request.setAttribute("ue", listUe);
				request.setAttribute("parcours", parcours);
			}
		} catch(DaoException e){
			request.setAttribute("erreur", e.getMessage());
		}
		//System.out.println("OK");
		this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
	}
}
