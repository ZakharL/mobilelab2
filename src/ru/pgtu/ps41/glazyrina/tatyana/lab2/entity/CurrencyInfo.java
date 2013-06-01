package ru.pgtu.ps41.glazyrina.tatyana.lab2.entity;


public class CurrencyInfo {
	
	public String charCode;
	public String nominal;
	public String name;
	public String value;
	
	public CurrencyInfo() {
	}
	
	public String toString(){
		return (String) charCode + nominal + name + value;
	}
	
}
