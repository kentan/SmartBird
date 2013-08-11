package org.sb.engine.validator;

import java.util.List;

import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;


public class YakuhaiValidator extends WinningHandsValidator {

	public boolean validate(WinningHands winningHands, WinningHandsStatus status){
		boolean isYakuhai = false;
		for(MeldElement elem : ((WinningHandsBasic)winningHands).getList()){
			isYakuhai |= validateEachPlayerWind(elem,status);
			isYakuhai |= validateEachPrevailingWind(elem,status);
		}
		return isYakuhai;
	}
	public boolean validateEachPlayerWind(MeldElement mentsu,WinningHandsStatus status) {
		
		return validateEach(mentsu, status, status.getPlayerWind());
	}
	public boolean validateEachPrevailingWind(MeldElement mentsu,WinningHandsStatus status) {
		
		return validateEach(mentsu, status, status.getPrevailingWind());
	}
	private boolean validateEach(MeldElement mentsu,WinningHandsStatus status,TileEnum wind) {
		
		if (MeldEnum.WALL_KONG.equals(mentsu.getMeldEnum()) || MeldEnum.STEAL_KONG.equals(mentsu.getMeldEnum())
				|| MeldEnum.WALL_PONG.equals(mentsu.getMeldEnum()) || MeldEnum.STEAL_PONG.equals(mentsu.getMeldEnum())) {
			List<TileEnum> tiles = mentsu.getList();
			TileEnum tile = tiles.get(0);
			if (tile.equals(wind)) {
				status.isYakuhai = true;
				return true;
			}
		}

		return false;
	}
	
}
