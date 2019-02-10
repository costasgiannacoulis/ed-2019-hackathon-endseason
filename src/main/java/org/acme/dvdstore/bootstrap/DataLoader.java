package org.acme.dvdstore.bootstrap;

import java.util.List;
import java.util.stream.IntStream;

import org.acme.dvdstore.model.Actor;
import org.acme.dvdstore.model.Category;
import org.acme.dvdstore.model.Customer;
import org.acme.dvdstore.model.Film;
import org.acme.dvdstore.model.Language;
import org.acme.dvdstore.service.ActorService;
import org.acme.dvdstore.service.CategoryService;
import org.acme.dvdstore.service.CustomerService;
import org.acme.dvdstore.service.FilmService;
import org.acme.dvdstore.service.LanguageService;
import org.acme.dvdstore.service.RentalService;
import org.acme.dvdstore.util.ContentGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.acme.dvdstore.util.ContentGenerator.createActor;
import static org.acme.dvdstore.util.ContentGenerator.createCustomer;
import static org.acme.dvdstore.util.ContentGenerator.createFilm;
import static org.acme.dvdstore.util.ContentGenerator.createRental;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("sampleData")
public class DataLoader implements CommandLineRunner {
	private final ActorService actorService;
	private final CategoryService categoryService;
	private final CustomerService customerService;
	private final FilmService filmService;
	private final LanguageService languageService;
	private final RentalService rentalService;

	@Override
	public void run(final String... args) throws Exception {
		log.trace("Create sample categories");
		categoryService.createAll(ContentGenerator.categories.stream().toArray(Category[]::new));

		log.trace("Create sample languages");
		languageService.createAll(ContentGenerator.languages.stream().toArray(Language[]::new));

		log.trace("Create sample actors");
		IntStream.rangeClosed(1, 300).forEach(i -> actorService.create(createActor()));

		log.trace("Create sample customers");
		IntStream.rangeClosed(1, 500).forEach(i -> customerService.create(createCustomer()));

		log.trace("Create sample films");
		final List<Actor> actors = actorService.findAll();
		final List<Category> categories = categoryService.findAll();
		final List<Language> languages = languageService.findAll();
		log.debug("loaded!");
		IntStream.rangeClosed(1, 2000).forEach(i -> filmService.create(createFilm(actors, categories, languages)));

		log.trace("Create sample rentals");
		final List<Film> films = filmService.findAll();
		final List<Customer> customers = customerService.findAll();
		IntStream.rangeClosed(1, 500).forEach(i -> rentalService.create(createRental(films, customers)));
	}
}
