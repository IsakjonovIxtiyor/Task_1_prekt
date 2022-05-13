package com.example.task_1_prekt.Service;

import com.example.task_1_prekt.Payload.*;

public interface TicketServiceInterface {
    public ResTicket getOne(Long id);
    public ApiResponse editAndCreateTicket(ReqTicket reqTicket);
    public ApiResponse deleteTicket(Long id);
}
