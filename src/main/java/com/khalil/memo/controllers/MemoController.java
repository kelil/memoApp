package com.khalil.memo.controllers;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.khalil.memo.services.MemoExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.khalil.memo.models.Memo;
import com.khalil.memo.repositories.EmployeeRepository;
import com.khalil.memo.services.DivisionService;
import com.khalil.memo.services.MemoService;

@CrossOrigin(origins = "http://10.1.11.145:4200", maxAge = 300, allowCredentials = "true")
@RestController
@RequestMapping(path = "api/v1/memos" )
public class MemoController {

    @Autowired
    private MemoService memoService;

    @Autowired
    private MemoExportService exportService;


    public MemoController(MemoService memoService, MemoExportService exportService){
        this.memoService = memoService;
        this.exportService = exportService;
    }
    
    @GetMapping()
    public List<Memo> getAllMemo(){
        return memoService.getAllMemo();
    }

    @PostMapping(value = "/memo")
    public ResponseEntity<?> createMemo(@RequestBody Memo memo){
        return memoService.createMemo(memo);
    }

    @PostMapping(value = "/generateMemo")
    public ResponseEntity<InputStreamResource> generateMemo(@RequestBody Memo memo){
        ByteArrayInputStream byteArrayInputStream = exportService.memoPdf(memo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline, filename="+memo.getName()+".pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteArrayInputStream));
    }
}
