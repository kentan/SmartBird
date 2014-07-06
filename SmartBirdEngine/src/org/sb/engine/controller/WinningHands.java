package org.sb.engine.controller;

public class WinningHands {
    private boolean _isTumo = false;
    private boolean _isStolen = false;
    private boolean _isRichi = false;
    public WinningHands(){}
    public WinningHands(boolean isNaki,boolean isTumo,boolean isRichi){
        _isStolen = isNaki;
        _isTumo = isTumo;
        _isRichi = isRichi;

    }
    public boolean isStolen(){
        return _isStolen;
	}
	
	public boolean isTumo(){
	    return _isTumo;
	
	}
	public boolean isRichi(){
		return _isRichi;
	}

    public void setStolen(){
    	_isStolen = true;
	}
	
	public void setTumo(){
		_isTumo = true;	
	}
	public void setRichi(){
		_isRichi = true;
	}
}
