package com.example.kredit.model;

public class KreditCreateRequest {


    private Integer accountNo;
    private Double kreditAmount;
    private Double interestRate;

    public KreditCreateRequest(Integer accountNo,
                               Double kreditAmount, Double interestRate) {

        this.accountNo = accountNo;

        this.kreditAmount = kreditAmount;
        this.interestRate = interestRate;
    }



    public Integer getAccountNo() {return accountNo;}



    public Double getKreditAmount() {return kreditAmount;}

    public Double getInterestRate() {return interestRate;}
}
