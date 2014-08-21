package org.smartbirdpj.engine.validator.yakuman;


import java.util.List;

import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.engine.validator.WinningHandsStatus;
import org.smartbirdpj.engine.validator.WinningHandsValidator;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.MeldEnum;


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
