/**
 * 
 */
package com.msv.roulette.model;

/**
 * @author DiegoCV
 *
 */
public class Winner {
	private int userId;
	private int betNumber;
	private String betColor;
	private int betMoney;
	private double wonTotal;
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @return the betNumber
	 */
	public int getBetNumber() {
		return betNumber;
	}
	/**
	 * @return the betColor
	 */
	public String getBetColor() {
		return betColor;
	}
	/**
	 * @return the betMoney
	 */
	public int getBetMoney() {
		return betMoney;
	}
	/**
	 * @return the wonTotal
	 */
	public double getWonTotal() {
		return wonTotal;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @param betNumber the betNumber to set
	 */
	public void setBetNumber(int betNumber) {
		this.betNumber = betNumber;
	}
	/**
	 * @param betColor the betColor to set
	 */
	public void setBetColor(String betColor) {
		this.betColor = betColor;
	}
	/**
	 * @param betMoney the betMoney to set
	 */
	public void setBetMoney(int betMoney) {
		this.betMoney = betMoney;
	}
	/**
	 * @param wonTotal the wonTotal to set
	 */
	public void setWonTotal(double wonTotal) {
		this.wonTotal = wonTotal;
	}
	
	
	
	
	
}
