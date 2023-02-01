package com.douzone.mysite.vo;

public class PageVo {
	private int no;
	private int amount = 5;	// 초기값 5
	private int w_size = 5;	// 아래 숫자 컬럼의 사이즈
	private int size;		// 총 페이지의 수
	private int totalRows;
	private int begin;
	private int end;
	
	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getW_size() {
		return w_size;
	}

	public void setW_size(int w_size) {
		this.w_size = w_size;
	}

	
	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	
}
