package org.acme.dvdstore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "RENTALS")
@SequenceGenerator(name = "idGenerator", sequenceName = "RENTALS_SEQ", initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Rental extends BaseEntity {
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "film_id", nullable = false)
	private Film film;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date rentalDate;
	@NotNull
	@Column
	private int rentalDays;
}
