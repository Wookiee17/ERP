package com.cloven.dev.erpdev.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.cloven.dev.erpdev.core.exception.CoreException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;


public class CoreUtil {

	public static String convertObjectToJson(Object object) {
		if (object == null) {
			return null;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat(Constants.UI_DATE_FORMAT);
			mapper.setDateFormat(df);
			JavaTimeModule javaTimeModule = new JavaTimeModule();
			javaTimeModule.addSerializer(LocalDateTime.class,
					new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(Constants.UI_DATE_TIME_FORMAT)));
			mapper.registerModule(javaTimeModule);
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			if (object instanceof String) {
				String str = object.toString().replace("’", "'");
				return mapper.writeValueAsString(str);
			}
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new CoreException("Object Parsing Exception");
		}
	}

}