package org.smartbirdpj.server;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.smartbirdpj.log.LoggerFactory;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.cnst.GameConstants;
import org.smartbirdpj.mdl.enm.TileEnum;

public class GameValidator {
	PrintStream ps = System.out;
	Logger LOGGER = LoggerFactory.getLogger();
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
				LOGGER.info("the number of discarded tiles is invalid:");
				LOGGER.info("round:" + count);
				LOGGER.info("playerid:" + i);
				LOGGER.info("huro tiles:" + huroList.size() * 3);
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
					LOGGER.info("the number of tile is invalid. 5 tiles for :" + tile);
					LOGGER.info("round:" + count);
					LOGGER.info("playerid:" + i);
					return false;
				}
				m.put(tile, i + 1);
			}
		}	
		return true;
	}
	
}
