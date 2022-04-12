package com.example.kredit.model;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
import java.time.LocalDate;

//@Entity
//@Table(name = "kredits")
public class KreditResponse{

//    @Id
    private Integer kreditNo;
    private Integer accountNo;
    private Double kreditAmount;
    private Double interestRate;
    private LocalDate startDate;

    public KreditResponse() {
    }

    public KreditResponse(Integer kreditNo, Integer accountNo,
                          Double kreditAmount,
                          Double interestRate,
                          LocalDate startDate) {

        this.kreditNo = kreditNo;
        this.accountNo = accountNo;
        this.kreditAmount = kreditAmount;
        this.interestRate = interestRate;
        this.startDate = startDate;
    }


    public Integer getkreditNo() {return kreditNo;}

    public Integer getAccountNo() {return accountNo;}

    public Double getKreditAmount() {return Math.round(kreditAmount*100.0)/100.0;}

    public Double getInterestRate() {return Math.round(interestRate*100.0)/100.0;}

    public LocalDate getStartDate() {return startDate;}

}
