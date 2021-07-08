package com.me.whiteship.demoinflearnrestapi.events;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id")
@Entity
public class Event {
	
	@Id @GeneratedValue
	private Integer id;
	private String name;
	private String description;
	
	// localDateTime 자동 mapping
	private LocalDateTime beginEnrollmentDateTime;
	private LocalDateTime closeEnrollmentDateTime;
	private LocalDateTime beginEventDateTime;
	private LocalDateTime endEventDateTime;
	private String location; // (optional) 이게 없으면 온라인 모임
	private int basePrice; // (optional)
	private int maxPrice; // (optional)
	private int limitOfEnrollment;
	private boolean offline;
	private boolean free;
	
	@Enumerated(EnumType.STRING)
	private EventStatus eventStatus;
	
}
