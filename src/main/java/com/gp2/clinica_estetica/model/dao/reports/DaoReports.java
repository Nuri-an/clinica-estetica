/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao.reports;

import com.gp2.clinica_estetica.model.exceptions.ReportsException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JTable;

/**
 *
 * @author nuria
 */
public abstract class DaoReports {

    public Document generateTablePDF(String nameFile, String title, JTable table) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(nameFile));
            document.open();

            Font titlefont = new Font(Font.HELVETICA, 24, Font.BOLD);
            Paragraph titleFile = new Paragraph(title, titlefont);
            titleFile.setAlignment(Element.ALIGN_CENTER);
            titleFile.setSpacingAfter(50);
            document.add(titleFile);

            // total colunas
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

            PdfPCell cell;
            Font headerTableFont = new Font(Font.HELVETICA, 12);

            for (int i = 0; i < table.getColumnCount(); i++) {
                cell = new PdfPCell(new Phrase(table.getColumnName(i), headerTableFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(Color.LIGHT_GRAY);
                cell.setPaddingTop(10);
                cell.setPaddingBottom(10);
                cell.setBorderColor(Color.DARK_GRAY);
                pdfTable.addCell(cell);
            }

            Font textTableFont = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.DARK_GRAY);
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    cell = new PdfPCell(new Phrase(table.getValueAt(i, j).toString(), textTableFont));
                    pdfTable.addCell(cell);
                }
            }
            document.add(pdfTable);

        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
            throw new ReportsException("Erro ao gerar relatório");
        }
        document.close();

        return document;
    }

    public void saveFile(File source, File dest) throws IOException {
        InputStream in = new FileInputStream(source);
        OutputStream out = new FileOutputStream(dest);
        try {
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ReportsException("Erro ao salvar arquivo");
        }
        in.close();
        out.close();
    }
}
