package org.acme.dvdstore.controller;

import org.acme.dvdstore.model.Language;
import org.acme.dvdstore.service.BaseService;
import org.acme.dvdstore.service.LanguageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController extends AbstractController<Language> {
	private final LanguageService languageService;

	@Override
	public BaseService<Language, Long> getBaseService() {
		return languageService;
	}
}
