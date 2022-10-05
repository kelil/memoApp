package com.khalil.memo.controllers;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khalil.memo.models.RequestFormMemo;
import com.khalil.memo.models.UserRole;
import com.khalil.memo.repositories.UserRoleRepository;
import com.khalil.memo.services.MemoExportService;
import com.khalil.memo.services.RequestFormService;

@CrossOrigin(origins = "http://10.1.11.145:4200", maxAge = 300, allowCredentials = "true")
@RestController
@RequestMapping(path = "api/v1/requestForms")
public class RequestFormController {
    
    @Autowired
    private RequestFormService requestFormService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private MemoExportService exportService;

    public RequestFormController(RequestFormService requestFormService, UserRoleRepository userRoleRepository) {
        this.requestFormService = requestFormService;
        this.userRoleRepository = userRoleRepository;
    }

    @GetMapping()
    public List<RequestFormMemo> getAllRequests(){
        return this.requestFormService.getAllRequests();
    }

    @GetMapping("/userRoles")
    public List<UserRole> getUserRoles(){
        return this.userRoleRepository.findAll();
    }

    @PostMapping("/request")
    public RequestFormMemo addRequest(@RequestBody RequestFormMemo requestFormMemo){
        return this.requestFormService.addRequest(requestFormMemo);
    }

    @PostMapping(value = "/generateRequestMemo")
    public ResponseEntity<InputStreamResource> generateMemo(@RequestBody RequestFormMemo requestForm){
        //System.out.println(requestForm.getSigniture());
        ByteArrayInputStream byteArrayInputStream = exportService.requestFormPdf(requestForm);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline, filename="+requestForm.getSubject()+".pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteArrayInputStream));
    }

    
}
