package org.smartbirdpj.engine.validator;


import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.mdl.enm.WinningHandsEnum;


public class TumoValidator extends WinningHandsValidator {
	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.TUMO)) {
			return false;
		}

		if(!winningHands.isStolen() && winningHands.isTumo()){
			status.isTumo = true;
			return true;
		}else{
			return false;
		}
	}
}
