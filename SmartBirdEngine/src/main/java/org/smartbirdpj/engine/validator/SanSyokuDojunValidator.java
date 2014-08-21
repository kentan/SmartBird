package org.smartbirdpj.engine.validator;


import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.mdl.enm.WinningHandsEnum;


public class SanSyokuDojunValidator extends SanSyokuValidator  {

	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.SANSYOKU_DOJUN)) {
			return false;
		}
		if(is3syokuWall(winningHands, false)){
			status.is3SyokuDojun = true;
			return true;
		}else if(is3syokuStolen(winningHands, false)){
			status.is3SyokuDojun = true;
			status.is3SyokuStolen = true;
			return true;
		}else{
			return false;
		}
	}
}
