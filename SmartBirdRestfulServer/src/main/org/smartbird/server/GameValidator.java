package org.smartbird.server;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sb.mdl.MeldElement;
import org.sb.mdl.cnst.GameConstants;
import org.sb.mdl.enm.TileEnum;

public class GameValidator {
	PrintStream ps = System.out;
	public boolean validate(GameTable table,int count){
		boolean valid = true;
		valid &= validateWallTiles(table, count);
		valid &= validateOpenedTiles(table, count);
		
		return valid;
	}
	// validate the number of wall tiles
	private boolean validateWallTiles(GameTable table,int count){

		for(int i = 0; i < GameConstants.PLAYER_NUM; i++){
			List<TileEnum> wallTiles = table.getWallTiles(i);
			List<MeldElement> huroList = table.getHuroTiles(i);
			
			
			
			if(wallTiles.size() + (huroList.size() * 3) != GameConstants.MAX_WALL_TILE_NUM - 1){

				GameServerLogger.writeln("the number of discarded tiles is invalid:");
				GameServerLogger.writeln("round:" + count);
				GameServerLogger.writeln("playerid:" + i);
				GameServerLogger.writeln("huro tiles:" + huroList.size() * 3);
				return false;
			}
		}
		return true;
	}
	// validate the number of all opened tile on table is below 5;	
	private boolean validateOpenedTiles(GameTable table,int count){

		List<TileEnum> openedTiles = new ArrayList<TileEnum>();		
		for(int i = 0; i < GameConstants.PLAYER_NUM; i++){

			openedTiles.addAll(table.getWallTiles(i));
			List<MeldElement> huroList = table.getHuroTiles(i);
			for(MeldElement meld : huroList){
				for(TileEnum tile : meld.getList()){
					if(tile != meld.getStolenTile()){
						openedTiles.add(tile);
					}
				}
			}
			openedTiles.addAll(table.getDiscardedTiles(i));
		}
		Map<TileEnum,Integer> m = new HashMap<TileEnum,Integer>();
		
		for(TileEnum tile : openedTiles){
			Integer i = m.get(tile);
			if(i == null){
				m.put(tile, 1);
			}else{
				if(i >= 5){
					GameServerLogger.writeln("the number of tile is invalid. 5 tiles for :" + tile);
					GameServerLogger.writeln("round:" + count);
					GameServerLogger.writeln("playerid:" + i);
					return false;
				}
				m.put(tile, i + 1);
			}
		}	
		return true;
	}
	
}
