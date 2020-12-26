package com.talissonmelo.projectevent.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.talissonmelo.projectevent.domain.Order;
import com.talissonmelo.projectevent.domain.Payment;

public interface EmailService {

	void sendOrderConfirmationEmail(Order order);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Payment obj);
	
	void sendHtmlEmail(MimeMessage msg);
}

