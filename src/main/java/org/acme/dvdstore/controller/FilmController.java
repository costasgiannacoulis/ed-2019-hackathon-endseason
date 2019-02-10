package org.acme.dvdstore.controller;

import org.acme.dvdstore.model.Film;
import org.acme.dvdstore.service.BaseService;
import org.acme.dvdstore.service.FilmService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController extends AbstractController<Film> {
	private final FilmService filmService;

	@Override
	public BaseService<Film, Long> getBaseService() {
		return filmService;
	}
}
