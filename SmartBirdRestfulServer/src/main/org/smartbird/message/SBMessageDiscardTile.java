package org.smartbird.message;

import org.json.simple.JSONArray;
import org.sb.mdl.enm.TileEnum;

public class SBMessageDiscardTile extends SBMessage{

	TileEnum discardedTile;
	private final static String OPERATION = "discard";

	public SBMessageDiscardTile(int playerId,TileEnum discardedTile){
		super(playerId,OPERATION);
		
		this.playerId = playerId;
		this.discardedTile = discardedTile;
		
		
		this.jsonDataMap.put(JSON_KEY_VALUES,this.discardedTile.toString());

	}
}
