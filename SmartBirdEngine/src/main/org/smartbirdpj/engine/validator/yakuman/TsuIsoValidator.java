package org.smartbirdpj.engine.validator.yakuman;

import java.util.List;

import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.engine.validator.WinningHandsStatus;
import org.smartbirdpj.engine.validator.WinningHandsValidator;
import org.smartbirdpj.mdl.MeldElement;


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
