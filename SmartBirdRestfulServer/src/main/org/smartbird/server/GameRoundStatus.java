package org.smartbird.server;


import org.sb.mdl.cnst.GameConstants;
import org.sb.mdl.enm.TileEnum;

public class GameRoundStatus {
	private int _parentPlayerId;
	private TileEnum _prevailingWind;
	private int _roundNumber;
	private int _extendedRoundNumber;
	private boolean _roundEnd;
	

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
	
	public boolean isRoundEnd(){
		return _roundEnd;
	}
	public void setPrevailingWind(TileEnum prevailingWind){
		 _prevailingWind = prevailingWind;
	}
	
	public void setRoundNumber(int roundNumber){
		_roundNumber = roundNumber;
	}
	public void setExtendedRoundNumber(int extendedRoundNumber){
		 _extendedRoundNumber = extendedRoundNumber;
	}
	public void setRoundEnd(){
		_roundEnd = true;
	}
	public void nextRound(){
		_roundNumber = (_roundNumber + 1) % GameConstants.ROUND_MAX;
		if(_roundNumber == 0){
			_prevailingWind =  _prevailingWind.next();
		}
		_roundEnd = false;
		_parentPlayerId = (_parentPlayerId + 1) % GameConstants.PLAYER_NUM;
	}
	
	public boolean isGameRoundFinish(boolean isHarfGame){
		if(isHarfGame){
			return getPrevailingWind() != TileEnum.WEST;
		}else{
			return getPrevailingWind() != TileEnum.WHITE;
		}
	}
	
}
