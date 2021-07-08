package com.me.whiteship.demoinflearnrestapi.events;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	EventRepository eventRepository;
	
	@Test
	public void createEvent() throws Exception {
		Event event = Event.builder()
						.name("Spring")
						.description("REST API development with Spring")
						.beginEnrollmentDateTime(LocalDateTime.of(2021, 07, 8, 14, 21))
						.closeEnrollmentDateTime(LocalDateTime.of(2021, 07, 9, 14, 21))
						.beginEventDateTime(LocalDateTime.of(2021, 07, 10, 14, 21))
						.endEventDateTime(LocalDateTime.of(2021, 07, 11, 14, 21))
						.basePrice(100)
						.maxPrice(200)
						.limitOfEnrollment(100)
						.location("강남역 D2 스타텁 팩토리")
						.build();
		
		// Mock 객체인 eventRepository를 불러서 nullpointer 에러가 났을 때 아래와 같은 코드 작성
		event.setId(10);
		Mockito.when(eventRepository.save(event)).thenReturn(event);
		
		mockMvc.perform(post("/api/events/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)// 본문에 json을 보내고 있다.
					.accept(MediaTypes.HAL_JSON) // 원하는 응답 형식
					.content(objectMapper.writeValueAsString(event))) // 본문
				.andDo(print()) // 실제 응답값을 보고싶을 떼
				.andExpect(status().isCreated()) // 201 응답 예상
				.andExpect(jsonPath("id").exists()) // 아이디가 있는지 확인 
				.andExpect(header().exists(HttpHeaders.LOCATION)) // Location 헤더가 있는지 확인
				.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE)) // 해당 헤더에 특정한 값이 나오는지 확인
				;
	}
}
