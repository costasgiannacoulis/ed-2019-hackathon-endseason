package org.acme.dvdstore.service;

import java.util.List;

public interface BaseService<T, N> {
	T create(final T entity);

	List<T> createAll(final T... entities);

	void update(T entity);

	void delete(T entity);

	void deleteById(N id);

	boolean exists(T entity);

	T get(N id);

	List<T> findAll();
}
