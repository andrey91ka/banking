package com.banking.bank.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Credit {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @Column(name = "credit_limit")
    private BigDecimal limit;

    @Column(name = "credit_percent")
    private Double percent;

    public BigDecimal getLimit() {
        return limit;
    }

    public Double getPercent() {
        return percent;
    }
}
