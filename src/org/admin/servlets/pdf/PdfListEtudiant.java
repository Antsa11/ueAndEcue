
package org.admin.servlets.pdf;


import org.admin.servlets.BaseServlet;

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


import org.admin.beans.scolarite.Student;
import org.admin.dao.DaoFactory;
import org.admin.dao.StudentDao;
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


@WebServlet("/pdf_list_etudiant")
public class PdfListEtudiant extends BaseServlet{

	private static final long serialVersionUID = 1L;
    private StudentDao studentDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdfListEtudiant() {
        super();
       
    }
	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		studentDao=daoFactory.getStudentDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		request.setAttribute("base_url",base_url);
		HttpSession session=request.getSession();
		
		if(session.getAttribute("utilisateur")==null)
		{
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			String niveau=request.getParameter("niveauEtu");
			String parcours = request.getParameter("parcoursEtu");
			String content="../preinscription/list_etudiant.jsp";
			request.setAttribute ("content",content);
			String LIST = "/opt/tomcat/webapps/admin/pdf/ListeEtudiant.pdf";
			
			//int id_critere=Integer.parseInt(id);
			//System.out.println("id_critere 2="+id_critere);
			/*int page = 1;
			int recordsPerPage = 10;
			if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));*/
			try
			{
				//List<Etudiant> etudiants=etudiantDao.lister();
				//System.out.println("Test ");
				List<Student> student = studentDao.getAllListStudentByParcours(parcours,niveau);
				System.out.println(student);
				File file = new File(LIST);
				file.getParentFile().mkdirs();
				try{
					createPdf(LIST,student);
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
			this.getServletContext().getRequestDispatcher("/pdf/ListeEtudiant.pdf").forward(request, response);
		}

	}

	public void createPdf(String list, List<Student> student) throws IOException, DocumentException {
        // Document document = new Document(PageSize.A4.rotate());
        Document document = new Document();        
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(list));
        writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
 
        HeaderFooterList event = new HeaderFooterList(student);
        writer.setPageEvent(event);
		
		document.setMargins(40, 40, 40, 40);
        document.addCreationDate();
        document.addCreator("Faculté des Sciences");
        document.addTitle("Liste des étudiant 2018");
        document.addSubject("Liste 2018");
        document.addAuthor("Faculté des Sciences - Université d'Antananarivo");

        document.open();
        
        Image img = Image.getInstance(base_url+"/img/logo_scs.png");
        //document.add(new Paragraph("Sample 1: This is simple image demo."));
        img.setAbsolutePosition(50f, 725f);
        img.scaleToFit(100,100);
        document.add(img);
        
   //LES ENTETES DU FICHIER PDF     
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

        document.add(new Paragraph("     ", font1));

        Paragraph anneeU = new Paragraph(" Année Universitaire 2017-2018",font2);
        Paragraph titre=new Paragraph("  Liste des étudiants "+ student.get(0).getNiveau(),font2);
        Paragraph titre1=new Paragraph(" "+ student.get(0).getNom_parcours(),font2);
        titre1.setAlignment(Paragraph.ALIGN_CENTER);
        titre.setAlignment(Paragraph.ALIGN_CENTER);
        anneeU.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(anneeU);
        document.add(titre);
        document.add(titre1);
        document.add(new Paragraph("  ",font1));
        // float[] columnWidths = {15,12};
        float[] columnWidths = {2,23, 5};
        PdfPTable table = new PdfPTable(columnWidths);
	    Font font_titre = new Font(FontFamily.HELVETICA, 11, Font.BOLD);

        Paragraph t_null = new Paragraph(" ",font_titre);

        PdfPCell cell_t_null = new PdfPCell();
        cell_t_null.addElement(t_null);
        table.addCell(cell_t_null);

//FIN DES ENTETES DU FICHIER PDF       
       
        // Font font = new Font(FontFamily.HELVETICA, 10);

        Paragraph t_nom = new Paragraph("Nom et Prénoms",font_titre);

        PdfPCell cell_t_nom = new PdfPCell();
        cell_t_nom.addElement(t_nom);
        table.addCell(cell_t_nom);
        
        //Paragraph t_prenom = new Paragraph("Prénoms ",font_titre);

        // PdfPCell cell_t_prenom = new PdfPCell();
        // cell_t_nom.addElement(t_prenom);
        // table.addCell(cell_t_prenom);
      
		
		Paragraph t_ddn = new Paragraph("Date de naissance",font_titre);
        PdfPCell cell_t_ddn = new PdfPCell();
        cell_t_ddn.addElement(t_ddn);
        table.addCell(cell_t_ddn);

        for (int i=0;i<student.size(); i++) {
        	Paragraph nom = new Paragraph(student.get(i).getNom()+" " + student.get(i).getPrenom(),font);
        	Paragraph prenom = new Paragraph(student.get(i).getPrenom());
        	Paragraph ddn = new Paragraph(student.get(i).getDdn());

            Paragraph t_num = new Paragraph(""+(i+1),font);
             PdfPCell cell_t_num = new PdfPCell();
             t_num.setAlignment(Paragraph.ALIGN_CENTER);
             cell_t_num.addElement(t_num);
             table.addCell(cell_t_num);

        	PdfPCell cell_nom= new PdfPCell();
        	cell_nom.addElement(nom);
        	table.addCell(cell_nom);

			// PdfPCell cell_username= new PdfPCell();
   //      	cell_name.addElement(prenom);
   //      	table.addCell(cell_username);

        	PdfPCell cell_ddn = new PdfPCell();
        	cell_ddn.addElement(ddn);
        	table.addCell(cell_ddn);

        }
        
        table.setWidthPercentage(95);
        document.add(table);
        
        document.close();
    }

}

