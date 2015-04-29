package com.excilys.cdb.page;

import java.util.List;

import com.excilys.cdb.servlet.ComputerDto;

// TODO: Auto-generated Javadoc
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
	private int nb;

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
	public int getNb() {
		return nb;
	}

	/**
	 * Sets the nb.
	 *
	 * @param nb
	 *            the new nb
	 */
	public void setNb(int nb) {
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
	 * @param npageNum
	 *            the npage num
	 * @param nrange
	 *            the nrange
	 */
	public void turn(int npageNum, int nrange) {
		offset = npageNum * nrange;
		if (offset < 0) {
			offset = 0;
		}
		setPageNum(npageNum);
		setRange(nrange);
	}

	/**
	 * Validate the page , if the data are wrong we apply default values.
	 */
	public void validate() {
		if (range > 100) {
			setRange(50);
		}
		if (pageNum > (nb / range)) {
			setPageNum(0);
			setOffset(0);
		}

	}

}
