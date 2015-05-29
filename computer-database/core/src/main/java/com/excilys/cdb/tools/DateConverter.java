package com.excilys.cdb.tools;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;

/**
 * The Class DateConverter. Convert a Timestamp to LocalDateTime and conversely
 * during the entity mapping.
 */
public class DateConverter implements
		AttributeConverter<LocalDateTime, Timestamp> {

	public Timestamp convertToDatabaseColumn(LocalDateTime arg0) {
		return DateMapper.toTimeStamp(arg0);
	}

	public LocalDateTime convertToEntityAttribute(Timestamp arg0) {
		return DateMapper.toLocalDateTime(arg0);
	}

}
