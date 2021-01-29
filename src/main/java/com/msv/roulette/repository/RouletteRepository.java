/**
 * 
 */
package com.msv.roulette.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import org.springframework.stereotype.Repository;

import com.msv.roulette.db.MyMongoConnection;
import com.msv.roulette.model.Bet;
import com.msv.roulette.model.Roulette;
import com.msv.roulette.model.Winner;
import com.msv.roulette.model.builder.RouletteBuilder;
import com.msv.roulette.model.builder.WinnerBuilder;

/**
 * @author DiegoCV
 *
 */
@Repository
public class RouletteRepository {
	
	private MongoDatabase db;
	
	
	public RouletteRepository() {
		this.db = MyMongoConnection.getMyMongoConnection().getConnection();
		System.out.print(MyMongoConnection.getMyMongoConnection().toString());
	}

	public Roulette create() {		
		MongoCollection<Document> collection = this.db.getCollection("roulettes");	
		Document doc = new Document("_id", new ObjectId());
		doc.append("create_date", new Date());
		doc.append("status", "closed");
		collection.insertOne(doc);
		RouletteBuilder rouletteBuilder = new RouletteBuilder();
		
		return rouletteBuilder.whitDocument(doc).build();
	}
	
	
	public List<Roulette> listAll(){
		MongoCollection<Document> collection = this.db.getCollection("roulettes");
		FindIterable<Document> all = collection.find();
		ArrayList<Roulette> roulettes = new ArrayList<Roulette>();
		RouletteBuilder rouletteBuilder = new RouletteBuilder();
		for (Document document : all) {			
			roulettes.add(rouletteBuilder.whitDocument(document).build());
		}
		
		return roulettes;
	}
	
	public void open(Roulette roulette){		
        this.setStatus(roulette, "open");
	}
	
	public void betNumber(Bet bet){
		MongoCollection<Document> collection = this.db.getCollection("roulettes");		
		Document doc = new Document("_id", new ObjectId());
		doc.append("type", "number");
		doc.append("number", bet.getNumber());
		doc.append("money", bet.getMoney());
		doc.append("userId", bet.getUserId());
		BasicDBObject updateFields = new BasicDBObject();
        updateFields.put("bets", doc );
        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$push", updateFields);
        BasicDBObject searchQuery = new BasicDBObject("_id", new ObjectId(bet.getRouletteId()));
        collection.updateOne(searchQuery, setQuery);		
	}
	
	public void betColor(Bet bet){
		MongoCollection<Document> collection = this.db.getCollection("roulettes");		
		Document doc = new Document("_id", new ObjectId());
		doc.append("type", "color");
		doc.append("color", bet.getColor());
		doc.append("money", bet.getMoney());
		doc.append("userId", bet.getUserId());
		BasicDBObject updateFields = new BasicDBObject();
        updateFields.put("bets", doc );
        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$push", updateFields);
        BasicDBObject searchQuery = new BasicDBObject("_id", new ObjectId(bet.getRouletteId()));
        collection.updateOne(searchQuery, setQuery);		
	}
	
	public boolean isOpen(String rouletteId) {
		MongoCollection<Document> collection = this.db.getCollection("roulettes");
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(rouletteId));
		FindIterable<Document> roulettes = collection.find(query);

		return roulettes.first().getString("status").equals("open");
	}
	
	public List<Winner> close(Roulette roulette) {		
		this.setStatus(roulette, "closed");
		int randomNumber = (int) Math.floor(Math.random()*37);		
		String color = randomNumber % 2 == 0 ? "red" : "black";
		List<Winner> winners = new ArrayList<Winner>();
		winners = setWinnersNumber(winners, this.find(roulette, randomNumber));
		winners = setWinnersColor(winners, this.find(roulette, color));
		
		return winners;
	}
	
	private List<Winner> setWinnersNumber(List<Winner> winners, List<Document> winnerDoc ){
		WinnerBuilder winnerBuilder = new WinnerBuilder();
		for (Document document : winnerDoc) {			
			winners.add(winnerBuilder.whitDocumentNumber(document).build());
		}
		
		return winners;
	}
	
	private List<Winner> setWinnersColor(List<Winner> winners, List<Document> winnerDoc ){
		WinnerBuilder winnerBuilder = new WinnerBuilder();
		for (Document document : winnerDoc) {			
			winners.add(winnerBuilder.whitDocumentColor(document).build());
		}
		
		return winners;
	}
	
	private List<Document> find(Roulette roulette, int number) {
		MongoCollection<Document> collection = this.db.getCollection("roulettes");
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(roulette.getId()));
		FindIterable<Document> winnerDoc = collection.find(query);
		List<Document> d = new ArrayList<Document>();
		for (Document document : winnerDoc) {	
			d = filter(document, "number", number);
		}
			
		return d;
	}
	
	private List<Document> find(Roulette roulette, String color) {
		MongoCollection<Document> collection = this.db.getCollection("roulettes");
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(roulette.getId()));
		FindIterable<Document> winnerDoc = collection.find(query);
		List<Document> d = new ArrayList<Document>();
		for (Document document : winnerDoc) {	
			d = filter(document, "color", color);
		}
			
		return d;
	}
	
	private List<Document> filter(Document document, String type, String color) {
		List<Document> beats = document.getList("bets",Document.class);
		List<Document> d = new ArrayList<Document>();
		if(beats != null) {
			for (Document beat : beats) {
				if(beat.getString("type").equals(type) && beat.getString("color").equals(color)) {
					d.add(beat);
				}
			}
		}
		
		return d;
	}
	
	private List<Document> filter(Document document, String type, int number) {
		List<Document> beats = document.getList("bets",Document.class);
		List<Document> d = new ArrayList<Document>();
		if(beats != null) {
			for (Document beat : beats) {			
				if(beat.getString("type").equals(type) && beat.getInteger("number").equals(number)) {
					d.add(beat);
				}
			}
		}
		
		return d;
	}
	
	private void setStatus(Roulette roulette, String status) {
		MongoCollection<Document> collection = this.db.getCollection("roulettes");		
		BasicDBObject updateFields = new BasicDBObject();
        updateFields.append("status", status);
        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);
        BasicDBObject searchQuery = new BasicDBObject("_id", new ObjectId(roulette.getId()));
        collection.updateOne(searchQuery, setQuery);	
	}
	
}











