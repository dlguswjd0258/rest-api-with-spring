package com.me.whiteship.demoinflearnrestapi.events;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EventTest {

	// builder 유무 확인
	@Test
	public void builder() {
		Event event = Event.builder()
				.name("Inflearn Spring REST API")
				.description("REST API development with Spring")
				.build();
		assertThat(event).isNull();
	}

	@Test
	public void javaBean() {
		// Given
		String name = "Event";
		String description = "Spring";

		// When
		Event event = new Event();
		event.setName(name);
		event.setDescription(description);

		// Then
		assertThat(event.getName()).isEqualTo(name);
		assertThat(event.getDescription()).isEqualTo(description);
	}

}
