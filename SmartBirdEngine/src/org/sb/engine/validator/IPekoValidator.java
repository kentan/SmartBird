package org.sb.engine.validator;

import java.util.HashMap;
import java.util.Map;

import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;
import org.sb.mdl.enm.WinningHandsEnum;


public class IPekoValidator extends WinningHandsValidator {
	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.I_PEKO)) {
			return false;
		}
		if (!((WinningHandsBasic)winningHands).isMenzen()) {
			return false;
		}

		Map<TileEnum, Boolean> map = new HashMap<TileEnum, Boolean>();
		for (MeldElement meld : ((WinningHandsBasic)winningHands).getList()) {
			if (MeldEnum.WALL_CHOW.equals(meld.getMeldEnum())) {
				if (map.get(meld.getList().get(0)) != null) {
					status.is1peko = true;
					return true;
				}
				map.put(meld.getList().get(0), new Boolean(true));
			}

		}

		return false;
	}
}
