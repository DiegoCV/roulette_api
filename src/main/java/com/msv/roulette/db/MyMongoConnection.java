/**
 * 
 */
package com.msv.roulette.db;


import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.msv.roulette.db.util.Env;

/**
 * @author DiegoCV
 *
 */
public class MyMongoConnection {
	
	private String user;
	private String database;
	private String password;
	private String host;
	private int port;
	private static MyMongoConnection myMongoConnection;
	
	private MyMongoConnection() {
		String[] var = Env.getMongoDBConnectionVariables();
		this.user = var[0];
		this.database = var[1];
		this.password = var[2];
		this.host = var[3];
		this.port = Integer.parseInt(var[4]);
	}
	
	public static MyMongoConnection getMyMongoConnection() {
		if(myMongoConnection == null) {
			myMongoConnection = new MyMongoConnection();
		}
		return myMongoConnection;
	}
	
	public MongoDatabase getConnection() {
		 String connectionString = "mongodb://"+this.user+":"+this.password+"@"+this.host+":"+this.port+"/?authSource=roulette&readPreference=primary&appname=MongoDB%20Compass&ssl=false";		 
	    return MongoClients.create(connectionString).getDatabase(this.database);
	}
	

	@Override
	public String toString() {
		return "MyMongoConnection [user=" + user + ", database=" + database + ",host=" + host + ", port=" + port + "]";
	}
	
	

}
