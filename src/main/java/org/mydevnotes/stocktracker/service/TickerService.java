package org.mydevnotes.stocktracker.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mydevnotes.stocktracker.domain.Ticker;
import org.mydevnotes.stocktracker.dto.TickerDTO;
import org.mydevnotes.stocktracker.repository.TickerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TickerService {
	private final TickerRepository repository;

	public TickerService(TickerRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<TickerDTO> getAllTickers() {
		return repository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public Optional<TickerDTO> getTickerById(Long id) {
		return repository.findById(id).map(this::mapToDto);
	}

	private TickerDTO mapToDto(Ticker entity) {
		return new TickerDTO(entity.getAutoid(), entity.getSymbol());
	}
	
    @Transactional
    public TickerDTO createTicker(TickerDTO dto) {
        Ticker entity = new Ticker(dto.getSymbol());
        entity = repository.save(entity);
        return mapToDto(entity);
    }
    
    @Transactional
    public Optional<TickerDTO> updateTicker(Long id, TickerDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setSymbol(dto.getSymbol());                    
                    return mapToDto(repository.save(entity));
                });
    }   
	
    @Transactional
    public boolean deleteTicker(Long id) {
        return repository.findById(id)
                .map(entity -> {
                    repository.delete(entity);
                    return true;
                })
                .orElse(false);
    }
    
    
	
}
