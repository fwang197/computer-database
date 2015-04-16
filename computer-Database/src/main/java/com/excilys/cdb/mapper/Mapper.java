package main.java.com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.servlet.ComputerDto;
import main.java.com.excilys.cdb.tools.Tools;

// TODO: Auto-generated Javadoc
/**
 * The Class Mapper.
 */
public class Mapper {

	/**
	 * To company.
	 *
	 * @param rs
	 *            the rs
	 * @return the company
	 */
	public static Company toCompany(ResultSet rs) throws SQLException {
		Company comp = null;
		if (rs.next()) {
			comp = new Company();
			comp.setId(rs.getLong("id"));
			comp.setName(rs.getString("name"));
		}
		return comp;
	}

	/**
	 * To computer.
	 *
	 * @param rs
	 *            the rs
	 * @return the computer
	 */
	public static Computer toComputer(ResultSet rs) throws SQLException {
		Computer comp = null;
		if (rs.next()) {
			comp = new Computer();
			comp.setId(rs.getLong("c_id"));
			comp.setIntroduced(Mapper.toLocalDateTime(rs
					.getTimestamp("introduced")));

			comp.setDiscontinued(Mapper.toLocalDateTime(rs
					.getTimestamp("discontinued")));

			comp.setName(rs.getString("c_name"));
			if (rs.getLong("company_id") != 0) {
				comp.setCompany(new Company(rs.getLong("company_id"), rs
						.getString("name")));
			}
		}
		return comp;
	}

	/**
	 * To local date time.
	 *
	 * @param ts
	 *            the ts
	 * @return the local date time
	 */
	public static LocalDateTime toLocalDateTime(Timestamp ts) {
		return Tools.isNull(ts) ? null : ts.toLocalDateTime();

	}

	/**
	 * To time stamp.
	 *
	 * @param ldt
	 *            the ldt
	 * @return the timestamp
	 */
	public static Timestamp toTimeStamp(LocalDateTime ldt) {
		return Tools.isNull(ldt) ? null : Timestamp.valueOf(ldt);
	}

	public static ComputerDto toComputerDto(Computer comp) {
		ComputerDto c = null;
		if (!Tools.isNull(comp)) {
			c = new ComputerDto(comp.getId(), comp.getName(), comp
					.getIntroduced().toLocalDate().toString(), comp
					.getDiscontinued().toLocalDate().toString(), comp
					.getCompany().getId(), comp.getCompany().getName());
		}
		return c;
	}
}
