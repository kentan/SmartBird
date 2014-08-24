package org.smartbirdpj.message;

import java.util.List;

import org.json.simple.JSONArray;
import org.smartbirdpj.mdl.enm.TileEnum;

public class SBMessageInitTile extends SBMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6921917288344485512L;



	final private static String OPERATION = "init";
	final private static String JSON_KEY_INIT_TILES = "initTiles";
	final private static String JSON_KEY_WIND = "wind";
	final private static String JSON_KEY_POINT = "point";
//	public SBMessageInitTile(int playerId, String operation){
//		
//	}
	public SBMessageInitTile(int playerId,List<TileEnum> initTiles,TileEnum wind,int point){
		super(playerId,OPERATION);
		
		this.playerId = playerId;
		
		JSONArray array = new JSONArray();
		for(TileEnum tile : initTiles){
			array.add(tile.toString());
		}
		this.jsonValueMap.put(JSON_KEY_INIT_TILES, array);
		this.jsonValueMap.put(JSON_KEY_WIND, wind.toString());
		this.jsonValueMap.put(JSON_KEY_POINT, point);

		

	}
}
