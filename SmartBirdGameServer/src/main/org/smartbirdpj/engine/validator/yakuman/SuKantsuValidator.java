package org.smartbirdpj.engine.validator.yakuman;

import java.util.List;

import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.engine.validator.WinningHandsStatus;
import org.smartbirdpj.engine.validator.WinningHandsValidator;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.MeldEnum;


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
