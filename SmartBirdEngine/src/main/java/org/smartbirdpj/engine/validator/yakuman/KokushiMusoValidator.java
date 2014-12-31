package org.smartbirdpj.engine.validator.yakuman;


import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.engine.validator.WinningHandsStatus;
import org.smartbirdpj.engine.validator.WinningHandsValidator;



public class KokushiMusoValidator extends WinningHandsValidator {
	@Override
	public boolean validate(WinningHands winningHands,WinningHandsStatus status){

		if(((WinningHandsBasic)winningHands).isKokushi()){
			status.isYakuman = true;
			return true;
		}
		return false;
	}
}
