package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.admin.beans.Candidat;
import org.admin.dao.DaoFactory;
import org.admin.dao.CandidatDao;
import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;


import org.admin.utils.Pagination;


/*import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;*/


/**
 * Servlet implementation class Accueil
 * 
 */
@WebServlet("/resultat_critere")
public class ResultatCritereServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CandidatDao candidatDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultatCritereServlet() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		candidatDao=daoFactory.getCandidatDao();
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
		
		else
		{
			
			String content="../preinscription/result_critere.jsp";
			request.setAttribute ("content",content);
			
			String id=request.getParameter("id");
			int id_critere=Integer.parseInt(id);
			
			int page = 1;
			int recordsPerPage = 5000;
			if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				List<Candidat> candidats = candidatDao.getListFromCritere(id_critere,(page-1)*recordsPerPage,recordsPerPage);
				int noOfRecords = candidatDao.getNoOfRecords();
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				Pagination pageNumber=new Pagination(page,recordsPerPage, noOfPages);
				request.setAttribute("start", pageNumber.getStart());
				request.setAttribute("end", pageNumber.getEnd());
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				request.setAttribute("count_student",candidats.size());
				request.setAttribute("etudiants",candidats);
				request.setAttribute("id",id_critere);
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
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
			String content="../preinscription/result_critere.jsp";
			request.setAttribute ("content",content);
			
			int id_critere=Integer.parseInt(id);
			//System.out.println("id_critere="+id_critere);
			int page = 1;
			int recordsPerPage = 10;
			if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				List<Candidat> candidats = candidatDao.getListFromCritere(id_critere,(page-1)*recordsPerPage,recordsPerPage);
				int noOfRecords = candidatDao.getNoOfRecords();
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				Pagination pageNumber=new Pagination(page,recordsPerPage, noOfPages);
				request.setAttribute("start", pageNumber.getStart());
				request.setAttribute("end", pageNumber.getEnd());
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				request.setAttribute("count_student",candidats.size());
				request.setAttribute("etudiants",candidats);
				request.setAttribute("id",id_critere);
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
		}
		
	}

}
