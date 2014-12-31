package org.smartbirdpj.mdl.enm;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public enum WinningFormEnum {
	PENTYAN,KANTYAN,RYANMEN,SYANPON,TANKI,KOKUSHI13,KOKUSHI_TANKI;
	
	private final static Map<WinningFormEnum, Integer>AGARI_TO_HU_MAP_RON = new HashMap<WinningFormEnum,Integer>(){
		{
			put(WinningFormEnum.PENTYAN,2);
			put(WinningFormEnum.KANTYAN,2);
			put(WinningFormEnum.RYANMEN,0);
			put(WinningFormEnum.SYANPON,0);
			put(WinningFormEnum.TANKI,2);
		}
	};
	
	private final static Map<WinningFormEnum, Integer>AGARI_TO_HU_MAP_TUMO = new HashMap<WinningFormEnum,Integer>(){
		{
			put(WinningFormEnum.PENTYAN,4);
			put(WinningFormEnum.KANTYAN,4);
			put(WinningFormEnum.RYANMEN,2);
			put(WinningFormEnum.SYANPON,2);
			put(WinningFormEnum.TANKI,4);
		}
	};
	private boolean _isRon = false;
	public void setRon(){
		_isRon = true;
	}
	public void setTumo(){
		_isRon = false;
	}
	public int getHu(){
		if(_isRon){
			return AGARI_TO_HU_MAP_RON.get(this);
		}else{
			return AGARI_TO_HU_MAP_TUMO.get(this);
		}
	}
}
