/**
 * 
 */
package com.msv.roulette.model.builder;

import org.bson.Document;

import com.msv.roulette.model.Winner;


/**
 * @author DiegoCV
 *
 */
public class WinnerBuilder {
	
	private int userId;
	private int betNumber;
	private String betColor;
	private int betMoney;
	private double wonTotal;
	
	public WinnerBuilder whitDocumentNumber(Document doc) {
		this.userId = doc.getInteger("userId");
		this.betNumber = doc.getInteger("number");
		this.betMoney = doc.getInteger("money");
		this.wonTotal = this.betMoney * 5; 
		return this;
	}
	
	public WinnerBuilder whitDocumentColor(Document doc) {
		this.userId = doc.getInteger("userId");
		this.betColor = doc.getString("color");
		this.betMoney = doc.getInteger("money");
		this.wonTotal = this.betMoney * 1.8; 
		return this;
	}
	
	public Winner build() {
		Winner winner = new Winner();
		winner.setUserId(userId);
		winner.setBetNumber(betNumber);
		winner.setBetColor(betColor);
		winner.setBetMoney(betMoney);
		winner.setWonTotal(wonTotal);
		
		return winner;
	}
}
