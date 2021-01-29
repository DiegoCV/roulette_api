/**
 * 
 */
package com.msv.roulette.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msv.roulette.model.Bet;
import com.msv.roulette.model.Roulette;
import com.msv.roulette.model.Winner;
import com.msv.roulette.services.RouletteServices;

/**
 * @author DiegoCV
 *
 */
@RestController
@Validated
@RequestMapping("/api/v1/roulette")
public class RouletteResources {
	
	private final RouletteServices rouletteServices;

	/**
	 * @param rouletteServices
	 */
	public RouletteResources(RouletteServices rouletteServices) {		
		this.rouletteServices = rouletteServices;
	}
	
	@PostMapping("/create")	
	public ResponseEntity<Roulette> createRoulette(){
		return new ResponseEntity<Roulette>(this.rouletteServices.create(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/list")	
	public ResponseEntity<List<Roulette>> listAllRoulettes(){
		return ResponseEntity.ok(this.rouletteServices.listAll());
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/open")	
	public ResponseEntity openRoulettes(@RequestBody Roulette roulette){
		try {
			this.rouletteServices.open(roulette);
			return new ResponseEntity(HttpStatus.ACCEPTED);
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/bet/number")	
	public ResponseEntity betNumberRoulette(@RequestHeader("User-Id") int userId, @RequestBody Bet bet){
		bet.setUserId(userId);
		HttpStatus status_code;
		boolean isOpen = this.rouletteServices.isOpen(bet.getRouletteId());
		if(isOpen && bet.getNumber() >= 0 && bet.getNumber() <= 36 && bet.getMoney() <= 10000){
			this.rouletteServices.betNumber(bet);
			status_code = HttpStatus.ACCEPTED;
		}else {
			status_code = HttpStatus.NOT_ACCEPTABLE;
		}
		
		return new ResponseEntity(status_code);		
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/bet/color")	
	public ResponseEntity betColorRoulette(@RequestHeader("User-Id") int userId, @RequestBody Bet bet){
		bet.setUserId(userId);
		HttpStatus status_code;
		boolean isOpen = this.rouletteServices.isOpen(bet.getRouletteId());
		if(isOpen && this.validateColor(bet.getColor()) && bet.getMoney() <= 10000){
			this.rouletteServices.betColor(bet);
			status_code = HttpStatus.ACCEPTED;
		}else {
			status_code = HttpStatus.NOT_ACCEPTABLE;
		}
		
		return new ResponseEntity(status_code);		
	}
	
	private boolean validateColor(String color) {
		return color.equals("red") || color.equals("black");
	}
	
	
	@PostMapping("/close")	
	public ResponseEntity<List<Winner>> closeRoulettes(@RequestBody Roulette roulette){
		return new ResponseEntity<List<Winner>>(this.rouletteServices.close(roulette), HttpStatus.ACCEPTED);
		
	}
}




