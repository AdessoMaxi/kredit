package com.example.kredit.service;

import com.example.kredit.model.KreditResponse;
import com.example.kredit.repository.KreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class KreditService {

    private final KreditRepository kreditRepository;

    public KreditService(KreditRepository kreditRepository){
        this.kreditRepository=kreditRepository;
    }
    /*@Autowired
    RestTemplate restTemplate;*/


    public List<KreditResponse> findAllKredits() {
        return kreditRepository.findAll();
    }
    public Optional<KreditResponse> findByKreditNo(Integer kreditNo){
        return kreditRepository.findBykreditNo(kreditNo);
    }
    public List<KreditResponse> findKreditByAccountNo(Integer accountNo){
        return kreditRepository.findKreditByAccountNo(accountNo);
    }

    public ResponseEntity<Object> createKredit(KreditResponse kreditNo){

//        boolean accounts = (restTemplate.getForObject("Http://localhost:8085/api/accounts/numbers", List.class).contains(kreditNo.getAccountNo()));
        //boolean accounts = (restTemplate.getForObject("Http://accounts:8085/api/accounts/numbers", List.class).contains(kreditNo.getAccountNo()));

        /*if(accounts == false){
            return null;
        }else {*/
            return kreditRepository.save(kreditNo);
        //}
    }

    public void deleteByKreditNo(Integer kreditNo) {
        kreditRepository.deleteByKreditNo(kreditNo);
    }


    public Void deleteKreditbyAccountNo(Integer accountNo) {
        kreditRepository.deleteByAccountNo(accountNo);
        return null;
    }
}
