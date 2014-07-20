package org.smartbirdpj.engine.validator;


import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.MeldEnum;
import org.smartbirdpj.mdl.enm.TileEnum;
import org.smartbirdpj.mdl.enm.WinningHandsEnum;


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
