package org.admin.servlets.data;


import org.admin.servlets.BaseServlet;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.http.HttpSession;


import org.admin.beans.Utilisateur;

import org.admin.dao.CandidatDao;
import org.admin.dao.DaoFactory;
import org.admin.dao.DaoException;
import org.admin.dao.UrlDao;

import java.io.*;
import java.sql.*;
@WebServlet("/resultInscript")

public class ResultatInscription extends BaseServlet{

	private static final long serialVersionUID = 1L;
 	
 	private CandidatDao candidatDao;
 	private UrlDao urlDao;

 	public ResultatInscription(){

 		super();
 	}

 	public void init() throws ServletException
	{
			DaoFactory daoFactory=DaoFactory.getInstance();
			candidatDao=daoFactory.getCandidatDao();
			urlDao = daoFactory.getUrlDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
 	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 		request.setCharacterEncoding("UTF-8");
		//String base_url="http://localhost/sciences";
		request.setAttribute("base_url",base_url);
	
		try{
			HttpSession session=request.getSession();
			System.out.println("This is the session status " + session.getAttribute("status"));
			boolean valid = (urlDao.validAccess(session, "/resultInscript"));
			if(valid == false)
			{
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
			}
			
			else
			{
				//String content="etudiant/inscription.jsp";
				String content="../inscriptionEtudiant/valid.jsp";
				request.setAttribute ("content",content);
				request.setAttribute ("title", "Resultat de l'inscription ");
		
				this.getServletContext().getRequestDispatcher("/WEB-INF/inscriptionEtudiant/valid.jsp").forward(request, response);
			}
			
		}catch(DaoException e){
			e.getMessage();
		}

 	}

 	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

 	}
}