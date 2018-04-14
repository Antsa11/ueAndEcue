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
import org.admin.dao.DaoFactory;
import org.admin.dao.DaoException;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

import java.util.List;
import java.util.ArrayList;

import java.io.*;
import java.sql.*;


@WebServlet("/importPhoto")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)
                    // 50MB
public class ImportPhoto extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;
	private static final String SAVE_DIR = "img";

	public ImportPhoto (){
		super();
	}

	public void init(){
		DaoFactory daoFactory = DaoFactory.getInstance();
		studentDao = daoFactory.getStudentDao();
		// cursusDao = daoFactory.getCursusDao();
		// carteDao = daoFactory.getCarteDao();
		// parcoursDao = daoFactory.getParcoursDao();
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		// String photo = request.getParameter("file");
		Part photo = request.getPart("file");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		InputStream inputStream = null;

		System.out.println(photo +" "+ nom +" "+ prenom);

		//Enregistrement de l'image de l'etudiant		

		try{

			if (photo != null) {
				long fileSize = photo.getSize();
				 // String fileName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
				String fileName = getSubmittedFileName(photo);
				inputStream = photo.getInputStream();
				System.out.println(fileName +" "+ nom +" "+ prenom);
				//this.addPhotoEtudiant(fileName,nom,prenom);

				// fileName = "img/"+fileName;

				String appPath = request.getServletContext().getRealPath("");
				String savePath = appPath + File.separator + SAVE_DIR;
				File fileSaveDir = new File(savePath);
				System.out.println(fileSaveDir+ " "+savePath);


				//SAUVER NOTRE IMAGE 

				if (fileSaveDir.exists()) {
					fileSaveDir.mkdir();
					System.out.println("mety le izy ");
				}else{
					System.out.println("tena tsy tafa ");
				}

				System.out.println(appPath + SAVE_DIR + File.separator + fileName);
				studentDao.insererPhotoEtudiant(appPath + SAVE_DIR + File.separator + fileName,nom,prenom);
				// String fileName = extractFileName(part);	

				fileName = new File(fileName).getName();
				//System.out.println("Fichier "+fileName);
				photo.write(savePath + File.separator + fileName);
					//this.saveInDB(appPath + SAVE_DIR + File.separator + fileName, id_import,userConnected,annee);
				
			}else{
				System.out.println("i say good bye");
			}
			
			// System.out.println(photo +" "+ nom +" "+ prenom);
		}
		catch(DaoException e){
			// request.setAttribute("erreur" + e.getMessage());
			System.out.println("mety an ");
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);

	}

	// public void addPhotoEtudiant(String nomFichier,String nom,String prenom) throws DaoException{

	// 	Student etudiant = new Student();
	// 	Connection connexion = null;
	// 	try
	// 	{
	// 		Class.forName("org.postgresql.Driver");

	// 		PreparedStatement preparedStatement = null;
	// 		Statement statement = null;
	// 		String query = "UPDATE t_etudiants SET photo=? WHERE nom=? AND prenom=?";
	// 		//String query = "INSERT INTO t_etudiants (photo) VALUES(?) WHERE nom=? AND prenom=?";
	// 		try{
	// 			connexion=DriverManager.getConnection("jdbc:postgresql://localhost/bddn","postgres","123456");
	// 			// connexion = daoFactory.getConnection();
	// 			preparedStatement = connexion.prepareStatement(query);
	// 			preparedStatement.setString(1,nomFichier);
	// 			preparedStatement.setString(2,nom);
	// 			preparedStatement.setString(3,prenom);
	// 			preparedStatement.executeUpdate();
	// 			preparedStatement.close();
	// 		}
	// 		catch(SQLException e)
	// 		{
	// 			throw new DaoException("Erreur sql ngamba le izy : " +e.getMessage()); 
	// 		}

	// 	}
	// 	catch(ClassNotFoundException e)
	// 	{

	// 	}

		
	// }

	private static String getSubmittedFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}

 }