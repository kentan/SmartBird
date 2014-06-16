package org.sb.engine.validator.yakuman;


import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsKokushiMuso;
import org.sb.engine.validator.WinningHandsStatus;
import org.sb.engine.validator.WinningHandsValidator;



public class KokushiMusoValidator extends WinningHandsValidator {
	@Override
	public boolean validate(WinningHands winningHands,WinningHandsStatus status){
		return winningHands instanceof WinningHandsKokushiMuso;
	}
}
