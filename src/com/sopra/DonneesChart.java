package com.sopra;

import java.io.Serializable;

public class DonneesChart implements Serializable {

	private static final long serialVersionUID = 1L;
	String heure;
	int today;
	int lastWeek;

	public DonneesChart(String key, int today, int lastWeek) {
		super();
		this.heure = key;
		this.today = today;
		this.lastWeek = lastWeek;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String key) {
		this.heure = key;
	}

	public int getToday() {
		return today;
	}

	public void setToday(int value) {
		this.today = value;
	}

	public int getLastWeek() {
		return lastWeek;
	}

	public void setLastWeek(int value) {
		this.lastWeek = value;
	}

}