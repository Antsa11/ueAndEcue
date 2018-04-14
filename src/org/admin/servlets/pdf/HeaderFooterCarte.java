package org.admin.servlets.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import com.itextpdf.text.Font.FontFamily;

import org.admin.beans.Candidat;
import org.admin.beans.scolarite.Student;
import java.util.List;

public class HeaderFooterCarte extends PdfPageEventHelper{


	private final String HEADER =
    "<table width=\"100%\" border=\"0\"><tr><td>Header</td><td align=\"right\">Some title</td></tr></table>";
		private final String FOOTER =
    "<table width=\"100%\" border=\"0\"><tr><td>Footer</td><td align=\"right\">Some title</td></tr></table>";
		
	    public HeaderFooterCarte()
	    {
			
			//header = XMLWorkerHelper.parseToElementList(HEADER, null);
        //footer = XMLWorkerHelper.parseToElementList(FOOTER, null);
		}

	
	    public void onStartPage(PdfWriter writer,Document document) {
	    	
 		   	Rectangle rect = writer.getBoxSize("art");
		}
 
        public void onEndPage (PdfWriter writer, Document document) {

            Rectangle rect = writer.getBoxSize("art");
            
            // PdfContentByte canvas = writer.getDirectContentUnder();
			//Phrase watermark = new Phrase("Faculté des Sciences d'Antananarivo", new Font(FontFamily.TIMES_ROMAN, 50, Font.NORMAL, BaseColor.LIGHT_GRAY));
			//ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, 325, 415, 45);
				
			//ColumnText.showTextAligned(writer.getDirectContent());
             
    
        }

}