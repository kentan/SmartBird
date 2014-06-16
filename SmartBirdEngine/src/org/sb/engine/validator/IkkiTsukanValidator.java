package org.sb.engine.validator;

import java.util.HashMap;
import java.util.Map;

import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;
import org.sb.mdl.enm.WinningHandsEnum;



public class IkkiTsukanValidator extends WinningHandsValidator {
	public boolean validate(WinningHands winningHands, WinningHandsStatus status) {
		if (status.isHigherWinningHandsValid(WinningHandsEnum.IKKI_TSUKAN)) {
			return false;
		}
		Map<TileEnum, Boolean> mapCharacter = new HashMap<TileEnum, Boolean>();
		Map<TileEnum, Boolean> mapBamboo = new HashMap<TileEnum, Boolean>();
		Map<TileEnum, Boolean> mapCircle = new HashMap<TileEnum, Boolean>();
		for (MeldElement meld : ((WinningHandsBasic)winningHands).getList()) {
			if (MeldEnum.WALL_CHOW.equals(meld.getMeldEnum()) || MeldEnum.STEAL_CHOW.equals(meld.getMeldEnum())) {
				TileEnum tile = meld.getList().get(0);
				if (TileEnum.CHARACTOR1.equals(tile) || TileEnum.CHARACTOR4.equals(tile) || TileEnum.CHARACTOR7.equals(tile)) {
					mapCharacter.put(tile, new Boolean(true));
				} else if (TileEnum.CIRCLE1.equals(tile) || TileEnum.CIRCLE4.equals(tile) || TileEnum.CIRCLE7.equals(tile)) {
					mapBamboo.put(tile, new Boolean(true));
				} else if (TileEnum.BAMBOO1.equals(tile) || TileEnum.BAMBOO4.equals(tile) || TileEnum.BAMBOO7.equals(tile)) {
					mapCircle.put(tile, new Boolean(true));
				}
			}
		}

		if (mapCharacter.get(TileEnum.CHARACTOR1) != null && mapCharacter.get(TileEnum.CHARACTOR4) != null && mapCharacter.get(TileEnum.CHARACTOR7) != null) {
			status.isIkkiTsukan = true;
			return true;
		}
		if (mapCharacter.get(TileEnum.BAMBOO1) != null && mapCharacter.get(TileEnum.BAMBOO4) != null && mapCharacter.get(TileEnum.BAMBOO7) != null) {
			status.isIkkiTsukan = true;
			return true;
		}
		if (mapCharacter.get(TileEnum.CIRCLE1) != null && mapCharacter.get(TileEnum.CIRCLE4) != null && mapCharacter.get(TileEnum.CIRCLE7) != null) {
			status.isIkkiTsukan = true;
			return true;
		}

		return false;
	}
}
