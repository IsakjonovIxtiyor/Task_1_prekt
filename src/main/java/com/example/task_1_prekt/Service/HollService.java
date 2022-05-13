package com.example.task_1_prekt.Service;


import com.example.task_1_prekt.Entity.Holl;
import com.example.task_1_prekt.Payload.ApiResponse;
import com.example.task_1_prekt.Payload.ReqHoll;
import com.example.task_1_prekt.Payload.ResHoll;
import com.example.task_1_prekt.Repository.HollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class HollService implements HollServiceInterface{
    @Autowired
    private HollRepository hollRepository;

    @Override
    public ResHoll getOne(Long id) {
        Optional<Holl> byId = hollRepository.findById(id);
        if (byId.isPresent()){
            Holl holl = byId.get();
            return new ResHoll(holl);
        }else{
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUser");
        }
    }

    @Override
    public ApiResponse editAndCreateHoll(ReqHoll reqHoll) {
        try {
            Holl holl = new Holl();
            if (reqHoll.getId() != null) {
                hollRepository.findById(reqHoll.getId()).orElseThrow(() ->
                        new IllegalStateException("Region with this id not found"));
            }
            holl.setZal(reqHoll.getZal());
            holl.setRow(reqHoll.getRow());
            holl.setPlacePrice(reqHoll.getPlacePrice());
            holl.setPlaceStatus(reqHoll.getPlaceStatus());

            hollRepository.save(holl);
            return new ApiResponse(reqHoll.getId() != null ? "Edit" : "Save", true);
        } catch (Exception e) {
            return new ApiResponse("Id yoq", false);
        }
    }

    @Override
    public ApiResponse deleteHoll(Long id) {
        Optional<Holl> byId = hollRepository.findById(id);
        if (byId.isPresent()){
            hollRepository.deleteById(id);

            return new ApiResponse("Holl o`chirildi",true);

        }else{
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUser");
        }
    }
}
