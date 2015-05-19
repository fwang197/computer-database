package com.excilys.cdb.page;

import java.util.List;

import com.excilys.cdb.mapper.ComputerDto;

/**
 * The Class Page; it's a representation of the data in the dashboard page.
 */
public class Page {

	/** The offset. */
	private int offset;

	/** The range. */
	private int range;

	/** The page num. */
	private int pageNum;

	/** The nb. */
	private long nb;

	private String search;

	private String order;

	private String field;

	/** The lcomp. */
	private List<ComputerDto> lcomp;

	/**
	 * Instantiates a new page.
	 */
	public Page() {

	}

	/**
	 * Gets the offset.
	 *
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Sets the offset.
	 *
	 * @param offset
	 *            the new offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Gets the range.
	 *
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

	/**
	 * Sets the range.
	 *
	 * @param range
	 *            the new range
	 */
	public void setRange(int range) {
		this.range = range;
	}

	/**
	 * Gets the page num.
	 *
	 * @return the page num
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * Sets the page num.
	 *
	 * @param pageNum
	 *            the new page num
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * Gets the nb.
	 *
	 * @return the nb
	 */
	public long getNb() {
		return nb;
	}

	/**
	 * Sets the nb.
	 *
	 * @param nb
	 *            the new nb
	 */
	public void setNb(long nb) {
		this.nb = nb;
	}

	/**
	 * Gets the lcomp.
	 *
	 * @return the lcomp
	 */
	public List<ComputerDto> getLcomp() {
		return lcomp;
	}

	/**
	 * Sets the lcomp.
	 *
	 * @param lcomp
	 *            the new lcomp
	 */
	public void setLcomp(List<ComputerDto> lcomp) {
		this.lcomp = lcomp;
	}

	/**
	 * Turn.
	 *
	 */
	public void turn() {
		offset = pageNum * range;
		if (offset < 0) {
			offset = 0;
		}
	}

	@Override
	public String toString() {
		return "Page [offset=" + offset + ", range=" + range + ", pageNum="
				+ pageNum + ", nb=" + nb + ", search=" + search + ", order="
				+ order + ", field=" + field + ", lcomp=" + lcomp + "]";
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void validate() {
		if (range <= 0 || range > 100) {
			range = 50;
		}
		if (search == null || search.isEmpty()) {
			search = "";
		}
		if (order == null || order.isEmpty()) {
			order = "asc";
		}
		if (field == null || field.isEmpty()) {
			field = "computer.id";
		}
		if (range > 100) {
			setRange(50);
		}
	}

	public void validatePageNum() {
		if (pageNum > (nb / range) || pageNum < 0) {
			pageNum = 0;
		}
	}
}
