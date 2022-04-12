package com.example.kredit.repository;

import com.example.kredit.model.KreditResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class KreditRepository {

    List<KreditResponse> kredits = new ArrayList<>();

    public  List<KreditResponse> findAll() {

        return kredits;
    }

    public ResponseEntity<Object> save(KreditResponse kRequest){

        kredits.add(
                new KreditResponse(

                        UUID.randomUUID().hashCode() & Integer.MAX_VALUE,
                        kRequest.getAccountNo(),
                        kRequest.getKreditAmount(),
                        kRequest.getInterestRate(),
                        LocalDate.now())
        );
        return ResponseEntity.ok().build();
    }

    public void deleteByKreditNo(Integer kreditNo) {
        this.kredits = kredits.stream()
                .filter(p -> !p.getkreditNo().equals(kreditNo))
                .collect(Collectors.toList());
    }


    public Optional<KreditResponse> findBykreditNo(Integer kreditNo) {
        Optional<KreditResponse> kredit = kredits.stream()
                .filter(c -> c.getkreditNo().equals(kreditNo))
                .findFirst();
        return kredit;
    }

    public List<KreditResponse> findKreditByAccountNo(Integer accountNo) {
        List<KreditResponse> kredit = kredits.stream()
                .filter(c -> c.getAccountNo().equals(accountNo))
                .collect(Collectors.toList());
        return kredit;
    }


   /* public List<KreditResponse> findKreditByKNr(Integer kNr, Integer accountNo) {
        if(!findKreditByAccountNo(accountNo).isEmpty()) {
            List<KreditResponse> kredit = kredits.stream().filter(c -> c.getAccountNo().equals(accountNo))
                    .collect(Collectors.toList());



        }
    }*/

    public void deleteByAccountNo(Integer accountNo) {
        this.kredits = kredits.stream().filter(k -> !k.getAccountNo().equals(accountNo)).collect(Collectors.toList());
    }
}

/*
//public interface KreditRepository extends JpaRepository<KreditResponse, Integer> {

    @Query("delete from KreditResponse a where a.accountNo = ?1")
    void deleteByAccountNo(Integer accountNo);

    @Query("SELECT a from KreditResponse a where a.accountNo = ?1")
    List<KreditResponse> findKreditByAccountNo(Integer accountNo);

}*/
