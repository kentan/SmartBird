package org.smartbirdpj.engine.validator;

import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.TileEnum;
import org.smartbirdpj.mdl.enm.WinningHandsEnum;


public class TanyaoValidator extends WinningHandsValidator {

	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.TANYAO)) {
			return false;
		}
		for (MeldElement meld : ((WinningHandsBasic)winningHands).getList()) {
			for (TileEnum tile : meld.getList()) {
				if (!tile.isTanyao()) {
					
					return false;
				}
			}
		}
		status.isTanyao = true;
		return true;
	}
}
