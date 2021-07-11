package com.me.whiteship.demoinflearnrestapi.common;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent // ObjectMapper에 등록하기
public class ErrorsSerializer extends JsonSerializer<Errors> {
	@Override
	public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		// errors 안에는 error가 여러개니까 배열로 담아주기 위해서 StartArray와 EndArray를 사용
		gen.writeStartArray();
		
		// FieldError 담기
		errors.getFieldErrors().stream().forEach(e -> {
			try {

				// JSON Object 채우기
				gen.writeStartObject();
				gen.writeStringField("field", e.getField());
				gen.writeStringField("objectName", e.getObjectName());
				gen.writeStringField("code", e.getCode());
				gen.writeStringField("defaultMessage", e.getDefaultMessage());

				// RejectedValue는 있을 수도 있고 없을 수도 있다.
				Object rejectedValue = e.getRejectedValue();
				if (rejectedValue != null) {
					gen.writeStringField("rejectedValue", rejectedValue.toString());
				}
				
				gen.writeEndObject();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		// GlobalError 담기
		errors.getGlobalErrors().stream().forEach(e -> {
			try {
				// JSON Object 채우기
				gen.writeStartObject();
				gen.writeStringField("objectName", e.getObjectName());
				gen.writeStringField("code", e.getCode());
				gen.writeStringField("defaultMessage", e.getDefaultMessage());
				gen.writeEndObject();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});;
		gen.writeEndArray();
	}
}
