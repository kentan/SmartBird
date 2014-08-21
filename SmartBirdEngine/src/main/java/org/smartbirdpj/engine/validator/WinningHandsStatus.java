package org.smartbirdpj.engine.validator;

import org.smartbirdpj.mdl.enm.TileEnum;
import org.smartbirdpj.mdl.enm.WinningHandsEnum;

public class WinningHandsStatus {
	public boolean isYakuman = false;
	public boolean isChinitsu = false;
	public boolean isHonrotou = false;
	public boolean isSyosangen = false;
	public boolean isJuntyan = false;
	public boolean  isHonitsu = false;
	public boolean  is2peko = false;;
	public boolean  isTanyao = false;
	public boolean  is3Kantsu = false;
	public boolean  is3Anko = false;
	public boolean  isToiToi = false;
	public boolean  is3SyokuDojun = false;
	public boolean  is3SyokuDokoku = false;
	public boolean  isIkkiTsukan = false;
	public boolean  is7Toitsu = false;
	public boolean  isChanta = false;
	public boolean  is1peko = false;
	public boolean  isYakuhai = false;
	public boolean  isTumo = false;
	public boolean  isPinhu = false;
	
	public boolean is3SyokuStolen = false;

	TileEnum _playerWind;
	TileEnum _prevailingWind;
	public WinningHandsStatus(TileEnum playerWind,TileEnum prevailingWind){
		_playerWind = playerWind;
		_prevailingWind = prevailingWind;
	}

	public boolean isHigherWinningHandsValid(WinningHandsEnum winingHandEnum) {
		switch (winingHandEnum) {
		case CHINITSU:
			return isHigherThanChinitsuValid();
		case HONROUTOU:
			return isHigherThanHonrotouValid();
		case SYOSANGEN:
			return isHigherThanSyosangenValid();
		case JUNCHAN:
			return isHigherThanJuntyanValid();
		case HONITSU:
			return isHigherThanHonitsuValid();
		case RYAN_PEKO:
			return isHigherThan2pekoValid();
		case TANYAO:
			return isHigherThanTanyaoValid();
		case SAN_KANTSU:
			return isHigherThan3KantsuValid();
		case SAN_ANKO:
			return isHigherThan3AnkoValid();
		case TOITOI:
			return isHigherThanToiToiValid();
		case SANSYOKU_DOJUN:
			return isHigherThan3SyokuDojunValid();
		case SANSYOKU_DOKOKU:
			return isHigherThan3SyokuDokokuValid();
		case IKKI_TSUKAN:
			return isHigherThanIkkiTsukanValid();
		case CHI_TOITSU:
			return isHigherThan7ToitsuValid();
		case CHANTA:
			return isHigherThanChantaValid();
		case I_PEKO:
			return isHigherThan1pekoValid();
		case YAKUHAI:
			return isHigherThanYakuhaiValid();
		case TUMO:
			return isHigherThanTumoValid();
		case PINHU:
			return isHigherThanPinhuValid();
		default:
			return false;

		}
	}

	private boolean isHigherThanChinitsuValid() {
		return isYakuman;
	}

	private boolean isHigherThanHonrotouValid() {
		return isYakuman;
	}

	private boolean isHigherThanSyosangenValid() {
		return isYakuman;
	}

	private boolean isHigherThanJuntyanValid() {
		return isYakuman;
	}

	private boolean isHigherThanHonitsuValid() {
		return isYakuman;
	}

	private boolean isHigherThan2pekoValid() {
		return isYakuman;
	}

	private boolean isHigherThanTanyaoValid() {
		return isYakuman;
	}

	private boolean isHigherThan3KantsuValid() {
		return isYakuman;
	}

	private boolean isHigherThan3AnkoValid() {
		return isYakuman;
	}

	private boolean isHigherThanToiToiValid() {
		return isYakuman || isHonrotou;
	}

	private boolean isHigherThan3SyokuDojunValid() {
		return isYakuman || isChinitsu;
	}

	private boolean isHigherThan3SyokuDokokuValid() {
		return isYakuman;
	}

	private boolean isHigherThanIkkiTsukanValid() {
		return isYakuman;
	}

	private boolean isHigherThan7ToitsuValid() {
		return isYakuman;
	}

	private boolean isHigherThanChantaValid() {
		return isYakuman || isTanyao || isHonrotou || is2peko || isJuntyan;
	}

	private boolean isHigherThan1pekoValid() {
		return isYakuman || is2peko;
	}

	private boolean isHigherThanYakuhaiValid() {
		return isYakuman;
	}

	private boolean isHigherThanTumoValid() {
		return isYakuman;
	}

	private boolean isHigherThanPinhuValid() {
		return isYakuman;
	}
	
	public TileEnum getPlayerWind(){
		return _playerWind;
	}
	public TileEnum getPrevailingWind(){
		return _prevailingWind;
	}
	

}
