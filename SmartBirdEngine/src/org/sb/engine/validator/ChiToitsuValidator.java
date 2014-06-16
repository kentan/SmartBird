package org.sb.engine.validator;

import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.enm.WinningHandsEnum;


public class ChiToitsuValidator extends WinningHandsValidator {

	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.TOITOI)) {
			return false;
		}
		if(((WinningHandsBasic)winningHands).is7Toitsu()){;
			status.is7Toitsu = true;
			return true;
		}else{
			return false;
		}
	}
}
