package com.me.whiteship.demoinflearnrestapi.events;

import java.time.LocalDateTime;

import javax.validation.constraints.*;

import lombok.*;

@Builder @NoArgsConstructor @AllArgsConstructor
@Data // getter setter 포함
public class EventDto {
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String description;

	// localDateTime 자동 mapping
	@NotNull
	private LocalDateTime beginEnrollmentDateTime;
	@NotNull
	private LocalDateTime closeEnrollmentDateTime;
	@NotNull
	private LocalDateTime beginEventDateTime;
	@NotNull
	private LocalDateTime endEventDateTime;
	private String location; // (optional) 이게 없으면 온라인 모임
	@Min(0)
	private int basePrice; // (optional)
	@Min(0)
	private int maxPrice; // (optional)
	@Min(0)
	private int limitOfEnrollment;
}
