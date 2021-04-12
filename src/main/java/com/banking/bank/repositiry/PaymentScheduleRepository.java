package com.banking.bank.repositiry;


import com.banking.bank.model.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, String> {
}
