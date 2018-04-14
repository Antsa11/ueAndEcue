package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.admin.utils.Outils;

import org.admin.beans.Personnel;
import org.admin.dao.DaoFactory;
import org.admin.dao.PersonnelDao;
import org.admin.dao.DaoException;



@WebServlet("/add_user")
public class InscriptionServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private PersonnelDao personnelDao;
	public InscriptionServlet() {
        super();
       
    }
    public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		personnelDao=daoFactory.getPersonnelDao();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/register.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		
		String username=request.getParameter("username");
		String password=request.getParameter("password1");
		String nom=request.getParameter("nom");
		String prenoms=request.getParameter("prenoms");
		String fonction=request.getParameter("fonction");
		
		Personnel personnel=new Personnel();
		personnel.setLogin(username);
		personnel.setPassword(Outils.md5(password));
		personnel.setNom(nom);
		personnel.setPrenoms(prenoms);
		personnel.setFonction(fonction);
		try
		{
		personnelDao.ajouterPersonnel(personnel);
		System.out.println("Login:"+username+" Password:"+password);
		}
		catch (DaoException e)
		{
			System.out.println("Erreur SQL:"+e.getMessage());
		}
		//System.out.println("Vita");
		this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
	
		//System.out.println("Login:"+username+" Password:"+password);
		
		
		
		
		
		
	}
	
		


	
}
