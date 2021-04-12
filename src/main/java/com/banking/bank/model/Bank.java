package com.banking.bank.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Data
public class Bank {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Credit credit;

    private String CreditOfferId;

    public String getCreditOfferId() {
        return CreditOfferId;
    }

    public void setCreditOfferId(String creditOfferId) {
        CreditOfferId = creditOfferId;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Credit getCredit() {
        return credit;
    }
}
