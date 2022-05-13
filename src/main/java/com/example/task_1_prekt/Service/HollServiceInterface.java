package com.example.task_1_prekt.Service;

import com.example.task_1_prekt.Payload.*;

public interface HollServiceInterface {
    public ResHoll getOne(Long id);
    public ApiResponse editAndCreateHoll(ReqHoll reqHoll);
    public ApiResponse deleteHoll(Long id);
}
