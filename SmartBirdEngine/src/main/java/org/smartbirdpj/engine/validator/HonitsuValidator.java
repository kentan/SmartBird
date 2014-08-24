package org.smartbirdpj.engine.validator;

import java.util.HashMap;
import java.util.Map;

import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.TileSuiteEnum;
import org.smartbirdpj.mdl.enm.WinningHandsEnum;



public class HonitsuValidator extends WinningHandsValidator {
	@Override
	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.HONITSU)) {
			return false;
		}
		Map<TileSuiteEnum, Boolean> map = new HashMap<TileSuiteEnum, Boolean>();
		boolean existWindsAndDragons = false;
		for (MeldElement meld : ((WinningHandsBasic)winningHands).getList()) {

			if (TileSuiteEnum.WINDS_AND_DRAGONS.equals(meld.getTileSuite())) {
				existWindsAndDragons = true;
				continue;
			}
			map.put(meld.getTileSuite(), new Boolean(true));
			if (map.size() >= 2) {
				return false;
			}
		}
		if (existWindsAndDragons && map.size() == 1) {
			status.isHonitsu = true;
			return true;
		} else {
			return false;
		}
	}


	
}
