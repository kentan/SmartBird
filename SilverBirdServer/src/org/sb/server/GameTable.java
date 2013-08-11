package org.sb.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sb.engine.controller.TileManager;
import org.sb.engine.controller.TileSet;
import org.sb.mdl.MeldElement;
import org.sb.mdl.cnst.GameConstants;
import org.sb.mdl.enm.TileEnum;


public class GameTable {
	
	private TileEnum _prevailingWind;
//	private List<TileEnum> doraList;
	
	private Map<Integer,TileSet> _playerTileSets = new HashMap<Integer, TileSet>();
	private Map<Integer,List<TileEnum>> _discardeTiles = new HashMap<Integer, List<TileEnum>>();
	private Map<Integer,TileEnum> _playerWinds = new HashMap<Integer, TileEnum>();
	private TileEnum _lastDiscardedTile = null;
	
	public GameTable(){
		init(TileEnum.EAST);
	}
	public void init(TileEnum prevailingWind){
		TileEnum[] winds = {TileEnum.EAST,TileEnum.SOUTH,TileEnum.WEST,TileEnum.NORTH};
		for(int i = 0; i < GameConstants.PLAYER_NUM; i++){	
			_discardeTiles.put(i, new ArrayList<TileEnum>());
			_playerWinds.put(i,winds[i]);
			TileSet tileSet = new TileSet(_playerWinds.get(i),_prevailingWind);
			tileSet.setTiles(TileManager.createInitialTiles());
			
			_playerTileSets.put(i,tileSet);
			_prevailingWind = prevailingWind;
		}
		
	}
	public TileEnum getLastDiscardedTile(){
		return _lastDiscardedTile;
	}
	
	public void setRichi(int playerId){
		TileSet tileSet = _playerTileSets.get(playerId);
		tileSet.setRichi();		
	}
	public void addDiscardeTile(int playerId,TileEnum discardedTile){
		List<TileEnum> tiles = _discardeTiles.get(playerId);
		if(tiles == null){
			//TODO error handling
		}
		tiles.add(discardedTile);
		_lastDiscardedTile = discardedTile;
	}
	public List<TileEnum> getDiscardedTiles(int playerId){
		return _discardeTiles.get(playerId);
	}
	
	public List<MeldElement> getHuroTiles(int playerId){
		return  _playerTileSets.get(playerId).getHuros();
	}
	
	public TileEnum getPlayerWind(int playerId){
		return _playerWinds.get(playerId);
	}

	public void removeWallTile(int playerId,TileEnum tile){
		List<TileEnum> tiles = _discardeTiles.get(playerId);
		if(tiles == null){
			//TODO error handling
		}
		tiles.remove(tile);
	}

	public void addWallTiles(int playerId,TileEnum tile){


		 _playerTileSets.get(playerId).addTile(tile);

	}
	public List<TileEnum> getWallTiles(int playerId){


		return _playerTileSets.get(playerId).getTiles();

	}
	public TileEnum getPrevailingWind(){
		return _prevailingWind;
	}
	public boolean isParent(int playerId){
		TileEnum playerWind = getPlayerWind(playerId);
		return playerWind.equals(TileEnum.EAST);
		
	}
	
	public void addHuro(int playerId, MeldElement melds){
		TileSet tileSet = _playerTileSets.get(playerId);
		tileSet.addHuro(melds);
	}
}
