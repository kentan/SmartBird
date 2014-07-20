package org.smartbirdpj.engine.validator;


import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.MeldEnum;
import org.smartbirdpj.mdl.enm.TileEnum;
import org.smartbirdpj.mdl.enm.WinningHandsEnum;


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
