package org.sb.engine.validator.yakuman;


import java.util.List;

import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.engine.validator.WinningHandsStatus;
import org.sb.engine.validator.WinningHandsValidator;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;


public class SuAnkoValidator extends WinningHandsValidator {
	
	@Override
	public boolean validate(WinningHands winningHands,WinningHandsStatus status){
		int numOfKong = 0,numOfPong = 0;
		WinningHandsBasic hands = (WinningHandsBasic)winningHands;
		List<MeldElement> melds = hands.getList();

		for(MeldElement meld : melds){
			if(MeldEnum.WALL_PONG.equals(meld.getMeldEnum())){
				numOfPong++;
			}
			if(MeldEnum.WALL_KONG.equals(meld.getMeldEnum())){
				numOfKong++;
			}
			
		}
		if( ((numOfPong + numOfKong) == 4) && (numOfKong <= 3)){
			status.isYakuman = true;
			return true;
		}
		return false;
			
	}
}
