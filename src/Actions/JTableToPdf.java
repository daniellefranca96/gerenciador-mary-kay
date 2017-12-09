/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Element;
import java.io.FileOutputStream;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPageEventHelper;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import sun.font.FontFamily;

/**
 *
 * @author Danielle
 */
public class JTableToPdf {

    private String titleReport;
    private String[] nameHeaders;
    private JTable jTable;

    public JTableToPdf(String reportTitle, String[] headerNames) {
        this.titleReport = reportTitle;
        this.nameHeaders = headerNames;
    }

    public void runReport(JTable jtable, String pathSaveFile, String fileName) throws Exception {
        try {
            this.jTable = jtable;
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pathSaveFile + "/" + fileName + ".pdf"));
            document.setHeader(getHeader());
            document.open();
            document.add(getPdfPTable());
            document.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar o pdf porque há um arquivo de mesmo nome aberto!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public HeaderFooter getHeader() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String data = formato.format(System.currentTimeMillis());
        Image logo = null;
        try {
            logo = Image.getInstance(this.getClass().getClassLoader().getResource("Imagens/logo.jpg"));
        } catch (BadElementException ex) {
            Logger.getLogger(JTableToPdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(JTableToPdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JTableToPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        logo.scaleAbsolute(100, 100);
        logo.setAlignment(Image.ALIGN_RIGHT);
        Paragraph nomePrograma = new Paragraph("GERENCIADOR DE REVENDEDORAS MARY KAY", FontFactory.getFont(FontFactory.COURIER_BOLD, 16));
        Paragraph nomeTabela = new Paragraph("RELATÓRIO DE " + titleReport.toUpperCase(), FontFactory.getFont(FontFactory.COURIER, 12));
        Paragraph info = new Paragraph(data, FontFactory.getFont(FontFactory.COURIER, 10));
        PdfPTable texto = new PdfPTable(2);
        PdfPCell cell = new PdfPCell(logo);
        //cell.setRowspan(3);
        cell.setBorder(Rectangle.NO_BORDER);
        PdfPCell cell1 = new PdfPCell(nomePrograma);
        cell1.setBorder(Rectangle.NO_BORDER);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell cell2 = new PdfPCell(nomeTabela);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell cell3 = new PdfPCell(info);
        cell3.setBorder(Rectangle.NO_BORDER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        texto.addCell(cell);
        texto.addCell(cell1);
        texto.addCell(cell2);
        texto.addCell(cell3);
        Phrase ph = new Phrase();
        ph.add(texto);
        HeaderFooter header = new HeaderFooter(new Phrase(ph), true);
        header.setBorder(Rectangle.NO_BORDER);
        return header;
    }

    private PdfPTable getPdfPTable() throws Exception {
        PdfPTable tab = new PdfPTable(nameHeaders.length);
        tab.setWidthPercentage(100);
        int size = nameHeaders.length;
        int i = 0;
        while (size != 0) {
            tab.addCell(nameHeaders[i]);
            size--;
            i++;
        }

        int rowCount = jTable.getRowCount();
        int collumCount = jTable.getColumnCount();
        for (int x = 0; x < rowCount; x++) {
            for (int y = 0; y < collumCount; y++) {
                Paragraph texto = new Paragraph(GetData(jTable, x, y).toString(), FontFactory.getFont(FontFactory.COURIER, 8));
                PdfPCell celula = new PdfPCell(texto);
                tab.addCell(celula);
            }

        }
        return tab;
    }

    private Object GetData(JTable table, int row_index, int col_index) throws Exception {
        return table.getModel().getValueAt(row_index, col_index);
    }

    private PdfPCell getCellTitle() {
        PdfPCell cell = new PdfPCell(new Paragraph("RELATÓRIO DE " + titleReport.toUpperCase()));
        cell.setColspan(nameHeaders.length);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }
}
