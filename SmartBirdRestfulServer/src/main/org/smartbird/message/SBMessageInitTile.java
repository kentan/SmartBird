package org.smartbird.message;

import java.util.List;

import org.json.simple.JSONArray;
import org.sb.mdl.enm.TileEnum;

public class SBMessageInitTile extends SBMessage {
	List<TileEnum> initTiles;

	final private static String OPERATION = "init";
//	public SBMessageInitTile(int playerId, String operation){
//		
//	}
	public SBMessageInitTile(int playerId,List<TileEnum> initTiles){
		super(playerId,OPERATION);
		
		this.playerId = playerId;
		this.initTiles = initTiles;
		
		this.jsonDataMap.put(JSON_KEY_PLAYERID, playerId);
		JSONArray array = new JSONArray();
		for(TileEnum tile : initTiles){
			array.add(tile.toString());
		}
		this.jsonDataMap.put(JSON_KEY_VALUES, array);
		

	}
}
