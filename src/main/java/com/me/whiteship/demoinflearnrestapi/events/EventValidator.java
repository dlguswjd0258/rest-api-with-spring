package com.me.whiteship.demoinflearnrestapi.events;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component // Bean 등록
public class EventValidator {
	public void vaildate(EventDto eventDto, Errors errors) {

		// 무제한 경매는 max값이 0이기 때문에 이런 경우가 아니면 base값이 작다
		if (eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() != 0) {
			// basePrice가 이상하다
			errors.rejectValue("basePrice", "wrongValue", "BasePrice is Wrong");
			// maxPrice가 이상하다
			errors.rejectValue("maxPrice", "wrongValue", "MaxPrice is Wrong");
		}

		LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
		if (endEventDateTime.isBefore(eventDto.getBeginEventDateTime()) ||
			endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ||
			endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
			// 종료 날짜가 이상할 때
			errors.rejectValue("endEventDateTime", "wrongValue", "endEventDateTime is Wrong");
		}

		// TODO beginEventDateTime
		LocalDateTime beginEventDateTime = eventDto.getBeginEventDateTime();
		if (beginEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ||
				beginEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
			// 날짜가 이상할 때
			errors.rejectValue("beginEventDateTime", "wrongValue", "beginEventDateTime is Wrong");
		}
		
		// TODO CloseEnrollmentDateTime
		LocalDateTime closeEnrollmentDateTime = eventDto.getCloseEnrollmentDateTime();
		if (closeEnrollmentDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
			// 날짜가 이상할 때
			errors.rejectValue("closeEnrollmentDateTime", "wrongValue", "closeEnrollmentDateTime is Wrong");
		}
	}
}
