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

import org.admin.beans.CandidatAnomalie;
import org.admin.beans.Candidat;
import org.admin.dao.DaoFactory;
import org.admin.dao.CandidatDao;
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


@WebServlet("/pdf_bac_etranger")
public class PdfBacEtrangerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CandidatDao candidatDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdfBacEtrangerServlet() {
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
			//String content="../admin/accueil.jsp";
			//request.setAttribute ("content",content);
			String choix=request.getParameter("choix");
			String content="../preinscription/result_critere.jsp";
			request.setAttribute ("content",content);
			//String DEST = "/opt/tomcat/webapps/admin/pdf/bac_etranger.pdf";
			String DEST = "/opt/tomcat/webapps/admin/pdf/bac_etranger.pdf";
			
			
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				
				//System.out.println("Test ");
				List<Candidat> candidats = candidatDao.afficheAnomalieVerifAttestation();
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
			this.getServletContext().getRequestDispatcher("/pdf/bac_etranger.pdf").forward(request, response);
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
	
	
	
	public void createPdf(String dest, List<Candidat> candidats) throws IOException, DocumentException {
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
		
		   document.setMargins(20, 10, 40, 40);
           document.addCreationDate();
           document.addCreator("Faculté des Sciences");
           document.addTitle("Pré-selection 2018");
           document.addSubject("Résultat - 1ère Vague");
           document.addAuthor("Faculté des Sciences - Université d'Antananarivo");

		
        document.open();
        Font font = new Font(FontFamily.HELVETICA, 10);
        Font font1 = new Font(FontFamily.HELVETICA, 11);
        Font font2 = new Font(FontFamily.HELVETICA, 11,Font.BOLD);
        document.add(new Paragraph("    Université d'Antananarivo",font1));   
        document.add(new Paragraph("    Faculté des Sciences",font1));
        document.add(new Paragraph("    Domaine Sciences et Technologies",font1));
        
        document.add(new Paragraph("    ",font1));
        
        //Paragraph titre=new Paragraph("    Liste des étudiants admis en L1 ",font2);
        //titre.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(new Paragraph("    Les étudiants suivants sont priés de présenter l'original de leur diplôme ou attestation de réussite ou relevé de notes du bac au bureau....",font1));
        
        
        //document.add(titre);
        
        document.add(new Paragraph("  ",font1));
         document.add(new Paragraph("  ",font1));
        
        float[] columnWidths = {16,6,8};
        PdfPTable table = new PdfPTable(columnWidths);
        //table.setBorder(0);
        table.setTableEvent(new BorderEvent());
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        
        //table.setBorderWidth(0);
        
        Font font_titre = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
        
        Paragraph t_nom = new Paragraph("Nom et Prénoms",font_titre);
        PdfPCell cell_t_nom = new PdfPCell();
        cell_t_nom.addElement(t_nom);
        table.addCell(cell_t_nom);
        
        
      
		
		Paragraph t_ddn = new Paragraph("Date de naissance",font_titre);
        PdfPCell cell_t_ddn = new PdfPCell();
        cell_t_ddn.addElement(t_ddn);
        //table.addCell(cell_t_ddn);
        
        Paragraph t_tel = new Paragraph("Tel",font_titre);
        PdfPCell cell_t_tel = new PdfPCell();
        cell_t_tel.addElement(t_tel);
        table.addCell(cell_t_tel);
        
        /*Paragraph t_adresse = new Paragraph("Adresse",font_titre);
        PdfPCell cell_t_adresse = new PdfPCell();
        cell_t_adresse.addElement(t_adresse);
        table.addCell(cell_t_adresse);
        */
        
        Paragraph t_choix = new Paragraph("Choix",font_titre);
        PdfPCell cell_t_choix = new PdfPCell();
        cell_t_choix.addElement(t_choix);
        //table.addCell(cell_t_choix);
		
		//cell_nom.addElement(nom);
        for(int i = 0; i < candidats.size() ; i++){
			Paragraph data = new Paragraph(candidats.get(i).getNom_prenom(),font);
			Paragraph moyenne = new Paragraph(Float.toString(candidats.get(i).getMoyenne()),font);
			Paragraph svt = new Paragraph(Float.toString(candidats.get(i).getSn()),font);
			
			Paragraph obs = new Paragraph(candidats.get(i).getObs(),font);
			//System.out.println("PDF:"+candidats.get(i).getDdn());
			Paragraph ddn = new Paragraph(candidats.get(i).getDdn(),font);
			Paragraph mention = new Paragraph(candidats.get(i).getMention(),font);
			
			
			Paragraph tel = new Paragraph(candidats.get(i).getTel(),font);
			Paragraph  choix= new Paragraph(candidats.get(i).getChoix(),font);
			//Paragraph adress =new Paragraph(candidats.get(i).getAdresse(),font);
			
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
			//table.addCell(cell_ddn);
			
			PdfPCell cell_mention = new PdfPCell();
            cell_mention.addElement(mention);    
			//table.addCell(cell_mention);
			
			PdfPCell cell_tel = new PdfPCell();
            cell_tel.addElement(tel);    
			table.addCell(cell_tel);
			
			/*PdfPCell cell_adress = new PdfPCell();
            cell_adress.addElement(adress);    
			table.addCell(cell_adress);
			*/
			PdfPCell cell_obs = new PdfPCell();
            cell_obs.addElement(obs);    
	//		table.addCell(cell_obs);
			
			PdfPCell cell_choix = new PdfPCell();
            cell_choix.addElement(choix);    
			//table.addCell(cell_choix);
	
				
			PdfPCell cell_svt = new PdfPCell();
            cell_svt.addElement(moyenne);    
			//table.addCell(svt);

        }
        table.setWidthPercentage(50);

        document.add(table);
        
        document.add(new Paragraph("  ",font1));
        document.add(new Paragraph("  ",font1));
        
        //document.add(new Paragraph("  CP: Copie",font1));
        //document.add(new Paragraph("  CIN: Carte d'identité nationale",font1));
        //document.add(new Paragraph("  RN: Relevé de notes",font1));
        //document.add(new Paragraph("  RB: Reçu de banque",font1));
        
        
        document.add(new Paragraph("  ",font1));
        
        
        //document.add(new Paragraph("   Arrêté la présente liste au nombre de "+candidats.size()+" étudiants",font1));
        
		document.add(new Paragraph("  ",font1));
        document.add(new Paragraph("  ",font1));
        
        
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

class BorderEvent implements PdfPTableEvent {
        public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases) {
            float width[] = widths[0];
            float x1 = width[0];
            float x2 = width[width.length - 1];
            float y1 = heights[0];
            float y2 = heights[heights.length - 1];
            PdfContentByte cb = canvases[PdfPTable.LINECANVAS];
            cb.rectangle(x1, y1, x2 - x1, y2 - y1);
            cb.stroke();
            cb.resetRGBColorStroke();
        }
    }


