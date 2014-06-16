package org.sb.engine.validator;


import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.WinningHandsEnum;


public class SanKantsuValidator extends WinningHandsValidator {
	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.SAN_KANTSU)) {
			return false;
		}
		int numOfKantsu = 0;

		for (MeldElement meld : ((WinningHandsBasic)winningHands).getList()) {
			if (MeldEnum.WALL_KONG.equals(meld.getMeldEnum()) || MeldEnum.STEAL_KONG.equals(meld.getMeldEnum())) {
				numOfKantsu++;
			}

		}
		if (numOfKantsu == 3) {
			status.is3Kantsu = true;
			return true;
		}

		return false;
	}
}
