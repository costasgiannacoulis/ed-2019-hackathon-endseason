package org.acme.dvdstore.service;

import org.acme.dvdstore.model.Language;
import org.acme.dvdstore.repository.LanguageRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl extends AbstractService<Language> implements LanguageService {
	private final LanguageRepository languageRepository;

	@Override
	public JpaRepository<Language, Long> getRepository() {
		return languageRepository;
	}
}
