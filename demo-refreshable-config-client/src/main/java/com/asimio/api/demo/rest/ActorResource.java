package com.asimio.api.demo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asimio.api.demo.model.Actor;

@RefreshScope
@RestController
@RequestMapping(value = "/actors", produces = "application/json")
public class ActorResource {

	@Value("${app.message:Default}")
	private String msg;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Actor getActor(@PathVariable("id") String id) {
		return this.buildActor(id, String.format("First%s", id), String.format("Last%s. %s", id, this.msg));
	}

	private Actor buildActor(String id, String firstName, String lastName) {
		Actor result = new Actor();
		result.setActorId(id);
		result.setFirstName(firstName);
		result.setLastName(lastName);
		return result;
	}
}