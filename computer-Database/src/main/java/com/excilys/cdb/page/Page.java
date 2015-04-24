package com.excilys.cdb.page;

import java.util.ArrayList;

import com.excilys.cdb.servlet.ComputerDto;

public class Page {
	private int offset;
	private int range;
	private int pageNum;
	private int nb;
	private ArrayList<ComputerDto> lcomp;

	public Page() {

	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNb() {
		return nb;
	}

	public void setNb(int nb) {
		this.nb = nb;
	}

	public ArrayList<ComputerDto> getLcomp() {
		return lcomp;
	}

	public void setLcomp(ArrayList<ComputerDto> lcomp) {
		this.lcomp = lcomp;
	}

	public void turn(int npageNum, int nrange) {
		offset = npageNum * nrange;
		if (offset < 0) {
			offset = 0;
		}
		setPageNum(npageNum);
		setRange(nrange);
	}
}
