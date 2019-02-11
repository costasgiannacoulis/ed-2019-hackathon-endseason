package org.acme.dvdstore.service.report;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.acme.dvdstore.model.Customer;
import org.acme.dvdstore.util.SpreadsheetStructureBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Component
public class CustomerSpreadsheetReportBuilder {
	public Workbook getCustomers(final List<Customer> customers) {
		final SpreadsheetStructureBuilder report = new SpreadsheetStructureBuilder("List of Customers");
		report.createHeaderRow(0, "Customer ID", "Firstname", "Lastname", "Email Address");

		report.registerCellStyles(report.numberCellStyle, report.defaultCellStyle, report.defaultCellStyle,
								  report.defaultCellStyle);

		final AtomicInteger rowNumberToWriteTo = new AtomicInteger(0);
		customers.stream().forEach(i -> report.createRow(rowNumberToWriteTo.incrementAndGet(),//@formatter:off
														 String.valueOf(i.getId()),
														 i.getFirstName(),
														 i.getLastName(),
														 i.getEmail()));//@formatter:on

		return report.workbook;
	}
}
