package org.sb.engine.validator;


import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;
import org.sb.mdl.enm.WinningHandsEnum;


public class SyoSangenValidator extends WinningHandsValidator {
	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.SYOSANGEN)) {
			return false;
		}

		int numOfSasngen = 0;
		boolean isSangenFoundInToitsu = false;
		for (MeldElement meld : ((WinningHandsBasic)winningHands).getList()) {
			TileEnum tile = meld.getList().get(0);
			if (TileEnum.WHITE.equals(tile) || TileEnum.GREEN.equals(tile) || TileEnum.RED.equals(tile)) {
				if (MeldEnum.EYES.equals(meld.getMeldEnum())) {
					isSangenFoundInToitsu = true;

				} else {
					numOfSasngen++;
				}
			}
		}
		return (isSangenFoundInToitsu && numOfSasngen == 2);
	}
}
