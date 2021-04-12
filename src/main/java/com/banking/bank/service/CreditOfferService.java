package com.banking.bank.service;


import com.banking.bank.model.CreditOffer;
import com.banking.bank.repositiry.CreditOfferRepository;
import com.banking.bank.repositiry.CreditRepository;
import com.banking.bank.repositiry.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditOfferService {

    private final CreditOfferRepository creditOfferRepository;
    private final PaymentScheduleRepository paymentScheduleRepository;
    private final CreditRepository creditRepository;

    @Autowired
    public CreditOfferService(CreditOfferRepository creditOfferRepository, PaymentScheduleRepository paymentScheduleRepository, CreditRepository creditRepository) {
        this.creditOfferRepository = creditOfferRepository;
        this.paymentScheduleRepository = paymentScheduleRepository;
        this.creditRepository = creditRepository;
    }

    public List<CreditOffer> creditOfferList() {
        return creditOfferRepository.findAll();
    }

    public CreditOffer saveCreditOffer(CreditOffer creditOffer) {

//        List<Credit> credits = creditRepository.findAll();
//
//        List<Credit> sorted = credits.stream()
//                .filter(credit -> creditOffer.getAmountOfCredit().compareTo(credit.getLimit()) <= 0)
//                .sorted(Comparator.comparing(Credit::getLimit)).collect(Collectors.toList());
//
//        Credit currentCredit = sorted.stream().findFirst().orElse(null);
//        creditOffer.setCredit(currentCredit);


        return creditOfferRepository.save(creditOffer);
    }


    public CreditOffer findById(String id) {
        return creditOfferRepository.getOne(id);
    }

    public void deleteCreditOffer(String id) {
        creditOfferRepository.deleteById(id);
    }


}
