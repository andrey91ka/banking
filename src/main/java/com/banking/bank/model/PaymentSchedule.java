package com.banking.bank.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class PaymentSchedule {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "repayment_credit")
    private BigDecimal repaymentCredit;

    @Column(name = "repayment_percent")
    private BigDecimal repaymentPercent;







    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getRepaymentCredit() {
        return repaymentCredit;
    }

    public void setRepaymentCredit(BigDecimal repaymentCredit) {
        this.repaymentCredit = repaymentCredit;
    }

    public BigDecimal getRepaymentPercent() {
        return repaymentPercent;
    }

    public void setRepaymentPercent(BigDecimal repaymentPercent) {
        this.repaymentPercent = repaymentPercent;
    }


}

