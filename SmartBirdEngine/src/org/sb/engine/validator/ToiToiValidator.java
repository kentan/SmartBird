package org.sb.engine.validator;


import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.WinningHandsEnum;


public class ToiToiValidator extends WinningHandsValidator {
	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.TOITOI)) {
			return false;
		}
		int numOfKotsu = 0;
		int numOfToitsu = 0;

		for (MeldElement meld : ((WinningHandsBasic)winningHands).getList()) {
			if (MeldEnum.WALL_PONG.equals(meld.getMeldEnum()) || MeldEnum.STEAL_PONG.equals(meld.getMeldEnum())) {
				numOfKotsu++;
				continue;
			} else if (MeldEnum.EYES.equals(meld.getMeldEnum())) {
				numOfToitsu++;
				continue;
			}
			return false;
		}

		if((numOfKotsu == 4) && (numOfToitsu == 1)){
			status.isToiToi = true;
			return true;
		}else{
			return false;
		}
	}

}
