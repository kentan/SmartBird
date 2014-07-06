package org.smartbirdpj.message;

import org.sb.mdl.enm.TileEnum;

public class SBMessageStartRound extends SBMessage {
	TileEnum discardedTile;
	private final static String OPERATION = "startRound";
	private final static String JSON_KEY_PREVAILING_WIND = "prevailingWind";
	private final static String JSON_KEY_DORA = "dora";
	private final static String JSON_KEY_PARENT_PLAYERID = "parentPlayerId";
	private final static String JSON_KEY_ROUND_NUMBER = "roundNumber";

	public SBMessageStartRound(TileEnum prevailingWind,TileEnum dora,int parentPlayerId,int roundNumber){
		super(-1, OPERATION);
		
		this.jsonValueMap.put(JSON_KEY_PREVAILING_WIND, prevailingWind.toString());
//		this.jsonValueMap.put(JSON_KEY_DORA, dora.toString());
		this.jsonValueMap.put(JSON_KEY_PARENT_PLAYERID, parentPlayerId);
		this.jsonValueMap.put(JSON_KEY_ROUND_NUMBER, roundNumber);
		

	}
}
