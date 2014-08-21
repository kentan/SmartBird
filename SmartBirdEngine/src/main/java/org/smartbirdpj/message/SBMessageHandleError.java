package org.smartbirdpj.message;


public class SBMessageHandleError extends SBMessage{
	
	
	final private static String OPERATION = "error";	
	final private static String JSON_KEY_SUB_OPERATION = "sub";


	public SBMessageHandleError(String subOperation){
		super(-1,OPERATION);
		
	
		this.jsonDataMap.put(JSON_KEY_PLAYERID,this.playerId);
				this.jsonValueMap.put(JSON_KEY_SUB_OPERATION,subOperation);
		

	}	
}
