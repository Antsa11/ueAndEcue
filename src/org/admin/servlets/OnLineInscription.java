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

@WebServlet("/validate_inscription")
public class OnLineInscription extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CandidatDao candidatDao;
	public OnLineInscription() {
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
		int annee=Integer.parseInt(request.getParameter("annee"));
		//String tel=request.getParameter("tel");
		Bachelier bachelier=new Bachelier();
		bachelier.setNum_bacc(num_bacc);
		bachelier.setAnnee(annee);
		bachelier.setChoix(request.getParameter("choix"));
		bachelier.setTel(request.getParameter("tel"));
		bachelier.setNom_prenom(request.getParameter("nom_prenom"));
		
		//System.out.println("Num:"+num_bacc+" Annee"+annee);
		try
		{
			request.setAttribute("msg", "Inscription terminée!  Déposer votre dossier complémentaire au bureau de la scolarité de la Faculté des Sciences.");
			candidatDao.insertToSaisieEnline(bachelier);
			
		}
		catch(DaoException e)
		{
				request.setAttribute("error", e.getMessage());
				
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription/inscription_termine.jsp").forward(request, response);

	}
	
}
