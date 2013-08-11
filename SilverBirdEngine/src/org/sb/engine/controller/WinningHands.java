package org.sb.engine.controller;

public class WinningHands {
    private boolean _isTumo = false;
    private boolean _isStolen = false;
    public WinningHands(boolean isNaki,boolean isTumo){
        _isStolen = isNaki;
        _isTumo = isTumo;

    }
    public boolean isStolen(){
        return _isStolen;
	}
	
	public boolean isTumo(){
	        return _isTumo;
	
	}

}
