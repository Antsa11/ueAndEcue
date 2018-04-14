package org.admin.servlets;

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

@WebServlet("/import")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB

public class Import extends BaseServlet {
	private static final long serialVersionUID = 1L;
 	
 	private CandidatDao candidatDao; 
 	private UrlDao urlDao;
 	private static final String SAVE_DIR = "uploadFiles";
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Import() {
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
		
		try{

			HttpSession session=request.getSession();
			System.out.println("This is the session status " + session.getAttribute("status"));
			boolean valid = (urlDao.validAccess(session, "/import"));
			if(valid == false)
			{
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
			}
			
			else
			{
				String content="../admin/import.jsp";
				request.setAttribute ("content",content);
				this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);
			}
		}catch(DaoException e){
			request.setAttribute("erreur", e.getMessage());
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
         
			String type_import=request.getParameter("type_import");
			annee=Integer.parseInt(request.getParameter("annee"));
			int id_import=Integer.parseInt(type_import);
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
         
			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				
				fileName = new File(fileName).getName();
				//System.out.println("Fichier "+fileName);
				part.write(savePath + File.separator + fileName);
				this.saveInDB(appPath + SAVE_DIR + File.separator + fileName, id_import,userConnected,annee);
				break;
				
			
			}
			
			request.setAttribute("message", "Importation terminée avec succés");
			
			if(id_import==1)
			//content="bac/result.jsp";
			response.sendRedirect(base_url+"/result");
			else if(id_import==2)
			response.sendRedirect(base_url+"/preinscription");
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
	
	private void saveInDB(String saveFile,int id_import, Utilisateur userConnected, int annee)
	{
		Connection con=null;
		
		
		try{
			StringBuilder contents = new StringBuilder();
			BufferedReader input = new BufferedReader(new FileReader(saveFile));
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection
			("jdbc:postgresql://localhost/bdd2","postgres","123456");
			//insertInPreInscription(con, input);
			if(id_import==1)
			{
				insertInBac(con, input,userConnected,annee);
				
			}
			if(id_import==2)
			{
				insertInPreInscription(con, input,userConnected);
			}
		}
		catch(Exception e)
		{
			
			System.out.println("Erreur :"+e.getMessage());
			System.out.println("erreur le ");
		}
	}
	
	private void insertInPreInscription(Connection con, BufferedReader input, Utilisateur userConnected) throws IOException, SQLException, DaoException
	{
		Statement pst=null;
		String line = null;
		//25->obs ; 24->operateur; 10->serie_saisie
		String dossier,nom, prenom ,sexe, choix, annee, obs, serie_saisie, operateur;
		String centre,mmention, tel,ddn,nom_pere,nom_mere,adresse,lieu_naissance;
		float math,pc,sn,a;
		int b,c,d;
			pst=con.createStatement();
			PreparedStatement preparedStatement = null;
			
			Statement statement=null;
			line = input.readLine();
            dossier = line.split(":")[0];
			nom = line.split(":")[1];
			prenom = line.split(":")[2];
			sexe = line.split(":")[5];
			choix = line.split(":")[20];
			annee = line.split(":")[11];
			obs = line.split(":")[25];
			serie_saisie = line.split(":")[10];
			operateur = line.split(":")[24];
			
			//centre=line.split(":")[13];
			//centre=line.split(":")[13];
			while (( line = input.readLine()) != null){         
				dossier = line.split(":")[0];
				if(dossier.isEmpty())
				dossier="00";
				nom = line.split(":")[1];
				prenom = line.split(":")[2];
				sexe = line.split(":")[5];
				choix = line.split(":")[20];
				annee = line.split(":")[11];
				obs = line.split(":")[25];
				serie_saisie = line.split(":")[10];
				operateur = line.split(":")[24];
				
				centre=line.split(":")[13];
				tel=line.split(":")[22];
				ddn=line.split(":")[3];
				
				nom_pere=line.split(":")[7];
				nom_mere=line.split(":")[8];
				adresse=line.split(":")[21];
				lieu_naissance=line.split(":")[4];
				//math=Float.parseFloat((line.split(":")[14]).replace(',','.'))/Float.parseFloat((line.split(":")[15]).replace(',','.'));
				if(serie_saisie.trim().toUpperCase().equals("C"))
				{
					b=5;
					c=5;
					d=2;
				}
				else if(serie_saisie.trim().toUpperCase().equals("D"))
				{
					b=4;
					c=4;
					d=4;
				}
				else if(serie_saisie.trim().toUpperCase().equals("S"))
				{
					b=Integer.parseInt((line.split(":")[15]).replace(',','.'));;
					c=Integer.parseInt((line.split(":")[17]).replace(',','.'));;
					d=Integer.parseInt((line.split(":")[19]).replace(',','.'));;
				}
				
				else
				{
					b=3;
					c=3;
					d=1;
				}
				
				 if((line.split(":")[14]).isEmpty())
				 math=0;
				 else
				 {
					a=Float.parseFloat((line.split(":")[14]).replace(',','.'));
					//b=Float.parseFloat((line.split(":")[15]).replace(',','.'));
					math=a/b;
				 }
				 if((line.split(":")[16]).isEmpty())
				 pc=0;
				 else
				 {
					a=Float.parseFloat((line.split(":")[16]).replace(',','.'));
					//b=Float.parseFloat((line.split(":")[17]).replace(',','.'));
					pc=a/c;
				 }
				 if((line.split(":")[18]).isEmpty())
				 sn=0;
				 else
				 {
				    a=Float.parseFloat((line.split(":")[18]).replace(',','.'));
					//b=Float.parseFloat((line.split(":")[19]).replace(',','.'));
					sn=a/d;
				 }	
				
				 //moyenne = line.split(":")[24];
				//pc=Float.parseFloat((line.split(":")[16]).replace(',','.'))/Float.parseFloat((line.split(":")[17]).replace(',','.'));
				
				
				boolean isValid=candidatDao.isValid(nom.trim().toUpperCase()+" "+prenom.trim().toUpperCase());
				System.out.println("N°: "+dossier+" : "+nom+ " : "+prenom+" : "+obs+" : "+serie_saisie+" : "+operateur+" : ");
				preparedStatement = 
				con.prepareStatement
				("insert into t_import_saisie (n_dossier, nom, prenoms, genre, choix, annee, obs, serie_saisie, operateur, \"isValid\",centre, math,pc,sn, tel,ddn,nom_pere,nom_mere,adresse,lieu_naissance) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
				preparedStatement.setInt(1,Integer.parseInt(dossier));
				preparedStatement.setString(2,nom.trim().toUpperCase());
				preparedStatement.setString(3,prenom.trim().toUpperCase());
				preparedStatement.setString(4,sexe.trim().toUpperCase());
				preparedStatement.setString(5,choix.trim().toUpperCase());
				if(annee.isEmpty())
				annee="0";
				preparedStatement.setInt(6,Integer.parseInt(annee));
				preparedStatement.setString(7,obs.trim().toUpperCase());
				preparedStatement.setString(8,serie_saisie.trim().toUpperCase());
				preparedStatement.setString(9,operateur.trim().toUpperCase());
				preparedStatement.setBoolean(10,isValid);
				
				preparedStatement.setString(11,centre.trim().toUpperCase());
				preparedStatement.setFloat(12,math);
				preparedStatement.setFloat(13,pc);
				preparedStatement.setFloat(14,sn);
				preparedStatement.setString(15,tel.trim().toUpperCase());
				preparedStatement.setString(16,ddn.trim().toUpperCase());
				preparedStatement.setString(17,nom_pere.trim().toUpperCase());
				preparedStatement.setString(18,nom_mere.trim().toUpperCase());
				preparedStatement.setString(19,adresse.trim().toUpperCase());
				preparedStatement.setString(20,lieu_naissance.trim().toUpperCase());
				
				preparedStatement.executeUpdate();
				preparedStatement.close();
				
			}
			con.close();
	}
	private void insertInBac(Connection con, BufferedReader input, Utilisateur userConnected,int year) throws IOException, SQLException
	{
		Statement pst=null;
		String line = null;
		
		String centre,serie, num ,nom_prenom, genre, mg, pc, svt, moyenne, mention, ddn;   
			pst=con.createStatement();
			PreparedStatement preparedStatement = null;
			line = input.readLine();
            centre = line.split(":")[0];
			serie = line.split(":")[1];
			num = line.split(":")[2];
			nom_prenom = line.split(":")[3];
			genre = line.split(":")[4];
			mg = line.split(":")[5];
			pc = line.split(":")[6];
			svt = line.split(":")[7];
			moyenne = line.split(":")[8];
			mention = line.split(":")[9];
			ddn = line.split(":")[10];
			while (( line = input.readLine()) != null){         
				centre = line.split(":")[0];
				
				serie = line.split(":")[1];
				num = line.split(":")[2];
				nom_prenom = line.split(":")[3];
				genre = line.split(":")[4];
				mg = line.split(":")[5];
				pc = line.split(":")[6];
				svt = line.split(":")[7];
				if(svt.isEmpty())
				svt="0";
				
				
				moyenne = line.split(":")[8];
				mention = line.split(":")[9];
				if(mention.isEmpty())
				{
					//System.out.println(moyenne);
					float m=new Float(moyenne.replace(',','.'));
					if((m>=10)&& (m<12))
					mention="Passable";
					if((m>=12)&& (m<14))
					mention="Assez bien";
					if((m>=14)&& (m<16))
					mention="Bien";
					if((m>=16))
					mention="Très bien";
				}	
				ddn = line.split(":")[10];
				
				System.out.println(" Centre: "+centre+" : "+serie+ " : "+num+ " : "+nom_prenom+" : "+genre+" : "
				+mg+" : "+pc+" : "+svt+" : "+moyenne+" : "+mention+" : "+ddn);
				/*if((!mention.isEmpty()) ||
				 (!mention.isEmpty() &&(!svt.equals("ABS") || !mg.equals("ABS") || !pc.equals("ABS")))
				 )*/
				 if	(!mention.isEmpty() &&(!svt.equals("ABS") || !mg.equals("ABS") || !pc.equals("ABS")))
				 
				{
					preparedStatement = 
					//con.prepareStatement("insert into t_candidat (centre,serie,num_bacc,nom_prenom,mg, pc,sn,moyenne,mention,date_naissance) values(?,?,?,?,?,?,?,?,?,?);");
					con.prepareStatement("insert into t_candidat (centre,serie,num_bacc,nom_prenom,mention,annee, math, pc, sn,moyenne,creer_par,date_naissance) values(?,?,?,?,?,?,?,?,?,?,?,?);");
					preparedStatement.setString(1,centre.trim().toUpperCase());
					preparedStatement.setString(2,serie.trim().toUpperCase());
					preparedStatement.setInt(3,Integer.parseInt(num));
					preparedStatement.setString(4,nom_prenom.trim().toUpperCase());
					//preparedStatement.setString(4,nom_prenom.trim());
					preparedStatement.setString(5,mention.trim().toUpperCase());
					preparedStatement.setInt(6,year);
				
					mg = mg.replace(',', '.');
					pc = pc.replace(',', '.');
					//if(!serie.equals("TGI"))
					svt = svt.replace(',', '.');
					moyenne = moyenne.replace(',', '.');
					if(mg.equals("ABS"))
					mg="0";
					if(pc.equals("ABS"))
					pc="0";
					if(svt.equals("ABS"))
					svt="0";
					preparedStatement.setFloat(7,new Float(mg));
					preparedStatement.setFloat(8,new Float(pc));
					/*if(serie.equals("TGI"))
					preparedStatement.setFloat(9,0);
					else*/
					
					
					preparedStatement.setFloat(9,new Float(svt));
					preparedStatement.setFloat(10,new Float(moyenne));
					
					
					preparedStatement.setString(11,userConnected.getLogin().trim().toUpperCase());
					preparedStatement.setString(12,ddn.trim());
					preparedStatement.executeUpdate();
				}
				
			}
	}
	 
}
