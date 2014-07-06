package org.smartbirdpj.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.sb.mdl.enm.TileEnum;

public class SBMessagePong extends SBMessage {
	final private static String OPERATION = "pong";
	final private static String JSON_KEY_STOLEN_PLAYERID = "stolenPlayerId";
	final private static String JSON_KEY_STOLEN_TILE = "stolenTile";
	final private static String JSON_KEY_HURO1 = "huro1";
	final private static String JSON_KEY_HURO2 = "huro2";
	final private static String JSON_KEY_DISCARDED_TILE = "discardedTile";
	final private static String JSON_KEY_WALL = "wall";
	
	public SBMessagePong(int stealingPlayerId, int stolenPlayerId, TileEnum stolenTile, TileEnum huro1, TileEnum huro2,TileEnum discardedTile,List<TileEnum> wall){
		super(stealingPlayerId, OPERATION);

		this.jsonValueMap.put(JSON_KEY_STOLEN_PLAYERID,stolenPlayerId);
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
