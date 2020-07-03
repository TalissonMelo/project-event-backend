package com.talissonmelo.projectevent.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Address;
import com.talissonmelo.projectevent.domain.City;
import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.domain.Order;
import com.talissonmelo.projectevent.domain.Payment;
import com.talissonmelo.projectevent.domain.State;
import com.talissonmelo.projectevent.domain.Ticket;
import com.talissonmelo.projectevent.domain.User;
import com.talissonmelo.projectevent.domain.enums.EventType;
import com.talissonmelo.projectevent.domain.enums.StatusPayment;
import com.talissonmelo.projectevent.domain.enums.UserType;
import com.talissonmelo.projectevent.repositories.AddressRepository;
import com.talissonmelo.projectevent.repositories.CityRepository;
import com.talissonmelo.projectevent.repositories.EventRepository;
import com.talissonmelo.projectevent.repositories.OrderRepository;
import com.talissonmelo.projectevent.repositories.PaymentRepository;
import com.talissonmelo.projectevent.repositories.StateRepository;
import com.talissonmelo.projectevent.repositories.TicketRepository;
import com.talissonmelo.projectevent.repositories.UserRepository;

@Service
public class DBService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private TicketRepository ticketRepository;

	public void instantiateTestDataBase() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		User user2 = new User(null, "Patrick", "patrickferdinan@gmail.com", UserType.PHYSICS, "000.000.000-00",
				"5534991136428", "123456");
		User user1 = new User(null, "Talisson", "talisson.cursos@gmail.com", UserType.LEGAL, "000.000.000-01",
				"34992319208", "123456");
		User user3 = new User(null, "João", "joao@gmail.com", UserType.PHYSICS, "000.000.000-00",
				"5534991136428", "123456");
		User user4 = new User(null, "Palmiro", "palmiro@gmail.com", UserType.LEGAL, "000.000.000-01",
				"34992319208", "123456");
		User user5 = new User(null, "Yuri", "yuri@gmail.com", UserType.LEGAL, "000.000.000-01",
				"34992319208", "123456");

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "Goiás");

		City ct1 = new City(null, "Araguari", st1);
		City ct2 = new City(null, "Itumbiara", st2);

		st1.getCities().addAll(Arrays.asList(ct1));
		st2.getCities().addAll(Arrays.asList(ct2));

		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(ct1, ct2));

		Address ad1 = new Address(null, "Centro", "Laboratorio 1", "Av. Minas Gerais", "1889", "38444-128", ct1);
		Address ad2 = new Address(null, "Centro", "Laboratorio 1", "Av. Minas Gerais", "1889", "38444-1283", ct2);

		addressRepository.saveAll(Arrays.asList(ad1, ad2));

		Event ev1 = new Event(null, "Análise e Desenvolvimento de Sistemas",
				"Apresentação dos Trabalhos de término de período.", sdf.parse("13/07/2020 21:00:00"),
				sdf.parse("13/07/2020 22:00:00"), 1.99, EventType.PUBLIC, ad1, user1);
		Event ev2 = new Event(null, "Bienal de ideias", "Apresentação dos Trabalhos", sdf.parse("02/11/2020 19:15:00"),
				sdf.parse("02/11/2020 22:40:00"), 80.00, EventType.PRIVATE, ad1, user1);

		ev2.getParticipants()
				.addAll(Arrays.asList("000.000.000-01", "000.000.000-02", "000.000.000-03", "000.000.000-04", "000.000.000-05"));

		user1.getEvents().addAll(Arrays.asList(ev1));
		user2.getEvents().addAll(Arrays.asList(ev2));

		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
		eventRepository.saveAll(Arrays.asList(ev1, ev2));

		Order or1 = new Order(null, sdf.parse("01/03/2020 11:59:00"), user2);
		Order or2 = new Order(null, sdf.parse("01/03/2020 11:59:00"), user1);

		Payment pay = new Payment(null, StatusPayment.AWAITING_PAYMENT, or1);
		or1.setPayment(pay);

		Payment pay2 = new Payment(null, StatusPayment.PAID, or2);
		or2.setPayment(pay2);

		user2.getOrders().addAll(Arrays.asList(or1));
		user1.getOrders().addAll(Arrays.asList(or2));

		orderRepository.saveAll(Arrays.asList(or1, or2));
		paymentRepository.saveAll(Arrays.asList(pay, pay2));

		Ticket t1 = new Ticket(or1, ev1, 1, 50.00);

		or1.getTickets().addAll(Arrays.asList(t1));

		ev1.getTickets().addAll(Arrays.asList(t1));

		ticketRepository.saveAll(Arrays.asList(t1));
	}
}
