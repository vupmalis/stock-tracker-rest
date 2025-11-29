package org.mydevnotes.stocktracker.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickers")
public class Ticker {
	
	public Ticker() {
		super();
	}	

	public Ticker(String symbol) {
		super();
		this.symbol = symbol;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long autoid;

	@Column(nullable = false, length = 10)
	private String symbol;

	public Long getAutoid() {
		return autoid;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setAutoid(Long autoid) {
		this.autoid = autoid;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
