package org.acme.dvdstore.service.report;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.acme.dvdstore.model.Customer;
import org.acme.dvdstore.model.system.KeyValue;
import org.acme.dvdstore.util.SpreadsheetStructureBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Component
public class RentalSpreadsheetReportBuilder {
	public Workbook getRentalsPerCustomer(final List<KeyValue<Customer, Long>> rentalsPerCustomer) {
		final SpreadsheetStructureBuilder report = new SpreadsheetStructureBuilder("List of Rentals per Customer");
		report.createHeaderRow(0, "Customer ID", "Firstname", "Lastname", "Number of Rentals");

		report.registerCellStyles(report.numberCellStyle, report.defaultCellStyle, report.defaultCellStyle,
								  report.numberCellStyle);

		final AtomicInteger rowNumberToWriteTo = new AtomicInteger(0);
		//@formatter:off
		rentalsPerCustomer.stream()
						  .forEach(e -> report.createRow(rowNumberToWriteTo.incrementAndGet(),
														 String.valueOf(e.getKey().getId()),
														 e.getKey().getFirstName(),
														 e.getKey().getLastName(),
														 String.valueOf(e.getValue())));
		//@formatter:on
		return report.workbook;
	}
}
