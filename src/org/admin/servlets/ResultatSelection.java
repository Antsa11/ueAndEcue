package org.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.admin.beans.Candidat;
import org.admin.dao.DaoFactory;
import org.admin.dao.CandidatDao;
import org.admin.dao.DaoException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
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


import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;

import org.admin.servlets.pdf.HeaderFooter;



@WebServlet("/result_selection")
public class ResultatSelection extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CandidatDao candidatDao;
	public ResultatSelection() {
        super();
       
    }
    public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		candidatDao=daoFactory.getCandidatDao();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/resultat/form.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		int id_vague=0;
		String choix=request.getParameter("choix");
		id_vague=Integer.parseInt(request.getParameter("id_vague"));
		String DEST = "/opt/tomcat/webapps/admin/pdf/resultat_selection.pdf";
		try
			{
				
				List<Candidat> candidats = candidatDao.getAllCandidatSelected(choix,id_vague);
				File file = new File(DEST);
				file.getParentFile().mkdirs();
				try{
					createPdf(DEST,candidats, id_vague);
					this.getServletContext().getRequestDispatcher("/pdf/resultat_selection.pdf").forward(request, response);
				}
				catch(DocumentException e)
				{
					//System.out.println("PDF error: "+e.getMessage() );
					request.setAttribute("error", "PDF error: "+e.getMessage());
					this.getServletContext().getRequestDispatcher("/WEB-INF/resultat/form.jsp").forward(request, response);
			
				}
				catch(IndexOutOfBoundsException e)
				{
					request.setAttribute("error", "Résultat non disponible");
					this.getServletContext().getRequestDispatcher("/WEB-INF/resultat/form.jsp").forward(request, response);
			
				}
				
			}
			catch(DaoException e)
			{
				//request.setAttribute("erreur", e.getMessage());
				System.out.println("SQL Error: "+e.getMessage() );
				request.setAttribute("error", "erreur "+e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/resultat/form.jsp").forward(request, response);
			
			}
			
			

	}
	
		public void createPdf(String dest, List<Candidat> candidats, int id_vague) throws IOException, DocumentException {
        //Document document = new Document(PageSize.A4.rotate());
        Document document = new Document();
         //PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(dest));
        // Rotate event = new Rotate();
		//	writer.setPageEvent(event);
		
		// creation of the different writers
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
            writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
 
            HeaderFooter event = new HeaderFooter(candidats);
            writer.setPageEvent(event);
		
		  // document.setMargins(20, 10, 40, 40);
		   document.setMargins(40, 40, 20, 50);
          
           document.addCreationDate();
           document.addCreator("Faculté des Sciences");
           document.addTitle("Pré-selection 2018");
           document.addSubject("Résultat - Liste N° " +id_vague);
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
        Paragraph titre=new Paragraph("    Liste Etudiants sélectionnés en L1 - "+candidats.get(0).getChoix(),font2);
        String textList="";
        if(id_vague==1)
        textList="Première liste";
        if(id_vague==2)
        textList="Deuxième liste";
        if(id_vague==3)
        textList="Troisième liste";
        
        
        Paragraph titreListe=new Paragraph(textList,font2);
        titre.setAlignment(Paragraph.ALIGN_CENTER);
        anneeU.setAlignment(Paragraph.ALIGN_CENTER);
        titreListe.setAlignment(Paragraph.ALIGN_CENTER);
        
        document.add(anneeU);
        document.add(titre);
        document.add(titreListe);
        document.add(new Paragraph("  ",font1));
        
        float[] columnWidths = {2,23, 5,6};
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
        
        
      
		
		Paragraph t_ddn = new Paragraph("Date de naissance",font_titre);
        PdfPCell cell_t_ddn = new PdfPCell();
        cell_t_ddn.addElement(t_ddn);
        table.addCell(cell_t_ddn);
        
        Paragraph t_mention = new Paragraph("Mention",font_titre);
        PdfPCell cell_t_mention = new PdfPCell();
        cell_t_mention.addElement(t_mention);
        //table.addCell(cell_t_mention);
        
        
        Paragraph t_obs = new Paragraph("Observation",font_titre);
         t_obs.setAlignment(Paragraph.ALIGN_CENTER);
        PdfPCell cell_t_obs = new PdfPCell();
        cell_t_obs.addElement(t_obs);
        table.addCell(cell_t_obs);
		
		//cell_nom.addElement(nom);
        for(int i = 0; i < candidats.size() ; i++){
			Paragraph data = new Paragraph(candidats.get(i).getNom_prenom(),font);
			Paragraph moyenne = new Paragraph(Float.toString(candidats.get(i).getMoyenne()),font);
			Paragraph svt = new Paragraph(Float.toString(candidats.get(i).getSn()),font);
			
			Paragraph obs = new Paragraph(candidats.get(i).getObs(),font);
			//System.out.println("PDF:"+candidats.get(i).getDdn());
			Paragraph ddn = new Paragraph(candidats.get(i).getDdn(),font);
			Paragraph mention = new Paragraph(candidats.get(i).getMention(),font);
			
			
			 Paragraph t_num = new Paragraph(""+(i+1),font);
			 PdfPCell cell_t_num = new PdfPCell();
			 t_num.setAlignment(Paragraph.ALIGN_CENTER);
			 cell_t_num.addElement(t_num);
			 table.addCell(cell_t_num);
			
			
			
			PdfPCell cell_nom = new PdfPCell();
			cell_nom.addElement(data);
			/*cell_nom.setUseVariableBorders(true);
			cell_nom.setBorderColorTop(BaseColor.WHITE);
			cell_nom.setBorderColorBottom(BaseColor.WHITE);
			cell_nom.setBorderColorLeft(BaseColor.WHITE);
			cell_nom.setBorderColorRight(BaseColor.WHITE); */
            table.addCell(cell_nom);
            
            PdfPCell cell_moyenne = new PdfPCell();
            cell_moyenne.addElement(moyenne);    
			//table.addCell(cell_moyenne);
			
			PdfPCell cell_ddn = new PdfPCell();
            cell_ddn.addElement(ddn);    
			table.addCell(cell_ddn);
			
			PdfPCell cell_mention = new PdfPCell();
            cell_mention.addElement(mention);    
			//table.addCell(cell_mention);
			
			
			PdfPCell cell_obs = new PdfPCell();
            obs.setAlignment(Paragraph.ALIGN_CENTER);
            cell_obs.addElement(obs);    
            
			table.addCell(cell_obs);
			
			PdfPCell cell_svt = new PdfPCell();
            cell_svt.addElement(moyenne);    
			//table.addCell(svt);

        }
        table.setWidthPercentage(95);

        document.add(table);
        
      //  document.add(new Paragraph("  ",font1));
        document.add(new Paragraph("   Arrêté la présente liste au nombre de "+candidats.size()+" étudiants",font1));
        document.add(new Paragraph("  ",font1));
        
        
        document.add(new Paragraph("  Les anciens bacheliers sélectionnés(formation à vocation académique ou professionnalisante) ne bénéficieront pas des allocations de bourse en 1ère année.",font2));
        //document.add(new Paragraph("  ",font1));
        document.add(new Paragraph("  Ils devront remplir une fiche d'engagement à la place de la fiche de demande de bourse lors de leur inscription. ",font2));
        
        document.add(new Paragraph("  NB:",font1));
        
        document.add(new Paragraph("  CAF: Copie à fournir (moins de trois mois)",font1));
        document.add(new Paragraph("  CIN: Carte d'identité nationale légalisée",font1));
        document.add(new Paragraph("  RNC: Relevé de notes certifiée",font1));
        document.add(new Paragraph("  RB: Reçu de banque",font1));
        document.add(new Paragraph("  DAF: Dossier à fournir",font1));
        
        
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
