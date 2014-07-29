package org.smartbirdpj.message;

import org.smartbirdpj.mdl.enm.TileEnum;

public class SBMessageStartRound extends SBMessage {
	TileEnum discardedTile;
	private final static String OPERATION = "startRound";
	private final static String JSON_KEY_PREVAILING_WIND = "prevailingWind";
	private final static String JSON_KEY_DORA = "dora";
	private final static String JSON_KEY_PARENT_PLAYERID = "parentPlayerId";
	private final static String JSON_KEY_ROUND_NUMBER = "roundNumber";
	private final static String JSON_KEY_NUMBER_OF_HUNDREAD_POINT_BAR = "numOfHundredPointBar";
	private final static String JSON_KEY_NUMBER_OF_THOUSAND_POINT_BAR = "numOfThousandPointBar";
	
	public SBMessageStartRound(TileEnum prevailingWind,TileEnum dora,int parentPlayerId,int roundNumber,int numOfHundredPointBar,int numOfThousandPointBar){
		super(-1, OPERATION);
		
		this.jsonValueMap.put(JSON_KEY_PREVAILING_WIND, prevailingWind.toString());
//		this.jsonValueMap.put(JSON_KEY_DORA, dora.toString());//TODO
		this.jsonValueMap.put(JSON_KEY_PARENT_PLAYERID, parentPlayerId);
		this.jsonValueMap.put(JSON_KEY_ROUND_NUMBER, roundNumber);
		this.jsonValueMap.put(JSON_KEY_NUMBER_OF_HUNDREAD_POINT_BAR, numOfHundredPointBar);
		this.jsonValueMap.put(JSON_KEY_NUMBER_OF_THOUSAND_POINT_BAR, numOfThousandPointBar);

	}
}
