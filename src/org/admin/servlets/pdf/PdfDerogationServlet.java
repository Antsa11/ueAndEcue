package org.admin.servlets.pdf;


import org .admin.servlets.BaseServlet;

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;

import org.admin.beans.Derogation;
import org.admin.dao.DaoFactory;
import org.admin.dao.DerogationDao;
import org.admin.utils.Outils;
import javax.servlet.http.HttpSession;
import org.admin.dao.DaoException;


//import org.admin.utils.Pagination;

import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
//import com.itextpdf.text.HeaderFooter;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
//import com.itextpdf.tool.xml.ElementList;
//import com.itextpdf.tool.xml.XMLWorkerHelper;


@WebServlet("/pdf_derogation")
public class PdfDerogationServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private DerogationDao derogationDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdfDerogationServlet() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		derogationDao=daoFactory.getDerogationDao();
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
			//String content="../admin/accueil.jsp";
			//request.setAttribute ("content",content);
			String choix=request.getParameter("choix");
			String content="../preinscription/result_critere.jsp";
			request.setAttribute ("content",content);
			//String DEST = "/opt/tomcat/webapps/admin/pdf/resultat_selection.pdf";
			String DEST = "/opt/tomcat/webapps/admin/pdf/resultat_selection.pdf";
			
			//int id_vague=Integer.parseInt(request.getParameter("v"));
			
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				
				//System.out.println("Test ");
				List<Derogation> candidats = derogationDao.getAllDerogationPerPortail(choix);
				File file = new File(DEST);
				file.getParentFile().mkdirs();
				try{
				createPdf(DEST,candidats);
				}
				catch(DocumentException e)
				{
					System.out.println("PDF error: "+e.getMessage() );
				}
			}
			catch(DaoException e)
			{
				//request.setAttribute("erreur", e.getMessage());
				System.out.println("SQL Error: "+e.getMessage() );
			}
			this.getServletContext().getRequestDispatcher("/pdf/resultat_selection.pdf").forward(request, response);
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
		
		
	}
	
	
	
	public void createPdf(String dest, List<Derogation> candidats) throws IOException, DocumentException {
        //Document document = new Document(PageSize.A4.rotate());
        Document document = new Document();
         //PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(dest));
        // Rotate event = new Rotate();
		//	writer.setPageEvent(event);
		
		// creation of the different writers
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
            writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
 
            HeaderFooterDerogation event = new HeaderFooterDerogation(candidats);
            writer.setPageEvent(event);
		
		  // document.setMargins(20, 10, 40, 40);
		   document.setMargins(40, 40, 20, 50);
          
           document.addCreationDate();
           document.addCreator("Faculté des Sciences");
           document.addTitle("Pré-selection 2018");
           document.addSubject("Résultat - 1ère Vague");
           document.addAuthor("Faculté des Sciences - Université d'Antananarivo");

		
        document.open();
        
        Image img = Image.getInstance(base_url+"/img/logo_scs.png");
        //document.add(new Paragraph("Sample 1: This is simple image demo."));
        img.setAbsolutePosition(50f, 725f);
        img.scaleToFit(100,100);
        document.add(img);
        
        
        Image img2 = Image.getInstance(base_url+"/img/logo_ua.png");
        //document.add(new Paragraph("Sample 1: This is simple image demo."));
        img2.setAbsolutePosition(450f, 725f);
        //img2.setAlignment(Image.RIGHT);
        img2.scaleToFit(100,100);
        document.add(img2);
        
        Font font = new Font(FontFamily.HELVETICA, 10);
        Font font1 = new Font(FontFamily.HELVETICA, 11);
        Font font2 = new Font(FontFamily.HELVETICA, 11,Font.BOLD);
        
        Font font3 = new Font(FontFamily.HELVETICA, 12,Font.BOLD);
        Font font4 = new Font(FontFamily.HELVETICA, 16,Font.BOLD);
        
        Paragraph titre_U =new Paragraph("    Université d'Antananarivo",font4);
        titre_U.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titre_U);
        
        Paragraph Fac_s =new Paragraph("    Faculté des Sciences",font3);
        Fac_s.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(Fac_s);
        
        
        //document.add();
        Paragraph Domaine_s=new Paragraph("    Domaine Sciences et Technologies",font2);
        Domaine_s.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(Domaine_s);
      
        document.add(new Paragraph("    ",font1));
        
        
        Paragraph anneeU = new Paragraph(" Année Universitaire 2017-2018 ",font2);
        Paragraph titre=new Paragraph("    Liste des étudiants selectionés par dérogation en L1 - "+candidats.get(0).getChoix(),font2);
        /* String textList="";
        if(id_vague==1)
        textList="Première liste";
        if(id_vague==2)
        textList="Deuxième liste";
        if(id_vague==3)
        textList="Troisième liste";
        */
        
        //Paragraph titreListe=new Paragraph(textList,font2);
        
        titre.setAlignment(Paragraph.ALIGN_CENTER);
        anneeU.setAlignment(Paragraph.ALIGN_CENTER);
        
        document.add(anneeU);
        document.add(titre);
        //document.add(titreListe);
        document.add(new Paragraph("  ",font1));
        
        float[] columnWidths = {2,18, 14};
        PdfPTable table = new PdfPTable(columnWidths);
        
        
        Font font_titre = new Font(FontFamily.HELVETICA, 11, Font.BOLD);
        
        Paragraph t_null = new Paragraph(" ",font_titre);
        PdfPCell cell_t_null = new PdfPCell();
        cell_t_null.addElement(t_null);
        table.addCell(cell_t_null);
        
        
        Paragraph t_nom = new Paragraph("Nom et Prénoms",font_titre);
        PdfPCell cell_t_nom = new PdfPCell();
        cell_t_nom.addElement(t_nom);
        table.addCell(cell_t_nom);
        
        
      
		
		/*Paragraph t_ddn = new Paragraph("Date de naissance",font_titre);
        PdfPCell cell_t_ddn = new PdfPCell();
        cell_t_ddn.addElement(t_ddn);
        table.addCell(cell_t_ddn);*/
        
        /*Paragraph t_recu = new Paragraph("Reçu Banque",font_titre);
        PdfPCell cell_t_recu = new PdfPCell();
        cell_t_recu.addElement(t_recu);
        table.addCell(cell_t_recu);*/
        
        /*Paragraph t_mention = new Paragraph("Mention",font_titre);
        PdfPCell cell_t_mention = new PdfPCell();
        cell_t_mention.addElement(t_mention);
        table.addCell(cell_t_mention);*/
        
        
        Paragraph t_rem = new Paragraph("Remarque",font_titre);
         t_rem.setAlignment(Paragraph.ALIGN_CENTER);
        PdfPCell cell_t_rem = new PdfPCell();
        cell_t_rem.addElement(t_rem);
        table.addCell(cell_t_rem);
		
		//cell_nom.addElement(nom);
        for(int i = 0; i < candidats.size() ; i++){
			Paragraph nom_prenom = new Paragraph(candidats.get(i).getNom()+" "+candidats.get(i).getPrenoms(),font);
			//Paragraph moyenne = new Paragraph(Float.toString(candidats.get(i).getMoyenne()),font);
			
			
			Paragraph rems = new Paragraph(candidats.get(i).getObs(),font);
			//System.out.println("PDF:"+candidats.get(i).getDdn());
			//Paragraph ddn = new Paragraph(candidats.get(i).getDdn(),font);
			
			
			
			 Paragraph t_num = new Paragraph(""+(i+1),font);
			 PdfPCell cell_t_num = new PdfPCell();
			 t_num.setAlignment(Paragraph.ALIGN_CENTER);
			 cell_t_num.addElement(t_num);
			 table.addCell(cell_t_num);
			
			
			
			PdfPCell cell_nom = new PdfPCell();
			cell_nom.addElement(nom_prenom);
			/*cell_nom.setUseVariableBorders(true);
			cell_nom.setBorderColorTop(BaseColor.WHITE);
			cell_nom.setBorderColorBottom(BaseColor.WHITE);
			cell_nom.setBorderColorLeft(BaseColor.WHITE);
			cell_nom.setBorderColorRight(BaseColor.WHITE); */
            table.addCell(cell_nom);
            
            /*PdfPCell cell_moyenne = new PdfPCell();
            cell_moyenne.addElement(moyenne);    
			table.addCell(cell_moyenne);*/
			
			/*PdfPCell cell_ddn = new PdfPCell();
            cell_ddn.addElement(ddn);    
			table.addCell(cell_ddn);
			*/
			
			/*PdfPCell cell_mention = new PdfPCell();
            cell_mention.addElement(mention);    
			table.addCell(cell_mention);*/
			
			
			PdfPCell cell_rems = new PdfPCell();
            rems.setAlignment(Paragraph.ALIGN_CENTER);
            cell_rems.addElement(rems);    
            
			table.addCell(cell_rems);
			
			/*PdfPCell cell_svt = new PdfPCell();
            cell_svt.addElement(moyenne);    
			table.addCell(svt);*/

        }
        table.setWidthPercentage(95);

        document.add(table);
        
      //  document.add(new Paragraph("  ",font1));
        document.add(new Paragraph("   Arrêté la présente liste au nombre de "+candidats.size()+" étudiants",font1));
        document.add(new Paragraph("  ",font1));
        
        
        
        /*document.add(new Paragraph("  NB:",font1));
        
        document.add(new Paragraph("  CAF: Copie à fournir (moins de trois mois)",font1));
        document.add(new Paragraph("  CIN: Carte d'identité nationale légalisée",font1));
        document.add(new Paragraph("  RNC: Relevé de notes certifiée",font1));
        document.add(new Paragraph("  RB: Reçu de banque",font1));
        document.add(new Paragraph("  DAF: Dossier à fournir",font1));*/
        
        
		//document.add(new Paragraph("  ",font1));
       
        
        
        Date d = new Date();
		Locale localeFr = new Locale("fr","FR");
        DateFormat dfFR = DateFormat.getDateInstance(DateFormat.MEDIUM, localeFr);
        
        Paragraph fin=new Paragraph("Fait à Antananarivo, le "+dfFR.format(d)+"            ",font1);
        fin.setAlignment(Paragraph.ALIGN_RIGHT);
        
        document.add(fin);
        
        //event.setOrientation(PdfPage.LANDSCAPE);
        document.close();
    }

}


