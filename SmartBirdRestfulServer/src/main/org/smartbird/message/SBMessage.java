package org.smartbird.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONValue;

public class SBMessage implements Serializable{
	final protected static String JSON_KEY_PLAYERID = "playerId";
	final protected static String JSON_KEY_VALUES = "value";
	final protected static String JSON_KEY_OPERATION = "operation";
	
	protected int playerId;
	protected String operation;
	protected Map<String, Object> jsonDataMap = new HashMap<String, Object>();
	public SBMessage(){
		
	}
	public SBMessage(int playerId,String operation){
		this.jsonDataMap.put(JSON_KEY_PLAYERID, playerId);
		this.jsonDataMap.put(JSON_KEY_OPERATION, operation);
		
		this.playerId = playerId;
		this.operation = operation;
		
	}
	public String toJson(){
		
		String jsonText = JSONValue.toJSONString(jsonDataMap);
		return jsonText;
	}
	protected void setOperation(String operation){
		this.jsonDataMap.put(JSON_KEY_OPERATION, operation);
	}
}
