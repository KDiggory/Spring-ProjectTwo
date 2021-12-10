package com.bae.gardening.entity;

public enum Months {
	JANURARY("WINTER"), FEBURARY("WINTER"), 
	MARCH("SPRING"), APRIL("SPRING"), MAY("SPRING"), 
	JUNE("SUMMER"), JULY("SUMMER"), AUGUST("SUMMER"),
	SEPTEMBER("AUTUMN"), OCTOBER("AUTUMN"), NOVEMBER("AUTUMN"), 
	DECEMBER("WINTER");
	
	private final String season;
	
	Months(String season) {
		this.season = season;
	}

	public String getSeason() {
		return season;
	}

}
