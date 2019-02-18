package org.acme.dvdstore.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.acme.dvdstore.model.Customer;
import org.acme.dvdstore.model.Rental;
import org.acme.dvdstore.model.system.ApiResponse;
import org.acme.dvdstore.model.system.KeyValue;
import org.acme.dvdstore.service.BaseService;
import org.acme.dvdstore.service.RentalService;
import org.acme.dvdstore.service.report.RentalReportService;
import org.acme.dvdstore.service.report.RentalSpreadsheetReportBuilder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.acme.dvdstore.util.SpreadsheetStructureBuilder.XLSX_MEDIA_TYPE;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
@Slf4j
public class RentalController extends AbstractController<Rental> {
	private final RentalService rentalService;
	private final RentalReportService rentalReportService;
	private final RentalSpreadsheetReportBuilder reportBuilder;

	@Override
	public BaseService<Rental, Long> getBaseService() {
		return rentalService;
	}

	@GetMapping(headers = "Action=report-getRentalsPerCustomer")
	public ResponseEntity<Resource> reportGetCustomers(
		@RequestHeader(value = "Content-Type", required = false, defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType)
		throws IOException {

		final HttpHeaders headers = new HttpHeaders();
		headers.addAll(getNoCacheHeaders());

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		final List<KeyValue<Customer, Long>> rentalsPerCustomer = rentalReportService.getRentalsPerCustomer();
		if (XLSX_MEDIA_TYPE.toString().equalsIgnoreCase(contentType)) {
			log.trace("Generating XLSX 'report-getRentalsPerCustomer' report.");

			headers.addAll(getDownloadHeaders("report-getrentalspercustomer.xlsx"));
			reportBuilder.getRentalsPerCustomer(rentalsPerCustomer).write(outputStream);
		} else {
			log.trace("Returning JSON representation for 'report-getRentalsPerCustomer' report.");

			final ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(outputStream,
							  ApiResponse.<List<KeyValue<Customer, Long>>>builder().data(rentalsPerCustomer).build());
			contentType = MediaType.APPLICATION_JSON_VALUE;
		}

		return ResponseEntity.ok().contentType(MediaType.valueOf(contentType)).headers(headers).contentLength(
			outputStream.size()).body(new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray())));
	}
}
