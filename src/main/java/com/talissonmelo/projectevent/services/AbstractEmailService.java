package com.talissonmelo.projectevent.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.talissonmelo.projectevent.domain.Order;
import com.talissonmelo.projectevent.domain.Payment;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(Order order) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(order);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order order) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(order.getUser().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Confirmação de Cadastro em Evento.\n Ingresso número : " + order.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(order.toString());
		return sm;
	}

	protected String htmlFromTemplatePayment(Payment obj) {
		Context context = new Context();
		context.setVariable("payment", obj);
		return templateEngine.process("email/confirmationPayment", context);
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Payment obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromPayment(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			throw new ObjectNotFoundException("Não Foi possivel enviar o email...");
		}
	}

	protected MimeMessage prepareMimeMessageFromPayment(Payment obj) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(message, true);
		mmh.setTo(obj.getOrder().getUser().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pagamento Confirmado!. Codigo : " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePayment(obj), true);
		
		mmh.addInline("img", new ClassPathResource("templates/email/event.png") );
		return message;
	}
}
