package org.sb.engine.validator;

import java.util.HashMap;
import java.util.Map;

import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;
import org.sb.mdl.enm.WinningHandsEnum;



public class RyanPekoValidator extends WinningHandsValidator {
	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.RYAN_PEKO)) {
			return false;
		}
		if (!((WinningHandsBasic)winningHands).isMenzen()) {
			return false;
		}

		boolean onePekoDone = false;

		onePekoDone = false;
		Map<TileEnum, Boolean> map1 = new HashMap<TileEnum, Boolean>();
		Map<TileEnum, Boolean> map2 = new HashMap<TileEnum, Boolean>();
		for (MeldElement meld : ((WinningHandsBasic)winningHands).getList()) {
			if (MeldEnum.WALL_CHOW.equals(meld.getMeldEnum())) {
				if (map1.get(meld.getList().get(0)) != null) {
					onePekoDone = true;
				} else if (map2.get(meld.getList().get(0)) != null) {
					status.is2peko = true;
					return true;
				}
				if (onePekoDone) {
					map2.put(meld.getList().get(0), new Boolean(true));
				} else {
					map1.put(meld.getList().get(0), new Boolean(true));
				}
			}

		}
		return false;
	}
}
