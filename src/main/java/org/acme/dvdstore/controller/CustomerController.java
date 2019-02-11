package org.acme.dvdstore.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.acme.dvdstore.model.Customer;
import org.acme.dvdstore.model.system.ApiResponse;
import org.acme.dvdstore.service.BaseService;
import org.acme.dvdstore.service.CustomerService;
import org.acme.dvdstore.service.report.CustomerSpreadsheetReportBuilder;
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
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController extends AbstractController<Customer> {
	private final CustomerService customerService;
	private final CustomerSpreadsheetReportBuilder reportBuilder;

	@Override
	public BaseService<Customer, Long> getBaseService() {
		return customerService;
	}

	@GetMapping(headers = "Action=report-getCustomers")
	public ResponseEntity<Resource> reportGetCustomers(
		@RequestHeader(value = "Content-Type", required = false, defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType)
		throws IOException {

		final HttpHeaders headers = new HttpHeaders();
		headers.addAll(getNoCacheHeaders());

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		final List<Customer> customers = customerService.findAll();
		if (XLSX_MEDIA_TYPE.toString().equalsIgnoreCase(contentType)) {
			headers.addAll(getDownloadHeaders("report-getCustomers.xlsx"));
			log.trace("Generating XLSX report for {} customers.", customers.size());
			reportBuilder.getCustomers(customers).write(outputStream);
		} else {
			log.trace("Returning JSON representation for {} customers.", customers.size());
			final ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(outputStream, ApiResponse.<List>builder().data(customers).build());
			contentType = MediaType.APPLICATION_JSON_VALUE;
		}

		return ResponseEntity.ok().contentType(MediaType.valueOf(contentType)).headers(headers).contentLength(
			outputStream.size()).body(new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray())));
	}
}
