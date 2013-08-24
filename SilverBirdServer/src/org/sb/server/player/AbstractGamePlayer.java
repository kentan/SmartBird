package org.sb.server.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.TileEnum;
import org.sb.server.GameServer;

public abstract class AbstractGamePlayer {

	abstract public void notifyTurn(final List<TileEnum> tiles,final TileEnum tileAtThisTurn,List<MeldElement> huroMelds,List<TileEnum> discardeTiles,Map<Integer,List<TileEnum>> otherPlayerDiscardedTiles,Map<Integer,List<MeldElement>> otherPlayerHuroTiles);

	abstract public void notifySteal();
	
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
	public boolean tumo(TileEnum tumoTile){
		return _gameServer.callTumo(_playerId,tumoTile);		
	}
	public boolean ron(TileEnum ronTile){
		return _gameServer.callRon(_playerId, ronTile);	
	}

	public boolean pong(TileEnum pongTile,TileEnum huro1,TileEnum huro2,TileEnum discardingTile){
		return _gameServer.callPong(_playerId,pongTile,huro1,huro2,discardingTile);
	}
	public boolean kongByWall(TileEnum kongTile,TileEnum huro1,TileEnum huro2,TileEnum huro3,TileEnum discardingTile){
		return _gameServer.callKongByWall(_playerId,kongTile,huro1,huro2,huro3,discardingTile);
	}	
	public boolean kongBySteal(TileEnum kongTile,TileEnum huro1,TileEnum huro2,TileEnum huro3,TileEnum discardingTile){
		return _gameServer.callKongBySteal(_playerId,kongTile,huro1,huro2,huro3,discardingTile);
	}
	public boolean chow(TileEnum chowTile,TileEnum huro1,TileEnum huro2,TileEnum discardingTile){
		return _gameServer.callChow(_playerId,chowTile,huro1,huro2,discardingTile);
	}
	
	public void richi(TileEnum richiTile){
		_gameServer.callRichi(_playerId,richiTile);		
	}

	public void discard(TileEnum discardingTile){		
		_gameServer.discardTile(_playerId,discardingTile);
	}
	public int getPlayerId(){
		return _playerId;
	}
	

}
