package com.khalil.memo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.khalil.memo.models.Memo;
import com.khalil.memo.payload.responses.MessageResponse;
import com.khalil.memo.repositories.MemoRepository;

@Service
public class MemoService {
    
    private MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository){
        this.memoRepository = memoRepository;
    }

    public List<Memo> getAllMemo() {
        return memoRepository.findAllByOrderByIdDesc();
    }

    public ResponseEntity<?> createMemo(Memo memo) {
        memoRepository.save(memo);
        return ResponseEntity.ok().body(new MessageResponse("Successfully Created!!"));
    }


    public Memo getMemoById(Integer id) {
        return memoRepository.findById(id).get();
    }
}
