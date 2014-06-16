package org.sb.engine.validator;


import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.WinningHandsEnum;


public class SanAnkoValidator extends WinningHandsValidator {
	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.SAN_ANKO)) {
			return false;
		}
		int numOfAnko = 0;

		for (MeldElement meld : ((WinningHandsBasic)winningHands).getList()) {
			if (MeldEnum.WALL_PONG.equals(meld.getMeldEnum())) {
				numOfAnko++;
			}

		}
		if (numOfAnko == 3) {
			status.is3Anko = true;
			return true;
		}

		return false;
	}

}
