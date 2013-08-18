package org.sb.server.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.sb.server.InputCommand;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.TileEnum;
import org.sb.server.GameServer;

public abstract class AbstractGamePlayer {

	abstract public void notifyTurn(final List<TileEnum> tiles,final TileEnum tileAtThisTurn,List<MeldElement> huroMelds,List<TileEnum> discardeTiles,Map<Integer,List<TileEnum>> otherPlayerDiscardedTiles,Map<Integer,List<MeldElement>> otherPlayerHuroTiles);

	abstract public InputCommand notifySteal();
	
	protected int _playerId;
	protected List<TileEnum> _tiles;
	protected List<TileEnum> _doraList;
	protected TileEnum _playerWind;
	protected TileEnum _prevailingWind;
	private GameServer _gameServer = null;
	
	public void initialize(int playerId,List<TileEnum> tiles,TileEnum doraTile,TileEnum prevailingWind,TileEnum playerWind){
		_playerId = playerId;
		_tiles = tiles;
		_gameServer = GameServer.getInstance();
		
		_doraList = new ArrayList<TileEnum>();
		_doraList.add(doraTile);
		_playerWind = playerWind;
		_prevailingWind = prevailingWind;
		
	}
	public boolean isWinningHandsValid(TileEnum tumoTile){
		return _gameServer.isWinningHandsValid(_playerId,tumoTile);
	}
	public boolean callFinishTumo(TileEnum tumoTile){
		return _gameServer.callFinishTumo(_playerId,tumoTile);		
	}
	public boolean callFinishRon(TileEnum ronTile){
		return _gameServer.callFinishRon(_playerId, ronTile);	
	}

	
	public void callRichi(TileEnum richiTile){
		_gameServer.callRichi(_playerId,richiTile);		
	}

	public void discardTile(TileEnum discardingTile){		
		_gameServer.discardTile(_playerId,discardingTile);
	}
	public int getPlayerId(){
		return _playerId;
	}
	

}
