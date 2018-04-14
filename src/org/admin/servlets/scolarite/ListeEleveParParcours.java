package org.admin.servlets.scolarite;

import org.admin.servlets.BaseServlet;
import org.admin.beans.scolarite.Inscription;
import org.admin.beans.scolarite.Student;
import org.admin.beans.scolarite.Cursus;
import org.admin.beans.scolarite.Carte; //Carte des étudiants 
import org.admin.dao.DaoFactory;
import org.admin.dao.CursusDao;
import org.admin.dao.CarteDao; //Carte des étudiants 
import org.admin.dao.StudentDao;
import org.admin.dao.ParcoursDao;
import org.admin.dao.DaoException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.ArrayList;

@WebServlet("/list_etudiant")
public class ListeEleveParParcours extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;
	private ParcoursDao parcoursDao;
	private CursusDao cursusDao;
	private CarteDao  carteDao;

	public ListeEleveParParcours(){
		super();
	}

	public void init(){
		DaoFactory daoFactory = DaoFactory.getInstance();
		studentDao = daoFactory.getStudentDao();
		cursusDao = daoFactory.getCursusDao();
		carteDao = daoFactory.getCarteDao();
		parcoursDao = daoFactory.getParcoursDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//System.out.println("ceci est une servlet");
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		String content="../preinscription/list_etudiant.jsp";
		request.setAttribute ("content",content);
		request.setAttribute ("title", "Liste par parcours");
		try{
			HttpSession session = request.getSession();
			//System.out.println("bloc try");
			String niveau = request.getParameter("niveau");
			int parcours = Integer.parseInt(request.getParameter("id_parcours"));
			//System.out.println(parcours);
			//System.out.println("Après parseInt");
			if(session.getAttribute("utilisateur") == null){
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
			}
			else{
				// System.out.println("ato anaty ELSE");
				List<Student> students = studentDao.getAllStudentByParcours(parcours,niveau);
				List<Cursus> cursus =  cursusDao.getCursus();
				List<Carte> carte = carteDao.getCarte();
				
				request.setAttribute("etudiants", students);
				request.setAttribute("niveau", niveau);
				request.setAttribute("nom_parcours", students.get(0).getNom_parcours());
				
				request.setAttribute("cursus", cursus);
				request.setAttribute("carte", carte);
			}
		} catch(DaoException e){
			request.setAttribute("erreur", e.getMessage());
		}
		//System.out.println("OK");
		this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
	}
}