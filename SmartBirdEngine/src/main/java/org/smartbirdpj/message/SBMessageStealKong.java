package org.smartbirdpj.message;


import java.util.List;

import org.json.simple.JSONArray;
import org.smartbirdpj.mdl.enm.TileEnum;

public class SBMessageStealKong extends SBMessage {
	final private static String OPERATION = "stealKong";
	final private static String JSON_KEY_STOLENTILE = "stolenTile";
	final private static String JSON_KEY_STOLEN_PLAYERID = "stolenPlayerId";
	final private static String JSON_KEY_HURO1 = "huro1";
	final private static String JSON_KEY_HURO2 = "huro2";
	final private static String JSON_KEY_HURO3 = "huro3";
	final private static String JSON_KEY_DISCARDED = "discardedTile";
	final private static String JSON_KEY_WALL = "wall";
	
	public SBMessageStealKong(int stealingPlayerId, int stolenPlayerId,TileEnum stolenTile, TileEnum huro1,TileEnum huro2, TileEnum huro3, TileEnum discardedTile,List<TileEnum> wall) {
		super(stealingPlayerId, OPERATION);
		this.jsonValueMap.put(JSON_KEY_STOLENTILE, stolenTile.toString());
		this.jsonValueMap.put(JSON_KEY_STOLEN_PLAYERID, stolenPlayerId);
		this.jsonValueMap.put(JSON_KEY_HURO1, huro1.toString());
		this.jsonValueMap.put(JSON_KEY_HURO2, huro2.toString());
		this.jsonValueMap.put(JSON_KEY_HURO3, huro3.toString());
		this.jsonValueMap.put(JSON_KEY_DISCARDED, discardedTile.toString());

		JSONArray array = new JSONArray();
		for(TileEnum tile : wall){
			array.add(tile.toString());
		}
		this.jsonValueMap.put(JSON_KEY_WALL, array);
	}

}
