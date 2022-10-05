package com.khalil.memo.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.khalil.memo.models.Division;
import com.khalil.memo.models.Memo;
import com.khalil.memo.models.RequestFormMemo;
import com.khalil.memo.models.UserRequest;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MemoExportService {
    public ByteArrayInputStream memoPdf(Memo memo) {
        Document document = new Document(PageSize.LETTER);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
            Rectangle rect = new Rectangle(30, 30, 550, 800);
            writer.setBoxSize("art", rect);
            AddFooter footer = new AddFooter();
            writer.setPageEvent(footer);
            document.open();

            // add text to pdf
            Font font = new Font(FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK));
            Image img = null;
            try {
                img = Image.getInstance("src/main/resources/images/logo.png");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            img.scaleToFit(100, 100);
            BaseFont bf = BaseFont.createFont("src/main/resources/images/washrab.ttf", BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED);
            // BaseFont bb =
            // BaseFont.createFont("src/main/resources/images/washrab.ttf",BaseFont.COURIER,BaseFont.EMBEDDED);
            Paragraph header_paragraph = new Paragraph("Baankii Hojii Gamtaa Oromiyaa\n የኦሮሚያ ኅብረት ስራ ባንክ \n" +
                    "አ.ማ\n", new Font(bf, 20));
            Paragraph request_Name = new Paragraph("Inter Office Memorandom", new Font(bf, 20));
            request_Name.setAlignment(Element.ALIGN_CENTER);

            PdfPTable table = new PdfPTable(new float[] { 1, 3 });
            PdfPCell image = new PdfPCell(img, false);
            PdfPCell header = new PdfPCell(header_paragraph);
            image.setBorder(0);
            header.setBorder(0);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(image);
            table.addCell(header);

            LineSeparator sep = new LineSeparator();
            sep.setLineWidth(2);
            sep.setPercentage(80);
            sep.setLineColor(BaseColor.DARK_GRAY);
            sep.setOffset(-5);

            // CREATE TABLE BASED ON DATA

            // date
            PdfPTable date = new PdfPTable(new float[] { 1, 3 });
            PdfPCell dateTitle = new PdfPCell(new Phrase("Date"));
            dateTitle.setBorderWidthLeft(0);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            PdfPCell dateContent = new PdfPCell(new Phrase(dtf.format(now)));
            dateContent.setBorderWidthRight(0);
            date.addCell(dateTitle);
            date.addCell(dateContent);

            // to
            PdfPTable to = new PdfPTable(new float[] { 1, 3 });
            PdfPCell toTitle = new PdfPCell(new Phrase("To", font));
            PdfPCell toContent = new PdfPCell(new Phrase(memo.getTo().getName()));
            toTitle.setBorderWidthLeft(0);
            toContent.setBorderWidthRight(0);
            to.addCell(toTitle);
            to.addCell(toContent);

            // cc
            PdfPTable cc = new PdfPTable(new float[] { 1, 3 });
            PdfPCell ccTitle = new PdfPCell(new Phrase("CC"));
            Phrase ccCon = new Phrase();
            for (Division dv : memo.getCc()) {
                ccCon.add(dv.getName() + "\n");
            }
            PdfPCell ccContent = new PdfPCell(ccCon);
            ccTitle.setBorderWidthLeft(0);
            ccContent.setBorderWidthRight(0);
            cc.addCell(ccTitle);
            cc.addCell(ccContent);

            // from
            PdfPTable from = new PdfPTable(new float[] { 1, 3 });
            PdfPCell fromTitle = new PdfPCell(new Phrase("From"));
            PdfPCell fromContent = new PdfPCell(new Phrase(memo.getFrom().getName()));
            fromTitle.setBorderWidthLeft(0);
            fromContent.setBorderWidthRight(0);
            from.addCell(fromTitle);
            from.addCell(fromContent);

            // subject
            PdfPTable subject = new PdfPTable(new float[] { 1, 3 });
            PdfPCell subjectTitle = new PdfPCell(new Phrase("Subject"));
            PdfPCell subjectContent = new PdfPCell(new Phrase(memo.getSubject()));
            subjectTitle.setBorderWidthLeft(0);
            subjectContent.setBorderWidthRight(0);
            subject.addCell(subjectTitle);
            subject.addCell(subjectContent);

            Paragraph messageBody = new Paragraph(memo.getBody(), new Font(bf));
            messageBody.setAlignment(Element.ALIGN_CENTER);
            PdfPTable body = new PdfPTable(1);
            PdfPCell mbody = new PdfPCell(messageBody);
            mbody.setBorder(0);
            body.addCell(mbody);

            document.add(table);
            document.add(sep);
            document.add(request_Name);
            document.add(sep);
            document.add(new Phrase("\n"));
            document.add(date);
            document.add(to);
            document.add(from);
            document.add(cc);
            document.add(subject);
            document.add(new Phrase("\n"));
            document.add(body);

            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public ByteArrayInputStream requestFormPdf(RequestFormMemo requestForm) {
        Document document = new Document(PageSize.LETTER);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
            Rectangle rect = new Rectangle(30, 30, 550, 800);
            writer.setBoxSize("art", rect);
            AddFooter footer = new AddFooter();
            writer.setPageEvent(footer);
            document.open();

            // add text to pdf
            Font font = new Font(FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK));
            Image img = null;
            try {
                img = Image.getInstance("src/main/resources/images/logo.png");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            img.scaleToFit(100, 100);
            BaseFont bf = BaseFont.createFont("src/main/resources/images/washrab.ttf", BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED);
            // BaseFont bb =
            // BaseFont.createFont("src/main/resources/images/washrab.ttf",BaseFont.COURIER,BaseFont.EMBEDDED);
            Paragraph header_paragraph = new Paragraph("Baankii Hojii Gamtaa Oromiyaa\n የኦሮሚያ ኅብረት ስራ ባንክ \n" +
                    "አ.ማ\n", new Font(bf, 20));
            // Paragraph request_Name = new Paragraph("Inter Office Memorandom", new
            // Font(bf, 20));
            // request_Name.setAlignment(Element.ALIGN_CENTER);

            PdfPTable table = new PdfPTable(new float[] { 1, 3 });
            PdfPCell image = new PdfPCell(img, false);
            PdfPCell header = new PdfPCell(header_paragraph);
            image.setBorder(0);
            header.setBorder(0);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(image);
            table.addCell(header);

            LineSeparator sep = new LineSeparator();
            sep.setLineWidth(2);
            sep.setPercentage(80);
            sep.setLineColor(BaseColor.DARK_GRAY);
            sep.setOffset(-5);

            // CREATE TABLE BASED ON DATA

            // date
            PdfPTable date = new PdfPTable(new float[] { 1, 3 });
            PdfPCell dateTitle = new PdfPCell(new Phrase("Date"));
            dateTitle.setBorderWidthLeft(0);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            PdfPCell dateContent = new PdfPCell(new Phrase(dtf.format(now)));
            dateContent.setBorderWidthRight(0);
            date.addCell(dateTitle);
            date.addCell(dateContent);

            // to
            PdfPTable to = new PdfPTable(new float[] { 1, 3 });
            PdfPCell toTitle = new PdfPCell(new Phrase("To", font));
            PdfPCell toContent = new PdfPCell(new Phrase(requestForm.getTo().getName()));
            toTitle.setBorderWidthLeft(0);
            toContent.setBorderWidthRight(0);
            to.addCell(toTitle);
            to.addCell(toContent);

            // cc
            PdfPTable cc = new PdfPTable(new float[] { 1, 3 });
            PdfPCell ccTitle = new PdfPCell(new Phrase("CC"));
            Phrase ccCon = new Phrase();
            for (Division dv : requestForm.getCc()) {
                ccCon.add(dv.getName() + "\n");
            }
            PdfPCell ccContent = new PdfPCell(ccCon);
            ccTitle.setBorderWidthLeft(0);
            ccContent.setBorderWidthRight(0);
            cc.addCell(ccTitle);
            cc.addCell(ccContent);

            // from
            PdfPTable from = new PdfPTable(new float[] { 1, 3 });
            PdfPCell fromTitle = new PdfPCell(new Phrase("From"));
            PdfPCell fromContent = new PdfPCell(new Phrase(requestForm.getFrom().getName()));
            fromTitle.setBorderWidthLeft(0);
            fromContent.setBorderWidthRight(0);
            from.addCell(fromTitle);
            from.addCell(fromContent);

            // subject
            PdfPTable subject = new PdfPTable(new float[] { 1, 3 });
            PdfPCell subjectTitle = new PdfPCell(new Phrase("Subject"));
            PdfPCell subjectContent = new PdfPCell(new Phrase(requestForm.getSubject()));
            subjectTitle.setBorderWidthLeft(0);
            subjectContent.setBorderWidthRight(0);
            subject.addCell(subjectTitle);
            subject.addCell(subjectContent);

            PdfPTable body = new PdfPTable(5);
            Streamable
                    .of("User First Name", "User Middle Name", "User Last Name", "Current Role", "Role To be assigned")
                    .forEach(headerTitle -> {
                        PdfPCell cheader = new PdfPCell();
                        cheader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        cheader.setPhrase(new Phrase(headerTitle));
                        body.addCell(cheader);
                    });

            for (UserRequest request : requestForm.getRequests()) {
                PdfPCell firstName = new PdfPCell();
                firstName.setPhrase(new Phrase(request.getUserFirstName()));
                body.addCell(firstName);

                PdfPCell middleName = new PdfPCell();
                middleName.setPhrase(new Phrase(request.getUserMiddleName()));
                body.addCell(middleName);

                PdfPCell lastName = new PdfPCell();
                lastName.setPhrase(new Phrase(request.getUserLastName()));
                body.addCell(lastName);

                PdfPCell currentRole = new PdfPCell();
                currentRole.setPhrase(new Phrase(request.getCurrentRole().getName()));;
                body.addCell(currentRole);

                PdfPCell assignedRole = new PdfPCell();
                assignedRole.setPhrase(new Phrase(request.getAssignRole().getName()));;
                body.addCell(assignedRole);
            }

            

            document.add(table);
            document.add(sep);
            // document.add(request_Name);
            // document.add(sep);
            document.add(new Phrase("\n"));
            document.add(date);
            document.add(to);
            document.add(from);
            document.add(cc);
            document.add(subject);
            document.add(new Phrase("\n"));
            document.add(body);

            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}
