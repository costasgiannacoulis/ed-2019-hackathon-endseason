package org.acme.dvdstore.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.acme.dvdstore.model.Actor;
import org.acme.dvdstore.model.Category;
import org.acme.dvdstore.model.Customer;
import org.acme.dvdstore.model.Film;
import org.acme.dvdstore.model.Language;
import org.acme.dvdstore.model.Rental;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import static java.util.Arrays.asList;

public class ContentGenerator {
	private static final Lorem LOREM = LoremIpsum.getInstance();
	public static List<Category> categories = asList(Category.builder().name("Action").build(),
													 Category.builder().name("Animation").build(),
													 Category.builder().name("Children").build(),
													 Category.builder().name("Classics").build(),
													 Category.builder().name("Comedy").build(),
													 Category.builder().name("Documentary").build(),
													 Category.builder().name("Drama").build(),
													 Category.builder().name("Family").build(),
													 Category.builder().name("Foreign").build(),
													 Category.builder().name("Games").build(),
													 Category.builder().name("Horror").build(),
													 Category.builder().name("Music").build(),
													 Category.builder().name("New").build(),
													 Category.builder().name("Sci-Fi").build(),
													 Category.builder().name("Sports").build(),
													 Category.builder().name("Travel").build());

	public static List<Language> languages = asList(Language.builder().name("English").build(),
													Language.builder().name("French").build(),
													Language.builder().name("German").build(),
													Language.builder().name("Spanish").build(),
													Language.builder().name("Italian").build(),
													Language.builder().name("Japanese").build(),
													Language.builder().name("Chinese").build());
	private static List<String> ratings = asList("G", "PG", "PG-13", "R", "NC-17");

	//
//	public static Product createProduct(final List<Category> categories) {
//		return Product.builder().description(LOREM.getWords(2, 4)).price(getRandomPrice()).category(
//			getRandomCategory(categories)).build();
//	}
//
	public static Actor createActor() {
		return Actor.builder().firstName(LOREM.getFirstName()).lastName(LOREM.getLastName()).build();
	}

	public static Customer createCustomer() {
		final String firstName = LOREM.getFirstName();
		final String lastName = LOREM.getLastName();
		return Customer.builder().firstName(firstName).lastName(lastName).email(
			String.format("%s.%s@example.com", firstName, lastName)).build();
	}

	public static Film createFilm(final List<Actor> actors, final List<Category> categories,
								  final List<Language> languages) {
		final Film newFilm = Film.builder()// @formatter:off
								 .title(LOREM.getTitle(1, 5))
								 .description(LOREM.getWords(20, 30))
								 .length(ThreadLocalRandom.current().nextInt(90, 150))
								 .release(ThreadLocalRandom.current().nextInt(1980, 2018))
								 .rating(getRandom(ratings))
								 .language(getRandom(languages))
								 .build();// @formatter:on

		final Set<Actor> participantActors = new HashSet<>();
		for (int i = 0; i < ThreadLocalRandom.current().nextInt(7, 15); i++) {
			participantActors.add(getRandom(actors));
		}
		newFilm.setActors(participantActors);

		final Set<Category> groupingCategories = new HashSet<>();
		for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 3); i++) {
			groupingCategories.add(getRandom(categories));
		}
		newFilm.setCategories(groupingCategories);

		return newFilm;
	}

	public static Rental createRental(final List<Film> films, final List<Customer> customers) {
		return Rental.builder() // @formatter:off
					 .customer(getRandom(customers))
					 .film(getRandom(films))
					 .rentalDate(getRandomDate())
					 .rentalDays(ThreadLocalRandom.current().nextInt(1, 5))
					 .build();
	}				 // @formatter:on

	private static String getRandomRating() {
		return ratings.get(ThreadLocalRandom.current().nextInt(ratings.size()));
	}

	private static <T> T getRandom(final List<T> list) {
		return list.get(ThreadLocalRandom.current().nextInt(list.size()));
	}

	private static Date getRandomDate() {
		final ZonedDateTime fromZdt = LocalDateTime.of(2019, 1, 1, 0, 0, 0).atZone(ZoneId.of("Europe/Athens"));
		final Long fromInMillis = fromZdt.toInstant().toEpochMilli();
		final ZonedDateTime toZdt = LocalDateTime.of(2019, 1, 31, 23, 59, 59).atZone(ZoneId.of("Europe/Athens"));
		final Long toInMillis = toZdt.toInstant().toEpochMilli();

		return new Date(ThreadLocalRandom.current().nextLong(fromInMillis, toInMillis));
	}
}
