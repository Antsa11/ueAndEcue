package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.admin.beans.Bachelier;
import org.admin.dao.DaoFactory;
import org.admin.dao.CandidatDao;
import org.admin.dao.DaoException;

@WebServlet("/inscription")
public class Inscription extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CandidatDao candidatDao;
	public Inscription() {
        super();
       
    }
    public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		candidatDao=daoFactory.getCandidatDao();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription/inscription.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		
		int num_bacc=Integer.parseInt(request.getParameter("num_bacc"));
		String nom=request.getParameter("nom");
		String prenoms=request.getParameter("prenoms");
		int annee=Integer.parseInt(request.getParameter("annee"));
		//String tel=request.getParameter("tel");
		Bachelier bachelier=new Bachelier();
		bachelier.setNum_bacc(num_bacc);
		bachelier.setNom_prenom(nom.trim().toUpperCase()+" "+prenoms.trim().toUpperCase());
		bachelier.setAnnee(annee);
		try
		{
			if(candidatDao.isBachelierValid(bachelier))
			{
				request.setAttribute("etudiant", candidatDao.getInfoBachelier(bachelier));
				this.getServletContext().getRequestDispatcher("/WEB-INF/inscription/confirmation.jsp").forward(request, response);
			}
		
			else
			{
				request.setAttribute("error", "Veuillez vérifier votre numéro!!");
				this.getServletContext().getRequestDispatcher("/WEB-INF/inscription/inscription.jsp").forward(request, response);
			}
		}
		catch(DaoException e)
		{
				request.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/inscription/inscription.jsp").forward(request, response);
		}

	}
	
}
