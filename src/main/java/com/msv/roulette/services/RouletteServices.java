package com.msv.roulette.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.msv.roulette.model.Bet;
import com.msv.roulette.model.Roulette;
import com.msv.roulette.model.Winner;
import com.msv.roulette.repository.RouletteRepository;

@Service
public class RouletteServices {
	
	private RouletteRepository rouletteRepository;

	/**
	 * @param rouletteRepository
	 */
	public RouletteServices(RouletteRepository rouletteRepository) {		
		this.rouletteRepository = rouletteRepository;
	}
	
	public Roulette create() {
		return this.rouletteRepository.create();
	}
	
	public List<Roulette> listAll(){
		return this.rouletteRepository.listAll();
	}
	
	public void open(Roulette roulette) {
		this.rouletteRepository.open(roulette);
	}
	
	public void betNumber(Bet bet) {
		this.rouletteRepository.betNumber(bet);
	}
	
	public void betColor(Bet bet) {
		this.rouletteRepository.betColor(bet);
	}
	
	public boolean isOpen(String rouletteId) {
		return this.rouletteRepository.isOpen(rouletteId);
	}
	
	public List<Winner> close(Roulette roulette){
		return this.rouletteRepository.close(roulette);
	}
}











