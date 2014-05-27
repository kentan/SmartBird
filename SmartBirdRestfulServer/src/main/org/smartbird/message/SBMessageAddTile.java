package org.smartbird.message;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.sb.mdl.enm.TileEnum;



public class SBMessageAddTile extends SBMessage{
	List<TileEnum> wall;
	TileEnum addedTile;
	
	final private static String JSON_KEY_WALL = "wall";
	final private static String JSON_KEY_ADDEDTILE = "addedTile";
	final private static String OPERATION = "add";

	public SBMessageAddTile(int playerId,List<TileEnum> wall,TileEnum addedTile){
		super(playerId,OPERATION);
		
		this.playerId = playerId;
		this.wall = wall;
		this.addedTile = addedTile;
	
		this.jsonDataMap.put(JSON_KEY_PLAYERID,this.playerId);
		
		Map<String,Object> jsonChildMap = new HashMap<String, Object>();
		JSONArray array = new JSONArray();
		Collections.sort(wall);
		for(TileEnum tile : wall){
			array.add(tile.toString());
		}
		jsonChildMap.put(JSON_KEY_WALL,array);
		jsonChildMap.put(JSON_KEY_ADDEDTILE,this.addedTile.toString());
		
		this.jsonDataMap.put(JSON_KEY_VALUES,jsonChildMap);

	}
	public int getPlayerId(){
		return playerId;
	}
	public List<TileEnum> getWall(){
		return wall;
	}
	public TileEnum getAddedTile(){
		return addedTile;
	}
}
