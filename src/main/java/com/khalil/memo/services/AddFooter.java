package com.khalil.memo.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class AddFooter extends PdfPageEventHelper {
    public void onEndPage(PdfWriter writer, Document document) {
        Rectangle rect = writer.getBoxSize("art");
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("________________", new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, BaseColor.DARK_GRAY))), rect.getRight() / 6, rect.getBottom(), 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Committed to Breakthrough!", new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, BaseColor.DARK_GRAY))), rect.getRight() / 2, rect.getBottom(), 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("________________", new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, BaseColor.DARK_GRAY))), rect.getRight() - rect.getRight() / 6, rect.getBottom(), 0);
    }
}
