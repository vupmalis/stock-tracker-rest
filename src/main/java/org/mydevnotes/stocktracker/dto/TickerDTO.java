package org.mydevnotes.stocktracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TickerDTO {
	
	public TickerDTO() {
		super();
	}

	public TickerDTO(Long autoid, String symbol) {
		super();
		this.autoid = autoid;
		this.symbol = symbol;
	}

	@Schema(description = "Unique identifier", example = "1")
	private Long autoid;	

	@Schema(description = "The ticker symbol", example = "AAPL")
	private String symbol;

	public Long getAutoid() {
		return autoid;
	}

	public void setAutoid(Long autoid) {
		this.autoid = autoid;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
}
