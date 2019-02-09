package org.acme.dvdstore.service;

import org.acme.dvdstore.model.Actor;
import org.acme.dvdstore.repository.ActorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl extends AbstractService<Actor> implements ActorService {
	private final ActorRepository actorRepository;

	@Override
	public JpaRepository<Actor, Long> getRepository() {
		return actorRepository;
	}
}
