package org.acme.dvdstore.service;

import org.acme.dvdstore.model.Rental;
import org.acme.dvdstore.repository.RentalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl extends AbstractService<Rental> implements RentalService {
	private final RentalRepository rentalRepository;

	@Override
	public JpaRepository<Rental, Long> getRepository() {
		return rentalRepository;
	}
}
