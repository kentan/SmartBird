package org.sb.engine.validator.yakuman;

import java.util.List;

import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.engine.validator.WinningHandsStatus;
import org.sb.engine.validator.WinningHandsValidator;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;


public class SuKantsuValidator extends WinningHandsValidator{

	@Override
	public boolean validate(WinningHands winningHands,WinningHandsStatus status){
		WinningHandsBasic hands = (WinningHandsBasic)winningHands;
		List<MeldElement> melds = hands.getList();
		int numOfKong = 0;
		for(MeldElement meld : melds){
			if(MeldEnum.WALL_KONG.equals(meld.getMeldEnum()) ||
					MeldEnum.STEAL_KONG.equals(meld.getMeldEnum())
					){
				numOfKong++;
			}	

		}
		return numOfKong == 4;
	}
}
