package org.smartbirdpj.mdl.enm;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public enum WinningHandsEnum {
	CHINITSU,
	HONROUTOU,
	SYOSANGEN,
	JUNCHAN,
	HONITSU,
	RYAN_PEKO,
	TANYAO,
	SAN_KANTSU,
	SAN_ANKO,
	TOITOI,
	SANSYOKU_DOKOKU,
	SANSYOKU_DOJUN,
	IKKI_TSUKAN,
	CHANTA,
	CHI_TOITSU,
	I_PEKO,
	YAKUHAI,
	TUMO,
	PINHU,
	FOR_PONGS,
	BIG_DORAGONS,
	THIRTEEN_ORPHANS,
	FOR_KONGS,
	FOR_WINDS,
	ALL_GREENS,
	ALL_HONORS,
	NINE_TRESURES,
	ALL_TERMINALS;
		
	final private static  Map<WinningHandsEnum,Integer> YAKU_TO_HAN_MAP = new HashMap<WinningHandsEnum, Integer>(){{
		put(WinningHandsEnum.I_PEKO,1);
		put(WinningHandsEnum.TANYAO,1);
		put(WinningHandsEnum.YAKUHAI,1);
		put(WinningHandsEnum.TUMO,1);
		put(WinningHandsEnum.PINHU,1);

		put(WinningHandsEnum.CHI_TOITSU,2);
		put(WinningHandsEnum.CHANTA,2);
		put(WinningHandsEnum.SANSYOKU_DOKOKU,2);
		put(WinningHandsEnum.SANSYOKU_DOJUN,2);
		put(WinningHandsEnum.IKKI_TSUKAN,2);
		put(WinningHandsEnum.SAN_ANKO,2);
		put(WinningHandsEnum.TOITOI,2);
		put(WinningHandsEnum.SAN_KANTSU,2);
		
		put(WinningHandsEnum.HONITSU,3);
		put(WinningHandsEnum.JUNCHAN,3);
		put(WinningHandsEnum.RYAN_PEKO,3);


		put(WinningHandsEnum.HONROUTOU,4);
		put(WinningHandsEnum.SYOSANGEN,2);

		put(WinningHandsEnum.CHINITSU,6);
		
		put(WinningHandsEnum.FOR_PONGS,13);
		put(WinningHandsEnum.BIG_DORAGONS,13);
		put(WinningHandsEnum.THIRTEEN_ORPHANS,13);
		put(WinningHandsEnum.FOR_KONGS,13);
		put(WinningHandsEnum.FOR_WINDS,13);
		put(WinningHandsEnum.ALL_GREENS,13);
		put(WinningHandsEnum.ALL_HONORS,13);
		put(WinningHandsEnum.NINE_TRESURES,13);
		put(WinningHandsEnum.ALL_TERMINALS,13);
		

	}};
	
	final private static  Map<WinningHandsEnum,Integer> YAKU_TO_HAN_STOLEN_MAP = new HashMap<WinningHandsEnum, Integer>(){{
		put(WinningHandsEnum.I_PEKO,0);
		put(WinningHandsEnum.TANYAO,1);
		put(WinningHandsEnum.YAKUHAI,1);
		put(WinningHandsEnum.TUMO,0);
		put(WinningHandsEnum.PINHU,0);

		put(WinningHandsEnum.CHI_TOITSU,0);
		put(WinningHandsEnum.CHANTA,1);
		put(WinningHandsEnum.SANSYOKU_DOKOKU,1);
		put(WinningHandsEnum.SANSYOKU_DOJUN,1);
		put(WinningHandsEnum.IKKI_TSUKAN,1);
		put(WinningHandsEnum.SAN_ANKO,0);
		put(WinningHandsEnum.TOITOI,2);
		put(WinningHandsEnum.SAN_KANTSU,2);
		
		put(WinningHandsEnum.HONITSU,2);
		put(WinningHandsEnum.JUNCHAN,2);
		put(WinningHandsEnum.RYAN_PEKO,0);


		put(WinningHandsEnum.HONROUTOU,4);
		put(WinningHandsEnum.SYOSANGEN,2);

		put(WinningHandsEnum.CHINITSU,6);
		
		put(WinningHandsEnum.FOR_PONGS,13);
		put(WinningHandsEnum.BIG_DORAGONS,13);
		put(WinningHandsEnum.THIRTEEN_ORPHANS,13);
		put(WinningHandsEnum.FOR_KONGS,13);
		put(WinningHandsEnum.FOR_WINDS,13);
		put(WinningHandsEnum.ALL_GREENS,13);
		put(WinningHandsEnum.ALL_HONORS,13);
		put(WinningHandsEnum.NINE_TRESURES,13);
		put(WinningHandsEnum.ALL_TERMINALS,13);
		

	}};
	public int getHan(boolean isStolen){
		try{
			if(isStolen){
				return YAKU_TO_HAN_STOLEN_MAP.get(this);
			}else{
				return YAKU_TO_HAN_MAP.get(this);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
}
