package ru.pgtu.ps41.glazyrina.tatyana.lab2;

import java.io.Serializable;

import android.widget.DatePicker;

public class DataInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	//private DatePicker firstDate;
	//private DatePicker secondDate;
	

	private int year;  
	private int month;  
	private int day;
	
	private int year2;  
	private int month2;  
	private int day2;

	public DataInfo(final int year, final int month,
			final int day, final int year2, final int month2,
			final int day2) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		
		this.year2 = year2;
		this.month2 = month2;
		this.day2 = day2;
	}


	public Integer getMonth() {
		// TODO Auto-generated method stub
		return this.month;
	}


	public Integer getDayOfMonth() {
		// TODO Auto-generated method stub
		return this.day;
	}


	public Integer getYear() {
		// TODO Auto-generated method stub
		return this.year;
	}
	
	public Integer getMonth2() {
		// TODO Auto-generated method stub
		return this.month2;
	}


	public Integer getDayOfMonth2() {
		// TODO Auto-generated method stub
		return this.day2;
	}


	public Integer getYear2() {
		// TODO Auto-generated method stub
		return this.year2;
	}


	

}
