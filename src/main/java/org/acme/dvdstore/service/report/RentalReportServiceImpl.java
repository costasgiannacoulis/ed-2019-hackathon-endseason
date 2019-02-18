package org.acme.dvdstore.service.report;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.acme.dvdstore.model.Customer;
import org.acme.dvdstore.model.Rental;
import org.acme.dvdstore.model.system.KeyValue;
import org.acme.dvdstore.service.RentalService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class RentalReportServiceImpl implements RentalReportService {
	private final RentalService rentalService;

	@Override
	public List<KeyValue<Customer, Long>> getRentalsPerCustomer() {
		final List<Rental> rentals = rentalService.findAll();
		//@formatter:off
		final Map<Customer, Long> rentalsByCustomer = rentals.stream()
												  .collect(groupingBy(o -> o.getCustomer(), Collectors.counting()));
		return rentalsByCustomer.entrySet()
								.stream()
								//.parallelStream()
								.sorted(Entry.<Customer, Long>comparingByValue().reversed())
								.map(e -> new KeyValue<>(e.getKey(), e.getValue()))
								.collect(Collectors.toList());
		//@formatter:on
	}
}
