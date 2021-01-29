/**
 * 
 */
package com.msv.roulette.model;

/**
 * @author DiegoCV
 *
 */
public class Bet {
	private int number;
	private String color; 
	private int money;
	private int userId;
	private String rouletteId;
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @return the rouletteId
	 */
	public String getRouletteId() {
		return rouletteId;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @param rouletteId the rouletteId to set
	 */
	public void setRouletteId(String rouletteId) {
		this.rouletteId = rouletteId;
	}
	@Override
	public String toString() {
		return "Bet [number=" + number + ", color=" + color + ", money=" + money + ", userId=" + userId
				+ ", rouletteId=" + rouletteId + "]";
	}
	
	
}
