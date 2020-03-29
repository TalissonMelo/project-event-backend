package com.talissonmelo.projectevent.services;

import org.springframework.mail.SimpleMailMessage;

import com.talissonmelo.projectevent.domain.Order;

public interface EmailService {

	void sendOrderConfirmationEmail(Order order);
	
	void sendEmail(SimpleMailMessage msg);
}

