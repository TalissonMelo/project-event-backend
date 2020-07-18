package com.talissonmelo.projectevent.infrastructure;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.dto.ParticipantsViewDTO;
import com.talissonmelo.projectevent.infrastructure.exception.ReportException;
import com.talissonmelo.projectevent.resources.EventOrderResource;
import com.talissonmelo.projectevent.services.EventReportService;
import com.talissonmelo.projectevent.services.EventService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JasperEventReportService implements EventReportService {

	@Autowired
	private EventService service;

	@Override
	public byte[] findAllEventParticipants(Integer eventId) {

		try {
			Event event = service.findById(eventId);
			List<ParticipantsViewDTO> participants = EventOrderResource.toCollectionModel(event.getOrders());

			InputStream inputStream = this.getClass().getResourceAsStream("/reports/eventpi.jasper");

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

			JRDataSource dataSource = new JRBeanCollectionDataSource(participants);

			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			throw new ReportException("Não foi possível emitir relatório de Paricipantes", e);
		}
	}

}
