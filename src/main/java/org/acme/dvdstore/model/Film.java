package org.acme.dvdstore.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "FILMS")
@SequenceGenerator(name = "idGenerator", sequenceName = "FILMS_SEQ", initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Film extends BaseEntity {
	@NotNull
	@Column(length = 100, nullable = false)
	private String title;
	@NotNull
	@Column(length = 512, nullable = false)
	private String description;
	@Column
	private int release;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "language_id", nullable = false)
	private Language language;
	@Column
	private int length;
	@NotNull
	@Column(length = 5, nullable = false)
	private String rating;

	// In order to skip org.hibernate.loader.MultipleBagFetchException, in Hibernate > 5.0.8 use Set instead of List
	// for Many-to-Many relations.
	// We are not using CascadeType.ALL because we are dealing with a unidirectional relationship
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable(name = "FILM_ACTORS", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns =
	@JoinColumn(name = "actor_id"))
	private Set<Actor> actors;
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable(name = "FILM_CATEGORIES", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns =
	@JoinColumn(name = "category_id"))
	private Set<Category> categories;
}
