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

@WebServlet("/loadInscriptionFromFile")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB

public class LoadInscriptionFromFile extends BaseServlet {
	private static final long serialVersionUID = 1L;

 	private CandidatDao candidatDao;
 	private UrlDao urlDao;
 	private static final String SAVE_DIR = "uploadFiles";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadInscriptionFromFile() {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String base_url="http://localhost/sciences";
		request.setAttribute("base_url",base_url);
		//HttpSession session=request.getSession();

		//try{

			HttpSession session=request.getSession();
			/*System.out.println("This is the session status " + session.getAttribute("status"));
			boolean valid = (urlDao.validAccess(session, "/import"));
			if(valid == false)
			{

				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
			}

			else
			{*/
				String content="../admin/import.jsp";
				request.setAttribute ("content",content);
				this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
			/*}
		}catch(DaoException e){
			request.setAttribute("erreur", e.getMessage());
		}*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String type = (String) request.getParameter("type_importation");
		//this.getServletContext().getRequestDispatcher("/vue/admin.jsp").forward(request, response);
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		HttpSession session=request.getSession();
		int annee=0;
		if(session.getAttribute("utilisateur")==null)
		{

		        this.getServletContext().getRequestDispatcher("/WEB-INF/vue/utilisateur/login.jsp").forward(request, response);
		}

		else
		{

			String appPath = request.getServletContext().getRealPath("");
			//HttpSession session=request.getSession();
			Utilisateur userConnected=(Utilisateur)session.getAttribute("utilisateur");
			String savePath = appPath + File.separator + SAVE_DIR;

			String niveau=request.getParameter("niveau");
			String annee_u=request.getParameter("annee_u");
			//int id_import=Integer.parseInt(type_import);
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);

				fileName = new File(fileName).getName();

				part.write(savePath + File.separator + fileName);
				this.saveInDB(appPath + SAVE_DIR + File.separator + fileName, niveau,userConnected,annee_u, type, response);
				break;


			}

			request.setAttribute("message", "Importation terminée avec succés");

			//if(id_import==1)
			//content="bac/result.jsp";
			response.sendRedirect(base_url+"/accueil");
			//else if(id_import==2)
			//response.sendRedirect(base_url+"/preinscription");
			//request.setAttribute ("content",content);
			//this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
		}
	}


    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

	private void saveInDB(String saveFile,String niveau, Utilisateur userConnected, String annee_u, String type, HttpServletResponse response)
	{
		Connection con=null;
		try{
			StringBuilder contents = new StringBuilder();
			BufferedReader input = new BufferedReader(new FileReader(saveFile));
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection
			("jdbc:postgresql://localhost/bddn","postgres","123456");
			//insertInPreInscription(con, input);

      //				insertInDB(con, input,userConnected,niveau,annee_u);
      System.out.println(type);
      int typeInt = Integer.parseInt(type);
      if (typeInt == 1) //inscription
      {
        insertInDB(con, input,userConnected,niveau,annee_u);
      }
      else if(typeInt == 2){ // resultat
        insertInDBResultat(con, input,userConnected,niveau,annee_u);
      }
      else
      {
        response.sendRedirect("/import");
      }
		}
		catch(Exception e)
		{

			System.out.println("Erreur :"+e.getMessage());
		}
	}

	// private void insertInDB(Connection con, BufferedReader input, Utilisateur userConnected, String niveau,String annee_u) throws IOException, SQLException, DaoException
	// {
  //
	// 	Statement pst=null;
	// 	String line = null;
	// 	//25->obs ; 24->operateur; 10->serie_saisie
	// 	int num;
	// 	String nom_etu, prenom_etu,dth_etu,lieu_etud,sexe_etud,adresse,tel,niveau_etu,anne_niveau,prom_insc,mention,parcours,form_inscr="",situation,spec_inscr="";
	// 		pst=con.createStatement();
	// 		PreparedStatement preparedStatement = null;
  //
	// 		Statement statement=null;
	// 		line = input.readLine();
  //           while (( line = input.readLine()) != null){
	// 			String n=line.split("#")[0];
	// 			if(n.isEmpty())
	// 			num=0;
	// 			else
	// 			num = Integer.parseInt(n);
	// 			nom_etu = line.split("#")[1];
	// 			prenom_etu = line.split("#")[2];
	// 			dth_etu = line.split("#")[3];
	// 			lieu_etud = line.split("#")[4];
	// 			sexe_etud = line.split("#")[5];
	// 			adresse = line.split("#")[6];
	// 			tel = line.split("#")[7];
	// 			niveau_etu = line.split("#")[8];
  //
	// 			anne_niveau=line.split("#")[9];
	// 			prom_insc=line.split("#")[10];
	// 			mention=line.split("#")[11];
  //
	// 			parcours=line.split("#")[12];
	// 			form_inscr=line.split("#")[13];
	// 			try{
	// 			situation=line.split("#")[14];
	// 			}
	// 			catch(Exception e)
	// 			{
	// 				//System.out.println("Erreur:"+e.getMessage());
	// 				situation="";
	// 			}
	// 			//if(situation.isEmpty()||situation==null)
	// 			//situation="";
	// 			//if(line.split(":")[15])
  //
	// 			try{
	// 			spec_inscr=line.split("#")[15];
	// 			}
	// 			catch(Exception e)
	// 			{
	// 				//System.out.println("Erreur:"+e.getMessage());
	// 					spec_inscr="";
	// 			}
	// 			//System.out.println(num+" "+nom+" "+ddn+" "+lieu_naissance+" "+sexe+" "+adresse+" "+tel+" "+grade+" "+annee_niveau+" "+annee_u);
	// 			//if(!specialite.isEmpty())
	// 			System.out.println(num+" "+nom_etu+" "+prenom_etu);
	// 			//else
	// 			//System.out.println(num+" "+nom+" "+ddn+" "+mention+" "+parcours+" "+form_inscr+" "+situation);
	// //String nom, prenom,ddn,lieu_naissance,sexe,adresse,tel,grade,annee_niveau,mention,parcours,form_inscr,situation="",specialite="";
	// 			preparedStatement = con.prepareStatement("insert into t_resultats (num, nom_etu, prenom_etu, dth_etu,lieu_etud, sexe_etud, adresse,tel,niveau,anne_niveau, prom_insc,mention,parcours,form_inscr,situation, spec_inscr) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
	// 			preparedStatement.setInt(1,num);
	// 			preparedStatement.setString(2,nom_etu.trim().toUpperCase());
	// 			preparedStatement.setString(3,prenom_etu.trim());
	// 			preparedStatement.setString(4,dth_etu.trim());
	// 			preparedStatement.setString(5,lieu_etud.trim());
  //
	// 			preparedStatement.setString(6,sexe_etud.trim());
  //
	// 			preparedStatement.setString(7,adresse.trim());
	// 			preparedStatement.setString(8,tel.trim());
	// 			preparedStatement.setString(9,niveau_etu.trim().toUpperCase());
  //
	// 			preparedStatement.setString(10,anne_niveau.trim());
	// 			preparedStatement.setString(11,prom_insc.trim().toUpperCase());//Mention,Parcours,form_inscr,Situation, spec_inscr
	// 			preparedStatement.setString(12,mention.trim().toUpperCase());
	// 			preparedStatement.setString(13,parcours.trim().toUpperCase());
	// 			preparedStatement.setString(14,form_inscr.trim().toUpperCase());
	// 			preparedStatement.setString(15,situation.trim().toUpperCase());
	// 			preparedStatement.setString(16,spec_inscr.trim().toUpperCase());
  //
  //
	// 			preparedStatement.executeUpdate();
	// 			preparedStatement.close();
  //
	// 		}
	// 		con.close();
	// }
  private void insertInDB(Connection con, BufferedReader input, Utilisateur userConnected, String niveau,String annee_u) throws IOException, SQLException, DaoException
	{
		Statement pst=null;
		String line = null;
		//25->obs ; 24->operateur; 10->serie_saisie
		int num_insc;
		String nom_insc, prenom_insc,ddn_insc,lieu_naissance_insc,sexe_insc,adresse_insc,tel_insc,grade_insc,annee_niveau_insc,mention_insc,parcours_insc,form_inscr_insc,situation_insc="",specialite_insc="";
			pst=con.createStatement();
			PreparedStatement preparedStatement = null;

			Statement statement=null;
			line = input.readLine();
            while (( line = input.readLine()) != null){
				String n=line.split("#")[0];
				if(n.isEmpty())
				num_insc=0;
				else
				num_insc = Integer.parseInt(n);
				nom_insc = line.split("#")[1];
				prenom_insc = line.split("#")[2];
				ddn_insc = line.split("#")[3];
				lieu_naissance_insc = line.split("#")[4];
				sexe_insc = line.split("#")[5];
				adresse_insc = line.split("#")[6];
				tel_insc = line.split("#")[7];
				grade_insc = line.split("#")[8];

				annee_niveau_insc=line.split("#")[9];
				annee_u=line.split("#")[10];
				mention_insc=line.split("#")[11];

				parcours_insc=line.split("#")[12];
				form_inscr_insc=line.split("#")[13];
				try{
				situation_insc=line.split("#")[14];
				}
				catch(Exception e)
				{
					//System.out.println("Erreur:"+e.getMessage());
					situation_insc="";
				}
				//if(situation.isEmpty()||situation==null)
				//situation="";
				//if(line.split(":")[15])

				try{
				specialite_insc=line.split("#")[15];
				}
				catch(Exception e)
				{
					//System.out.println("Erreur:"+e.getMessage());
						specialite_insc="";
				}
				//System.out.println(num+" "+nom+" "+ddn+" "+lieu_naissance+" "+sexe+" "+adresse+" "+tel+" "+grade+" "+annee_niveau+" "+annee_u);
				//if(!specialite.isEmpty())
				System.out.println(num_insc+" "+nom_insc+" "+ddn_insc+" "+mention_insc+" "+parcours_insc+" "+form_inscr_insc+" "+situation_insc+" "+specialite_insc);
				//else
				//System.out.println(num+" "+nom+" "+ddn+" "+mention+" "+parcours+" "+form_inscr+" "+situation);
	//String nom, prenom,ddn,lieu_naissance,sexe,adresse,tel,grade,annee_niveau,mention,parcours,form_inscr,situation="",specialite="";
				preparedStatement = con.prepareStatement("insert into t_new_inscription (num_xls, nom, prenom, ddn,lieu_naissance, sexe, adresse,tel,grade,annee_u,mention, parcours,abr_parcours,situation,specialite,niveau) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
				preparedStatement.setInt(1,num_insc);
				preparedStatement.setString(2,nom_insc.trim().toUpperCase());
				preparedStatement.setString(3,prenom_insc.trim());
				preparedStatement.setString(4,ddn_insc.trim());
				preparedStatement.setString(5,lieu_naissance_insc.trim());

				preparedStatement.setString(6,sexe_insc.trim());

				preparedStatement.setString(7,adresse_insc.trim());
				preparedStatement.setString(8,tel_insc.trim());
				preparedStatement.setString(9,grade_insc.trim().toUpperCase());

				preparedStatement.setString(10,annee_u.trim());
				preparedStatement.setString(11,mention_insc.trim().toUpperCase());
				preparedStatement.setString(12,parcours_insc.trim().toUpperCase());
				preparedStatement.setString(13,form_inscr_insc.trim().toUpperCase());
				preparedStatement.setString(14,situation_insc.trim().toUpperCase());
				preparedStatement.setString(15,specialite_insc.trim().toUpperCase());
				preparedStatement.setString(16,niveau.trim().toUpperCase());


				preparedStatement.executeUpdate();
				preparedStatement.close();

			}
			con.close();
	}
  private void insertInDBResultat(Connection con, BufferedReader input, Utilisateur userConnected, String niveau,String annee_u) throws IOException, SQLException, DaoException
	{
		Statement pst=null;
		String line = null;
		//25->obs ; 24->operateur; 10->serie_saisie
		int num;
		String nom_etu, prenom_etu,dth_etu,lieu_etud,sexe_etud,adresse,tel,niveau_etu,anne_niveau,prom_insc,mention,parcours,form_inscr="",situation,spec_inscr="";
			pst=con.createStatement();
			PreparedStatement preparedStatement = null;

			Statement statement=null;
			line = input.readLine();
            while (( line = input.readLine()) != null){
				String n=line.split("#")[0];
				if(n.isEmpty())
				num=0;
				else
				num = Integer.parseInt(n);
				nom_etu = line.split("#")[1];
				prenom_etu = line.split("#")[2];
				dth_etu = line.split("#")[3];
				lieu_etud = line.split("#")[4];
				sexe_etud = line.split("#")[5];
				adresse = line.split("#")[6];
				tel = line.split("#")[7];
				niveau_etu = line.split("#")[8];

				anne_niveau=line.split("#")[9];
				prom_insc=line.split("#")[10];
				mention=line.split("#")[11];

				parcours=line.split("#")[12];
				form_inscr=line.split("#")[13];
				try{
				situation=line.split("#")[14];
				}
				catch(Exception e)
				{
					//System.out.println("Erreur:"+e.getMessage());
					situation="";
				}
				//if(situation.isEmpty()||situation==null)
				//situation="";
				//if(line.split(":")[15])

				try{
				spec_inscr=line.split("#")[15];
				}
				catch(Exception e)
				{
					//System.out.println("Erreur:"+e.getMessage());
						spec_inscr="";
				}
				//System.out.println(num+" "+nom+" "+ddn+" "+lieu_naissance+" "+sexe+" "+adresse+" "+tel+" "+grade+" "+annee_niveau+" "+annee_u);
				//if(!specialite.isEmpty())
				System.out.println(num+" "+nom_etu+" "+prenom_etu);
				//else
				//System.out.println(num+" "+nom+" "+ddn+" "+mention+" "+parcours+" "+form_inscr+" "+situation);
	//String nom, prenom,ddn,lieu_naissance,sexe,adresse,tel,grade,annee_niveau,mention,parcours,form_inscr,situation="",specialite="";
				preparedStatement = con.prepareStatement("insert into t_resultats (num, nom_etu, prenom_etu, dth_etu,lieu_etud, sexe_etud, adresse,tel,niveau,anne_niveau, prom_insc,mention,parcours,form_inscr,situation, spec_inscr) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
				preparedStatement.setInt(1,num);
				preparedStatement.setString(2,nom_etu.trim().toUpperCase());
				preparedStatement.setString(3,prenom_etu.trim());
				preparedStatement.setString(4,dth_etu.trim());
				preparedStatement.setString(5,lieu_etud.trim());

				preparedStatement.setString(6,sexe_etud.trim());

				preparedStatement.setString(7,adresse.trim());
				preparedStatement.setString(8,tel.trim());
				preparedStatement.setString(9,niveau_etu.trim().toUpperCase());

				preparedStatement.setString(10,anne_niveau.trim());
				preparedStatement.setString(11,prom_insc.trim().toUpperCase());//Mention,Parcours,form_inscr,Situation, spec_inscr
				preparedStatement.setString(12,mention.trim().toUpperCase());
				preparedStatement.setString(13,parcours.trim().toUpperCase());
				preparedStatement.setString(14,form_inscr.trim().toUpperCase());
				preparedStatement.setString(15,situation.trim().toUpperCase());
				preparedStatement.setString(16,spec_inscr.trim().toUpperCase());


				preparedStatement.executeUpdate();
				preparedStatement.close();

			}
			con.close();
	}


}
