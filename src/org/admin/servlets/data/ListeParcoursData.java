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
import org.admin.dao.ParcoursDao;
import org.admin.dao.MentionDao;
import org.admin.dao.DaoException;
import org.admin.dao.DaoFactory;

@WebServlet("/liste_parcours_data")
public class ListeParcoursData extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private MentionDao mentionDao;
	private ParcoursDao parcoursDao;

	public ListeParcoursData(){
		super();
	}

	public void init(){
		DaoFactory daoFactory = DaoFactory.getInstance();
		mentionDao = daoFactory.getMentionDao();
		parcoursDao = daoFactory.getParcoursDao();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("ceci est une servlet");
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		String content="../inscription/parcours.jsp";
		request.setAttribute ("content",content);
		request.setAttribute ("title", "Parcours");
		try{
			HttpSession session = request.getSession();
			int id_mention = Integer.parseInt(request.getParameter("mention"));
			System.out.println(id_mention);
			//System.out.println("Après parseInt");
			if(session.getAttribute("utilisateur") == null){
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
			}
			else{
                // String nom_mention = mentionDao.getNomMention(id_mention);
				System.out.println("Block try");
				List<Parcours> parcours = parcoursDao.getAllParcoursByAuthorization(id_mention);
				System.out.println("parcours");
				request.setAttribute("parcours", parcours);
				if(parcours.size() != 0){
					System.out.println("OK");
				}
				else{
					System.out.println("NOT OKK");
				}
			}
		} catch(DaoException e){
			request.setAttribute("erreur", e.getMessage());
		}
		System.out.println("OK2");
		this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
	}
}
