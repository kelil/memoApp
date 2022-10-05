package com.khalil.memo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khalil.memo.models.RequestFormMemo;
import com.khalil.memo.repositories.RequestFormRepository;
import com.khalil.memo.repositories.UserRequestRepository;

@Service
public class RequestFormService {

    @Autowired
    private RequestFormRepository requestFormRepository;

    @Autowired
    private UserRequestRepository userRequestRepository;


    public RequestFormService(RequestFormRepository requestFormRepository,
            UserRequestRepository userRequestRepository) {
        this.requestFormRepository = requestFormRepository;
        this.userRequestRepository = userRequestRepository;
    }

    public List<RequestFormMemo> getAllRequests() {
        return this.requestFormRepository.findAll();
    }


    public RequestFormMemo addRequest(RequestFormMemo requestFormMemo) {
        this.userRequestRepository.saveAll(requestFormMemo.getRequests());
        return this.requestFormRepository.save(requestFormMemo);
    }
    

}
