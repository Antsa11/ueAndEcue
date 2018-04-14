package org.admin.servlets.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import com.itextpdf.text.Font.FontFamily;

import org.admin.beans.scolarite.Student;
import java.util.List;

public class HeaderFooterList extends PdfPageEventHelper{
	
	    //protected ElementList header;
		//protected ElementList footer;
		
		private final String HEADER =
    "<table width=\"100%\" border=\"0\"><tr><td>Header</td><td align=\"right\">Some title</td></tr></table>";
		private final String FOOTER =
    "<table width=\"100%\" border=\"0\"><tr><td>Footer</td><td align=\"right\">Some title</td></tr></table>";
		
	    public HeaderFooterList(List<Student> students)
	    {
			//header = XMLWorkerHelper.parseToElementList(HEADER, null);
            //footer = XMLWorkerHelper.parseToElementList(FOOTER, null);
		}    
        
	    // onOpenDocument - onStartPage - onEndPage - onCloseDocument - onParagraph - onParagraphEnd 	
	    public void onStartPage(PdfWriter writer,Document document) {
    	Rectangle rect = writer.getBoxSize("art");
        // ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase("Top Left"), rect.getLeft(), rect.getTop(), 0);
        // ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase("Top Right"), rect.getRight(), rect.getTop(), 0);
		
		}
 
        public void onEndPage (PdfWriter writer, Document document) {
            Rectangle rect = writer.getBoxSize("art");
            
            PdfContentByte canvas = writer.getDirectContentUnder();
			Phrase watermark = new Phrase("Faculté des Sciences d'Antananarivo", new Font(FontFamily.TIMES_ROMAN, 50, Font.NORMAL, BaseColor.LIGHT_GRAY));
			ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, 325, 415, 45);
			
			ColumnText.showTextAligned(writer.getDirectContent(),
	            Element.ALIGN_CENTER, new Phrase(String.format(" -  Page %d", writer.getPageNumber())),
	            (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 25, 0);
            
            ColumnText.showTextAligned(writer.getDirectContent(),
	            Element.ALIGN_CENTER, new Phrase("Liste disponible sur le site http://fac-scs.univ-antananarivo.mg"),
	            (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 40, 0);
             
      }  

}
