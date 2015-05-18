package com.excilys.cdb.tools;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;

import com.excilys.cdb.mapper.DateMapper;

public class DateConverter implements
		AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime arg0) {
		return DateMapper.toTimeStamp(arg0);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp arg0) {
		return DateMapper.toLocalDateTime(arg0);
	}

}
