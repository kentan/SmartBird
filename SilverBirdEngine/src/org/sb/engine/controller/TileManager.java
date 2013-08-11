package org.sb.engine.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.sb.mdl.enm.TileEnum;
import org.sb.mdl.cnst.GameConstants;

public class TileManager {
	private final static Random rnd = new Random(); 
	private final static int TILE_INDEX_MAX = TileEnum.RED.getIndex();
	private static Map<TileEnum,Integer> tileHistory = new HashMap<TileEnum,Integer>();



	
	public static TileEnum takeTileFromTable(){
		TileEnum tile = null;
		while(tile == null){
			int index = rnd.nextInt(TILE_INDEX_MAX);
			tile = TileEnum.getEnum(index);
			if(tile == null){
				continue;
			}
			
			Integer num = tileHistory.get(tile);
			if(num == null){
				tileHistory.put(tile,1);				
			}else if(num >= 5){
				continue;
			}else{
				tileHistory.put(tile,num + 1);				
			}
		
		}

		return tile;
	}
	public static List<TileEnum> createInitialTiles(){
		List<TileEnum> tiles = new ArrayList<TileEnum>();
		int i = 0;
		while( i < GameConstants.WALL_TILE_MAX_NUM - 1){

			tiles.add(TileManager.takeTileFromTable());
			i++;
		}
		return tiles;
	}
}
