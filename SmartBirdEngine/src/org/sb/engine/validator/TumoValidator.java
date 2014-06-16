package org.sb.engine.validator;


import org.sb.engine.controller.WinningHands;
import org.sb.mdl.enm.WinningHandsEnum;


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
