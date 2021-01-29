/**
 * 
 */
package com.msv.roulette.model.builder;

import java.util.Date;

import org.bson.Document;

import com.msv.roulette.model.Roulette;

/**
 * @author DiegoCV
 *
 */
public class RouletteBuilder {
	
	private String id;

	private Date create_date;

	private String status;
	
	public RouletteBuilder whitDocument(Document doc) {
		this.id = doc.getObjectId("_id").toString();
		this.create_date = doc.getDate("create_date");
		this.status = doc.getString("status");
		return this;
	}
	
	public Roulette build() {
		Roulette roulette = new Roulette();
		roulette.setId(this.id);
		roulette.setCreate_date(this.create_date);
		roulette.setStatus(this.status);
		
		return roulette;
	}
}
