package org.sb.engine.validator.yakuman;

import java.util.List;

import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.engine.validator.WinningHandsStatus;
import org.sb.engine.validator.WinningHandsValidator;
import org.sb.mdl.MeldElement;


public class TsuIsoValidator extends WinningHandsValidator {
	@Override
	public boolean validate(WinningHands winningHands,WinningHandsStatus status){
		WinningHandsBasic hands = (WinningHandsBasic)winningHands;
		List<MeldElement> melds = hands.getList();
		for(MeldElement meld : melds){
			if(meld.getList().get(0).isWindAndDragon()){
				continue;
			}
				

			return false;
		}
		return true;
	}
}
