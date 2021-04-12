package com.banking.bank.service;


import com.banking.bank.model.Bank;
import com.banking.bank.repositiry.BankRepository;
import com.banking.bank.repositiry.CreditOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    private final BankRepository bankRepository;
    private final CreditOfferRepository creditOfferRepository;

    @Autowired
    public BankService(BankRepository bankRepository, CreditOfferRepository creditOfferRepository) {
        this.bankRepository = bankRepository;
        this.creditOfferRepository = creditOfferRepository;
    }

    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Bank findById(String id) {
        return bankRepository.getOne(id);
    }

    public void deleteBank(String id) {
        bankRepository.deleteById(id);
    }


}
