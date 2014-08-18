package org.smartbirdpj.server;


import org.smartbirdpj.mdl.cnst.GameConstants;
import org.smartbirdpj.mdl.enm.TileEnum;

public class GameRoundStatus {
	private int _parentPlayerId;
	private TileEnum _prevailingWind;
	private int _roundNumber;
	private int _extendedRoundNumber;
	private int _pooledThousandBarNumber;
	private boolean _roundEnd;
	private boolean _isParentWon;

	public GameRoundStatus(){
		_prevailingWind = TileEnum.EAST;
		_roundNumber = 0;
		_extendedRoundNumber = 0;
		_parentPlayerId = 0;
	}
	public int getParentPlayerId(){
		return _parentPlayerId;
	}
	public TileEnum getPrevailingWind(){
		return _prevailingWind;
	}
	
	public int getRoundNumber(){
		return _roundNumber;
	}
	public int getExtendedRoundNumber(){
		return _extendedRoundNumber;
	}
	public int getPooledThousandBarNumber(){
		return _pooledThousandBarNumber;
	}
	public boolean isRoundEnd(){
		return _roundEnd;
	}
	public void setPrevailingWind(TileEnum prevailingWind){
		 _prevailingWind = prevailingWind;
	}
	
	public void setRoundNumber(int roundNumber){
		_roundNumber = roundNumber;
	}
	
	public void setRoundEnd(){
		_roundEnd = true;
	}
	public void setParentWon(){
		_isParentWon = true;
	}
	public void nextRound(){
		if(_isParentWon){
			_extendedRoundNumber++;
			_isParentWon = false;
		}else{
			_extendedRoundNumber = 0;
			_roundNumber = (_roundNumber + 1) % GameConstants.ROUND_MAX;
			if(_roundNumber == 0){
				_prevailingWind =  _prevailingWind.next();
			}
			_parentPlayerId = (_parentPlayerId + 1) % GameConstants.PLAYER_NUM;
		}
		_roundEnd = false;
	}
	
	public boolean isGameRoundFinish(boolean isHarfGame){
		if(isHarfGame){
			return getPrevailingWind() != TileEnum.WEST;
		}else{
			return getPrevailingWind() != TileEnum.WHITE;
		}
	}
	
	public TileEnum getPlayerWind(int playerId){
		
		playerId += (_parentPlayerId > playerId) ? 4 : 0;
		int diff = playerId - _parentPlayerId;
		return TileEnum.getEnum((TileEnum.EAST.getIndex() + diff));
	}
}
