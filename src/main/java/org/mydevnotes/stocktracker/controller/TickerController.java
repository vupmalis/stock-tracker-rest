package org.mydevnotes.stocktracker.controller;

import java.net.URI;
import java.util.List;

import org.mydevnotes.stocktracker.dto.TickerDTO;
import org.mydevnotes.stocktracker.service.TickerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tickers")
@Tag(name = "Tickers", description = "API for stock tickers")
public class TickerController {

	private final TickerService tickerService;

	public TickerController(TickerService tickerService) {
		this.tickerService = tickerService;
	}

	@PostMapping
	public ResponseEntity<TickerDTO> createTicker(@RequestBody TickerDTO dto) {
		TickerDTO created = tickerService.createTicker(dto);

		// Return HTTP 201 with the location of the new resource
		return ResponseEntity.created(URI.create("/api/tickers/" + created.getAutoid())).body(created);
	}

	@Operation(summary = "Get all tickers", description = "Fetches all tickers entries")
	@ApiResponse(responseCode = "200", description = "List of hosts", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TickerDTO.class))))
	@GetMapping
	public List<TickerDTO> getAll() {
		return tickerService.getAllTickers();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get ticker by ID", description = "Provide the ticker ID to retrieve details")
	public ResponseEntity<TickerDTO> getById(
			@Parameter(description = "ID of the ticker to retrieve", required = true, example = "42") @PathVariable("id") Long id) {
		return tickerService.getTickerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<TickerDTO> update(@PathVariable Long id, @Valid @RequestBody TickerDTO dto) {
		return tickerService.updateTicker(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		return tickerService.deleteTicker(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
