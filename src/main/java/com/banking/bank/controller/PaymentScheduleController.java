package com.banking.bank.controller;

import com.banking.bank.model.CreditOffer;
import com.banking.bank.model.PaymentSchedule;
import com.banking.bank.service.CreditOfferService;
import com.banking.bank.service.PaymentScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PaymentScheduleController {
    private final PaymentScheduleService paymentScheduleService;
    private final CreditOfferService creditOfferService;

    public PaymentScheduleController(PaymentScheduleService paymentScheduleService, CreditOfferService creditOfferService) {
        this.paymentScheduleService = paymentScheduleService;
        this.creditOfferService = creditOfferService;
    }

    @GetMapping("paymentSchedule/{id}")
    public String paymentSchedules(@PathVariable String id, Model model) {
        CreditOffer creditOffer = creditOfferService.findById(id);
        List<PaymentSchedule> paymentScheduleList = creditOffer.getPaymentSchedules();
        model.addAttribute("paymentScheduleList", paymentScheduleList);


        return "paymentSchedule";
    }
}

