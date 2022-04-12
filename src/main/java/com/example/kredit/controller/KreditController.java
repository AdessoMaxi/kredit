package com.example.kredit.controller;

import com.example.kredit.model.KreditCreateRequest;
import com.example.kredit.model.KreditResponse;
import com.example.kredit.service.KreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class KreditController {
     private final KreditService kreditService;

    public KreditController(KreditService kreditService) {

        this.kreditService = kreditService;
    }

    @GetMapping("/kredits")
    public List<KreditResponse> getAllKredits(){

        return kreditService.findAllKredits();
    }
    @GetMapping("/kredits/accounts-kredits/{accountNo}")
    public Object[] getKreditByANr(
            @PathVariable Integer accountNo
            ){
        String emptyKr = "Kredit does not exist, or Account "+ accountNo +" does not exist ";
        List<KreditResponse> kredit = kreditService.findKreditByAccountNo(accountNo);
        if (kredit.isEmpty())
            return new String[]{emptyKr};
        else
            return kreditService.findKreditByAccountNo(accountNo).toArray();
    }


    /*@GetMapping("/kredits/customer-kredits/{kNr}/{aNr}")
    public Object[] getKreditByKNr(
            @PathVariable Integer kNr,
            @PathVariable Integer aNr
    ){

        String emptyK = "Kredit does not exist, or Customer "+ kNr +" does not exist ";
        String emptyKr = "Kredit does not exist, or Account "+ aNr +" does not exist ";
        List<KreditResponse> kredit = bankingService.findKreditByANr(aNr);
        List<AccountResponse> account = bankingService.findAccountByKNr(kNr);
        if (!kredit.isEmpty())
            if (!account.isEmpty())
                return bankingService.findAccountByKNr(kNr).toArray();
           else return new String[]{emptyK};
        else
            return new String[]{emptyKr};

    }*/

    @PostMapping("/kredits")
    public ResponseEntity<Object> createKredit(
            @RequestBody KreditCreateRequest kRequest
    )  {
        KreditResponse kred = new KreditResponse(

        UUID.randomUUID().hashCode() & Integer.MAX_VALUE,
                kRequest.getAccountNo(),
                kRequest.getKreditAmount(),
                kRequest.getInterestRate(),
                LocalDate.now()
        );
        return kreditService.createKredit(kred);
    }

    private KreditResponse mapToResponse(KreditResponse savedKredit){
        return new KreditResponse(
                savedKredit.getkreditNo(),
                savedKredit.getAccountNo(),
                savedKredit.getKreditAmount(),
                savedKredit.getInterestRate(),
                savedKredit.getStartDate());
    }

    @DeleteMapping("/kredits/{kreditNo}")
    public ResponseEntity deleteKredit(
            @PathVariable Integer kreditNo

    ){
        Optional<KreditResponse> kredit = kreditService.findByKreditNo(kreditNo);

        if (kredit.isPresent()){
            kreditService.deleteByKreditNo(kreditNo);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Kredit with kredit number "+kreditNo+" deleted.");

        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete. Kredit with kredit number "+kreditNo+" does not exist.");

    }

    @DeleteMapping("/kredits/accounts-kredits/{accountNo}")
    public Void deleteKreditsOfAccount(
        @PathVariable Integer accountNo
    ){
        List<KreditResponse> kredit = kreditService.findKreditByAccountNo(accountNo);

        if(kredit.isEmpty()){
            return null;
        }else{
            return kreditService.deleteKreditbyAccountNo(accountNo);
        }
    }

}
