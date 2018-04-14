// package org.admin.servlets;

// import java.io.IOException;
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import java.util.List;
// import org.admin.dao.DaoFactory;
// import org.admin.dao.UeDao;
// import org.admin.utils.Outils;
// import javax.servlet.http.HttpSession;
// import org.admin.dao.DaoException;


// import org.admin.utils.Pagination;

// /**
//  * Servlet implementation class Accueil
//  * 
//  */
// @WebServlet("/ajoutUe")
// public class AjoutUe extends BaseServlet {
// 	private static final long serialVersionUID = 1L;
//     private UeDao ueDao;
//     /**
//      * @see HttpServlet#HttpServlet()
//      */
//     public AjoutUe() {
//         super();
       
//     }
// 	public void init() throws ServletException
// 	{
// 		DaoFactory daoFactory = DaoFactory.getInstance();
// 		ueDao = daoFactory.getUeDao();
// 	}
// 	/**
// 	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
// 	 */
// 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 		request.setCharacterEncoding("UTF-8");
// 		}

// 	/**
// 	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
// 	 */
// 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 		ue = request.getParameter("donne");
		
		
// 	}

// }
