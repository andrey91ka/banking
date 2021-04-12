package com.banking.bank.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class CreditOffer {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @Column(name = "credit_amount")
    private BigDecimal amountOfCredit;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PaymentSchedule> paymentSchedules;

    private int mount;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setAmountOfCredit(BigDecimal amountOfCredit) {
        this.amountOfCredit = amountOfCredit;
    }

    public Client getClient() {
        return client;
    }

    public List<PaymentSchedule> getPaymentSchedules() {
        return paymentSchedules;
    }

    public void setPaymentSchedules(List<PaymentSchedule> paymentSchedules) {
        this.paymentSchedules = paymentSchedules;
    }

    public void setMount(int mount) {
        this.mount = mount;
    }

    public Credit getCredit() {
        return credit;
    }

    public int getMount() {
        return mount;
    }

    public BigDecimal getAmountOfCredit() {
        return amountOfCredit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }


}


