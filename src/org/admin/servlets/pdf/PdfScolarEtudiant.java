package org.admin.servlets.pdf;


import org.admin.servlets.BaseServlet;

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

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


@WebServlet("/pdf_scola_etudiant")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)

public class PdfScolarEtudiant extends BaseServlet{

	private static final long serialVersionUID = 1L;
    private StudentDao studentDao;
    private static final String SAVE_DIR = "img";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdfScolarEtudiant() {
        super();
       
    }

	public void init() throws ServletException
	{
		DaoFactory daoFactory=DaoFactory.getInstance();
		studentDao=daoFactory.getStudentDao();
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException , IOException{

		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		String content="../preinscription/list_etudiant.jsp";
		request.setAttribute ("content",content);
		HttpSession session = request.getSession();

		if(session.getAttribute("utilisateur")==null)
		{
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
		}else{

			this.getServletContext().getRequestDispatcher("/WEB-INF/resultat/form.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException , IOException{

		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		HttpSession session=request.getSession();

		if(session.getAttribute("utilisateur")==null)
		{
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
		}
		
		else
		{

			String nom = request.getParameter("nomEtudiant");
			String prenom = request.getParameter("prenomEtudiant");
			String ddn = request.getParameter("ddnEtudiant");
			String parcours = request.getParameter("parcoursEtudiant");
			String mention  = request.getParameter("mentionEtudiant");
			String niveau = request.getParameter("niveauEtudiant");
			String adresse = request.getParameter("adresseEtudiant");

			String content="../preinscription/list_etudiant.jsp";
			request.setAttribute ("content",content);
			//String content="../preinscription/list_etudiant.jsp";
			//request.setAttribute ("content",content);
			String LISTA = "/opt/tomcat/webapps/admin/pdf/CertEtudiant.pdf";
			File file = new File(LISTA);
			file.getParentFile().mkdirs();

			try{

				//List<Student> student = studentDao.getAllListStudentByParcours(parcours,niveau);
				createPdf(LISTA,nom,prenom,ddn,parcours,mention,niveau,adresse);
			}
			catch(DocumentException e)
			{
				System.out.println("PDF error: "+e.getMessage() );
			}

			this.getServletContext().getRequestDispatcher("/pdf/CertEtudiant.pdf").forward(request, response);
		}

	}

	public void createPdf(String lista,String name,String username,String ddn1,String parcours1,String mention1,String niveau1,String adresse1) throws IOException, DocumentException {
         //Document document = new Document(PageSize.A4.rotate());
        Document document = new Document(PageSize.A4,20,20,20,20);        
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(lista));
        writer.setBoxSize("art", new Rectangle(20, 20, 150, 200));
         //writer.setBoxSize("art", new Rectangle(100,100));
         writer.setCompressionLevel(9);
 		// writer.setBoxSize("art", new Rectangle(30, 45, 300, 400));

       	HeaderFooterCarte event = new HeaderFooterCarte();
        writer.setPageEvent(event);
		
		// document.setMargins(20, 10, 30, 30);
        document.addCreationDate();
        document.addCreator("Faculté des Sciences");
        document.addTitle("Liste des étudiant 2018");
        document.addSubject("Liste 2018");
        document.addAuthor("Faculté des Sciences - Université d'Antananarivo");

        document.open();

        Image img = Image.getInstance(base_url+"/img/logo_scs.png");
        //document.add(new Paragraph("Sample 1: This is simple image demo."));
        img.setAbsolutePosition(50f, 725f);
        img.scaleToFit(80,80);
        document.add(img);
        
   //LES ENTETES DU FICHIER PDF     
        Image img2 = Image.getInstance(base_url+"/img/logo_ua.png");
        //document.add(new Paragraph("Sample 1: This is simple image demo."));
        img2.setAbsolutePosition(450f, 725f);
        //img2.setAlignment(Image.RIGHT);
        img2.scaleToFit(100,100);
        document.add(img2);

        Font font = new Font(FontFamily.HELVETICA, 10);
        Font font1 = new Font(FontFamily.HELVETICA, 10);
        Font font2 = new Font(FontFamily.HELVETICA, 11,Font.BOLD);
        
        Font font3 = new Font(FontFamily.HELVETICA, 12,Font.BOLD);
        Font font4 = new Font(FontFamily.HELVETICA, 16,Font.BOLD);
 

        Paragraph titre_U =new Paragraph("    Université d'Antananarivo",font4);
        titre_U.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titre_U);
        
        Paragraph Fac_s =new Paragraph("    Faculté des Sciences",font1);
        Fac_s.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(Fac_s);
        
        
        //document.add();
        Paragraph Domaine_s=new Paragraph("    Domaine Sciences et Technologies",font1);
        Domaine_s.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(Domaine_s);

        Paragraph anneeU = new Paragraph(" Année Universitaire 2017-2018",font1);
        anneeU.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(anneeU);

        Paragraph objet = new Paragraph("CERTIFICAT DE SCOLARITE",font4);
        objet.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(objet);

        Paragraph line = new Paragraph("  ",font4);
        line.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(line);

        Paragraph nom = new Paragraph("Nom : "+name,font1);
        nom.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(nom);

        Paragraph prenom = new Paragraph("Prénoms : "+username,font1);
        prenom.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(prenom);

        Paragraph ddn = new Paragraph("Né(e) le "+ddn1,font1);
        ddn.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(ddn);

        Paragraph parcours = new Paragraph("Parcours "+parcours1,font1);
        parcours.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(parcours);

       	Paragraph mention = new Paragraph("Mention "+mention1,font1);
        mention.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(mention);

        Paragraph niveau = new Paragraph("Grade "+niveau1,font1);
        niveau.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(niveau);

        Paragraph adresse = new Paragraph("Adresse : "+ adresse1,font1);
        adresse.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(adresse);

        // Paragraph etudiant = new Paragraph("L'etudiant(e)",font1);
        // etudiant.setAlignment(Paragraph.ALIGN_LEFT);
        // etudiant.setIndentationLeft(50);
        // document.add(etudiant);

        // Paragraph responsable = new Paragraph("Le responsable",font1);
        // responsable.setAlignment(Paragraph.ALIGN_RIGHT);
        // responsable.setIndentationRight(50);
        // document.add(responsable);

        // float[] columnWidths = {2,3, 5};
        // PdfPTable table = new PdfPTable(columnWidths);


        Paragraph etudiant = new Paragraph("L'étudiant(e)",font1);
        // PdfPCell etudiant_cell = new PdfPCell();
        // etudiant_cell.addElement(etudiant);
        // table.addCell(etudiant_cell);
        etudiant.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(etudiant);

        Paragraph responsable = new Paragraph("Le responsable",font1);
       	// PdfPCell respon_cell = new PdfPCell();
       	// respon_cell.addElement(responsable);
       	// table.addCell(respon_cell);

       	// table.setWidthPercentage(95);
        // document.add(table);
        responsable.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(responsable);

        Paragraph line1 = new Paragraph("  ",font3);
        line1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(line1);

        Date d = new Date();
		Locale localeFr = new Locale("fr","FR");
        DateFormat dfFR = DateFormat.getDateInstance(DateFormat.MEDIUM, localeFr);
       
        Paragraph fin=new Paragraph("Fait à Antananarivo, le "+dfFR.format(d)+"            ",font1);
        fin.setAlignment(Paragraph.ALIGN_RIGHT);
       	document.add(fin);

        
        document.close();
    }
}