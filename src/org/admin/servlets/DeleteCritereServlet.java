package org.admin.servlets;

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ByteArrayOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.util.List;
import org.admin.beans.Candidat;
import org.admin.dao.DaoFactory;
import org.admin.dao.CritereDao;
import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;


import org.admin.utils.Pagination;




@WebServlet("/delete_selection")
public class DeleteCritereServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CritereDao critereDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCritereServlet() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		critereDao=daoFactory.getCritereDao();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String base_url="http://localhost/sciences";
		request.setAttribute("base_url",base_url);
		HttpSession session=request.getSession();
		
		if(session.getAttribute("utilisateur")==null)
		{
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
		}
		
		}
		/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//this.getServletContext().getRequestDispatcher("/vue/admin.jsp").forward(request, response);
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		HttpSession session=request.getSession();
		
		if(session.getAttribute("utilisateur")==null)
		{
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
		}
		
		else
		{
			//String content="../admin/accueil.jsp";
			//request.setAttribute ("content",content);
			String id=request.getParameter("id_critere");
			String content="../preinscription/selection.jsp";
			request.setAttribute ("content",content);
			String DEST = "/opt/tomcat/webapps/admin/pdf/result.csv";
			
			int id_critere=Integer.parseInt(id);
			//System.out.println("id_critere="+id_critere);
			int page = 1;
			int recordsPerPage = 10;
			if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
			    critereDao.delete(id_critere);
				
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
			//this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
			response.sendRedirect("http://localhost/admin/selection");
		}
		
	}
	
	private void createExcel(String DEST, List<Candidat> candidats)
	{
	
		try
		{
			FileWriter fw = new FileWriter(DEST);
			fw.append("Nom et Prénoms");
			fw.append(';');
			fw.append("Moyenne");
			fw.append(';');
			fw.append("SVT");
			fw.append('\n');
			for(int i = 0; i < candidats.size() ; i++){
					fw.append(candidats.get(i).getNom_prenom());
					fw.append(';');
					fw.append(Float.toString(candidats.get(i).getMoyenne()));
					fw.append(';');
					fw.append(Float.toString(candidats.get(i).getSn()));
					fw.append('\n');
			}
			
			fw.flush();
			fw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
