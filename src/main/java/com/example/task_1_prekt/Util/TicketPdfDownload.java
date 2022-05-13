package com.example.task_1_prekt.Util;

import com.example.task_1_prekt.Entity.Ticket;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class TicketPdfDownload {
    private List<Ticket> listUsers;

    public  TicketPdfDownload(List<Ticket> listUsers) {
        this.listUsers = listUsers;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Id", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("FilmName", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("FilmDate", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("TicketPrice", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("TicketStatus", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Row", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("RowColumn", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("CreatedAt", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("UpdatedAt", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Ticket user : listUsers) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getFilmName());
            table.addCell(user.getFilmDate());
            table.addCell(user.getTicketPrice());
            table.addCell(user.getTicketStatus());
            table.addCell(String.valueOf(user.getRow()));
            table.addCell(String.valueOf(user.getRowColumn()));
            table.addCell(String.valueOf(user.getCreatedAt()));
            table.addCell(String.valueOf(user.getUpdatedAt()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 3.0f, 1.5f,3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
