package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.admin.beans.Utilisateur;
import org.admin.utils.Outils;

import org.admin.beans.Utilisateur;
import org.admin.dao.DaoFactory;
import org.admin.dao.UtilisateurDao;


@WebServlet("/login")
public class Login extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
	public Login() {
        super();
       
    }
    public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		utilisateurDao=daoFactory.getUtilisateurDao();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		String login=(String) request.getParameter("loginUsername");
		String password=(String) request.getParameter("loginPassword");
		
		String title="Faculté des Sciences | Accueil";
		request.setAttribute ("title",title);
		//request.setAttribute("login", login);
		//request.setAttribute("password",password);
		String content="../admin/accueil.jsp";
		request.setAttribute ("content",content);
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setLogin(login);
		utilisateur.setPassword(Outils.md5(password));
		if(utilisateurDao.isValid(utilisateur))
		{
			HttpSession session=request.getSession();
			utilisateur.setId_utilisateur(utilisateurDao.getUserId(utilisateur));
			session.setAttribute("utilisateur", utilisateur);
			session.setAttribute("login", login);
			
			String fonction=utilisateurDao.getUserFonction(utilisateur);
			session.setAttribute("status", fonction);
			if(fonction.trim().equals("Enseignant|Bac"))
				response.sendRedirect(base_url+"/result");
			else
				response.sendRedirect(base_url+"/accueil");
		}
		else
		{
			String error="Utilisateur invalide";
			request.setAttribute("error",error);
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
		}

	}
	
}
