package com.talissonmelo.projectevent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talissonmelo.projectevent.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
