package org.sb.engine.validator;

import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.enm.TileSuiteEnum;
import org.sb.mdl.enm.WinningHandsEnum;



public class ChinitsuValidator extends WinningHandsValidator {

	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.CHINITSU)) {
			return false;
		}
		TileSuiteEnum prev = ((WinningHandsBasic)winningHands).get(0).getTileSuite();
		for (int i = 1; i < ((WinningHandsBasic)winningHands).getList().size(); i++) {

			TileSuiteEnum current = ((WinningHandsBasic)winningHands).get(i).getTileSuite();
			if (TileSuiteEnum.WINDS_AND_DRAGONS.equals(current)) {
				return false;
			}
			if (!current.equals(prev)) {
				return false;
			}
			prev = current;
		}
		status.isChinitsu = true;
		return true;
	}
}
