package com.hd.daiban.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Page<T> {

	private int page;
	
	private int total;
	
	private int records;
	
	private List<T> rows;

	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	
	public Page() {
		super();
	}
	
	/*
	 * Parameters:
	 * page: the page of request
	 * total: the number of total pages
	 * records: the number of all records
	 * */
	public Page(int page, int total, int records, List<T> ptList) {
		super();
		this.page = page;
		this.total = total;
		this.records = records;
		this.rows = ptList;
		//Collections.sort(ptList);
	}
	
	public Page(int pageSize,int pageNum, String sord, List<T> ptList){
		int records = ptList.size();
		int total = records/pageSize+(records%pageSize>0?1:0);
		if(pageNum<1){
			pageNum = 1;
		}
		if(pageNum>total){
			pageNum = total;
		}
		if(!"desc".equals(sord)){
			Collections.reverse(ptList);
		}
		List<T> rowData = new ArrayList<T>();
		for(int i = pageSize*(pageNum-1);i<ptList.size()&&i<pageSize*(pageNum-1)+pageSize;i++){
			rowData.add(ptList.get(i));
		}
		
		this.page = pageNum;
		this.total = total;
		this.records = records;
		this.rows = rowData;
	}

	public static void main(String[] args){
		int pageSize = 10;
		int records = 12;
		int total = records/pageSize+(records%pageSize>0?1:0);
		System.out.println("total is:" + total);
	}
	
	

	
}
