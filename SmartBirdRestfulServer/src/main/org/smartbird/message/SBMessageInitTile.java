package org.smartbird.message;

import java.util.List;

import org.json.simple.JSONArray;
import org.sb.mdl.enm.TileEnum;

public class SBMessageInitTile extends SBMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6921917288344485512L;

	List<TileEnum> initTiles;

	final private static String OPERATION = "init";
	final private static String JSON_KEY_INIT_TILES = "initTiles";
	final private static String JSON_KEY_WIND = "wind";
//	public SBMessageInitTile(int playerId, String operation){
//		
//	}
	public SBMessageInitTile(int playerId,List<TileEnum> initTiles,TileEnum wind){
		super(playerId,OPERATION);
		
		this.playerId = playerId;
		this.initTiles = initTiles;
		
		JSONArray array = new JSONArray();
		for(TileEnum tile : initTiles){
			array.add(tile.toString());
		}
		this.jsonValueMap.put(JSON_KEY_INIT_TILES, array);
		this.jsonValueMap.put(JSON_KEY_WIND, wind.toString());

		

	}
}
