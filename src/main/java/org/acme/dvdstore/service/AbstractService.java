package org.acme.dvdstore.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.acme.dvdstore.base.AbstractLogEntity;
import org.acme.dvdstore.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractService<T extends BaseEntity> extends AbstractLogEntity implements BaseService<T, Long> {
	public abstract JpaRepository<T, Long> getRepository();

	@PostConstruct
	private void init() {
		log.trace("Starting {}.", getClass().getName());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor =
		Exception.class)
	public List<T> createAll(final T... entities) {
		final List<T> updatedEntities = new ArrayList<>();
		for (final T entity : entities) {
			updatedEntities.add(create(entity));
		}
		return updatedEntities;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor =
		Exception.class)
	public T create(final T entity) {
		log.trace("Creating {}.", entity);
		return getRepository().save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor =
		Exception.class)
	public void update(final T entity) {
		log.trace("Updating {}.", entity);
		getRepository().save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor =
		Exception.class)
	public void delete(final T entity) {
		log.trace("Deleting {}.", entity);
		getRepository().delete(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor =
		Exception.class)
	public void deleteById(final Long id) {
		final T entityFound = getRepository().getOne(id);
		log.trace("Deleting {}.", entityFound);
		getRepository().deleteById(id);
	}

	@Override
	public boolean exists(final T entity) {
		log.trace("Checking whether {} exists.", entity);
		return getRepository().existsById(entity.getId());
	}

	@Override
	public T get(final Long id) {
		log.trace("Retrieving entity with id {}.", id);
		/*
		 * T findOne(ID id) (name in the old API) / Optional<T> findById(ID id) (name in the new API) relies on
		 * EntityManager.find() that performs an entity eager loading.
		 *
		 * T getOne(ID id) relies on EntityManager.getReference() that performs an entity lazy loading. So to ensure
		 * the effective loading of the entity, invoking a method on it is required.
		 */
		return getRepository().findById(id).get();
	}

	@Override
	public List<T> findAll() {
		log.trace("Retrieving all entities.");
		return getRepository().findAll();
	}
}
