package com.banking.bank.service;


import com.banking.bank.model.PaymentSchedule;
import com.banking.bank.repositiry.BankRepository;
import com.banking.bank.repositiry.CreditOfferRepository;
import com.banking.bank.repositiry.PaymentScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentScheduleService {
    private final PaymentScheduleRepository paymentScheduleRepository;
    private final BankRepository bankRepository;
    private final CreditOfferRepository creditOfferRepository;


    public PaymentScheduleService(PaymentScheduleRepository paymentScheduleRepository, BankRepository bankRepository, CreditOfferRepository creditOfferRepository) {
        this.paymentScheduleRepository = paymentScheduleRepository;
        this.bankRepository = bankRepository;
        this.creditOfferRepository = creditOfferRepository;

    }

    public List<PaymentSchedule> findAllPaymentsSchedule() {
        return paymentScheduleRepository.findAll();
    }

    public PaymentSchedule findById(String id) {
        return paymentScheduleRepository.getOne(id);
    }

    public PaymentSchedule savePS(PaymentSchedule paymentSchedule) {
        return paymentScheduleRepository.save(paymentSchedule);
    }

//    public PaymentSchedule savePaymentSchedule(CreditOffer creditOffer, PaymentSchedule paymentSchedule ){
//
//        LocalDate localDate = LocalDate.now();
//
//
//
//        // Расчет ежемесячного платежа
//        BigDecimal percent = new BigDecimal(creditOffer.getCredit().getPercent() );
//        BigDecimal deadlineCredit = valueOf(creditOffer.getMount());
//        BigDecimal mountPercentMidl = percent.divide(valueOf(100));
//        BigDecimal mountsPercent = mountPercentMidl.divide(deadlineCredit, 5, RoundingMode.HALF_UP);
//
//        BigDecimal amountOfCredit = creditOffer.getAmountOfCredit();
//
//        BigDecimal step1 = mountsPercent.add(valueOf(1));
//        BigDecimal step2 = step1.pow(creditOffer.getMount());
//        BigDecimal step3 = step2.subtract(BigDecimal.valueOf(1));
//        BigDecimal step4 = mountsPercent.divide(step3, 5, RoundingMode.HALF_UP);
//        BigDecimal step5 = mountsPercent.add(step4);
//        BigDecimal mountPay = amountOfCredit.multiply(step5);
//
//        // Рассчет гашения процентов
//
//        BigDecimal percentPayment = amountOfCredit.multiply(mountsPercent);
//
//        //расчет гашения тела
//
//        BigDecimal repaymentBodyCredit = mountPay.subtract(percentPayment);
//
//
//
//        paymentSchedule.setPaymentDate(localDate);
//        paymentSchedule.setPaymentAmount(mountPay);
//        paymentSchedule.setRepaymentPercent(percentPayment);
//        paymentSchedule.setRepaymentCredit(repaymentBodyCredit);
//
//        return paymentScheduleRepository.save(paymentSchedule);
//    }

//    public PaymentSchedule savePaymentSchedules(PaymentSchedule paymentSchedule, Bank bank, CreditOffer creditOffer){
//        LocalDate futureDate = paymentSchedule.getPaymentDate().plusMonths(1);
//
//
//        BigDecimal percent = new BigDecimal(bank.getCredit().getPercent());
//        BigDecimal deadlineCredit = valueOf(creditOffer.getMount());
//        BigDecimal mountPercentMidl = percent.divide(valueOf(100));
//        BigDecimal mountsPercent = mountPercentMidl.divide(deadlineCredit, 5, RoundingMode.HALF_UP);
//
//
//        BigDecimal amountOfCredit = creditOffer.getAmountOfCredit().subtract(paymentSchedule.getRepaymentPercent());
//
//        BigDecimal step1 = mountsPercent.add(valueOf(1));
//        BigDecimal step2 = step1.pow(creditOffer.getMount());
//        BigDecimal step3 = step2.subtract(BigDecimal.valueOf(1));
//        BigDecimal step4 = mountsPercent.divide(step3, 5, RoundingMode.HALF_UP);
//        BigDecimal step5 = mountsPercent.add(step4);
//        BigDecimal mountPay = amountOfCredit.multiply(step5);
//
//
//        BigDecimal percentPayment = amountOfCredit.multiply(mountsPercent);
//
//        BigDecimal repaymentBodyCredit = mountPay.subtract(percentPayment);
//
//        paymentSchedule.setPaymentDate(futureDate);
//        paymentSchedule.setPaymentAmount(mountPay);
//        paymentSchedule.setRepaymentPercent(percentPayment);
//        paymentSchedule.setRepaymentCredit(repaymentBodyCredit);
//
//        return paymentScheduleRepository.save(paymentSchedule);
//    }

}
