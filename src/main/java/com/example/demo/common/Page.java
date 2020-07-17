/*
 * Company: 
 * Copyright (c) 2012-2032 
 * All Rights Reserved.
 */
package com.example.demo.common;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

	private long totalRow; // 总记录数
	
	private int pageSize = 10; // 每页条数
	
	private List<T> data = new ArrayList<T>(); // 当前页的数据
	
	private int currentPage = 1; // 当前页
	
	private int start; // 启始条数
	
	private int end; // 结束条数
	
	private int totalPage; // 总页数

	
	public Page() {
		super();
	}

	public Page(long totalRow, int pageSize, List<T> data) {
		super();
		this.totalRow = totalRow;
		this.pageSize = pageSize;
		this.data = data;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(long totalRow) {
		this.totalRow = totalRow;
		this.totalPage = (int) (totalRow / pageSize);
		if (totalRow % pageSize > 0)
			this.totalPage = this.totalPage + 1;
		this.currentPage = (currentPage <= 0) ? 1 : currentPage;
		if (this.currentPage > this.totalPage)
			this.currentPage = this.totalPage;
		if (totalPage == 0) {
			this.currentPage = 1;
		}
		this.start = (this.currentPage - 1) * pageSize;
		this.end = this.start + pageSize - 1;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Page [totalRow=" + totalRow + ", pageSize=" + pageSize + ", data=" + data + ", currentPage="
				+ currentPage + ", start=" + start + ", end=" + end + ", totalPage=" + totalPage + "]";
	}
}
