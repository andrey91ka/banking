package com.banking.bank.service;


import com.banking.bank.model.Credit;
import com.banking.bank.repositiry.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {
    private final CreditRepository creditRepository;

    @Autowired
    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public List<Credit> findAll() {
        return creditRepository.findAll();
    }

    public Credit findById(String id) {
        return creditRepository.getOne(id);
    }

    public Credit saveCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    public void deleteCredit(String id) {
        creditRepository.deleteById(id);
    }


}
