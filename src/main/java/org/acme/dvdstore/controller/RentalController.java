package org.acme.dvdstore.controller;

import org.acme.dvdstore.model.Rental;
import org.acme.dvdstore.service.BaseService;
import org.acme.dvdstore.service.RentalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController extends AbstractController<Rental> {
	private final RentalService rentalService;

	@Override
	public BaseService<Rental, Long> getBaseService() {
		return rentalService;
	}
}
