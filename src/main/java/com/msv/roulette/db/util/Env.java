package com.msv.roulette.db.util;

import java.util.Map;

public class Env {
	
	public static String[] getMongoDBConnectionVariables() {
		Map<String, String> map = System.getenv();
		String user = (String) map.get("USER_ROULETTE");
		String bd = (String) map.get("BD_NAME_ROULETTE");
		String password = (String) map.get("PASS_ROULETE");
		String host = (String) map.get("HOST_ROULETTE");
		String port = (String) map.get("PORT_ROULETTE");
		String[] var = {user, bd, password, host, port};
		return var;
	}
	
}
