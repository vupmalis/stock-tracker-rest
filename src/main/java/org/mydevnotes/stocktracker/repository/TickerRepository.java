package org.mydevnotes.stocktracker.repository;

import java.util.Optional;

import org.mydevnotes.stocktracker.domain.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerRepository extends JpaRepository<Ticker, Long> {

	public Optional<Ticker> findBySymbol(String symbol);
}