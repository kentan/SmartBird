package org.sb.engine.validator.yakuman;


import java.util.List;

import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.engine.validator.WinningHandsStatus;
import org.sb.engine.validator.WinningHandsValidator;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.TileEnum;


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
		return true;
	}
}
