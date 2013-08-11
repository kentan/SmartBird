package org.sb.engine.validator;


import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;
import org.sb.mdl.enm.WinningHandsEnum;


public class HonrotoValidator extends WinningHandsValidator {


	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.HONROUTOU)) {
			return false;
		}
		for (MeldElement meld : ((WinningHandsBasic)winningHands).getList()) {
			TileEnum tile = meld.getList().get(0);

			if (MeldEnum.WALL_CHOW.equals(meld.getMeldEnum()) || MeldEnum.STEAL_CHOW.equals(meld.getMeldEnum())) {
				return false;
			}
			if (tile.isTanyao()) {
				return false;
			}

		}
		status.isHonrotou = true;
		return true;
	}
}
