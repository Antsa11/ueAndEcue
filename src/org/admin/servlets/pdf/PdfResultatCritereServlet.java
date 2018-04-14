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


@WebServlet("/resultat_selection")
public class PdfResultatCritereServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CandidatDao candidatDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdfResultatCritereServlet() {
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
			String id=request.getParameter("id_criterePdf");
			String content="../preinscription/result_critere.jsp";
			request.setAttribute ("content",content);
			String DEST = "/opt/tomcat/webapps/admin/pdf/test.pdf";
			
			int id_critere=Integer.parseInt(id);
			//System.out.println("id_critere 2="+id_critere);
			/*int page = 1;
			int recordsPerPage = 10;
			if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));*/
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				System.out.println("io fa mety le ");
				//System.out.println("Test ");
				List<Candidat> candidats = candidatDao.getAllListFromCritere(id_critere);
				File file = new File(DEST);
				file.getParentFile().mkdirs();
				try{
					createPdf(DEST,candidats);
					System.out.println("efa tafa nedala");
				}
				catch(DocumentException e)
				{
					System.out.println("PDF error: "+e.getMessage() );
				}
			}
			catch(DaoException e)
			{
				request.setAttribute("erreur", e.getMessage());
				System.out.println("tsy tonga le izy ");
			}
			this.getServletContext().getRequestDispatcher("/pdf/test.pdf").forward(request, response);
		}
		
	}
	
	
	
	public void createPdf(String dest, List<Candidat> candidats) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        //Document document = new Document();
         //PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(dest));
        // Rotate event = new Rotate();
		//	writer.setPageEvent(event);
		
		// creation of the different writers
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
            writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
 
            HeaderFooter event = new HeaderFooter(candidats);
            writer.setPageEvent(event);
		
		   document.setMargins(40, 40, 40, 40);
           document.addCreationDate();
           document.addCreator("Faculté des Sciences");
           document.addTitle("Pré-selection 2018");
           document.addSubject("Résultat - 1ère Vague");
           document.addAuthor("Faculté des Sciences - Université d'Antananarivo");

		
        document.open();
        
        
        float[] columnWidths = {12, 5};
        PdfPTable table = new PdfPTable(columnWidths);
        Font font = new Font(FontFamily.HELVETICA, 10);
        Font font_titre = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
        
        Paragraph t_nom = new Paragraph("Nom et Prénoms",font_titre);
        PdfPCell cell_t_nom = new PdfPCell();
        cell_t_nom.addElement(t_nom);
        table.addCell(cell_t_nom);
        
        
      
		
		Paragraph t_ddn = new Paragraph("Date de naissance",font_titre);
        PdfPCell cell_t_ddn = new PdfPCell();
        cell_t_ddn.addElement(t_ddn);
        table.addCell(cell_t_ddn);
        
        Paragraph t_obs = new Paragraph("Observation",font_titre);
        PdfPCell cell_t_obs = new PdfPCell();
        cell_t_obs.addElement(t_obs);
        //table.addCell(cell_t_obs);
		
		//cell_nom.addElement(nom);
        for(int i = 0; i < candidats.size() ; i++){
			Paragraph data = new Paragraph(candidats.get(i).getNom_prenom(),font);
			Paragraph moyenne = new Paragraph(Float.toString(candidats.get(i).getMoyenne()),font);
			Paragraph svt = new Paragraph(Float.toString(candidats.get(i).getSn()),font);
			
			Paragraph vide = new Paragraph("",font);
			//System.out.println("PDF:"+candidats.get(i).getDdn());
			Paragraph ddn = new Paragraph(candidats.get(i).getDdn(),font);
			
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
			
			PdfPCell cell_vide = new PdfPCell();
            cell_vide.addElement(vide);    
			//table.addCell(cell_vide);
			
			PdfPCell cell_svt = new PdfPCell();
            cell_svt.addElement(moyenne);    
			//table.addCell(svt);

        }
        
        document.add(table);
        
        
        //event.setOrientation(PdfPage.LANDSCAPE);
        document.close();
    }

}
/*

class HeaderFooter extends PdfPageEventHelper {
	
	    private String choix;
	    //protected ElementList header;
		//protected ElementList footer;
		
		private final String HEADER =
    "<table width=\"100%\" border=\"0\"><tr><td>Header</td><td align=\"right\">Some title</td></tr></table>";
		private final String FOOTER =
    "<table width=\"100%\" border=\"0\"><tr><td>Footer</td><td align=\"right\">Some title</td></tr></table>";
		
	    HeaderFooter(List<Candidat> candidats)
	    {
			this.choix=candidats.get(0).getChoix();
			//header = XMLWorkerHelper.parseToElementList(HEADER, null);
        //footer = XMLWorkerHelper.parseToElementList(FOOTER, null);
		}
	
	    // onOpenDocument - onStartPage - onEndPage - onCloseDocument - onParagraph - onParagraphEnd 
	
	
	    public void onStartPage(PdfWriter writer,Document document) {
    	Rectangle rect = writer.getBoxSize("art");
        //ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase("Top Left"), rect.getLeft(), rect.getTop(), 0);
        //ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase("Top Right"), rect.getRight(), rect.getTop(), 0);
		
		}
 
        public void onEndPage (PdfWriter writer, Document document) {
            Rectangle rect = writer.getBoxSize("art");
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Phrase(String.format(this.choix+" -  Page %d", writer.getPageNumber())),
                    (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 25, 0);
        }
    }
*/
