package org.sb.server.player;

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
	private GameServer _gameServer = null;
	public void initialize(int playerId,List<TileEnum> tiles){
		_playerId = playerId;
		_tiles = tiles;
		_gameServer = GameServer.getInstance();
		
	}
	public boolean isWinningHandsValid(){
		return _gameServer.isWinningHandsValid(_playerId);
	}
	public boolean callFinishTumo(){
		return _gameServer.callFinishTumo(_playerId);		
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
