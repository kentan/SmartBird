package org.smartbirdpj.engine.validator;


import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.mdl.enm.WinningHandsEnum;


public class SanSyokuDokokuValidator extends SanSyokuValidator {

	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.SANSYOKU_DOKOKU)) {
			return false;
		}
		if(is3syokuWall(winningHands, true)){
			status.is3SyokuDokoku = true;
			return true;
		}else if(is3syokuStolen(winningHands, true)){
			status.is3SyokuDokoku = true;
			status.is3SyokuStolen = true;
			return true;
		}else{
			return false;
		}
	}
}
