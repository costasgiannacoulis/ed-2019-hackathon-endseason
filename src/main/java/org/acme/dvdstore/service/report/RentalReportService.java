package org.acme.dvdstore.service.report;

import java.util.List;

import org.acme.dvdstore.model.Customer;
import org.acme.dvdstore.model.system.KeyValue;

public interface RentalReportService {
	List<KeyValue<Customer, Long>> getRentalsPerCustomer();
}
