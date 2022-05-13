package com.example.task_1_prekt.Controller;

import com.example.task_1_prekt.Payload.ReqFilm;
import com.example.task_1_prekt.Payload.ReqHoll;
import com.example.task_1_prekt.Service.HollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/holl")
public class HollController {
    @Autowired
    private HollService hollService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneHoll(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(hollService.getOne(id));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public HttpEntity<?> EditAndCreateHoll(@RequestBody ReqHoll reqHoll) {
        return ResponseEntity.ok().body(hollService.editAndCreateHoll(reqHoll));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteHoll(@PathVariable Long id) {
        return ResponseEntity.ok().body(hollService.deleteHoll(id));
    }


}
