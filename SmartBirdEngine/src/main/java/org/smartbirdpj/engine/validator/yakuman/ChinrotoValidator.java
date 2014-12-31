package org.smartbirdpj.engine.validator.yakuman;


import java.util.List;

import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.engine.validator.WinningHandsStatus;
import org.smartbirdpj.engine.validator.WinningHandsValidator;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.TileEnum;


public class ChinrotoValidator extends WinningHandsValidator {


	@Override
	public boolean validate(WinningHands winningHands,WinningHandsStatus status){
		WinningHandsBasic hands = (WinningHandsBasic)winningHands;
		List<MeldElement> meldList = hands.getList();
		for(MeldElement meld : meldList){
			for(TileEnum tile : meld.getList()){
				if(tile.isTerminal()){
					continue;
				}	
				return false;				
			}

		}
		status.isYakuman = true;
		return true;
	}
}
