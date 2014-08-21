package org.smartbirdpj.message;

import org.json.simple.JSONArray;
import org.smartbirdpj.mdl.enm.TileEnum;

public class SBMessageDiscardTile extends SBMessage{

	TileEnum discardedTile;
	final private static String OPERATION = "discard";
	final private static String JSON_KEY_DISCARDED_TILE = "discardedTile";
	public SBMessageDiscardTile(int playerId,TileEnum discardedTile){
		super(playerId,OPERATION);
		
		
		this.jsonValueMap.put(JSON_KEY_DISCARDED_TILE, discardedTile.toString());


	}
}
