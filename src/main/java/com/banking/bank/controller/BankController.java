package com.banking.bank.controller;


import com.banking.bank.model.Bank;
import com.banking.bank.model.CreditOffer;
import com.banking.bank.service.BankService;
import com.banking.bank.service.CreditOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BankController {

    private final BankService bankService;
    private final CreditOfferService creditOfferService;

    @Autowired
    public BankController(BankService bankService, CreditOfferService creditOfferService) {
        this.bankService = bankService;
        this.creditOfferService = creditOfferService;
    }

    @GetMapping("/")
    public String main(){
        return "mainPage";
    }

    @GetMapping("/bank-list")
    public String banks(Model model) {
        List<Bank> banks = bankService.findAll();
        model.addAttribute("banks", banks);
        List<CreditOffer> creditOffers = creditOfferService.creditOfferList();
        model.addAttribute("creditOffers", creditOffers);
        return "bank-list";
    }

    @GetMapping("bank-delete/{id}")
    public String deleteBank(@PathVariable("id") String id) {
        Bank bank = bankService.findById(id);
        creditOfferService.deleteCreditOffer(bank.getCreditOfferId());
        bankService.deleteBank(id);
        return "redirect:/bank-list";
    }

}