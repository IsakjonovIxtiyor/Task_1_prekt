package com.example.task_1_prekt.Util;

import com.example.task_1_prekt.Entity.Ticket;
import com.example.task_1_prekt.Repository.TicketRepository;
import com.example.task_1_prekt.Service.TicketService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TicketPdfDownloadController {
    @Autowired
    private TicketService ticketService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Ticket> listUsers = ticketService.listAll();

        TicketPdfDownload exporter = new TicketPdfDownload(listUsers);
        exporter.export(response);

    }
}
