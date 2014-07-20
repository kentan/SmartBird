package org.smartbirdpj.engine.validator;

import java.util.HashMap;
import java.util.Map;

import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.MeldEnum;
import org.smartbirdpj.mdl.enm.TileEnum;
import org.smartbirdpj.mdl.enm.WinningHandsEnum;



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
