package org.acme.dvdstore.controller;

import org.acme.dvdstore.model.Actor;
import org.acme.dvdstore.service.ActorService;
import org.acme.dvdstore.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController extends AbstractController<Actor> {
	private final ActorService actorService;

	@Override
	public BaseService<Actor, Long> getBaseService() {
		return actorService;
	}
}
