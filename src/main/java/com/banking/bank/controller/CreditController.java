package com.banking.bank.controller;


import com.banking.bank.model.Credit;
import com.banking.bank.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CreditController {
    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }


    @GetMapping("/credits")
    public String credits(Model model) {
        List<Credit> credits = creditService.findAll();
        model.addAttribute("credits", credits);
        return "credit/credits";
    }

    @GetMapping("/credit-add")
    public String createClient(Credit credit) {
        return "credit/credit-add";
    }

    @PostMapping("/credit-add")
    public String creditAdd(Credit credit) {
        creditService.saveCredit(credit);
        return "redirect:/credits";
    }

    @GetMapping("credit-delete/{id}")
    public String deleteCredit(@PathVariable("id") String id) {
        creditService.deleteCredit(id);
        return "redirect:/credits";
    }

    @GetMapping("/credit-update/{id}")
    public String updateCreditForm(@PathVariable("id") String id, Model model) {
        Credit credit = creditService.findById(id);
        model.addAttribute("credit", credit);
        return "credit/credit-update";
    }

    @PostMapping("/credit-update")
    public String updateCredit(Credit credit) {
        creditService.saveCredit(credit);
        return "redirect:/credits";
    }
}
