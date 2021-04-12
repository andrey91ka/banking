package com.banking.bank.controller;


import com.banking.bank.model.*;
import com.banking.bank.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;

@Controller
public class CreditOfferController {
    private final CreditOfferService creditOfferService;
    private final CreditService creditService;
    private final ClientService clientService;
    private final BankService bankService;
    private final PaymentScheduleService paymentScheduleService;

    public CreditOfferController(CreditOfferService creditOfferService, ClientService clientService,
                                 CreditService creditService, BankService bankService, PaymentScheduleService paymentScheduleService) {
        this.creditOfferService = creditOfferService;
        this.clientService = clientService;
        this.creditService = creditService;
        this.bankService = bankService;
        this.paymentScheduleService = paymentScheduleService;
    }

    @GetMapping("/creditSchedule")
    public String creditOdder(Model model) {
        List<CreditOffer> creditOfferList = creditOfferService.creditOfferList();
        model.addAttribute("creditOfferList", creditOfferList);
        return "creditSchedule";
    }


    @GetMapping("/creditoffer-add")
    public String createClient(Model model) {
        CreditOffer creditOffer = new CreditOffer();
        model.addAttribute("creditOffer", creditOffer);

        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);

        List<Credit> credits = creditService.findAll();
        model.addAttribute("credits", credits);

        List<Integer> mounts = Arrays.asList(3, 6, 9, 12, 18, 24, 30, 36, 48, 60);
        model.addAttribute("mounts", mounts);

        return "creditoffer-add";
    }


    @PostMapping("/creditoffer-add")
    public String paymentScheduleadd(@RequestParam(value = "amountOfCredit") BigDecimal amountOfCredit,
                                     @RequestParam(value = "mount") int mount, @RequestParam("client") Client client) {

        // Подбор подходящего кредита для заданной суммы кредита

        List<Credit> credits = creditService.findAll();
        List<Credit> sorted = credits.stream()
                .filter(credit -> amountOfCredit.compareTo(credit.getLimit()) <= 0)
                .sorted(Comparator.comparing(Credit::getLimit)).collect(Collectors.toList());
        Credit currentCredit = sorted.stream().findFirst().orElse(null);


        // Расчет ежемесячной процентной ставки

        BigDecimal percent = new BigDecimal(currentCredit.getPercent());
        BigDecimal deadlineCredit = valueOf(mount);
        BigDecimal mountPercentMiddle = percent.divide(valueOf(100));
        BigDecimal mountsPercent = mountPercentMiddle.divide(deadlineCredit, 5, RoundingMode.HALF_UP);

        //  Расчет ежемесячного платежа
        //  P = S * (i + i / (1 + i)^n - 1 )
        //  где: P – ежемесячный платёж по кредиту ;
        //  S – сумма кредита;
        //  i – ежемесячная процентная ставка (рассчитывается по следующей формуле: годовая процентная ставка/100/12);
        //  n – срок, на который берётся кредит (указывается количество месяцев).

        BigDecimal step1 = mountsPercent.add(valueOf(1));
        BigDecimal step2 = step1.pow(mount);
        BigDecimal step3 = step2.subtract(BigDecimal.valueOf(1));
        BigDecimal step4 = mountsPercent.divide(step3, 5, RoundingMode.HALF_UP);
        BigDecimal step5 = mountsPercent.add(step4);
        MathContext m = new MathContext(4);
        BigDecimal monthlyPayment = amountOfCredit.multiply(step5, m);

        // Рассчет гашения процентов

        MathContext mc = new MathContext(5);
        BigDecimal percentPayment = amountOfCredit.multiply(mountsPercent, mc);

        //расчет гашения тела

        BigDecimal repaymentBodyCredit = monthlyPayment.subtract(percentPayment);

        LocalDate localDate = LocalDate.now();

        List<PaymentSchedule> paymentScheduleList = new ArrayList<>();
        CreditOffer creditOffer = new CreditOffer();
        PaymentSchedule paymentSchedule = new PaymentSchedule();

        paymentSchedule.setPaymentDate(localDate);
        paymentSchedule.setPaymentAmount(monthlyPayment);
        paymentSchedule.setRepaymentPercent(percentPayment);
        paymentSchedule.setRepaymentCredit(repaymentBodyCredit);


        paymentScheduleService.savePS(paymentSchedule);
        paymentScheduleList.add(paymentSchedule);

        BigDecimal amountOfCredit1 = amountOfCredit.subtract(repaymentBodyCredit);

        //цикл для расчета и добавления в поля графика платежей последующей информацией о платежах

        for (int i = 0; i < mount - 1; i++) {
            PaymentSchedule paymentSchedule1 = new PaymentSchedule();

            BigDecimal repaymentPercent1 = amountOfCredit1.multiply(mountsPercent);
            BigDecimal repaymentCredit1 = monthlyPayment.subtract(repaymentPercent1);
            amountOfCredit1 = amountOfCredit1.subtract(repaymentCredit1);

            paymentSchedule1.setPaymentDate(localDate.plusMonths(i));
            paymentSchedule1.setPaymentAmount(monthlyPayment);
            paymentSchedule1.setRepaymentPercent(repaymentPercent1);
            paymentSchedule1.setRepaymentCredit(repaymentCredit1);


            paymentScheduleService.savePS(paymentSchedule1);
            paymentScheduleList.add(paymentSchedule1);
        }


        creditOffer.setMount(mount);
        creditOffer.setClient(client);
        creditOffer.setCredit(currentCredit);
        creditOffer.setAmountOfCredit(amountOfCredit);
        creditOffer.setPaymentSchedules(paymentScheduleList);
        creditOfferService.saveCreditOffer(creditOffer);

        Bank bank = new Bank();
        bank.setClient(client);
        bank.setCredit(currentCredit);
        bank.setCreditOfferId(creditOffer.getId());
        bankService.saveBank(bank);


        return "redirect:/credit-offer";
    }

    @GetMapping("/credit-offer")
    public String creditOffer(Model model) {
        List<CreditOffer> creditOffers = creditOfferService.creditOfferList();
        model.addAttribute("creditOffers", creditOffers);

        return "credit-offer";
    }


}