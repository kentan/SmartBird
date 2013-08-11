package org.sb.engine.validator;


import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;
import org.sb.mdl.enm.WinningFormEnum;
import org.sb.mdl.enm.WinningHandsEnum;


public class PinhuValidator extends WinningHandsValidator {
	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.PINHU)) {
			return false;
		}
		if (!WinningFormEnum.RYANMEN.equals(((WinningHandsBasic)winningHands).getWinningFormEnum())) {
			return false;
		}
		for (MeldElement meld : ((WinningHandsBasic)winningHands).getList()) {
			if (MeldEnum.WALL_CHOW.equals(meld.getMeldEnum())) {
				continue;
			} else if (MeldEnum.EYES.equals(meld.getMeldEnum())) {
				TileEnum tile = meld.getList().get(0);
				if (tile.equals(status.getPlayerWind()) || tile.equals(status.getPrevailingWind()) || tile.equals(TileEnum.WHITE) || tile.equals(TileEnum.GREEN)
						|| tile.equals(TileEnum.RED)) {
					return false;
				}
				continue;
			} else {
				return false;
			}

		}
		status.isPinhu = true;
		return true;
	}
}
