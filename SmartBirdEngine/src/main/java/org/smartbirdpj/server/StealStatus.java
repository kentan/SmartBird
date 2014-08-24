package org.smartbirdpj.server;

public class StealStatus {

	private int _playerId = -1;
	private boolean _doTransit = true;
	private boolean _isAbleToChow = true;
	private boolean _isIllegalChow = false;
	private int _theNumberOfIllegalChowCalled = 0;
	
	public int getStealingPlayerId(){
		return _playerId;
	}
	
	public boolean doTransit(){
		return _doTransit;
	}
	
	public boolean isAbleToChow(){
		return _isAbleToChow;
	}
	public boolean isChowIllegal(){
		return _isIllegalChow;
	}
	
	public int getTheNumberOfIllegatChowCalled(){
		return _theNumberOfIllegalChowCalled;
	}
	public void setChiDisable(){
		_isAbleToChow = false;
	}
	public void setIllegalChow(){
		_isIllegalChow = true;
	}
	public void addIlleagalChowCount(){
		_theNumberOfIllegalChowCalled++;
	}
//	public void setStealingPlayerId(int plyaerId){
//		_playerId = plyaerId;
//	}
	public void finishTransition(int stealingPlayerId){
		_doTransit = false;
		_playerId = stealingPlayerId;
	}
}
