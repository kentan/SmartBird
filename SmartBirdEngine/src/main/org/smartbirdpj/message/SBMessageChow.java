package org.smartbirdpj.message;


import java.util.List;

import org.json.simple.JSONArray;
import org.smartbirdpj.mdl.enm.TileEnum;

public class SBMessageChow extends SBMessage {
	final private static String OPERATION = "chow";
	final private static String JSON_KEY_STOLEN_TILE = "stolenTile";
	final private static String JSON_KEY_STOLEN_PlayerId = "stolenPlayerId";
	final private static String JSON_KEY_HURO1 = "huro1";
	final private static String JSON_KEY_HURO2 = "huro2";
	final private static String JSON_KEY_DISCARDED_TILE = "discardedTile";
	final private static String JSON_KEY_WALL = "wall";
	
	public SBMessageChow(int stealingPlayerId, int stolenPlayerId,TileEnum stolenTile, TileEnum huro1, TileEnum huro2,TileEnum discardedTile,List<TileEnum> wall){
		super(stealingPlayerId, OPERATION);

		
		this.jsonValueMap.put(JSON_KEY_STOLEN_PlayerId,stolenPlayerId);
		this.jsonValueMap.put(JSON_KEY_STOLEN_TILE,stolenTile.toString());
		this.jsonValueMap.put(JSON_KEY_HURO1,huro1.toString());
		this.jsonValueMap.put(JSON_KEY_HURO2,huro2.toString());
		this.jsonValueMap.put(JSON_KEY_DISCARDED_TILE,discardedTile.toString());
		
		JSONArray array = new JSONArray();
		for(TileEnum tile : wall){
			array.add(tile.toString());
		}
		this.jsonValueMap.put(JSON_KEY_WALL, array);
	}
}
