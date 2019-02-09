package org.acme.dvdstore.service;

import org.acme.dvdstore.model.Film;
import org.acme.dvdstore.repository.FilmRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl extends AbstractService<Film> implements FilmService {
	private final FilmRepository filmRepository;

	@Override
	public JpaRepository<Film, Long> getRepository() {
		return filmRepository;
	}
}
