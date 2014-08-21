package org.smartbirdpj.engine.validator;

import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.mdl.enm.WinningHandsEnum;


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
