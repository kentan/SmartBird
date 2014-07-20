package org.smartbirdpj.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.smartbirdpj.engine.controller.TileManager;
import org.smartbirdpj.engine.controller.TileSet;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.PaidPoint;
import org.smartbirdpj.mdl.Point;
import org.smartbirdpj.mdl.cnst.GameConstants;
import org.smartbirdpj.mdl.enm.MeldEnum;
import org.smartbirdpj.mdl.enm.TileEnum;


public class GameTable {
	
	private int _parentPlayerId;
	private int _ronedPlayerId;
	private TileEnum _prevailingWind;
	
	private List<TileEnum> _doraList;
	
	private Map<Integer,TileSet> _playerTileSets = new HashMap<Integer, TileSet>();
	private Map<Integer,List<TileEnum>> _discardeTiles = new HashMap<Integer, List<TileEnum>>();
	private Map<Integer,TileEnum> _playerWinds = new HashMap<Integer, TileEnum>();
	private TileEnum _lastDiscardedTile = null;
	private TileManager _tileManager = null;
	public GameTable(TileEnum prevailingWind,int parentPlayerId){
		init(prevailingWind,parentPlayerId);
	}
	public void init(TileEnum prevailingWind,int parentPlayerId){
		_tileManager = new TileManager();
		_doraList = new ArrayList<TileEnum>();
		TileEnum[] winds = {TileEnum.EAST,TileEnum.SOUTH,TileEnum.WEST,TileEnum.NORTH};
		int playerId = parentPlayerId;
		int count = 0;
		while(count < GameConstants.PLAYER_NUM){
			_discardeTiles.put(playerId, new ArrayList<TileEnum>());
			_playerWinds.put(playerId,winds[playerId]);
			TileSet tileSet = new TileSet(_playerWinds.get(playerId),_prevailingWind);
			tileSet.setTiles(_tileManager.createInitialTiles());
			
			_playerTileSets.put(playerId,tileSet);
			_prevailingWind = prevailingWind;
			

			
			playerId = (playerId + 1)%GameConstants.PLAYER_NUM;
			count++;
		}
		_doraList.add(_tileManager.takeDora());		
		_parentPlayerId = parentPlayerId;
		
	}

	public TileSet getPlayerTileSet(int playerId){
		return _playerTileSets.get(playerId);
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
	
	public Map<Integer,List<MeldElement>> getOtherPlayersHuroTiles(int playerId){

		Map<Integer,List<MeldElement>> otherPlayersHuroTiles = new HashMap<Integer,List<MeldElement>>();
		for(int i=0 ; i < GameConstants.PLAYER_NUM; i++){
			if(i == playerId){
				continue;
			}

			otherPlayersHuroTiles.put(i,getHuroTiles(playerId));
		}
		return otherPlayersHuroTiles;
	}
	
	public Map<Integer,List<TileEnum>> getOtherPlayersDiscardedTiles(int playerId){
		Map<Integer,List<TileEnum>> otherPlayersDiscardedTiles = new HashMap<Integer,List<TileEnum>>();
		for(int i=0 ; i < GameConstants.PLAYER_NUM; i++){
			if(i == playerId){
				continue;
			}
			otherPlayersDiscardedTiles.put(i,getDiscardedTiles(playerId));
		}
		return otherPlayersDiscardedTiles;
	}
	
	public List<MeldElement> getHuroTiles(int playerId){
		return  _playerTileSets.get(playerId).getHuros();
	}
	
	public void addHuro(int playerId, MeldElement melds){
		TileSet tileSet = _playerTileSets.get(playerId);
		tileSet.addHuro(melds);
		if(melds.getMeldEnum() == MeldEnum.STEAL_CHOW || 
				melds.getMeldEnum() == MeldEnum.STEAL_PONG ||
				melds.getMeldEnum() == MeldEnum.STEAL_KONG){
			tileSet.setStolen();
		}
	}
	
	public TileEnum getPlayerWind(int playerId){
		return _playerWinds.get(playerId);
	}

	public List<TileEnum> getDoraTiles(){
		//TODO deep copy
		return _doraList;
	}
	

	public List<TileEnum> getWallTiles(int playerId){


		return _playerTileSets.get(playerId).getTiles();

	}
	public TileEnum getPrevailingWind(){
		return _prevailingWind;
	}

	
	public TileEnum takeTileFromTable(int playerId){
		TileEnum t = _tileManager.takeTileFromTable();
		 _playerTileSets.get(playerId).addTile(t);
		 return t;
	}
	
	

	private List<Integer> getAllPlayerIds(){
		List<Integer> l = new ArrayList<Integer>();
		for(int i = 0; i < GameConstants.PLAYER_NUM;i++){
			l.add(i);
		}
		return l;
	}
	public PaidPoint calculate(int playerId){
		TileSet tileSet = _playerTileSets.get(playerId);
		Point point = tileSet.calculate();
		PaidPoint paidPoint = new PaidPoint(point.getPoint1(),point.getPoint2());
		paidPoint.setPaidPlayerId(playerId);
		if(tileSet.isTumo()){
			if(playerId == _parentPlayerId){
				List<Integer> payingPlayerIds = getAllPlayerIds();
				payingPlayerIds.remove(new Integer(playerId));
				
				paidPoint.setPayingPlayerIdOnPoint1(payingPlayerIds);
				
			}else{
				
				List<Integer> childPlayerIds = getAllPlayerIds();
				childPlayerIds.remove(new Integer(playerId));
				childPlayerIds.remove(new Integer(_parentPlayerId));
				
				paidPoint.setPayingPlayerIdOnPoint1(_parentPlayerId);
				paidPoint.setPayingPlayerIdOnPoint2(childPlayerIds);
				
			}
		}else{
			paidPoint.setPayingPlayerIdOnPoint1(_ronedPlayerId);
		}
		
		return paidPoint;
	}
	public void setRonTile(int playerId,TileEnum ronTile,int ronedPlayerId){
		TileSet tileSet = _playerTileSets.get(playerId);
		tileSet.addTile(ronTile);
		tileSet.setRon();
		tileSet.setWinningTile(ronTile);
		
		_ronedPlayerId = ronedPlayerId;
	}
	public void setTumoTile(int playerId,TileEnum tumoTile){
		TileSet tileSet = _playerTileSets.get(playerId);
		tileSet.setTumo();
		tileSet.setWinningTile(tumoTile);
		
	}
	public boolean isWinningHandsValid(int playerId){
		TileSet tileSet = _playerTileSets.get(playerId);
//		return tileSet.isWinningHandsValid();
		Point point = tileSet.calculate();
		return point != null;
	}
}
