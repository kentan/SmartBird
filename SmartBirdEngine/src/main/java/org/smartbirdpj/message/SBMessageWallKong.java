package org.smartbirdpj.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.smartbirdpj.mdl.enm.TileEnum;

public class SBMessageWallKong extends SBMessage {
	final private static String OPERATION = "wallKong";

	final private static String JSON_KEY_HURO1 = "huro1";
	final private static String JSON_KEY_HURO2 = "huro2";
	final private static String JSON_KEY_HURO3 = "huro3";
	final private static String JSON_KEY_HURO4 = "huro4";
	final private static String JSON_KEY_DISCARDED = "discardedTile";
	final private static String JSON_KEY_WALL = "wall";
	
	public SBMessageWallKong(int playerId, TileEnum huro1, TileEnum huro2, TileEnum huro3, TileEnum huro4,TileEnum discardedTile,List<TileEnum> wall){
		super(playerId, OPERATION);


		this.jsonValueMap.put(JSON_KEY_HURO1,huro1.toString());
		this.jsonValueMap.put(JSON_KEY_HURO2,huro2.toString());
		this.jsonValueMap.put(JSON_KEY_HURO3,huro3.toString());
		this.jsonValueMap.put(JSON_KEY_HURO4,huro4.toString());
		this.jsonValueMap.put(JSON_KEY_DISCARDED,discardedTile.toString());
		JSONArray array = new JSONArray();
		for(TileEnum tile : wall){
			array.add(tile.toString());
		}
		this.jsonValueMap.put(JSON_KEY_WALL, array);
	}
}
